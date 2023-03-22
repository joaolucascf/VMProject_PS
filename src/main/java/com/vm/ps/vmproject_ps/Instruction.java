package com.vm.ps.vmproject_ps;

import java.util.ArrayList;
import java.util.List;

public class Instruction {
	List<InstructionStruct> op = new ArrayList<InstructionStruct>();
	//os opcodes est�o em decimal para facilitar a convers�o e interpreta��o dos dados.
	public Instruction() {
		op.add(new InstructionStruct("add","24", "3/4"));
		op.add(new InstructionStruct("addr","88", "2"));
		op.add(new InstructionStruct("and","64", "3/4"));
		op.add(new InstructionStruct("clear","4", "2"));
		op.add(new InstructionStruct("comp","40", "3/4"));
		op.add(new InstructionStruct("compr","160", "2"));
		op.add(new InstructionStruct("div","36", "3/4"));
		op.add(new InstructionStruct("divr","156", "2"));
		op.add(new InstructionStruct("j","60", "3/4"));
		op.add(new InstructionStruct("jeq","48", "3/4"));
		op.add(new InstructionStruct("jgt","52", "3/4"));
		op.add(new InstructionStruct("jlt","56", "3/4"));
		op.add(new InstructionStruct("jsub","72", "3/4"));
		op.add(new InstructionStruct("lda","0", "3/4"));
		op.add(new InstructionStruct("ldb","104", "3/4"));
		op.add(new InstructionStruct("ldch","80", "3/4"));
		op.add(new InstructionStruct("ldl","8", "3/4"));
		op.add(new InstructionStruct("lds","108", "3/4"));
		op.add(new InstructionStruct("ldt","116", "3/4"));
		op.add(new InstructionStruct("ldx","4", "3/4"));
		op.add(new InstructionStruct("mul","32", "3/4"));
		op.add(new InstructionStruct("mulr","152", "2"));
		op.add(new InstructionStruct("or","68", "3/4"));
		op.add(new InstructionStruct("rmo","172", "2"));
		op.add(new InstructionStruct("rsub","76", "2"));
		op.add(new InstructionStruct("shiftl","164", "2"));
		op.add(new InstructionStruct("shiftr","168", "2"));
		op.add(new InstructionStruct("sta","12", "3/4"));
		op.add(new InstructionStruct("stb","120", "3/4"));
		op.add(new InstructionStruct("stch","84", "3/4"));
		op.add(new InstructionStruct("stl","20", "3/4"));
		op.add(new InstructionStruct("sts","124", "3/4"));
		op.add(new InstructionStruct("stt","132", "3/4"));
		op.add(new InstructionStruct("stx","16", "3/4"));
		op.add(new InstructionStruct("sub","28", "3/4"));
		op.add(new InstructionStruct("subr","148", "2"));
		op.add(new InstructionStruct("tix","44", "3/4"));
		op.add(new InstructionStruct("tixr","184", "2"));
	}

}
