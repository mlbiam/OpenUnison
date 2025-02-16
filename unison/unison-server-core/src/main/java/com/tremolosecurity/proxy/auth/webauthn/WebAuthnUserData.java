/*******************************************************************************
 * Copyright (c) 2021 Tremolo Security, Inc.
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
package com.tremolosecurity.proxy.auth.webauthn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.webauthn4j.authenticator.Authenticator;
import com.webauthn4j.data.client.challenge.DefaultChallenge;

public class WebAuthnUserData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3506362272344388035L;
	
	
	byte[] id;
	String displayName;
	
	List<OpenUnisonAuthenticator> authenticators;
	
	public WebAuthnUserData(String displayName) {
		this.displayName = displayName;
		this.id = new DefaultChallenge().getValue();
		this.authenticators = new ArrayList<OpenUnisonAuthenticator>();
	}

	public byte[] getId() {
		return id;
	}

	public String getDisplayName() {
		return displayName;
	}

	public List<OpenUnisonAuthenticator> getAuthenticators() {
		return authenticators;
	}
	
	
}
