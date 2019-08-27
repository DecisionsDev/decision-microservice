package com.ibm.decisionMicroservice.codeGen.parameters;

import java.util.Objects;

public class Parameter {
    private String name;
    private String type;
    private Directions direction;

    public Parameter(String name, String type, Directions direction) {
        this.name = name;
        this.type = type;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Directions getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parameter parameter = (Parameter) o;
        return Objects.equals(name, parameter.name) &&
                Objects.equals(type, parameter.type) &&
                direction == parameter.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, direction);
    }
}
