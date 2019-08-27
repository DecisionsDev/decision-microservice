package com.ibm.decisionMicroservice.codeGen.processor;

import com.ibm.decisionMicroservice.codeGen.GeneratorOptions;
import com.ibm.decisionMicroservice.utils.SwaggerNormalizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// normalize swagger enumeration
// only necessary for springboot generation
public class SwaggerNormalizerProcessor implements Processor {
    private GeneratorOptions options;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void setOptions(GeneratorOptions options) {
        this.options = options;
    }

    @Override
    public void process() {
        if (this.options.getSwagger() == null){
            logger.info("Open api spec not normalized");
            return;
        }

        SwaggerNormalizer normalizer = new SwaggerNormalizer();
        normalizer.normalizeEnumeration(this.options.getSwagger());

        logger.info("swagger normalized");
    }
}
