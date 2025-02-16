/*******************************************************************************
 * Copyright 2021 Tremolo Security, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.tremolosecurity.proxy.dynamicconfiguration;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tremolosecurity.config.util.ConfigManager;
import com.tremolosecurity.config.xml.AuthChainType;
import com.tremolosecurity.config.xml.ConfigType;
import com.tremolosecurity.config.xml.CustomAzRuleType;
import com.tremolosecurity.config.xml.MechanismType;
import com.tremolosecurity.config.xml.ParamListType;
import com.tremolosecurity.config.xml.ParamType;
import com.tremolosecurity.config.xml.ResultGroupType;
import com.tremolosecurity.config.xml.ResultType;
import com.tremolosecurity.config.xml.TargetType;
import com.tremolosecurity.config.xml.TremoloType;
import com.tremolosecurity.k8s.watch.K8sWatchTarget;
import com.tremolosecurity.k8s.watch.K8sWatcher;
import com.tremolosecurity.openunison.util.config.OpenUnisonConfigLoader;
import com.tremolosecurity.provisioning.core.ProvisioningEngine;
import com.tremolosecurity.provisioning.core.ProvisioningException;
import com.tremolosecurity.proxy.dynamicloaders.DynamicAuthMechs;
import com.tremolosecurity.proxy.dynamicloaders.DynamicAuthorizations;
import com.tremolosecurity.proxy.dynamicloaders.DynamicResultGroups;
import com.tremolosecurity.provisioning.targets.LoadTargetsFromK8s;
import com.tremolosecurity.provisioning.util.HttpCon;
import com.tremolosecurity.saml.Attribute;
import com.tremolosecurity.server.GlobalEntries;

public class LoadAuthMechsFromK8s  implements DynamicAuthMechs, K8sWatchTarget {
static org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(LoadAuthMechsFromK8s.class.getName());
	
	K8sWatcher k8sWatch;
	
	TremoloType tremolo;

	private ProvisioningEngine provisioningEngine;
	private ConfigManager cfgMgr;
	
	
	private MechanismType createAuthMech (JSONObject item, String name) throws Exception {
		MechanismType mechType = new MechanismType();
		
		JSONObject spec = (JSONObject) item.get("spec");
		
		mechType.setName(name);
		mechType.setClassName((String) spec.get("className"));
		mechType.setUri((String) spec.get("uri"));
		mechType.setInit(new ConfigType());
		mechType.setParams(new ParamListType());
		
		JSONObject params = (JSONObject) spec.get("init");
		
		for (Object o : params.keySet()) {
			String keyName = (String) o;
			Object v = params.get(keyName);
			if (v instanceof String) {
				String val = (String) v;
				ParamType pt = new ParamType();
				pt.setName(keyName);
				pt.setValue(val);
				mechType.getInit().getParam().add(pt);
			} else if (v instanceof JSONArray) {
				for (Object ov : ((JSONArray) v)) {
					ParamType pt = new ParamType();
					pt.setName(keyName);
					pt.setValue((String) ov);
					mechType.getInit().getParam().add(pt);
				}
			}
		}
		
		JSONArray secretParams = (JSONArray) spec.get("secretParams");
		
		if (secretParams != null) {
			HttpCon nonwatchHttp = this.k8sWatch.getK8s().createClient();
			String token = this.k8sWatch.getK8s().getAuthToken();
			
			try {
				for (Object o : secretParams) {
					JSONObject secretParam = (JSONObject) o;
					String paramName = (String) secretParam.get("name");
					String secretName = (String) secretParam.get("secretName");
					String secretKey = (String) secretParam.get("secretKey");
					
					String secretValue = this.k8sWatch.getSecretValue(secretName, secretKey, token, nonwatchHttp);
					ParamType pt = new ParamType();
					pt.setName(paramName);
					pt.setValue(secretValue);
					
					mechType.getInit().getParam().add(pt);
					
				}
			} finally {
				nonwatchHttp.getHttp().close();
				nonwatchHttp.getBcm().close();
			}
		}
		
		return mechType;
		
	}
	
	

	
	
	@Override
	public void loadDynamicAuthMechs(ConfigManager cfgMgr, ProvisioningEngine provisioningEngine,
			Map<String, Attribute> init) throws ProvisioningException {
		this.tremolo = cfgMgr.getCfg();
		String k8sTarget = 	init.get("k8starget").getValues().get(0);
		String namespace = init.get("namespace").getValues().get(0);
		String uri = "/apis/openunison.tremolo.io/v1/namespaces/" + namespace + "/authmechs";
		
		
		this.provisioningEngine = provisioningEngine;
		this.cfgMgr = cfgMgr;
		
		this.k8sWatch = new K8sWatcher(k8sTarget,namespace,uri,this,cfgMgr,provisioningEngine);
		
		this.k8sWatch.initalRun();

	}



	@Override
	public void addObject(TremoloType cfg, JSONObject item) throws ProvisioningException {
		String rawJson = item.toJSONString();
		StringBuffer b = new StringBuffer();
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,rawJson);
		try {
			JSONObject newRoot = (JSONObject) new JSONParser().parse(b.toString());
			JSONObject metadata = (JSONObject) newRoot.get("metadata");
			
			if (metadata == null) {
				throw new ProvisioningException("No metadata");
			}
			
			String name = (String) metadata.get("name");
			
			logger.info("Adding authentication mechanism " + name);
			
			
			try {
				MechanismType mt = this.createAuthMech(item, name);
				GlobalEntries.getGlobalEntries().getConfigManager().addAuthenticationMechanism(mt);
				
				
				synchronized (GlobalEntries.getGlobalEntries().getConfigManager().getCfg()) {
					MechanismType curMech = null;
					for (MechanismType itMech : GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getAuthMechs().getMechanism()) {
						if (itMech.getName().equals(mt.getName())) {
							curMech = itMech;
							break;
						}
					}
					
					if (curMech != null) {
						GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getAuthMechs().getMechanism().remove(curMech);
					}
					
					GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getAuthMechs().getMechanism().add(mt);
				}
			} catch (Exception e) {
				logger.warn("Could not initialize authentication mechanism " + name,e);
				return;
			}
			
			
			
			
		} catch (ParseException e) {
			throw new ProvisioningException("Could not parse custom authorization",e);
		}
		
	}



	@Override
	public void modifyObject(TremoloType cfg, JSONObject item) throws ProvisioningException {
		String rawJson = item.toJSONString();
		StringBuffer b = new StringBuffer();
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,rawJson);
		try {
			JSONObject newRoot = (JSONObject) new JSONParser().parse(b.toString());
			JSONObject metadata = (JSONObject) newRoot.get("metadata");
			
			if (metadata == null) {
				throw new ProvisioningException("No metadata");
			}
			
			String name = (String) metadata.get("name");
			
			logger.info("Modifying authentication mechanism " + name);
			
			try {
				MechanismType mt = this.createAuthMech(item, name);
				GlobalEntries.getGlobalEntries().getConfigManager().addAuthenticationMechanism(mt);
				
				synchronized (GlobalEntries.getGlobalEntries().getConfigManager().getCfg()) {
					MechanismType curMech = null;
					for (MechanismType itMech : GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getAuthMechs().getMechanism()) {
						if (itMech.getName().equals(mt.getName())) {
							curMech = itMech;
							break;
						}
					}
					
					if (curMech != null) {
						GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getAuthMechs().getMechanism().remove(curMech);
					}
					
					GlobalEntries.getGlobalEntries().getConfigManager().getCfg().getAuthMechs().getMechanism().add(mt);
				}
			} catch (Exception e) {
				logger.warn("Could not initialize authentication mechanism " + name,e);
				return;
			}
			
			
		} catch (ParseException e) {
			throw new ProvisioningException("Could not parse custom authorization",e);
		}
		
	}



	@Override
	public void deleteObject(TremoloType cfg, JSONObject item) throws ProvisioningException {
		JSONObject metadata = (JSONObject) item.get("metadata");
		
		if (metadata == null) {
			throw new ProvisioningException("No metadata");
		}
		
		String name = (String) metadata.get("name");
		
		logger.info("Deleting authentication mechanism" + name);
		
		GlobalEntries.getGlobalEntries().getConfigManager().removeAuthenticationMechanism(name);
		
		
	}
	
	
}
