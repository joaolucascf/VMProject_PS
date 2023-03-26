package com.vm.ps.vmproject_ps;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    public void reset_param(ListView memList){
        mem.cmdMemory.clear();
        mem.resetMem();
        variables.clear();
        reg.clearAll();
        refreshMem(memList);
    }
    public int read(String code, ListView memList){
        reset_param(memList);
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
        allocateVariables(sc, memList);
        run();
        return 0;
    }
    public void allocateVariables(Scanner sc, ListView memList) {
        while (sc.hasNext()) {
            String varLine = sc.nextLine();
            if (!varLine.isEmpty()) {
                String[] allocLine = varLine.split(" ");
                variables.put(allocLine[0].toLowerCase(), mem.nextEmptyPosition(allocLine[2]));
            }
        }
        sc.close();
        refreshMem(memList);
    }

    private void refreshMem(ListView<String> t){
        t.getItems().clear();
        t.getItems().addAll(mem.dataMemory);
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
                        opCode.equals("164") || opCode.equals("168") || opCode.equals("184") || opCode.equals("12");
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
                break;
            case 88:
                String[] regs = operand.trim().toUpperCase().split(",");
                reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue()+reg.registerSet.get(regs[1]).getValue());
                break;
            case 64:
                reg.registerSet.get("A").setValue(reg.registerSet.get("A").getValue()&Byte.parseByte(operand,2));
                break;
            case 180:
            	String[] regs2 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs2[0]).setValue(0);
                break;
            case 40:
            	reg.registerSet.get("A");
            	break;
            case 160:
                //reg.registerSet.get("A").getValue().equals(reg.registerSet.get("B").getValue().parseByte());
                break;
            case 36:
                String[] regs4 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs4[0]).setValue(2);
            	reg.registerSet.get(regs4[1]).setValue(3);
            	reg.registerSet.get("A").setValue(reg.registerSet.get(regs4[0]).getValue()/reg.registerSet.get(regs4[1]).getValue());
                break;
            case 156:
            	String[] regs3 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs3[0]).setValue(2);
            	reg.registerSet.get(regs3[1]).setValue(3);
            	reg.registerSet.get(regs3[0]).setValue(reg.registerSet.get(regs3[0]).getValue()/reg.registerSet.get(regs3[1]).getValue());
                break;
            case 60:
            	reg.registerSet.get("PC").setValue(Byte.parseByte(operand));
                break;
            case 48:
                if(reg.registerSet.get("A").getValue() == reg.registerSet.get(operand).getValue())
                reg.registerSet.get("PC").setValue(Byte.parseByte(operand));
                break;
            case 52:
                if(reg.registerSet.get("A").getValue() <= reg.registerSet.get(operand).getValue())
                reg.registerSet.get("PC").setValue(Byte.parseByte(operand));
                break;
            case 56:
                if(reg.registerSet.get("A").getValue() >= reg.registerSet.get(operand).getValue())
                reg.registerSet.get("PC").setValue(Byte.parseByte(operand));
                break;
            case 72:
                break;
            case 0:
            	reg.registerSet.get("A").setValue(Byte.parseByte(operand, 2));
                break;
            case 104:
            	reg.registerSet.get("B").setValue(Byte.parseByte(operand, 2));
                break;
            case 80:
                break;
            case 8:
            	reg.registerSet.get("L").setValue(Byte.parseByte(operand, 2));
                break;
            case 108:
            	reg.registerSet.get("S").setValue(Byte.parseByte(operand, 2));
                break;
            case 116:
            	reg.registerSet.get("T").setValue(Byte.parseByte(operand, 2));
                break;
            case 4:
            	reg.registerSet.get("X").setValue(Byte.parseByte(operand, 2));
                break;
            case 32:
            	 reg.registerSet.get("A").setValue(reg.registerSet.get("A").getValue()*Byte.parseByte(operand,2));	
                break;
            case 152:
            	String[] regs7 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs7[0]).setValue(2);
            	reg.registerSet.get(regs7[1]).setValue(3);
            	reg.registerSet.get(regs7[0]).setValue(reg.registerSet.get(regs7[0]).getValue()*reg.registerSet.get(regs7[1]).getValue());
                break;
            case 68:
            	reg.registerSet.get("A").setValue(reg.registerSet.get("A").getValue()| Byte.parseByte(operand,2));	
                break;
            case 172:
            	String [] regs5 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs5[0]).setValue(2);
            	reg.registerSet.get(regs5[1]).setValue(3);
            	reg.registerSet.get(regs5[0]).setValue(reg.registerSet.get(regs5[1]).getValue());
                break;
            case 76:
            	reg.registerSet.get("PC").setValue(reg.registerSet.get("L").getValue());
                break;
            case 164:
            	reg.registerSet.get(Byte.parseByte(operand, 2)).setValue(reg.registerSet.get(operand).getValue());
                break;
            case 168:
                break;
            case 12:
                mem.saveInPosition(variables.get(operand), String.valueOf(reg.registerSet.get("A").getValue()));
                break;
            case 120:
                mem.saveInPosition(variables.get(operand), String.valueOf(reg.registerSet.get("B").getValue()));
                break;
            case 84:
                break;
            case 20:
                mem.saveInPosition(variables.get(operand), String.valueOf(reg.registerSet.get("L").getValue()));
                break;
            case 124:
                mem.saveInPosition(variables.get(operand), String.valueOf(reg.registerSet.get("S").getValue()));
                break;
            case 132:
                mem.saveInPosition(variables.get(operand), String.valueOf(reg.registerSet.get("T").getValue()));
                break;
            case 16:
                mem.saveInPosition(variables.get(operand), String.valueOf(reg.registerSet.get("X").getValue()));
                break;
            case 28:
            	reg.registerSet.get("A").setValue(reg.registerSet.get("A").getValue() - Byte.parseByte(operand,2));
                break;
            case 148:
            	String[] regs6 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs6[0]).setValue(2);
            	reg.registerSet.get(regs6[1]).setValue(3);
            	reg.registerSet.get(regs6[0]).setValue(reg.registerSet.get(regs6[0]).getValue()*reg.registerSet.get(regs6[1]).getValue());
                break;
            case 44:            	
                break;
            case 78:
            mem.saveInPosition(variables.get(operand), String.valueOf(reg.registerSet.get("B").getValue()));
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

    public Memory getMem(){
        return mem;
    }
}