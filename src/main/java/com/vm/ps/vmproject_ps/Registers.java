package com.vm.ps.vmproject_ps;

import java.util.HashMap;
import java.util.Map;

public class Registers {
    static Map<String, Register> registerSet = new HashMap<>();

    /* A = Acumulador
    * X = Indice
    * L = Ligação
    * B = Base
    * S = Genérico
    * T = Genérico
    * F = Float Point, não é utilizado nesse checkpoint
    * PC = Program Counter
    * SW = Status Word
    */
    static { //Instancia o conjunto de registradores, sendo 8 no total
        String[] registerMnemonics = {"A", "X", "L", "B", "S", "T", "F", "PC", "SW"};
        for(int i=0; i<9; i++)
            registerSet.put(registerMnemonics[i], new Register(i));
    }
    public void clearAll(){
        for(String i: registerSet.keySet()){
            registerSet.get(i).setValue((short) 0);
        }
    }
}


