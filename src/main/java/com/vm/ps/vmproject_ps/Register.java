package com.vm.ps.vmproject_ps;

public class Register {
    public int index;
    private short value = 0;
    public Register(int index){
        this.index = index;
    }
    public void setValue(short value) {
        this.value = value;
    }
    public short getValue() {
        return value;
    }
}
