package com.vm.ps.vmproject_ps;

import java.util.ArrayList;
import java.util.List;

public class Memory{
    private short[] memory;

    public Memory(int size){
        memory = new short[size];
    }
    public void storeWord(short pos, short word) {
        if(pos>memory.length)
            throw new RuntimeException("Memory index out of bounds");
        memory[pos] = word;
    }
    public short getWord(short pos, boolean n, boolean i){
        if(n && !i) {
            int result = memory[pos];
            return memory[result];
        }else
            if(!n && i){
            return pos;
        }else{
                return memory[pos];
            }
    }
    public short[] getMemory() {
        return memory;
    }
}
