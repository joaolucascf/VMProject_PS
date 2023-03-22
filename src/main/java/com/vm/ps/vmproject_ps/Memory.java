package com.vm.ps.vmproject_ps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Memory{
    private final int WORD_SIZE = 3;
    private final int MEMORY_MIN_SIZE = 1024; //constante de tamanho mínimo de memória de dados
    private List<Byte> dataMemory = new ArrayList<>(); //memória de dados realocável, tem tamanho minimo de 1024 bytes, alocados no construtor
    private List<Byte> cmdMemory = new ArrayList<>(); //memória de instruções realocável, tem tamanho minimo de 1024 bytes, alocados no construtor
    public Memory(){
        //inicializa a memória toda em zero
        for(int i=0; i<MEMORY_MIN_SIZE; i++)
            dataMemory.add((byte)0b00000000);
    }
}
