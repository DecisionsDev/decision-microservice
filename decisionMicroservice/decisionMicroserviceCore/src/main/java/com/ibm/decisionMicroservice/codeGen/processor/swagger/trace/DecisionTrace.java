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
import java.util.HashMap;
import java.util.Map;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="decisionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestedRulesetPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="executedRulesetPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="executionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="executionDuration" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="rulesetProperties" type="{http://www.ibm.com/rules/decisionservice/trace}Properties" minOccurs="0"/>
 *         &lt;element name="systemProperties" type="{http://www.ibm.com/rules/decisionservice/trace}Properties" minOccurs="0"/>
 *         &lt;element name="inetAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalRules" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="totalRulesFired" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="totalRulesNotFired" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="rules" type="{http://www.ibm.com/rules/decisionservice/trace}RulesFired" minOccurs="0"/>
 *         &lt;element name="rulesFired" type="{http://www.ibm.com/rules/decisionservice/trace}RulesFired" minOccurs="0"/>
 *         &lt;element name="rulesNotFired" type="{http://www.ibm.com/rules/decisionservice/trace}Rules" minOccurs="0"/>
 *         &lt;element name="totalTasks" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="totalTasksExecuted" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="totalTasksNotExecuted" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="tasks" type="{http://www.ibm.com/rules/decisionservice/trace}Tasks" minOccurs="0"/>
 *         &lt;element name="tasksExecuted" type="{http://www.ibm.com/rules/decisionservice/trace}TasksExecuted" minOccurs="0"/>
 *         &lt;element name="tasksNotExecuted" type="{http://www.ibm.com/rules/decisionservice/trace}Tasks" minOccurs="0"/>
 *         &lt;element name="outputString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inputParameters" type="{http://www.ibm.com/rules/decisionservice/trace}Parameter" minOccurs="0"/>
 *         &lt;element name="outputParameters" type="{http://www.ibm.com/rules/decisionservice/trace}Parameter" minOccurs="0"/>
 *         &lt;element name="workingMemory" type="{http://www.ibm.com/rules/decisionservice/trace}DataObjects" minOccurs="0"/>
 *         &lt;element name="executionEvents" type="{http://www.ibm.com/rules/decisionservice/trace}ExecutionEvents" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "decisionId",
    "requestedRulesetPath",
    "executedRulesetPath",
    "executionDate",
    "executionDuration",
    "rulesetProperties",
    "systemProperties",
    "inetAddress",
    "totalRules",
    "totalRulesFired",
    "totalRulesNotFired",
    "rules",
    "rulesFired",
    "rulesNotFired",
    "totalTasks",
    "totalTasksExecuted",
    "totalTasksNotExecuted",
    "tasks",
    "tasksExecuted",
    "tasksNotExecuted",
    "outputString",
    "inputParameters",
    "outputParameters",
    "workingMemory",
    "executionEvents"
})
@XmlRootElement(name = "decisionTrace")
@JsonInclude(Include.NON_NULL)
public class DecisionTrace {
	public final static String DEFAULT_NAMESPACE = "http://www.ibm.com/rules/decisionservice/trace";

    protected String decisionId;
    protected String requestedRulesetPath;
    protected String executedRulesetPath;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionDate;
    protected BigInteger executionDuration;
    protected Properties rulesetProperties;
    protected Properties systemProperties;
    protected String inetAddress;
    protected BigInteger totalRules;
    protected BigInteger totalRulesFired;
    protected BigInteger totalRulesNotFired;
    protected RulesFired rules;
    protected RulesFired rulesFired;
    protected Rules rulesNotFired;
    protected BigInteger totalTasks;
    protected BigInteger totalTasksExecuted;
    protected BigInteger totalTasksNotExecuted;
    protected Tasks tasks;
    protected TasksExecuted tasksExecuted;
    protected Tasks tasksNotExecuted;
    protected String outputString;
    protected Map<String, Object> inputParameters;
    protected Map<String, Object> outputParameters;
    protected DataObjects workingMemory;
    protected ExecutionEvents executionEvents;

