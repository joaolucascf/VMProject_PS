package com.vm.ps.vmproject_ps;

import java.util.HashMap;
import java.util.Map;

public class Registers {
    Map<String, Register> registerSet = new HashMap<>();

    public Registers() {
        String[] registerMnemonics = {"A", "X", "L", "B", "S", "T", "F", "PC", "SW"};
        for(int i=0; i<8; i++)
            registerSet.put(registerMnemonics[i], new Register(i));
    }
    public int getAccumulator(){
        return registerSet.get("A").getValue();
    }
    public void setAccumulator(int accumulator) {
        registerSet.get("A").setValue(accumulator);
    }
}
