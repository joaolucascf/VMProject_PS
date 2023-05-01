package com.vm.ps.vmproject_ps;

public class ULA {
    /*
     * String a = Integer.toBinaryString(113);
     * Byte b = Byte.parseByte(a, 2);
     */
    private final int MEMORY_MIN_SIZE = 1024; // constante de tamanho mínimo de memória de dados
    private Memory mem = new Memory(MEMORY_MIN_SIZE);
    private Registers reg = new Registers();

    public void run(String code) throws Exception {
        Assembler.firstStep();
    }

    private short flagToBitArray(boolean[] flags){
        short value = 0;
        for(int i = 3; i < 6; i++){
            if(flags[i])
                value = (short) (value + Math.pow(2, i + 13));
        }
        return value;
    }

    short calculatePosition(boolean[] flags){
        /*
        * 0 = n
        * 1 = i
        * 2 = x
        * 3 = b
        * 4 = p
        * 5 = e
        * */
        if (flags.length != 6)
            throw new RuntimeException("Formato de instrução inválido");
        short disp = 0;
        short TA = 0;
        if(!flags[0] && !flags[1])
            TA = (short) (flagToBitArray(flags) + disp);
        else{
            if(flags[0] && flags[1])
                if (flags[3])
                    TA = (short) (reg.registerSet.get("B").getValue() + disp);
            if (flags[4])
                TA = (short) (reg.registerSet.get("PC").getValue() + disp);
            else
                TA = disp;
            if (flags[2])
                TA += (short) reg.registerSet.get("X").getValue();
        }
        return TA;
    }

    /*private void execute(String opCode, String operand){
                boolean flags [] = {true, true, true, false, false, false};
                String[] regs = operand.trim().toUpperCase().split(",");
        switch (Integer.valueOf(opCode)){
            case 24:
                reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue() + mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 88:
                reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[1]).getValue() + reg.registerSet.get(regs[0]).getValue());
                break;
            case 64:
                reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue()&(mem.getWord(calculatePosition(flags), flags[0], flags[1])));
                break;
            case 180:
            	reg.registerSet.get(regs[0]).setValue(0);
                break;
            case 40:
                boolean equals;
            	if (reg.registerSet.get(regs[0]).getValue() == (mem.getWord(calculatePosition(flags), flags[0], flags[1])))
                    equals = true;
            	break;
            case 160:
                if(reg.registerSet.get(regs[0]).getValue() == (reg.registerSet.get(regs[1]).getValue()))
                    equals = true;
                break;
            case 36:
            	reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue()/mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 156:
            	reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[1]).getValue()/reg.registerSet.get(regs[0]).getValue());
                break;
            case 60:
            	reg.registerSet.get(regs[7]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 48:
                if(reg.registerSet.get(regs[0]).getValue() == (reg.registerSet.get(operand).getValue()))//duvida em relação ao operando, se é uma posição de memoria nao devo usar o mem.getWord()?
                    reg.registerSet.get(regs[7]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 52:
                if(reg.registerSet.get(regs[0]).getValue() < reg.registerSet.get(operand).getValue())
                    reg.registerSet.get(regs[7]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 56:
                if(reg.registerSet.get(regs[0]).getValue() > reg.registerSet.get(operand).getValue())
                    reg.registerSet.get(regs[7]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 72:
                reg.registerSet.get(regs[2]).setValue(reg.registerSet.get(regs[7]).getValue());
                reg.registerSet.get(regs[7]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 0:
            	reg.registerSet.get(regs[0]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 104:
            	reg.registerSet.get(regs[3]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 80:
                //nao sei fazer ainda
                break;
            case 8:
                reg.registerSet.get(regs[2]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 108:
                reg.registerSet.get(regs[4]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 116:
                reg.registerSet.get(regs[5]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 4:
                reg.registerSet.get(regs[1]).setValue(mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 32:
            	 reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue()* mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 152:
            	reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue()*reg.registerSet.get(regs[1]).getValue());
                break;
            case 68:
            	reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue() | mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 172:
            	reg.registerSet.get(regs[1]).setValue(reg.registerSet.get(regs[0]).getValue());
                break;
            case 76:
            	reg.registerSet.get(regs[7]).setValue(reg.registerSet.get(regs[2]).getValue());
                break;
            case 164:
                reg.registerSet.get(Byte.parseByte(regs[0], 2)).setValue(reg.registerSet.get(regs[0]).getValue() << reg.registerSet.get(regs[4]).getValue());                break;
            case 168:
                reg.registerSet.get(Byte.parseByte(regs[0], 2)).setValue(reg.registerSet.get(regs[0]).getValue() >> reg.registerSet.get(regs[4]).getValue());
                break;
            case 12:
                mem.storeWord(calculatePosition(flags), reg.registerSet.get(regs[0]).getValue());
                break;
            case 120:
                mem.storeWord(calculatePosition(flags), reg.registerSet.get(regs[3]).getValue());                
                break;
            case 84:
                //byte mais a direita
                
                break;
            case 20:
                mem.storeWord(calculatePosition(flags), reg.registerSet.get(regs[2]).getValue());
                break;
            case 124:
                mem.storeWord(calculatePosition(flags), reg.registerSet.get(regs[4]).getValue());
                break;
            case 132:
                mem.storeWord(calculatePosition(flags), reg.registerSet.get(regs[5]).getValue());
                break;
            case 16:
                mem.storeWord(calculatePosition(flags), reg.registerSet.get(regs[1]).getValue());
                break;
            case 28:
            	reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[0]).getValue() - mem.getWord(calculatePosition(flags), flags[0], flags[1]));
                break;
            case 148:
            	reg.registerSet.get(regs[0]).setValue(reg.registerSet.get(regs[1]).getValue()*reg.registerSet.get(regs[0]).getValue());
                break;
            case 44:
                if(reg.registerSet.get(regs[1]).getValue() == mem.getWord(calculatePosition(flags), flags[0], flags[1]))
                reg.registerSet.get(regs[1]).setValue(reg.registerSet.get(regs[1]).getValue() + 1);     	
                break;
            case 184:
                if(reg.registerSet.get(regs[1]).getValue() == reg.registerSet.get(regs[0]).getValue())
                reg.registerSet.get(regs[1]).setValue(reg.registerSet.get(regs[1]).getValue() + 1);
                break;
            default:
                throw new RuntimeException("Código de operação inválido");
        }
    }*/
}