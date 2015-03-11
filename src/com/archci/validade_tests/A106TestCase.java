package com.archci.validade_tests;

import java.util.List;

import com.archci.core.DependencyConstraint.ArchitecturalDrift;
import com.archci.core.DependencyConstraint.DivergenceArchitecturalDrift;
import com.archci.dependencies.DeclareParameterizedTypeDependency;

/**
 * An example of a DCLTestCase 
 * 1. Test classes must end with "TestCase" and extend DCLTestCase
 * 2. Test methods must start with "test" followed by a two digit number, e.g., test01(), test02(), etc.
 * 3. There is no attributes or constructors, i.e., only methods.
 * 4. There is pre-defined modules: MA, MB, MC, and MEX;
 *    MA  refers to classes of com.example.a.*
 *    MB  refers to classes of com.example.b.*
 *    MC  refers to classes of com.example.c.*
 *    MEX refers to classes of com.example.ex.*
 * @author Ricardo Terra
 */
public class A106TestCase extends DCLTestCase { 

	public void test01() throws Exception {
		List<ArchitecturalDrift> violations = this.validateSystem("com.example.a.A106 can-declare-only com.example.b.B106, java.util.*"); //Define the constraint to be validated
		
		assertEquals(2, violations.size()); //Check the number of violations (usually only one violation for constraint)
		
		ArchitecturalDrift ad = violations.get(0);
		
		assertEquals(DivergenceArchitecturalDrift.class, ad.getClass()); //Check the type of violation (divergence or absence)
		DivergenceArchitecturalDrift dad = (DivergenceArchitecturalDrift) ad;
		
		assertEquals(DeclareParameterizedTypeDependency.class, dad.getForbiddenDependency().getClass()); //Check the type of dependency
		DeclareParameterizedTypeDependency declareParameterizedTypeDependency = (DeclareParameterizedTypeDependency) dad.getForbiddenDependency();
		
		//Check each attribute of the violation
		assertEquals("com.example.a.A106",declareParameterizedTypeDependency.getClassNameA());
		assertEquals("com.example.c.C106",declareParameterizedTypeDependency.getClassNameB());
		assertEquals("f",declareParameterizedTypeDependency.getMethodNameA());
		
		ad = violations.get(1);
		
		assertEquals(DivergenceArchitecturalDrift.class, ad.getClass()); //Check the type of violation (divergence or absence)
		dad = (DivergenceArchitecturalDrift) ad;
		
		assertEquals(DeclareParameterizedTypeDependency.class, dad.getForbiddenDependency().getClass()); //Check the type of dependency
		declareParameterizedTypeDependency = (DeclareParameterizedTypeDependency) dad.getForbiddenDependency();
		
		//Check each attribute of the violation
		assertEquals("com.example.a.A106",declareParameterizedTypeDependency.getClassNameA());
		assertEquals("com.example.c.C106",declareParameterizedTypeDependency.getClassNameB());
		assertEquals("f",declareParameterizedTypeDependency.getMethodNameA());
	}

}