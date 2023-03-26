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
            	String[] regs2 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs2[0]).setValue(0);
                break;
            case 40:
            	reg.registerSet.get("A");
            	break;
            case 160:
                break;
            case 36:
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
                break;
            case 52:
                break;
            case 56:
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
            	String[] regs4 = operand.trim().toUpperCase().split(",");
            	reg.registerSet.get(regs4[0]).setValue(2);
            	reg.registerSet.get(regs4[1]).setValue(3);
            	reg.registerSet.get(regs4[0]).setValue(reg.registerSet.get(regs4[0]).getValue()*reg.registerSet.get(regs4[1]).getValue());
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
            	//reg.registerSet.get("PC").setValue();
                break;
            case 164:
           // 	reg.registerSet.get(Byte.parseByte(operand, 2)).setValue(A);
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
