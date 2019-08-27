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

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ibm.rules.decisionservice.trace package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ibm.rules.decisionservice.trace
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DecisionTrace }
     * 
     */
    public DecisionTrace createDecisionTrace() {
        return new DecisionTrace();
    }

    /**
     * Create an instance of {@link Properties }
     * 
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create an instance of {@link RulesFired }
     * 
     */
    public RulesFired createRulesFired() {
        return new RulesFired();
    }

    /**
     * Create an instance of {@link Rules }
     * 
     */
    public Rules createRules() {
        return new Rules();
    }

    /**
     * Create an instance of {@link Tasks }
     * 
     */
    public Tasks createTasks() {
        return new Tasks();
    }

    /**
     * Create an instance of {@link TasksExecuted }
     * 
     */
    public TasksExecuted createTasksExecuted() {
        return new TasksExecuted();
    }

    /**
     * Create an instance of {@link DataObjects }
     * 
     */
    public DataObjects createDataObjects() {
        return new DataObjects();
    }

    /**
     * Create an instance of {@link ExecutionEvents }
     * 
     */
    public ExecutionEvents createExecutionEvents() {
        return new ExecutionEvents();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link ArtifactInformation }
     * 
     */
    public ArtifactInformation createArtifactInformation() {
        return new ArtifactInformation();
    }

    /**
     * Create an instance of {@link DataObject }
     * 
     */
    public DataObject createDataObject() {
        return new DataObject();
    }

    /**
     * Create an instance of {@link RuleEvent }
     * 
     */
    public RuleEvent createRuleEvent() {
        return new RuleEvent();
    }

    /**
     * Create an instance of {@link TaskEvent }
     * 
     */
    public TaskEvent createTaskEvent() {
        return new TaskEvent();
    }

}
