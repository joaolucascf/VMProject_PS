package com.vm.ps.vmproject_ps.Controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Memory{

    private final int WORD_SIZE = 3;
    private final int MEMORY_MIN_SIZE = 1024;
    List<Byte> memory = new ArrayList<>();

    public Memory(){
        //inicializa a memória toda em zero
        for(int i=0; i<MEMORY_MIN_SIZE; i++)
            memory.add((byte)0b00000000);
    }
    public void printMemory(){
        Iterator memIt = memory.iterator();

        while(memIt.hasNext()){
            Byte printByte = (Byte) memIt.next();
            System.out.println(printByte);
        }
    }
}
