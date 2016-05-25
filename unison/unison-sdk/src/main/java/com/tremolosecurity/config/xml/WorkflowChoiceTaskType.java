/*******************************************************************************
 * Copyright 2016 Tremolo Security, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.05.24 at 04:57:23 PM EDT 
//


package com.tremolosecurity.config.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 				Individual workflow task that can choose between two
 * 				paths (success and fail)
 * 			
 * 
 * <p>Java class for workflowChoiceTaskType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="workflowChoiceTaskType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.tremolosecurity.com/tremoloConfig}workflowTaskType">
 *       &lt;sequence>
 *         &lt;element name="onSuccess" type="{http://www.tremolosecurity.com/tremoloConfig}workflowTaskListType" minOccurs="0"/>
 *         &lt;element name="onFailure" type="{http://www.tremolosecurity.com/tremoloConfig}workflowTaskListType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "workflowChoiceTaskType", propOrder = {
    "onSuccess",
    "onFailure"
})
@XmlSeeAlso({
    MappingType.class,
    ApprovalType.class,
    IfAttrHasValueType.class,
    IfAttrExistsType.class,
    IfNotUserExistsType.class
})
public abstract class WorkflowChoiceTaskType
    extends WorkflowTaskType
{

    protected WorkflowTaskListType onSuccess;
    protected WorkflowTaskListType onFailure;

    /**
     * Gets the value of the onSuccess property.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowTaskListType }
     *     
     */
    public WorkflowTaskListType getOnSuccess() {
        return onSuccess;
    }

    /**
     * Sets the value of the onSuccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowTaskListType }
     *     
     */
    public void setOnSuccess(WorkflowTaskListType value) {
        this.onSuccess = value;
    }

    /**
     * Gets the value of the onFailure property.
     * 
     * @return
     *     possible object is
     *     {@link WorkflowTaskListType }
     *     
     */
    public WorkflowTaskListType getOnFailure() {
        return onFailure;
    }

    /**
     * Sets the value of the onFailure property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkflowTaskListType }
     *     
     */
    public void setOnFailure(WorkflowTaskListType value) {
        this.onFailure = value;
    }

}
