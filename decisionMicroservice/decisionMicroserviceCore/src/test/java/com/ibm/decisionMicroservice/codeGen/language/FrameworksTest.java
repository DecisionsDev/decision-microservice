package com.ibm.decisionMicroservice.codeGen.language;

import com.ibm.decisionMicroservice.codeGen.factory.Frameworks;
import com.ibm.decisionMicroservice.exception.NoSuchFrameworkException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FrameworksTest {

    @Test
    public void getLanguageTest(){
        assertEquals(Frameworks.MICRONAUT, Frameworks.getLanguage(Frameworks.MICRONAUT.getName()));
    }

    @Test(expected = NoSuchFrameworkException.class)
    public void noLanguageException(){
        Frameworks.getLanguage("azaza");
    }
}
