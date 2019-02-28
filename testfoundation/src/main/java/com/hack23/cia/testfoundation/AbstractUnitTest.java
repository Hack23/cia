/*
 * Copyright 2010 James Pether Sörling
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *	$Id$
 *  $HeadURL$
*/

package com.hack23.cia.testfoundation;

import java.util.List;

import com.openpojo.business.identity.IdentityFactory;
import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SerializableTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.openpojo.validation.utils.IdentityHandlerStub;


/**
 * The Class AbstractUnitTest.
 */
public abstract class AbstractUnitTest extends AbstractTest {
	
	/**
	 * Instantiates a new abstract unit test.
	 */
	protected AbstractUnitTest() {
		super();
	}

	/**
	 * Check all classes in package.
	 *
	 * @param string the string
	 * @return true, if successful
	 */
	public final boolean checkAllClassesInPackage(final String string) {
		
		List<PojoClass> pojoClassesRecursively = PojoClassFactory.getPojoClassesRecursively(string, new FilterTestClasses());
		
		 Validator validator = ValidatorBuilder.create()
                 .with(new SetterMustExistRule(),
                       new GetterMustExistRule()).with(new SerializableTester())
                 .with(new SetterTester(),
                       new GetterTester()).with(new InvokeToStringTester()).with(new InvokeHashcodeTester()).with(new DummyEqualsTester())
                 .with(new EqualsAndHashCodeMatchRule())
                 .build();
		 validator.validate(pojoClassesRecursively);
		 return true;
	}

	public final boolean checkAllDtoClassesInPackage(final String string) {
		
		List<PojoClass> pojoClassesRecursively = PojoClassFactory.getPojoClassesRecursively(string, new FilterTestClasses());
		
		 Validator validator = ValidatorBuilder.create()
                 .with(new GetterMustExistRule())
                 .with(new GetterTester()).with(new SerializableTester())
                 .with(new EqualsAndHashCodeMatchRule()).with(new InvokeToStringTester()).with(new InvokeHashcodeTester()).with(new DummyEqualsTester())
                 .build();
		 validator.validate(pojoClassesRecursively);
		 return true;
	}
	
	public class InvokeToStringTester implements Tester {
		  public void run(PojoClass pojoClass) {
			    Object instance = RandomFactory.getRandomValue(pojoClass.getClazz());
			    Affirm.affirmNotNull("toStringFailure", instance.toString());
		  }
	}

	public class InvokeHashcodeTester implements Tester {
		  public void run(PojoClass pojoClass) {
			    Object instance = RandomFactory.getRandomValue(pojoClass.getClazz());
			    Affirm.affirmFalse("hashCodeFailure", 0 == instance.hashCode());
		  }
	}

	public class DummyEqualsTester implements Tester {
		  public void run(PojoClass pojoClass) {
			    Object instance = RandomFactory.getRandomValue(pojoClass.getClazz());

			    Affirm.affirmTrue("EqualsFailureSameInstanceDontMatch", instance.equals(instance));
			    			    
			    Affirm.affirmFalse("EqualsFailureNullValue", instance.equals(null));
		  }
	}

	
	private static final FilterPackageInfo FilterPackageInfo = new FilterPackageInfo();
	
	private static class FilterTestClasses implements PojoClassFilter {
	    public boolean include(PojoClass pojoClass) {
	      return !(pojoClass.getSourcePath().contains("/test-classes/") || pojoClass.getClazz().getName().contains("_") || pojoClass.isEnum()) && FilterPackageInfo.include(pojoClass);
	    }
	}
	
}