package com.ibm.decisionMicroservice.codeGen.parameters;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ParameterTest {

    @Test
    public void equalityTest(){
        Parameter a = new Parameter("test","test",Directions.IN);
        Parameter b = new Parameter("test","test",Directions.IN);

        assertEquals(a,a);
        assertEquals(b,b);
        assertEquals(b,a);
    }

    @Test
    public void inequalityTest(){
        Parameter a = new Parameter("test","test",Directions.IN);
        Parameter b = new Parameter("test","test",Directions.OUT);
        assertNotEquals(a,b);
    }
}
