package com.vm.ps.vmproject_ps;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class splitLine{
    int line;
    int format;
    String label;
    String opCode;
    String operand;
    String addressingMode;
    String objCode;
}
public class Assembler {
    static ArrayList<String> code = new ArrayList<>();
    static ArrayList<splitLine> codeToBeObject = new ArrayList<>();
    static HashMap<String, Integer> symbolTable = new HashMap<>();
    static int LC = 0;
    static void fileToMemory() throws IOException {
        try {
            File f = new File("src/main/resources/teste.asm");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String scanned = sc.nextLine();
                if(!scanned.isEmpty() && !scanned.isBlank())
                    code.add(scanned.trim());
            }
            sc.close();
        }catch (FileNotFoundException e){
            System.out.println("arquivo assembly não encontrado");
        }
    }

    static private splitLine separateCode(String code){

        String[] splitter = code.trim().split("\\s+");
        splitLine splitted = new splitLine();

        switch(splitter.length) {
            case 1:
                splitted.opCode = splitter[0].trim();
                break;
            case 2:
                splitted.opCode = splitter[0].trim();
                splitted.operand = splitter[1].trim();
                break;
            case 3:
                splitted.label = splitter[0].trim();
                splitted.opCode = splitter[1].trim();
                splitted.operand = splitter[2].trim();
                break;
            default:
                throw new RuntimeException("Código mal formado");
        }
        return splitted;
    }

    static void firstStep() throws Exception {
        fileToMemory();
        LC = 0;
        int i=0;
        for(;;) {
            splitLine splittingNow = separateCode(code.get(i++));
            splittingNow.line = LC;
            if(splittingNow.opCode.equals("END"))
                break;
            if(splittingNow.label != null && !splittingNow.label.isEmpty()){
                if(symbolTable.containsKey(splittingNow.label)){
                    System.out.println("Símbolo já está na tabela");
                    return;
                }
                symbolTable.put(splittingNow.label, LC);
            }
            if(splittingNow.opCode.startsWith("+")) {
                splittingNow.opCode = splittingNow.opCode.replace("+", "");
            }
            Operation operation = InstructionSet.getOpByName(splittingNow.opCode);
            if(operation != null){
                if(!operation.getDecimalOpCode().equals("76") && splittingNow.operand.startsWith("#"))
                    splittingNow.addressingMode = "imediato";
                else if(!operation.getDecimalOpCode().equals("76") && splittingNow.operand.startsWith("@"))
                    splittingNow.addressingMode = "indireto";
                else if(!operation.getDecimalOpCode().equals("76") && splittingNow.operand.endsWith(",X"))
                    splittingNow.addressingMode = "indexado";
                else
                    splittingNow.addressingMode = "direto";

                if(splittingNow.opCode.startsWith("+")){
                    LC += 4;
                    splittingNow.format = 4;
                }else if(operation.getInstructionFormat().equals("3/4")){
                    LC+=3;
                    splittingNow.format = 3;
                }else{
                    LC+=2;
                    splittingNow.format = 2;
                }
            }else if(splittingNow.opCode.equals("WORD")){
                LC+=3;
            }else if(splittingNow.opCode.equals("RESW")){
                LC+=(Integer.parseInt(splittingNow.operand)*3);
            }else if(splittingNow.opCode.equals("RESB")){
                LC+=(Integer.parseInt(splittingNow.operand));
            }else if(splittingNow.opCode.equals("BYTE")){
                LC++;
            }else{
                throw new RuntimeException("Operação inválida");
            }
            codeToBeObject.add(splittingNow);
        }
        printProgram(codeToBeObject);
    }

    static void stepTwo(){
        List<String> finalObjCode = new ArrayList<>();
        for(splitLine line: codeToBeObject){

            String lineObj = new String("");

            switch (line.format){
                case 2:
                    lineObj = formaTwo(lineObj, line);
                    break;
                case 3:
                    lineObj = formaThree(lineObj, line);
                    break;
            }
        }
    }

    private static String formaTwo(String lineObj, splitLine line){
        Operation op = InstructionSet.getOpByName(line.opCode);
        if(op != null){
            lineObj = lineObj.concat(convertOpCode(Integer.toBinaryString(Integer.valueOf(op.getDecimalOpCode())), line.operand));
        }else{
            throw new RuntimeException(line.opCode + "não existe");
        }

        if(line.operand != null){
            if(line.operand.length() == 3){
                lineObj = lineObj.concat(preenche4Bits(Integer.toBinaryString(Registers.registerSet.get(line.operand.charAt(0)).getIndex())));
                lineObj = lineObj.concat(preenche4Bits(Integer.toBinaryString(Registers.registerSet.get(line.operand.charAt(2)).getIndex())));
            }else
                lineObj = lineObj.concat("0000"+preenche4Bits(Integer.toBinaryString(Registers.registerSet.get(line.operand).getIndex())));
        }
        return lineObj;
    }

    private static String formaThree(String lineObj, splitLine line){
        Operation op = InstructionSet.getOpByName(line.opCode);
        if(op != null){
            lineObj = lineObj.concat(convertOpCode(Integer.toBinaryString(Integer.valueOf(op.getDecimalOpCode())), line.operand));
        }

        return lineObj;
    }

//    public static String formatFour(String lineObj, splitLine line){
//        Operation op = InstructionSet.getOpByName(line.opCode);
//        if(op != null){
//            lineObj = lineObj.concat(convertOpCode(Integer.toBinaryString(Integer.valueOf(op.getDecimalOpCode())), line.operand));
//        }
//
//    }

    public static void printProgram(List<splitLine> program){
        for (splitLine line: program) {
            System.out.println(
                    "LOCCTR: " + String.format("%06X", (line.line))
                            + " | " + "OPCODE: " + String.format("%-5s", line.opCode)
                            + " | " + "OPERAN: " + String.format("%-5s", line.operand));
        }
    }

