package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;

import java.util.ArrayList;
import java.util.List;

//aggregate multiple processor as one
public class AggregatorProcessor implements Processor {
    List<Processor> processors;

    public AggregatorProcessor(){
        processors = new ArrayList<>();
    }

    public AggregatorProcessor(List<Processor> processors){
        this.processors = processors;
    }

    public AggregatorProcessor(Processor... processors){
        this();

        for (Processor processor : processors){
            this.processors.add(processor);
        }
    }

    @Override
    public void setOptions(GeneratorOptions options) {
        processors.stream().forEach(processor -> {processor.setOptions(options);});

    }

    @Override
    public void process() {
        processors.stream().forEach(processor -> {processor.process();});
    }
}
