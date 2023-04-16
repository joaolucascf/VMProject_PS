package com.vm.ps;

import java.util.*;


public class Macro {
    // definindo os macros
    private String macroName;
    private int numpar;
    private ArrayList<String> parameters;
    private ArrayList<String> instructions;

    public Macro(String macroName, int numpar, ArrayList<String> parameters, ArrayList<String> instructions) {
        this.macroName = macroName;
        this.numpar = numpar;
        this.parameters = parameters;
        this.instructions = instructions;
    }

    public void addInstruction(String linha) {
        this.instructions.add(linha);
    }

    public void addParameter(String linha) {
        this.parameters.add(linha);
    }

    public String getMacroName() {
        return macroName;
    }

    public int getNumpar() {
        return numpar;
    }

    public ArrayList<String> getParameters() {
        return parameters;
    }

    public ArrayList<String> getInstructions() {
        return instructions;
    }
    public void setInstructions(ArrayList<String> Instructions){//define lista de instruções
        this.instructions = Instructions;
    }
}
