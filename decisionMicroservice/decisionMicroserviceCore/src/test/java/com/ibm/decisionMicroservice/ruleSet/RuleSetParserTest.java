package com.ibm.decisionMicroservice.ruleSet;

import com.ibm.decisionMicroservice.codeGen.parameters.Directions;
import com.ibm.decisionMicroservice.codeGen.parameters.Parameter;
import com.ibm.decisionMicroservice.codeGen.parameters.ParameterTest;
import com.ibm.decisionMicroservice.exception.RuleSetReadException;
import ilog.rules.res.model.IlrRulesetParameter;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RuleSetParserTest {

    private String ruleSetArchivePath = this.getClass().getClassLoader().getResource("ruleset.jar").getPath();
    private String ruleSetArchivePathDsar = this.getClass().getClassLoader().getResource("ruleset.dsar").getPath();

    @Test
    public void mappingTest(){
        RuleSetParser parser = new RuleSetParser(this.ruleSetArchivePath);

        Map<String,String> mapping = parser.getRuleSetParametersMapping();

        assertEquals("miniloan.Borrower",mapping.get("borrower"));
        assertEquals("miniloan.Borrower",mapping.get("Borrower"));
        assertEquals("miniloan.Loan",mapping.get("loan"));
        assertEquals("miniloan.Loan",mapping.get("Loan"));

    }

    @Test(expected = RuleSetReadException.class)
    public void exceptionTest(){
        new RuleSetParser(";;;;").getRuleSetParametersMapping();
    }

    @Test
    public void dsarArchiveTest(){
        RuleSetParser parser = new RuleSetParser(this.ruleSetArchivePathDsar);
        Map<String,String> mapping = parser.getRuleSetParametersMapping();

        assertEquals("myModel.CustomerStatus",mapping.get("Customer"));
        assertEquals("myModel.odm.Shipment",mapping.get("Shipment"));
        assertEquals("myModel.odm.Method",mapping.get("Method"));

    }

    @Test
    public void parameterListTest(){
        RuleSetParser parser = new RuleSetParser(this.ruleSetArchivePathDsar);
        List<IlrRulesetParameter> params = parser.getParameters();
        assertEquals(5,params.size());

    }

    @Test
    public void inputListTest(){
        RuleSetParser parser = new RuleSetParser(this.ruleSetArchivePath);
        Parameter loan = new Parameter("loan","miniloan.Loan", Directions.IN);
        Parameter borrower = new Parameter("borrower","miniloan.Borrower", Directions.IN);

        List<Parameter> inputs = parser.getInputParameters();

        assertTrue(inputs.contains(loan));
        assertTrue(inputs.contains(borrower));
        assertEquals(2,inputs.size());
    }

    @Test
    public void outputListTest(){
        RuleSetParser parser = new RuleSetParser(this.ruleSetArchivePath);
        Parameter loan = new Parameter("loan","miniloan.Loan", Directions.OUT);

        List<Parameter> outputs = parser.getOutputParameters();
        assertTrue(outputs.contains(loan));
        assertEquals(1,outputs.size());
    }

}
