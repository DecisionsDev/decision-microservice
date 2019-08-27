/*
 *
 * IBM Confidential
 * OCO Source Materials
 * 5724X98 5724Y15 5655V82 5724X99 5724Y16 5655V89 5725B69 5655W88 5725C52 5655W90 5655Y31
 * Copyright IBM Corp. 1987, 2019
 * The source code for this program is not published or other-
 * wise divested of its trade secrets, irrespective of what has
 * been deposited with the U.S Copyright Office.
 *
 */


package com.ibm.decisionMicroservice.codeGen.processor.swagger.trace;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;


/**
 * Represents a rule that has been executed.
 * 
 * <p>Java class for RuleEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RuleEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="ruleInformation" type="{http://www.ibm.com/rules/decisionservice/trace}ArtifactInformation"/>
 *         &lt;element name="boundObjects" type="{http://www.ibm.com/rules/decisionservice/trace}DataObjects" minOccurs="0"/>
 *         &lt;element name="endDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RuleEvent", propOrder = {
    "startDate",
    "priority",
    "ruleInformation",
    "boundObjects",
    "endDate"
})
@JsonInclude(Include.NON_NULL)
public class RuleEvent {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    protected BigInteger priority;
    @XmlElement(required = true)
    protected ArtifactInformation ruleInformation;
    protected DataObjects boundObjects;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPriority(BigInteger value) {
        this.priority = value;
    }

    /**
     * Gets the value of the ruleInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ArtifactInformation }
     *     
     */
    public ArtifactInformation getRuleInformation() {
        return ruleInformation;
    }

    /**
     * Sets the value of the ruleInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArtifactInformation }
     *     
     */
    public void setRuleInformation(ArtifactInformation value) {
        this.ruleInformation = value;
    }

    /**
     * Gets the value of the boundObjects property.
     * 
     * @return
     *     possible object is
     *     {@link DataObjects }
     *     
     */
    public DataObjects getBoundObjects() {
        return boundObjects;
    }

    /**
     * Sets the value of the boundObjects property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjects }
     *     
     */
    public void setBoundObjects(DataObjects value) {
        this.boundObjects = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

}
