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
// Generated on: 2016.04.08 at 12:00:44 PM EDT 
//


package com.tremolosecurity.config.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the database used to track requests and approvals
 * 
 * <p>Java class for approvalDBType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="approvalDBType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="driver" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="user" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxConns" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxIdleConns" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userIdAttribute" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="approverAttributes" type="{http://www.tremolosecurity.com/tremoloConfig}listType"/>
 *         &lt;element name="userAttributes" type="{http://www.tremolosecurity.com/tremoloConfig}listType"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="smtpHost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpPort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="smtpUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpSubject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpFrom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpTLS" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="encryptionKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpUseSOCKSProxy" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="smtpSOCKSProxyHost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smtpSOCKSProxyPort" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="smtpLocalhost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maskAttribute" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="validationQuery" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hibernateDialect" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "approvalDBType", propOrder = {
    "driver",
    "url",
    "user",
    "password",
    "maxConns",
    "maxIdleConns",
    "userIdAttribute",
    "approverAttributes",
    "userAttributes",
    "enabled",
    "smtpHost",
    "smtpPort",
    "smtpUser",
    "smtpPassword",
    "smtpSubject",
    "smtpFrom",
    "smtpTLS",
    "encryptionKey",
    "smtpUseSOCKSProxy",
    "smtpSOCKSProxyHost",
    "smtpSOCKSProxyPort",
    "smtpLocalhost",
    "maskAttribute",
    "validationQuery",
    "hibernateDialect"
})
public class ApprovalDBType {

    @XmlElement(required = true)
    protected String driver;
    @XmlElement(required = true)
    protected String url;
    @XmlElement(required = true)
    protected String user;
    @XmlElement(required = true)
    protected String password;
    protected int maxConns;
    protected int maxIdleConns;
    @XmlElement(required = true)
    protected String userIdAttribute;
    @XmlElement(required = true)
    protected ListType approverAttributes;
    @XmlElement(required = true)
    protected ListType userAttributes;
    protected boolean enabled;
    @XmlElement(required = true)
    protected String smtpHost;
    protected int smtpPort;
    @XmlElement(required = true)
    protected String smtpUser;
    @XmlElement(required = true)
    protected String smtpPassword;
    @XmlElement(required = true)
    protected String smtpSubject;
    @XmlElement(required = true)
    protected String smtpFrom;
    protected boolean smtpTLS;
    @XmlElement(required = true)
    protected String encryptionKey;
    protected boolean smtpUseSOCKSProxy;
    @XmlElement(required = true)
    protected String smtpSOCKSProxyHost;
    protected int smtpSOCKSProxyPort;
    @XmlElement(required = true)
    protected String smtpLocalhost;
    protected List<String> maskAttribute;
    protected String validationQuery;
    @XmlElement(required = true)
    protected String hibernateDialect;

    /**
     * Gets the value of the driver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Sets the value of the driver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriver(String value) {
        this.driver = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUser(String value) {
        this.user = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the maxConns property.
     * 
     */
    public int getMaxConns() {
        return maxConns;
    }

    /**
     * Sets the value of the maxConns property.
     * 
     */
    public void setMaxConns(int value) {
        this.maxConns = value;
    }

    /**
     * Gets the value of the maxIdleConns property.
     * 
     */
    public int getMaxIdleConns() {
        return maxIdleConns;
    }

    /**
     * Sets the value of the maxIdleConns property.
     * 
     */
    public void setMaxIdleConns(int value) {
        this.maxIdleConns = value;
    }

    /**
     * Gets the value of the userIdAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserIdAttribute() {
        return userIdAttribute;
    }

    /**
     * Sets the value of the userIdAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserIdAttribute(String value) {
        this.userIdAttribute = value;
    }

    /**
     * Gets the value of the approverAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link ListType }
     *     
     */
    public ListType getApproverAttributes() {
        return approverAttributes;
    }

    /**
     * Sets the value of the approverAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListType }
     *     
     */
    public void setApproverAttributes(ListType value) {
        this.approverAttributes = value;
    }

    /**
     * Gets the value of the userAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link ListType }
     *     
     */
    public ListType getUserAttributes() {
        return userAttributes;
    }

