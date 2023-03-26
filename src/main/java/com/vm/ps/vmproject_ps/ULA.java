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
        run();
        return 0;
    }
    public void allocateVariables(Scanner sc){
        while(sc.hasNext()){
            String varLine = sc.nextLine();
            if(!varLine.isEmpty()){
                String[] allocLine = varLine.split(" ");
                variables.put(allocLine[0].toLowerCase(), mem.nextEmptyPosition(allocLine[2]));
            }
        }
    }

    private void run(){
        boolean isRegisterOP;
        String operand;
        String opCode;
        Register PC = reg.registerSet.get("PC");
        PC.setValue(0);
        while(!mem.cmdMemory.get(PC.getValue()).equals("stop")){
            if(PC.getValue()%2==0){
                opCode = InstructionSet.getOpByName(mem.cmdMemory.get(PC.getValue()));
                PC.setValue(PC.getValue()+1);
                isRegisterOP =opCode.equals("88") || opCode.equals("160") || opCode.equals("156") || opCode.equals("152") ||
                        opCode.equals("172") || opCode.equals("148")|| opCode.equals("180") ||
                        opCode.equals("164") || opCode.equals("168") || opCode.equals("184");
                if(isRegisterOP)
                    operand = mem.cmdMemory.get(PC.getValue());
                else
                    operand = mem.dataMemory.get(variables.get(mem.cmdMemory.get(PC.getValue())));
                execute(opCode, operand);
                PC.setValue(PC.getValue()+1);
            }
        }
    }

    private void execute(String opCode, String operand){
        switch (Integer.valueOf(opCode)){
            case 24:
                reg.registerSet.get("A").setValue(reg.registerSet.get("A").getValue()+Byte.parseByte(operand,2));
                System.out.println(reg.registerSet.get("A").getValue());
                break;
            case 88:
                String[] regs = operand.trim().toUpperCase().split(",");
                reg.registerSet.get(regs[0]).setValue(2);
                reg.registerSet.get(regs[1]).setValue(3);
                reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue()+reg.registerSet.get(regs[1]).getValue());
                break;
            case 64:
                reg.registerSet.get("A").setValue(10);
                reg.registerSet.get("A").setValue(reg.registerSet.get("A").getValue()&Byte.parseByte(operand,2));
                System.out.println(reg.registerSet.get("A").getValue());
                break;
            case 180:
                break;
            case 40:
                break;
            case 160:
                break;
            case 36:
                break;
            case 156:
                break;
            case 60:
                break;
            case 48:
                break;
            case 52:
                break;
            case 56:
                break;
            case 72:
                break;
            case 0:
                break;
            case 104:
                break;
            case 80:
                break;
            case 8:
                break;
            case 108:
                break;
            case 116:
                break;
            case 4:
                break;
            case 32:
                break;
            case 152:
                break;
            case 68:
                break;
            case 172:
                break;
            case 76:
                break;
            case 164:
                break;
            case 168:
                break;
            case 12:
                break;
            case 120:
                break;
            case 84:
                break;
            case 20:
                break;
            case 124:
                break;
            case 132:
                break;
            case 16:
                break;
            case 28:
                break;
            case 148:
                break;
            case 44:
                break;
            case 184:
                break;
            default:
                throw new RuntimeException("Código de operação inválido");
        }
    }
    public void printData(){
        for(String i : mem.dataMemory){
            System.out.println(i);
        }
    }
}
