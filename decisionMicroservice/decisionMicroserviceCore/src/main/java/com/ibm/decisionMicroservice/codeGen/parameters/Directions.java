package com.ibm.decisionMicroservice.codeGen.parameters;

public enum  Directions {
    IN((byte) 1),
    OUT((byte) 2),
    INOUT((byte)4);

    private byte directionCode;

    Directions(byte directionCode){
        this.directionCode = directionCode;
    }

    public byte getDirectionCode() {
        return directionCode;
    }


}