//    static int getSize(splitLine splitted){
//        Operation op = InstructionSet.getOpByName(splitted.opCode.toLowerCase());
//        try {
//            if (op.getInstructionFormat().equals("2"))
//                return 2;
//            else if (op.getInstructionFormat().equals("3/4"))
//                return 3;
//        }catch (NullPointerException e){
//            return 0;
//        }
//        return 0;
//    }
//
//    static void addSymbol(splitLine splitted) throws Exception {
//        if(symbolTable.containsKey(splitted.label))
//            throw new Exception("A label já existe");
//        switch(splitted.opCode){
//            case "WORD":
//                symbolTable.put(splitted.label, splitted.line);
//                LC+=3;
//                break;
//            case "BYTE":
//                symbolTable.put(splitted.label, splitted.line);
//                LC++;
//                break;
//            case "RESW":
//                symbolTable.put(splitted.label, splitted.line);
//                LC+=(Integer.parseInt(splitted.operand)*3);
//                break;
//            case "RESB":
//                symbolTable.put(splitted.label, splitted.line);
//                LC+=(Integer.parseInt(splitted.operand));
//                break;
//            default:
//                symbolTable.put(splitted.label, splitted.line);
//                break;
//        }
//    }
//    /*
//    * splitted.line = inicio da posição de memória que guarda uma determinada linha de comando
//    * splitted.label = label da linha de comando, pode ou não ser NULL
//    * splitted.opcode = recebe o opcode da linha de comando, em caso de alocação de variável,recebe o tipo
//    * splitted.operand = recebe o nome do operando, posteriormente convertido em posição de memória
//    * */
//
//    static String objectCode = new String();
//    static void secondStep(){
//        for(splitLine i: codeInMem){
//            try {
//                Operation op = InstructionSet.getOpByName(i.opCode);
//                String opCode = Integer.toBinaryString(Integer.parseInt(op.getDecimalOpCode()));
//                opCode = convertOpCode(opCode, i.operand);
//                objectCode = objectCode.concat(opCode + "^");
//                switch (getSize(i)){
//                    case 2:
//                        separateByRegister(i);
//                        break;
//                    case 3:
//                        setFlags(i);
//                        break;
//                    default:
//                        break;
//                }
//                i.operand = i.operand.replace("@", "").replace("#","");
//                //System.out.txt.println(opCode);
////                System.out.txt.println(i.operand);
//            }catch (NullPointerException e){
//                continue;
//            }
//        }
//    }
//    /*if(operand.contains(",")){
//        String[] operands = operand.split(",");
//        Integer address = symbolTable.get(operands[0]);
//    }*/
//
//    static void separateByRegister(splitLine i){
//        String[] splitRegisters = i.operand.split(",");
//        splitRegisters[0] = Integer.toBinaryString(Registers.registerSet.get(splitRegisters[0]).getIndex());
//        splitRegisters[1] = Integer.toBinaryString(Registers.registerSet.get(splitRegisters[1]).getIndex());
//        splitRegisters[0] = preenche8Bits(splitRegisters[0]);
//        splitRegisters[1] = preenche8Bits(splitRegisters[1]);
//        objectCode = objectCode.concat(splitRegisters[0] + "^").concat(splitRegisters[1] + "^");
//    }
//
//    static void setFlags(splitLine i){
//        String[] operandSplit = i.operand.split(",");
//        if(operandSplit.length > 2){
//            throw new RuntimeException("Mais operandos que o esperado");
//        }else if(operandSplit.length > 1){
//            if(operandSplit[1].equals("X")) {
//                String operando = preenche8Bits(Integer.toBinaryString(symbolTable.get(operandSplit[0])));
//                objectCode = objectCode.concat(operando + "^").concat("00000001^");
//            }else{
//                throw new RuntimeException("O registrador de endereçamento indireto não é o X");
//            }
//        }else if(operandSplit.length == 1) {
//            if (symbolTable.get(i.operand) != null) {
//                Integer operand = symbolTable.get(i.operand);
//                System.out.println(operand);
//                String operandBinary = Integer.toBinaryString(operand);
//                operandBinary = preenche8Bits(operandBinary);
//                objectCode = objectCode.concat(operandBinary + "^");
//            } else {
//
//            }
//        }
//    }
    public static void main(String[] args) throws Exception {
        firstStep();
    }

    /*converte o opcode de decimal para binário e ajusta ele aos padrões da máquina adicionando
    * as flags N e I */
    private static String convertOpCode(String opCode, String operand){
        StringBuilder strBuild = new StringBuilder();
        if(opCode.length()<8) {
            for (int i = 0; i < 8 - opCode.length(); i++)
                strBuild.append("0");
            opCode = strBuild.toString() + opCode;
        }
        opCode = opCode.substring(0, 6);
        if(operand.startsWith("@")){
            opCode = opCode.concat("10");
        }else if(operand.startsWith("#")){
            opCode = opCode.concat("01");
        }
        return opCode;
    }

    private static String preenche8Bits(String operand){
        StringBuilder strBuild = new StringBuilder();
        if(operand.length()<8) {
            for (int i = 0; i < 8 - operand.length(); i++)
                strBuild.append("0");
            operand = strBuild.toString() + operand;
        }
        return operand;
    }

    private static String preenche4Bits(String operand){
        StringBuilder strBuild = new StringBuilder();
        if(operand.length()<4) {
            for (int i = 0; i < 4 - operand.length(); i++)
                strBuild.append("0");
            operand = strBuild.toString() + operand;
        }
        return operand;
    }
}
