package com.vm.ps.vmproject_ps;

import java.util.*;

public class ULA {
    /*String a = Integer.toBinaryString(113);
        Byte b = Byte.parseByte(a, 2);
LDA FIVE
STA ALPHA
LDCH CHARZ
STCH C1
STOP

ALPHA RESW 1
FIVE WORD 5
CHARZ BYTE C'Z'
C1 RESB 1
        */
    private static Memory mem = new Memory();
    private Registers reg = new Registers();
    Map<String, Integer> variables = new HashMap<>();
    public int read(String code){
        Scanner sc = new Scanner(code);
        while(sc.hasNext()){
            boolean stopFound = false;
            String codeLine = sc.nextLine();
            String[] cmdLine = codeLine.split(" ");
            for(int i=0; i<cmdLine.length; i++){
                if(!cmdLine[i].isBlank() || !cmdLine[i].isEmpty())
                    mem.cmdMemory.add(cmdLine[i].trim().toLowerCase());
                if(cmdLine[i].trim().toLowerCase().equals("stop")){
                    stopFound = true;
                    break;
                }
            }
            if(stopFound)
                break;
        }
        allocateVariables(sc);
        printData();
        return 0;
    }
    public void allocateVariables(Scanner sc){
        while(sc.hasNext()){
            String varLine = sc.nextLine();
            if(!varLine.isEmpty()){
                String[] allocLine = varLine.split(" ");
                variables.put(allocLine[0], mem.nextEmptyPosition(allocLine[2]));
            }
        }
    }
    public void printData(){
        for(String i : mem.dataMemory){
            System.out.println(i);
        }
    }
}
