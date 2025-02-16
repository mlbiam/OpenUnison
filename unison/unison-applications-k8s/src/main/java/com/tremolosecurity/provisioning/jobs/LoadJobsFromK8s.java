/*******************************************************************************
 * Copyright 2020 Tremolo Security, Inc.
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
package com.tremolosecurity.provisioning.jobs;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.quartz.SchedulerException;

import com.tremolosecurity.config.util.ConfigManager;
import com.tremolosecurity.config.xml.CronScheduleType;
import com.tremolosecurity.config.xml.JobType;
import com.tremolosecurity.config.xml.OrgType;
import com.tremolosecurity.config.xml.ParamType;
import com.tremolosecurity.config.xml.ParamWithValueType;
import com.tremolosecurity.config.xml.ReportType;
import com.tremolosecurity.config.xml.TargetAttributeType;
import com.tremolosecurity.config.xml.TargetConfigType;
import com.tremolosecurity.config.xml.TargetType;
import com.tremolosecurity.config.xml.TremoloType;
import com.tremolosecurity.k8s.watch.K8sWatchTarget;
import com.tremolosecurity.k8s.watch.K8sWatcher;
import com.tremolosecurity.openunison.util.config.OpenUnisonConfigLoader;
import com.tremolosecurity.provisioning.core.ProvisioningEngine;
import com.tremolosecurity.provisioning.core.ProvisioningException;
import com.tremolosecurity.provisioning.targets.DynamicTargets;
import com.tremolosecurity.provisioning.util.HttpCon;
import com.tremolosecurity.saml.Attribute;

public class LoadJobsFromK8s implements DynamicJobs, K8sWatchTarget {
	
	static org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger(LoadJobsFromK8s.class.getName());
	
	K8sWatcher k8sWatch;
	
	TremoloType tremolo;

	private ProvisioningEngine provisioningEngine;
	private ConfigManager cfgMgr;

	HashSet<String> jobKeys;
	
	
	
	@Override
	public void addObject(TremoloType cfg, JSONObject item) throws ProvisioningException {
		
		JSONObject metadata = (JSONObject) item.get("metadata");
		String name = (String) metadata.get("name");
		logger.info("Creating job '" + name + "'");
		createJob(item,name);
		
		
	}

	private void createJob(JSONObject item,String name) throws ProvisioningException {
		HttpCon nonwatchHttp = null;
		JobType job = new JobType();
		job.setName(name);
		JSONObject spec = (JSONObject) item.get("spec");
		
		
		
		StringBuffer b = new StringBuffer();
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) spec.get("className")  );
		job.setClassName(b.toString());
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) spec.get("group")  );
		job.setGroup(b.toString());
		
		JSONArray params = (JSONArray) spec.get("params");
		for (Object o : params ) {
			JSONObject param = (JSONObject) o;
			ParamWithValueType pt = new ParamWithValueType();
			
			b.setLength(0);
			OpenUnisonConfigLoader.integrateIncludes(b,(String) param.get("name")  );
			pt.setName(b.toString());
			
			b.setLength(0);
			OpenUnisonConfigLoader.integrateIncludes(b,(String) param.get("value")  );
			pt.setValue(b.toString());
			
			job.getParam().add(pt);
			
			
			
		}
		
		JSONArray secretParams = (JSONArray) spec.get("secretParams");
		
		if (secretParams != null) {
			try {
				
				nonwatchHttp = this.k8sWatch.getK8s().createClient();
				String token = this.k8sWatch.getK8s().getAuthToken();
				for (Object o : secretParams) {
					JSONObject secretParam = (JSONObject) o;
					String paramName = (String) secretParam.get("name");
					String secretName = (String) secretParam.get("secretName");
					String secretKey = (String) secretParam.get("secretKey");
					
					String secretValue = this.k8sWatch.getSecretValue(secretName, secretKey, token, nonwatchHttp);
					ParamWithValueType pt = new ParamWithValueType();
					pt.setName(paramName);
					pt.setValue(secretValue);
					job.getParam().add(pt);
					
				}
			} catch (Exception e) {
				throw new ProvisioningException("Could not load secrets for '" + name + "'");
			} finally {
				if (nonwatchHttp != null) {
					try {
						nonwatchHttp.getHttp().close();
					} catch (IOException e) {
						
					}
					nonwatchHttp.getBcm().close();
				}
			}
		}
		
		
		job.setCronSchedule(new CronScheduleType());
		
		JSONObject cron = (JSONObject) spec.get("cronSchedule");
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) cron.get("seconds")  );
		job.getCronSchedule().setSeconds(b.toString());
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) cron.get("minutes")  );
		job.getCronSchedule().setMinutes(b.toString());
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) cron.get("hours")  );
		job.getCronSchedule().setHours(b.toString());
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) cron.get("dayOfMonth")  );
		job.getCronSchedule().setDayOfMonth(b.toString());
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) cron.get("month")  );
		job.getCronSchedule().setMonth(b.toString());
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) cron.get("dayOfWeek")  );
		job.getCronSchedule().setDayOfWeek(b.toString());
		
		b.setLength(0);
		OpenUnisonConfigLoader.integrateIncludes(b,(String) cron.get("year")  );
		job.getCronSchedule().setYear(b.toString());
		
		
		
		
		try {
			this.cfgMgr.getProvisioningEngine().addNewJob(jobKeys, job);
		} catch (ClassNotFoundException | SchedulerException | ProvisioningException e) {
			throw new ProvisioningException("Could not add job '" + name + "'",e);
		}
		
		
	}

	@Override
	public void modifyObject(TremoloType cfg, JSONObject item) throws ProvisioningException {
		JSONObject metadata = (JSONObject) item.get("metadata");
		String name = (String) metadata.get("name");
		logger.info("Replacing job '" + name + "'");
		this.createJob(item, name);
		

	}

	@Override
	public void deleteObject(TremoloType cfg, JSONObject item) throws ProvisioningException {
		JSONObject metadata = (JSONObject) item.get("metadata");
		String name = (String) metadata.get("name");
		JSONObject spec = (JSONObject) item.get("spec");
		logger.info("Deleting job '" + name + "'");
		String groupName = (String) spec.get("group");
		
		
		
		try {
			this.cfgMgr.getProvisioningEngine().deleteJob(name, groupName);
		} catch (SchedulerException e) {
			throw new ProvisioningException("Could not delete job '" + name + "'",e);
		}
		
		

	}

	@Override
	public void loadDynamicJobs(ConfigManager cfgMgr, ProvisioningEngine provisioningEngine,Map<String, Attribute> init,HashSet<String> jobKeys) throws ProvisioningException {
		this.tremolo = cfgMgr.getCfg();
		String k8sTarget = 	init.get("k8starget").getValues().get(0);
		String namespace = init.get("namespace").getValues().get(0);
		String uri = "/apis/openunison.tremolo.io/v1/namespaces/" + namespace + "/oujobs";
		
		
		this.provisioningEngine = provisioningEngine;
		this.cfgMgr = cfgMgr;
		
		this.k8sWatch = new K8sWatcher(k8sTarget,namespace,uri,this,cfgMgr,provisioningEngine);
		this.jobKeys = jobKeys;
		this.k8sWatch.initalRun();

	}

}
