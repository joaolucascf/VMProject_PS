package com.vm.ps.vmproject_ps;

import java.util.ArrayList;
import java.util.List;

public class InstructionSet {
	private static List<Operation> op = new ArrayList<Operation>();
	//os opcodes est�o em decimal para facilitar a convers�o e interpreta��o dos dados.
	public InstructionSet() {
		op.add(new Operation("add","24", "3/4"));
		op.add(new Operation("addr","88", "2"));
		op.add(new Operation("and","64", "3/4"));
		op.add(new Operation("clear","4", "2"));
		op.add(new Operation("comp","40", "3/4"));
		op.add(new Operation("compr","160", "2"));
		op.add(new Operation("div","36", "3/4"));
		op.add(new Operation("divr","156", "2"));
		op.add(new Operation("j","60", "3/4"));
		op.add(new Operation("jeq","48", "3/4"));
		op.add(new Operation("jgt","52", "3/4"));
		op.add(new Operation("jlt","56", "3/4"));
		op.add(new Operation("jsub","72", "3/4"));
		op.add(new Operation("lda","0", "3/4"));
		op.add(new Operation("ldb","104", "3/4"));
		op.add(new Operation("ldch","80", "3/4"));
		op.add(new Operation("ldl","8", "3/4"));
		op.add(new Operation("lds","108", "3/4"));
		op.add(new Operation("ldt","116", "3/4"));
		op.add(new Operation("ldx","4", "3/4"));
		op.add(new Operation("mul","32", "3/4"));
		op.add(new Operation("mulr","152", "2"));
		op.add(new Operation("or","68", "3/4"));
		op.add(new Operation("rmo","172", "2"));
		op.add(new Operation("rsub","76", "2"));
		op.add(new Operation("shiftl","164", "2"));
		op.add(new Operation("shiftr","168", "2"));
		op.add(new Operation("sta","12", "3/4"));
		op.add(new Operation("stb","120", "3/4"));
		op.add(new Operation("stch","84", "3/4"));
		op.add(new Operation("stl","20", "3/4"));
		op.add(new Operation("sts","124", "3/4"));
		op.add(new Operation("stt","132", "3/4"));
		op.add(new Operation("stx","16", "3/4"));
		op.add(new Operation("sub","28", "3/4"));
		op.add(new Operation("subr","148", "2"));
		op.add(new Operation("tix","44", "3/4"));
		op.add(new Operation("tixr","184", "2"));
	}
	public static boolean isInstruction(String mnemonic){
		for(Operation operation : op){
			if(operation.getInstructionName().equals(mnemonic.toLowerCase()))
				return true;
		}
		return false;
	}
}
