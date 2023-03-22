package com.vm.ps.vmproject_ps;

public class Registers {

    private byte[] accumulator = new byte[3];
    private byte[] indexRegister = new byte[3];
    private byte[] jumpRegister = new byte[3];
    private byte[] basisRegister = new byte[3];
    private byte[] generalRegisterT = new byte[3];
    private byte[] generalRegisterR = new byte[3];
    private byte[] PC = new byte[3];
    private byte[] SW = new byte[3];

    public Registers() {
        for(int i=0; i<3; i++){
            accumulator[i] = 0b00000000;
            indexRegister[i] = 0b00000000;
            jumpRegister[i] = 0b00000000;
            basisRegister[i] = 0b00000000;
            generalRegisterT[i] = 0b00000000;
            generalRegisterR[i] = 0b00000000;
            PC[i] = 0b00000000;
            SW[i] = 0b00000000;
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
