package com.vm.ps.vmproject_ps;

import java.util.ArrayList;
import java.util.List;

public class Memory{
    private final int WORD_SIZE = 3;
    private final int MEMORY_MIN_SIZE = 1024; //constante de tamanho mínimo de memória de dados
    public List<String> dataMemory = new ArrayList<>(); //memória de dados realocável, tem tamanho minimo de 1024 bytes, alocados no construtor
    public List<String> cmdMemory = new ArrayList<>(); //memória de instruções realocável, tem tamanho minimo de 1024 bytes, alocados no construtor
    private int freeMemoryIndex = 0;
    public Memory(){
        //inicializa a memória toda em zero
        for(int i=0; i<MEMORY_MIN_SIZE; i++)
            dataMemory.add("00000000");
    }
    public void printCmdMemory(){
        for(String i : cmdMemory){
            System.out.println(i);
        }
    }

    public int nextEmptyPosition(String value){
        value = manipulateValue(value);
        for(int i=freeMemoryIndex ; i<dataMemory.size(); i++){
            if(dataMemory.get(i).equals("00000000")){
                dataMemory.remove(i);
                dataMemory.add(i, value);
                freeMemoryIndex = i+1;
                return i;
            }
        }
        throw new OutOfMemoryError("Sem espaço em memória");
    }

    private String manipulateValue(String value){
        if(value.trim().startsWith("C'")){
            value = value.trim();
            value = value.substring(2,value.length()-1);
        }else{
            value = Integer.toBinaryString(Integer.valueOf(value));
            value = String.format("%0" + 8 + "d", Integer.valueOf(value));
        }
        return value;
    }
}
