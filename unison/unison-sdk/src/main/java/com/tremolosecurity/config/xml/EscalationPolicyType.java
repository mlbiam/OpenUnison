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
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.04.26 at 11:05:20 AM EDT 
//


package com.tremolosecurity.config.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Provides an optional escalation policy for an approval
 * 			
 * 
 * <p>Java class for escalationPolicyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="escalationPolicyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="escalationFailure" type="{http://www.tremolosecurity.com/tremoloConfig}escalationFailureType"/>
 *         &lt;element name="escalation" type="{http://www.tremolosecurity.com/tremoloConfig}escalationType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "escalationPolicyType", propOrder = {
    "escalationFailure",
    "escalation"
})
public class EscalationPolicyType {

    @XmlElement(required = true)
    protected EscalationFailureType escalationFailure;
    protected List<EscalationType> escalation;

    /**
     * Gets the value of the escalationFailure property.
     * 
     * @return
     *     possible object is
     *     {@link EscalationFailureType }
     *     
     */
    public EscalationFailureType getEscalationFailure() {
        return escalationFailure;
    }

    /**
     * Sets the value of the escalationFailure property.
     * 
     * @param value
     *     allowed object is
     *     {@link EscalationFailureType }
     *     
     */
    public void setEscalationFailure(EscalationFailureType value) {
        this.escalationFailure = value;
    }

    /**
     * Gets the value of the escalation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the escalation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEscalation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EscalationType }
     * 
     * 
     */
    public List<EscalationType> getEscalation() {
        if (escalation == null) {
            escalation = new ArrayList<EscalationType>();
        }
        return this.escalation;
    }

}