    /**
     * Gets the value of the decisionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecisionId() {
        return decisionId;
    }

    /**
     * Sets the value of the decisionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecisionId(String value) {
        this.decisionId = value;
    }

    /**
     * Gets the value of the requestedRulesetPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestedRulesetPath() {
        return requestedRulesetPath;
    }

    /**
     * Sets the value of the requestedRulesetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestedRulesetPath(String value) {
        this.requestedRulesetPath = value;
    }

    /**
     * Gets the value of the executedRulesetPath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExecutedRulesetPath() {
        return executedRulesetPath;
    }

    /**
     * Sets the value of the executedRulesetPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExecutedRulesetPath(String value) {
        this.executedRulesetPath = value;
    }

    /**
     * Gets the value of the executionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the value of the executionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecutionDate(XMLGregorianCalendar value) {
        this.executionDate = value;
    }

    /**
     * Gets the value of the executionDuration property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getExecutionDuration() {
        return executionDuration;
    }

    /**
     * Sets the value of the executionDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setExecutionDuration(BigInteger value) {
        this.executionDuration = value;
    }

    /**
     * Gets the value of the rulesetProperties property.
     * 
     * @return
     *     possible object is
     *     {@link Properties }
     *     
     */
    public Properties getRulesetProperties() {
        return rulesetProperties;
    }

    /**
     * Sets the value of the rulesetProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Properties }
     *     
     */
    public void setRulesetProperties(Properties value) {
        this.rulesetProperties = value;
    }

    /**
     * Gets the value of the systemProperties property.
     * 
     * @return
     *     possible object is
     *     {@link Properties }
     *     
     */
    public Properties getSystemProperties() {
        return systemProperties;
    }

    /**
     * Sets the value of the systemProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Properties }
     *     
     */
    public void setSystemProperties(Properties value) {
        this.systemProperties = value;
    }

    /**
     * Gets the value of the inetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInetAddress() {
        return inetAddress;
    }

    /**
     * Sets the value of the inetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInetAddress(String value) {
        this.inetAddress = value;
    }

    /**
     * Gets the value of the totalRules property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalRules() {
        return totalRules;
    }

    /**
     * Sets the value of the totalRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalRules(BigInteger value) {
        this.totalRules = value;
    }

    /**
     * Gets the value of the totalRulesFired property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalRulesFired() {
        return totalRulesFired;
    }

    /**
     * Sets the value of the totalRulesFired property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalRulesFired(BigInteger value) {
        this.totalRulesFired = value;
    }

    /**
     * Gets the value of the totalRulesNotFired property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalRulesNotFired() {
        return totalRulesNotFired;
    }

    /**
     * Sets the value of the totalRulesNotFired property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalRulesNotFired(BigInteger value) {
        this.totalRulesNotFired = value;
    }

    /**
     * Gets the value of the rules property.
     * 
     * @return
     *     possible object is
     *     {@link RulesFired }
     *     
     */
    public RulesFired getRules() {
        return rules;
    }

    /**
     * Sets the value of the rules property.
     * 
     * @param value
     *     allowed object is
     *     {@link RulesFired }
     *     
     */
    public void setRules(RulesFired value) {
        this.rules = value;
    }

    /**
     * Gets the value of the rulesFired property.
     * 
     * @return
     *     possible object is
     *     {@link RulesFired }
     *     
     */
    public RulesFired getRulesFired() {
        return rulesFired;
    }

    /**
     * Sets the value of the rulesFired property.
     * 
     * @param value
     *     allowed object is
     *     {@link RulesFired }
     *     
     */
    public void setRulesFired(RulesFired value) {
        this.rulesFired = value;
    }

