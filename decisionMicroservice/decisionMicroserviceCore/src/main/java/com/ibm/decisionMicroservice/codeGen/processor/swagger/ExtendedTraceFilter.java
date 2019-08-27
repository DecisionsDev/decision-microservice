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
package com.ibm.decisionMicroservice.codeGen.processor.swagger;

import ilog.rules.res.session.IlrTraceFilter;
import ilog.rules.res.session.impl.IlrTraceFilterImpl;
import io.swagger.annotations.ApiModel;

import java.util.Map;

@ApiModel(value="TraceFilter")
public class ExtendedTraceFilter implements IlrTraceFilter {

  private static final long serialVersionUID = 1L;

  public static enum SerializationType {
    JAXB, ToString, ClassName
  };

  private IlrTraceFilterImpl wrappedTraceFilter = new IlrTraceFilterImpl();

  private boolean infoRulesetProperties;

  private boolean infoOutputString;

  private boolean infoInputParameters;

  private boolean infoOutputParameters;

  private boolean none;

  private boolean ruleFired;

  private boolean taskExecuted;

  private boolean infoExecutionEventsAsked;

  private SerializationType serializationType = SerializationType.ClassName;

  public ExtendedTraceFilter() {
    wrappedTraceFilter.setFilter(INFO_EXECUTION_DATE, "true");
  }

  @Override
  public String getWorkingMemoryFilter() {
    return wrappedTraceFilter.getWorkingMemoryFilter();
  }

  @Override
  public Boolean isInfoBoundObjectByRule() {
    return wrappedTraceFilter.isInfoBoundObjectByRule();
  }

  @Override
  public Boolean isInfoExecutionDuration() {
    return wrappedTraceFilter.isInfoExecutionDuration();
  }
  
  @Override
  public Boolean isInfoExecutionDate() {
    return wrappedTraceFilter.isInfoExecutionDate();
  }

  @Override
  public Boolean isInfoExecutionEvents() {
    return wrappedTraceFilter.isInfoExecutionEvents();
  }

  @Override
  public Boolean isInfoInetAddress() {
    return wrappedTraceFilter.isInfoInetAddress();
  }

  @Override
  public Boolean isInfoRules() {
    return wrappedTraceFilter.isInfoRules();
  }

  @Override
  public Boolean isInfoRulesNotFired() {
    return wrappedTraceFilter.isInfoRulesNotFired();
  }

  @Override
  public Boolean isInfoSystemProperties() {
    return wrappedTraceFilter.isInfoSystemProperties();
  }

  @Override
  public Boolean isInfoTasks() {
    return wrappedTraceFilter.isInfoTasks();
  }

  @Override
  public Boolean isInfoTasksNotExecuted() {
    return wrappedTraceFilter.isInfoTasksNotExecuted();
  }

  @Override
  public Boolean isInfoTotalRulesFired() {
    return wrappedTraceFilter.isInfoTotalRulesFired();
  }

  @Override
  public Boolean isInfoTotalRulesNotFired() {
    return wrappedTraceFilter.isInfoTotalRulesNotFired();
  }

  @Override
  public Boolean isInfoTotalTasksExecuted() {
    return wrappedTraceFilter.isInfoTotalTasksExecuted();
  }

  @Override
  public Boolean isInfoTotalTasksNotExecuted() {
    return wrappedTraceFilter.isInfoTotalTasksNotExecuted();
  }

  @Override
  public Boolean isInfoWorkingMemory() {
    return wrappedTraceFilter.isInfoWorkingMemory();
  }

  @Override
  public void loadFromProperties(Map<String, String> arg0) {
    wrappedTraceFilter.loadFromProperties(arg0);
  }

  @Override
  public void setFilter(String key, String value) {
    wrappedTraceFilter.setFilter(key, value);
  }

  @Override
  public void setInfoAllFilters(Boolean value) {
    wrappedTraceFilter.setInfoAllFilters(value);
  }

  @Override
  public void setInfoBoundObjectByRule(Boolean value) {
    wrappedTraceFilter.setInfoBoundObjectByRule(value);
  }

