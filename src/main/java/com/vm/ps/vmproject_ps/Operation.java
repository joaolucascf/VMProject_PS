package com.vm.ps.vmproject_ps;

public class Operation {
	private String instructionName;
	private String decimalOpCode;
	private String instructionFormat;
	public Operation(String name, String opcode, String format){
		this.instructionName = name;
		this.decimalOpCode = opcode;
		this.instructionFormat = format;
	}
	public String getInstructionName() {
		return instructionName;
	}
	public String getDecimalOpCode() {
		return decimalOpCode;
	}
	public String getInstructionFormat() {
		return instructionFormat;
	}
}
