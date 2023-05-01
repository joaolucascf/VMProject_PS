package com.vm.ps.vmproject_ps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
class splitLine{
    int line;
    String label;
    String opCode;
    String operand;
}
public class Assembler {
    static File f = new File("src/main/resources/teste.asm");
    static ArrayList<String> code = new ArrayList<>();
    static ArrayList<splitLine> codeInMem = new ArrayList<>();
    static HashMap<String, Integer> symbolTable = new HashMap<>();
    static int LC = 0;
    static void fileToMemory() throws IOException {
        Scanner sc = new Scanner(f);
        while(sc.hasNext())
            code.add(sc.nextLine());
        sc.close();
    }
    static void firstStep() throws Exception {
        fileToMemory();
        for(String i: code){
            i = i.trim();
            String[] splitter = i.split("\\s+");
            splitLine splitted = new splitLine();
            splitted.line = LC;
            switch(splitter.length) {
                case 1:
                    splitted.opCode = splitter[0].trim();
                    LC++;
                    break;
                case 2:
                    splitted.opCode = splitter[0].trim();
                    splitted.operand = splitter[1].trim();
                    LC+=getSize(splitted);
                    break;
                case 3:
                    splitted.label = splitter[0].trim();
                    splitted.opCode = splitter[1].trim();
                    splitted.operand = splitter[2].trim();
                    LC+=getSize(splitted);
                    addSymbol(splitted);
                    break;
                default:
                    System.out.println("Fim do código");
                    break;
            }
            System.out.println(LC);
            codeInMem.add(splitted);
        }
    }

    static int getSize(splitLine splitted){
        Operation op = InstructionSet.getOpByName(splitted.opCode.toLowerCase());
        try {
            if (op.getInstructionFormat().equals("2"))
                return 2;
            else if (op.getInstructionFormat().equals("3/4"))
                return 3;
        }catch (NullPointerException e){
            return 0;
        }
        return 0;
    }

    static void addSymbol(splitLine splitted) throws Exception {
        if(symbolTable.containsKey(splitted.label))
            throw new Exception("A label já existe");
        switch(splitted.opCode){
            case "WORD":
                symbolTable.put(splitted.label, splitted.line);
                LC+=3;
                break;
            case "BYTE":
                symbolTable.put(splitted.label, splitted.line);
                reserveMemory(splitted);
                break;
            case "RESW":
                symbolTable.put(splitted.label, splitted.line);
                LC+=(Integer.parseInt(splitted.operand)*3);
                break;
            case "RESB":
                symbolTable.put(splitted.label, splitted.line);
                LC+=(Integer.parseInt(splitted.operand));
                break;
            default:
                symbolTable.put(splitted.label, splitted.line);
                break;
        }
    }

    static void reserveMemory(splitLine splitted){
        if(splitted.operand.startsWith("C")){
            String res = splitted.operand.substring(2, splitted.operand.length()-1);
            LC+=res.length();
        }else
            LC++;
    }

    static void secondStep(){
        for(splitLine i: codeInMem){
            try {
                Operation op = InstructionSet.getOpByName(i.opCode);
                String opCode = Integer.toBinaryString(Integer.parseInt(op.getDecimalOpCode()));
            }catch (NullPointerException e){
                continue;
            }
        }
    }
}