    /**
     * Sets the value of the userAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListType }
     *     
     */
    public void setUserAttributes(ListType value) {
        this.userAttributes = value;
    }

    /**
     * Gets the value of the enabled property.
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     */
    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the smtpHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpHost() {
        return smtpHost;
    }

    /**
     * Sets the value of the smtpHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpHost(String value) {
        this.smtpHost = value;
    }

    /**
     * Gets the value of the smtpPort property.
     * 
     */
    public int getSmtpPort() {
        return smtpPort;
    }

    /**
     * Sets the value of the smtpPort property.
     * 
     */
    public void setSmtpPort(int value) {
        this.smtpPort = value;
    }

    /**
     * Gets the value of the smtpUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpUser() {
        return smtpUser;
    }

    /**
     * Sets the value of the smtpUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpUser(String value) {
        this.smtpUser = value;
    }

    /**
     * Gets the value of the smtpPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpPassword() {
        return smtpPassword;
    }

    /**
     * Sets the value of the smtpPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpPassword(String value) {
        this.smtpPassword = value;
    }

    /**
     * Gets the value of the smtpSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpSubject() {
        return smtpSubject;
    }

    /**
     * Sets the value of the smtpSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpSubject(String value) {
        this.smtpSubject = value;
    }

    /**
     * Gets the value of the smtpFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpFrom() {
        return smtpFrom;
    }

    /**
     * Sets the value of the smtpFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpFrom(String value) {
        this.smtpFrom = value;
    }

    /**
     * Gets the value of the smtpTLS property.
     * 
     */
    public boolean isSmtpTLS() {
        return smtpTLS;
    }

    /**
     * Sets the value of the smtpTLS property.
     * 
     */
    public void setSmtpTLS(boolean value) {
        this.smtpTLS = value;
    }

    /**
     * Gets the value of the encryptionKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptionKey() {
        return encryptionKey;
    }

    /**
     * Sets the value of the encryptionKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptionKey(String value) {
        this.encryptionKey = value;
    }

    /**
     * Gets the value of the smtpUseSOCKSProxy property.
     * 
     */
    public boolean isSmtpUseSOCKSProxy() {
        return smtpUseSOCKSProxy;
    }

    /**
     * Sets the value of the smtpUseSOCKSProxy property.
     * 
     */
    public void setSmtpUseSOCKSProxy(boolean value) {
        this.smtpUseSOCKSProxy = value;
    }

    /**
     * Gets the value of the smtpSOCKSProxyHost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpSOCKSProxyHost() {
        return smtpSOCKSProxyHost;
    }

    /**
     * Sets the value of the smtpSOCKSProxyHost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpSOCKSProxyHost(String value) {
        this.smtpSOCKSProxyHost = value;
    }

    /**
     * Gets the value of the smtpSOCKSProxyPort property.
     * 
     */
    public int getSmtpSOCKSProxyPort() {
        return smtpSOCKSProxyPort;
    }

    /**
     * Sets the value of the smtpSOCKSProxyPort property.
     * 
     */
    public void setSmtpSOCKSProxyPort(int value) {
        this.smtpSOCKSProxyPort = value;
    }

    /**
     * Gets the value of the smtpLocalhost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmtpLocalhost() {
        return smtpLocalhost;
    }

    /**
     * Sets the value of the smtpLocalhost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmtpLocalhost(String value) {
        this.smtpLocalhost = value;
    }

    /**
     * Gets the value of the maskAttribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the maskAttribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMaskAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMaskAttribute() {
        if (maskAttribute == null) {
            maskAttribute = new ArrayList<String>();
        }
        return this.maskAttribute;
    }

    /**
     * Gets the value of the validationQuery property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationQuery() {
        return validationQuery;
    }

    /**
     * Sets the value of the validationQuery property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationQuery(String value) {
        this.validationQuery = value;
    }

    /**
     * Gets the value of the hibernateDialect property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHibernateDialect() {
        return hibernateDialect;
    }

    /**
     * Sets the value of the hibernateDialect property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHibernateDialect(String value) {
        this.hibernateDialect = value;
    }

}
