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
// Generated on: 2021.02.23 at 06:38:48 AM EST 
//


package com.tremolosecurity.config.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * List of provisioning targets
 * 
 * <p>Java class for targetsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="targetsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dynamicTargets" type="{http://www.tremolosecurity.com/tremoloConfig}dynamicPortalUrlsType" minOccurs="0"/>
 *         &lt;element name="target" type="{http://www.tremolosecurity.com/tremoloConfig}targetType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "targetsType", propOrder = {
    "dynamicTargets",
    "target"
})
public class TargetsType {

    protected DynamicPortalUrlsType dynamicTargets;
    protected List<TargetType> target;

    /**
     * Gets the value of the dynamicTargets property.
     * 
     * @return
     *     possible object is
     *     {@link DynamicPortalUrlsType }
     *     
     */
    public DynamicPortalUrlsType getDynamicTargets() {
        return dynamicTargets;
    }

    /**
     * Sets the value of the dynamicTargets property.
     * 
     * @param value
     *     allowed object is
     *     {@link DynamicPortalUrlsType }
     *     
     */
    public void setDynamicTargets(DynamicPortalUrlsType value) {
        this.dynamicTargets = value;
    }

    /**
     * Gets the value of the target property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the target property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTarget().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TargetType }
     * 
     * 
     */
    public List<TargetType> getTarget() {
        if (target == null) {
            target = new ArrayList<TargetType>();
        }
        return this.target;
    }

}