    /**
     * Gets the value of the rulesNotFired property.
     * 
     * @return
     *     possible object is
     *     {@link Rules }
     *     
     */
    public Rules getRulesNotFired() {
        return rulesNotFired;
    }

    /**
     * Sets the value of the rulesNotFired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rules }
     *     
     */
    public void setRulesNotFired(Rules value) {
        this.rulesNotFired = value;
    }

    /**
     * Gets the value of the totalTasks property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalTasks() {
        return totalTasks;
    }

    /**
     * Sets the value of the totalTasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalTasks(BigInteger value) {
        this.totalTasks = value;
    }

    /**
     * Gets the value of the totalTasksExecuted property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalTasksExecuted() {
        return totalTasksExecuted;
    }

    /**
     * Sets the value of the totalTasksExecuted property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalTasksExecuted(BigInteger value) {
        this.totalTasksExecuted = value;
    }

    /**
     * Gets the value of the totalTasksNotExecuted property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalTasksNotExecuted() {
        return totalTasksNotExecuted;
    }

    /**
     * Sets the value of the totalTasksNotExecuted property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalTasksNotExecuted(BigInteger value) {
        this.totalTasksNotExecuted = value;
    }

    /**
     * Gets the value of the tasks property.
     * 
     * @return
     *     possible object is
     *     {@link Tasks }
     *     
     */
    public Tasks getTasks() {
        return tasks;
    }

    /**
     * Sets the value of the tasks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tasks }
     *     
     */
    public void setTasks(Tasks value) {
        this.tasks = value;
    }

    /**
     * Gets the value of the tasksExecuted property.
     * 
     * @return
     *     possible object is
     *     {@link TasksExecuted }
     *     
     */
    public TasksExecuted getTasksExecuted() {
        return tasksExecuted;
    }

    /**
     * Sets the value of the tasksExecuted property.
     * 
     * @param value
     *     allowed object is
     *     {@link TasksExecuted }
     *     
     */
    public void setTasksExecuted(TasksExecuted value) {
        this.tasksExecuted = value;
    }

    /**
     * Gets the value of the tasksNotExecuted property.
     * 
     * @return
     *     possible object is
     *     {@link Tasks }
     *     
     */
    public Tasks getTasksNotExecuted() {
        return tasksNotExecuted;
    }

    /**
     * Sets the value of the tasksNotExecuted property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tasks }
     *     
     */
    public void setTasksNotExecuted(Tasks value) {
        this.tasksNotExecuted = value;
    }

    /**
     * Gets the value of the outputString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputString() {
        return outputString;
    }

    /**
     * Sets the value of the outputString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputString(String value) {
        this.outputString = value;
    }

    /**
     * Gets the value of the inputParameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Map<String, Object> getInputParameters() {
    	if (inputParameters == null) {
    		inputParameters = new HashMap<String, Object>();
    	}
        return inputParameters;
    }

    public void addInputParameter(String name, Object value) {
    	if (inputParameters == null) {
    		inputParameters = new HashMap<String, Object>();
    	}
    	this.inputParameters.put(name, value);
    }

    /**
     * Gets the value of the outputParameters property.
     * 
     * @return
     *     possible object is
     *     {@link Parameters }
     *     
     */
    public Map<String, Object> getOutputParameters() {
    	if (outputParameters == null) {
    		outputParameters = new HashMap<String, Object>();
    	}
        return outputParameters;
    }
   
    public void addOutputParameter(String name, Object value) {
    	if (outputParameters == null) {
    		outputParameters = new HashMap<String, Object>();
    	}
    	this.outputParameters.put(name, value);
    }

    /**
     * Gets the value of the workingMemory property.
     * 
     * @return
     *     possible object is
     *     {@link DataObjects }
     *     
     */
    public DataObjects getWorkingMemory() {
        return workingMemory;
    }

    /**
     * Sets the value of the workingMemory property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataObjects }
     *     
     */
    public void setWorkingMemory(DataObjects value) {
        this.workingMemory = value;
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

}
