package com.archci.dependencies;

import java.util.Properties;

import com.archci.enums.ViolationProperties;

public final class AnnotateVariableDependency extends AnnotateDependency {
	private final String variableNameA;
	private final String methodNameA;
	
	public AnnotateVariableDependency(String classNameA, String classNameB, Integer lineNumberA, Integer offset, Integer length, String methodNameA, String variableNameA) {
		super(classNameA,classNameB,lineNumberA, offset, length);
		this.methodNameA = methodNameA;
		this.variableNameA = variableNameA;
	}

	public String getVariableNameA() {
		return this.variableNameA;
	}
	
	public String getMethodNameA() {
		return this.methodNameA;
	}
	
	@Override
	public String toString() {
		return "'" + 
				this.classNameA + "' contains the variable '" + this.variableNameA + 
				"' in method '" + this.methodNameA + "' which is annotated by '" + this.classNameB + "'";
	}
	
	@Override
	public Properties props() {
		Properties props = super.props();
		props.put(ViolationProperties.VARIABLE_NAME_A.getKey(), this.variableNameA);
		props.put(ViolationProperties.METHOD_NAME_A.getKey(), this.methodNameA);
		return props;
	}
}