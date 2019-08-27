package com.ibm.decisionMicroservice.utils;

import io.swagger.models.Model;
import io.swagger.models.ModelImpl;
import io.swagger.models.Swagger;
import io.swagger.models.properties.DateTimeProperty;
import io.swagger.models.properties.Property;
import io.swagger.models.properties.RefProperty;
import io.swagger.models.properties.StringProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SwaggerNormalizer {

    public SwaggerNormalizer() {
    }

    public void normalizeEnumeration(Swagger swagger) {
        List<String> modelsToAdd = new ArrayList<>();
        for (Model model : swagger.getDefinitions().values()){
            List<String> propertiesToRemove = new ArrayList<>();
            for (Map.Entry<String, Property> entry : model.getProperties().entrySet()){
                Property property = entry.getValue();
                if (property.getType().equals("string")){

                    if(property instanceof StringProperty
                            && ((StringProperty) property).getEnum() != null){
                        propertiesToRemove.add(entry.getKey());
                        modelsToAdd.add(entry.getKey());
                    } else if(property instanceof DateTimeProperty
                            && ((DateTimeProperty) property).getEnum() != null){
                        propertiesToRemove.add(entry.getKey());
                        modelsToAdd.add(entry.getKey());
                    }

                }
            }

            for(String property : propertiesToRemove){
                model.getProperties().put(property, new RefProperty(property));
            }
        }

        for (String model : modelsToAdd){
            swagger.getDefinitions().put(model,new ModelImpl());
        }
    }
}
