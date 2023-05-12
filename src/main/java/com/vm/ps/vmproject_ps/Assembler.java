package com.vm.ps.vmproject_ps;

import java.io.*;
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
        stepTwo();
    }

    static void stepTwo(){
        List<String> finalObjCode = new ArrayList<>();
        String objCode = new String("");
        for(splitLine line: codeToBeObject){

            String lineObj = "";

            if(line.opCode.equals("END")){
                lineObj = lineObj.concat("11111100");
                continue;
            }

            switch (line.format){
                case 2:
                    if(!line.opCode.equals("76"))
                        lineObj = formaTwo(lineObj, line);
                    break;
                case 3:
                    if(!line.opCode.equals("76"))
                        lineObj = formaThree(lineObj, line);
                    break;
                case 4:
                    //TODO: Implement FormatFour Method
                    break;
                default:
                    break;
            }

            if(line.opCode.equals("WORD")){
                lineObj = lineObj.concat(Integer.toBinaryString(Integer.parseInt(line.operand)));
            }

            if(line.opCode.equals("BYTE")){
                lineObj = lineObj.concat(Integer.toBinaryString(Integer.parseInt(line.operand)));
            }
            finalObjCode.add(lineObj);
            objCode = objCode.concat(lineObj+ "\n");
        }

        System.out.println(" -- Código Objeto -- \n" + objCode + "\n");
        printSymbolTable(symbolTable);
        System.out.println();

        writeObjCodeToFile(objCode, "out.txt");
    }

    private static String formaTwo(String lineObj, splitLine line){
        Operation op = InstructionSet.getOpByName(line.opCode);
        if(op != null){
            if(!op.getDecimalOpCode().equals("76"))
                lineObj = lineObj.concat(convertOpCode(Integer.toBinaryString(Integer.valueOf(op.getDecimalOpCode())), line.operand));
        }else{
            throw new RuntimeException(line.opCode + " não existe");
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

    public static void printProgram(List<splitLine> program){
        for (splitLine line: program) {
            System.out.println(
                    "LOCCTR: " + String.format("%06X", (line.line))
                            + " | " + "OPCODE: " + String.format("%-5s", line.opCode)
                            + " | " + "OPERAN: " + String.format("%-5s", line.operand));
        }
    }

    public static void printSymbolTable(HashMap<String, Integer> SymTab){
        System.out.println("-- Tabela de Símbolos --");
        for (String instrucao : SymTab.keySet())
            System.out.println(instrucao + " = " + String.format("%06X", SymTab.get(instrucao)));
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
        if(!Byte.valueOf(opCode, 2).equals("76")) {
            if (operand.startsWith("@")) {
                opCode = opCode.concat("10");
            } else if (operand.startsWith("#")) {
                opCode = opCode.concat("01");
            }
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

    public static void writeObjCodeToFile(String objCode, String nomearq) {
        FileOutputStream printWriterObjeto = null;

        try {
            OutputStream output = new FileOutputStream(nomearq);
            output.write(objCode.getBytes());
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
