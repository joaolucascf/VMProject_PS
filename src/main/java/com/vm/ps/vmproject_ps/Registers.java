package com.vm.ps.vmproject_ps;

public class Registers {

    private byte[] accumulator = new byte[3];
    private byte[] indexRegister = new byte[3];
    private byte[] jumpRegister = new byte[3];
    private byte[] basisRegister = new byte[3];
    private byte[] generalRegisterT = new byte[3];
    private byte[] generalRegisterR = new byte[3];
    private byte[] floatPointAccumulator = new byte[6];
    private byte[] PC = new byte[3];
    private byte[] SW = new byte[3];

    public Registers() {
        for(int i=0; i<3; i++){
            accumulator[i] = 0b00000001;
            indexRegister[i] = 0b00000001;
            jumpRegister[i] = 0b00000001;
            basisRegister[i] = 0b00000001;
            generalRegisterT[i] = 0b00000001;
            generalRegisterR[i] = 0b00000001;
            floatPointAccumulator[i] = 0b00000001;
            PC[i] = 0b00000001;
            SW[i] = 0b00000001;
        }
    }

    public byte[] getAccumulator() {
        return accumulator;
    }
    public void setAccumulator(byte[] accumulator) {
        for(int i=0; i<3; i++){
            this.accumulator[i] = accumulator[i];
        }
    }
}
