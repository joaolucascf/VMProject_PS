package com.vm.ps.vmproject_ps;

public class Register {
    public int index;
    private int value = 0;
    public Register(int index){
        this.index = index;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