  @Override
  public void setInfoExecutionDuration(Boolean value) {
    wrappedTraceFilter.setInfoExecutionDuration(value);
  }
  
  @Override
  public void setInfoExecutionDate(Boolean value) {
    wrappedTraceFilter.setInfoExecutionDate(value);
  }

  @Override
  public void setInfoExecutionEvents(Boolean value) {
    infoExecutionEventsAsked = value;
    wrappedTraceFilter.setInfoExecutionEvents(this.ruleFired | this.infoExecutionEventsAsked | this.taskExecuted);
  }

  @Override
  public void setInfoInetAddress(Boolean value) {
    wrappedTraceFilter.setInfoInetAddress(value);
  }

  @Override
  public void setInfoRules(Boolean value) {
    wrappedTraceFilter.setInfoRules(value);
  }

  @Override
  public void setInfoRulesNotFired(Boolean value) {
    wrappedTraceFilter.setInfoRulesNotFired(value);
  }

  @Override
  public void setInfoSystemProperties(Boolean value) {
    wrappedTraceFilter.setInfoSystemProperties(value);
  }

  @Override
  public void setInfoTasks(Boolean value) {
    wrappedTraceFilter.setInfoTasks(value);
  }

  @Override
  public void setInfoTasksNotExecuted(Boolean value) {
    wrappedTraceFilter.setInfoTasksNotExecuted(value);
  }

  @Override
  public void setInfoTotalRulesFired(Boolean value) {
    wrappedTraceFilter.setInfoTotalRulesFired(value);
  }

  @Override
  public void setInfoTotalRulesNotFired(Boolean value) {
    wrappedTraceFilter.setInfoTotalRulesNotFired(value);
  }

  @Override
  public void setInfoTotalTasksExecuted(Boolean value) {
    wrappedTraceFilter.setInfoTotalTasksExecuted(value);
  }

  @Override
  public void setInfoTotalTasksNotExecuted(Boolean value) {
    wrappedTraceFilter.setInfoTotalTasksNotExecuted(value);
  }

  @Override
  public void setInfoWorkingMemory(Boolean value) {
    wrappedTraceFilter.setInfoWorkingMemory(value);
  }

  @Override
  public void setWorkingMemoryFilter(String value) {
    wrappedTraceFilter.setWorkingMemoryFilter(value);
  }

  public Map<String, String> toMap() {
    return wrappedTraceFilter.toMap();
  }

  // Extensions ...

  public boolean isInfoRulesetProperties() {
    return infoRulesetProperties;
  }

  public void setInfoRulesetProperties(boolean value) {
    this.infoRulesetProperties = value;
  }

  public boolean isInfoOutputString() {
    return infoOutputString;
  }

  public void setInfoOutputString(boolean value) {
    this.infoOutputString = value;
  }

  public boolean isInfoInputParameters() {
    return infoInputParameters;
  }

  public void setInfoInputParameters(boolean value) {
    this.infoInputParameters = value;
  }

  public boolean isInfoOutputParameters() {
    return infoOutputParameters;
  }

  public void setInfoOutputParameters(boolean value) {
    this.infoOutputParameters = value;
  }

  public boolean isNone() {
    return none;
  }

  public void setNone(boolean none) {
    this.none = none;
  }

  public boolean isInfoRulesFired() {
    return ruleFired;
  }

  public void setInfoRulesFired(boolean ruleFired) {
    this.ruleFired = ruleFired;
    wrappedTraceFilter.setInfoExecutionEvents(this.ruleFired | this.infoExecutionEventsAsked | this.taskExecuted);
  }

  public boolean isInfoTasksExecuted() {
    return taskExecuted;
  }

  public void setInfoTasksExecuted(boolean value) {
    this.taskExecuted = value;
    wrappedTraceFilter.setInfoExecutionEvents(this.ruleFired | this.infoExecutionEventsAsked | this.taskExecuted);
  }

  public boolean isInfoExecutionEventsAsked() {
    return infoExecutionEventsAsked;
  }

  public void setInfoBoundObjectSerializationType(SerializationType serializationType) {
    this.serializationType = serializationType;
  }

  public SerializationType getInfoBoundObjectSerializationType() {
    return serializationType;
  }


}
