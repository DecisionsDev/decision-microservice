package com.ibm.decisionMicroservice.codeGen;

import com.ibm.decisionMicroservice.codeGen.processor.Processor;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;


public class GeneratorOrchestratorTest {
    private Queue<Integer> queue = new ArrayDeque<>();

    private Processor preProcessor = new Processor() {
        @Override
        public void setOptions(GeneratorOptions options) {

        }

        @Override
        public void process() {
            queue.add(1);
        }
    };

    private Generator generator = new Generator() {
        @Override
        public void setOptions(GeneratorOptions options) {

        }

        @Override
        public void generate() {
            queue.add(2);
        }
    };

    private Processor postProcessor = new Processor() {

        @Override
        public void setOptions(GeneratorOptions options) {

        }

        @Override
        public void process() {
            queue.add(3);
        }
    };

    @Test
    public void runTest(){
        GeneratorOrchestrator orchestrator = new GeneratorOrchestrator();

        orchestrator.setPreProcessor(preProcessor);
        orchestrator.setGenerator(generator);
        orchestrator.setPostProcessor(postProcessor);

        orchestrator.run();

        Assert.assertEquals(1, (int)this.queue.poll());
        Assert.assertEquals(2, (int)this.queue.poll());
        Assert.assertEquals(3, (int)this.queue.poll());
    }
}
