package com.vm.ps.vmproject_ps;

public class InstructionStruct {
	private String instructionName;
	private String decimalOpCode;
	private String instructionFormat;
	public InstructionStruct(String name, String decimal, String format){
		this.instructionName = name;
		this.decimalOpCode = decimal;
		this.instructionFormat = format;
	}
}
