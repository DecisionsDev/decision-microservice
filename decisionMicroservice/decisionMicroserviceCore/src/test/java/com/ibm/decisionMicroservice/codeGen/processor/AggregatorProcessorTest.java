package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class AggregatorProcessorTest {
    private Queue<Integer> orderQueue;

    @Before
    public void init(){
        orderQueue = new ArrayDeque<>();
    }

    @Test
    public void setOptionTest(){
        AggregatorProcessor processor = new AggregatorProcessor(new TestProcessor());
        GeneratorOptions options = new GeneratorOptions();
        options.setRuleSetPath("test");
        processor.setOptions(options);

        processor.process();

        assertEquals(options.getRuleSetPathArchive(),options.getRuleSetPathArchive());
    }

    @Test
    public void orderTest(){
        AggregatorProcessor processor = new AggregatorProcessor(
                new FirstProcessor(),
                new SecondProcessor(),
                new ThirdProcessor()
        );

        processor.process();

        assertEquals(new Integer(1),this.orderQueue.poll());
        assertEquals(new Integer(2),this.orderQueue.poll());
        assertEquals(new Integer(3),this.orderQueue.poll());
    }

    private class TestProcessor implements Processor{
        private GeneratorOptions options;
        @Override
        public void setOptions(GeneratorOptions options) {
            this.options = options;
        }

        @Override
        public void process() {
            options.setRuleSetPathArchive(this.options.getRuleSetPath());
        }
    }

    private class FirstProcessor implements Processor{
        private GeneratorOptions options;
        @Override
        public void setOptions(GeneratorOptions options) {
            this.options = options;
        }

        @Override
        public void process() {
            orderQueue.add(1);
        }
    }

    private class SecondProcessor implements Processor{
        private GeneratorOptions options;
        @Override
        public void setOptions(GeneratorOptions options) {
            this.options = options;
        }

        @Override
        public void process() {
            orderQueue.add(2);
        }
    }

    private class ThirdProcessor implements Processor{
        private GeneratorOptions options;
        @Override
        public void setOptions(GeneratorOptions options) {
            this.options = options;
        }

        @Override
        public void process() {
            orderQueue.add(3);
        }
    }


}
