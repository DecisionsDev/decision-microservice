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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * The list of execution events.
 * 
 * <p>Java class for ExecutionEvents complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExecutionEvents">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice minOccurs="0">
 *         &lt;element name="taskEvent" type="{http://www.ibm.com/rules/decisionservice/trace}TaskEvent" maxOccurs="unbounded"/>
 *         &lt;element name="ruleEvent" type="{http://www.ibm.com/rules/decisionservice/trace}RuleEvent" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExecutionEvents", propOrder = {
    "taskEvent",
    "ruleEvent"
})
@JsonInclude(Include.NON_NULL)
public class ExecutionEvents {

    protected List<TaskEvent> taskEvent;
    protected List<RuleEvent> ruleEvent;

    /**
     * Gets the value of the taskEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the taskEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTaskEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TaskEvent }
     * 
     * 
     */
    @JsonInclude(Include.NON_NULL)
    public List<TaskEvent> getTaskEvent() {
        if (taskEvent == null) {
            taskEvent = new ArrayList<TaskEvent>();
        }
        return this.taskEvent;
    }

    /**
     * Gets the value of the ruleEvent property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ruleEvent property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRuleEvent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuleEvent }
     * 
     * 
     */
    @JsonInclude(Include.NON_NULL)
    public List<RuleEvent> getRuleEvent() {
        if (ruleEvent == null) {
            ruleEvent = new ArrayList<RuleEvent>();
        }
        return this.ruleEvent;
    }

}
