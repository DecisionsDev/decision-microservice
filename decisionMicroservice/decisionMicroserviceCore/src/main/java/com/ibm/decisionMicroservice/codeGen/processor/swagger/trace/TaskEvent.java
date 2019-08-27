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


/**
 * Represents a task that has been executed.
 * 
 * <p>Java class for TaskEvent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaskEvent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="taskInformation" type="{http://www.ibm.com/rules/decisionservice/trace}ArtifactInformation"/>
 *         &lt;element name="executionEvents" type="{http://www.ibm.com/rules/decisionservice/trace}ExecutionEvents"/>
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
@XmlType(name = "TaskEvent", propOrder = {
    "startDate",
    "taskInformation",
    "executionEvents",
    "endDate"
})
@JsonInclude(Include.NON_NULL)
public class TaskEvent {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(required = true)
    protected ArtifactInformation taskInformation;
    @XmlElement(required = true)
    protected ExecutionEvents executionEvents;
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
     * Gets the value of the taskInformation property.
     * 
     * @return
     *     possible object is
     *     {@link ArtifactInformation }
     *     
     */
    public ArtifactInformation getTaskInformation() {
        return taskInformation;
    }

    /**
     * Sets the value of the taskInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArtifactInformation }
     *     
     */
    public void setTaskInformation(ArtifactInformation value) {
        this.taskInformation = value;
    }

    /**
     * Gets the value of the executionEvents property.
     * 
     * @return
     *     possible object is
     *     {@link ExecutionEvents }
     *     
     */
    public ExecutionEvents getExecutionEvents() {
        return executionEvents;
    }

    /**
     * Sets the value of the executionEvents property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExecutionEvents }
     *     
     */
    public void setExecutionEvents(ExecutionEvents value) {
        this.executionEvents = value;
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
