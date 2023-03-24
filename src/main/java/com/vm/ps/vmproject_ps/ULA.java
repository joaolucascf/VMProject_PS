package com.vm.ps.vmproject_ps;

import javax.xml.stream.events.Characters;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ULA {
    private static Memory mem = new Memory();
    private Registers reg = new Registers();

    public int stepOne(String code){
        Instruction instructions = new Instruction();
        mem.cmdMemory.clear();
        Scanner sc = new Scanner(code);
        while(sc.hasNext()){
            String codeLine = sc.nextLine();
            String[] commandsLine = codeLine.split("\t");
            //TODO: need to fix the blank spaces in cmdMemory.
            for(int i=0; i<commandsLine.length; i++){
                for(InstructionStruct j : instructions.getInstructionList()){
                    if(commandsLine[i].toLowerCase().contains(j.getInstructionName())){
                        mem.cmdMemory.add(j.getInstructionName());
                    }
                }
            }
        }
        mem.printCmdMemory();
        return 0;
    }

    public Memory getMem(){
        return mem;
    }
}
