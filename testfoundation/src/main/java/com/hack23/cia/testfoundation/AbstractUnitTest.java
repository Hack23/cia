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

import static com.openpojo.validation.utils.ToStringHelper.safeToString;

import java.util.List;

import com.openpojo.log.LoggerFactory;
import com.openpojo.random.RandomFactory;
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.PojoClassFilter;
import com.openpojo.reflection.PojoField;
import com.openpojo.reflection.PojoMethod;
import com.openpojo.reflection.filters.FilterPackageInfo;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.affirm.Affirm;
import com.openpojo.validation.rule.Rule;
import com.openpojo.validation.rule.impl.EqualsAndHashCodeMatchRule;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.Tester;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.openpojo.validation.utils.SameInstanceIdentityHandlerStub;
import com.openpojo.validation.utils.ValidationHelper;

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
		final List<PojoClass> pojoClassesRecursively = PojoClassFactory.getPojoClassesRecursively(string,
				new FilterTestClasses());

		final Validator validator = ValidatorBuilder.create().with(new SetterMustExistRule(), new GetterMustExistRule())
				.with(new SetterTester(), new GetterTester()).with(new InvokeToStringTester())
				.with(new InvokeHashcodeTester()).with(new DummyEqualsTester()).with(new WithTester())
				.with(new ObjectFactoryTester()).with(new EqualsAndHashCodeMatchRule()).build();
		validator.validate(pojoClassesRecursively);

		final List<PojoClass> enumClassesRecursively = PojoClassFactory.getPojoClassesRecursively(string,
				new FilterNonEnumClasses());

		final Validator enumValidator = ValidatorBuilder.create().with(new EnumTester()).build();
		enumValidator.validate(enumClassesRecursively);

		return true;
	}

	public final boolean checkAllDtoClassesInPackage(final String string) {

		final List<PojoClass> pojoClassesRecursively = PojoClassFactory.getPojoClassesRecursively(string,
				new FilterTestClasses());

		final Validator validator = ValidatorBuilder.create().with(new GetterMustExistRule()).with(new GetterTester())
				.with(new EqualsAndHashCodeMatchRule()).with(new InvokeToStringTester())
				.with(new InvokeHashcodeTester()).with(new DummyEqualsTester()).with(new WithTester()).build();
		validator.validate(pojoClassesRecursively);
		return true;
	}

	public class InvokeToStringTester implements Tester {
		@Override
		public void run(final PojoClass pojoClass) {
			final Object instance = RandomFactory.getRandomValue(pojoClass.getClazz());
			Affirm.affirmNotNull("toStringFailure", instance.toString());
		}
	}

	public class InvokeHashcodeTester implements Tester {
		@Override
		public void run(final PojoClass pojoClass) {
			final Object instance = RandomFactory.getRandomValue(pojoClass.getClazz());
			Affirm.affirmFalse("hashCodeFailure", 0 == instance.hashCode());
		}
	}

	public class DummyEqualsTester implements Tester {
		@Override
		public void run(final PojoClass pojoClass) {
			final Object instance = randomValues(pojoClass);

			final Object instance2 = randomValues(pojoClass);

			instance.equals(instance2);
			// Affirm.affirmFalse("EqualsFailureSameInstanceDontMatch:" + instance + ":" +
			// instance2, instance.equals(instance2));
		}

		private Object randomValues(final PojoClass pojoClass) {
			final Object instance = RandomFactory.getRandomValue(pojoClass.getClazz());
			randomValues(instance, pojoClass);

			return instance;
		}

		private void randomValues(final Object instance, final PojoClass pojoClass) {
			if (pojoClass == null) {
				return;
			}

			for (final PojoField fieldEntry : pojoClass.getPojoFields()) {
				if (fieldEntry.hasSetter()) {
					final Object value;

					value = RandomFactory.getRandomValue(fieldEntry);
					fieldEntry.invokeSetter(instance, value);
				}
			}
			randomValues(instance, pojoClass.getSuperClass());
		}
	}

	public class WithTester implements Tester {

		@Override
		public void run(final PojoClass pojoClass) {
			final Object classInstance = ValidationHelper.getBasicInstance(pojoClass);
			for (final PojoField fieldEntry : pojoClass.getPojoFields()) {

				if (fieldEntry.hasSetter()) {
					final String name = fieldEntry.getName();

					final List<PojoMethod> methods = pojoClass.getPojoMethods();

					for (final PojoMethod pojoMethod : methods) {
						if (("with" + name).equalsIgnoreCase(pojoMethod.getName())) {

							final Object value = RandomFactory.getRandomValue(fieldEntry);

							SameInstanceIdentityHandlerStub.registerIdentityHandlerStubForValue(value);
							LoggerFactory.getLogger(this.getClass()).debug("Testing Field [{0}] with value [{1}]",
									fieldEntry, safeToString(value));

							pojoMethod.invoke(classInstance, value);

							Affirm.affirmEquals("With test failed, non equal value for field=[" + fieldEntry + "]",
									value, fieldEntry.get(classInstance));

							SameInstanceIdentityHandlerStub.unregisterIdentityHandlerStubForValue(value);
						}
					}
				}
			}
		}
	}

	public class ObjectFactoryTester implements Tester {
		@Override
		public void run(final PojoClass pojoClass) {
			final Object classInstance = ValidationHelper.getBasicInstance(pojoClass);

			if (classInstance.getClass().getSimpleName().startsWith("ObjectFactory")) {
				final List<PojoMethod> methods = pojoClass.getPojoMethods();

				for (final PojoMethod pojoMethod : methods) {
					if (pojoMethod.getPojoParameters().isEmpty()) {
						pojoMethod.invoke(classInstance);
					}
				}
			}
		}
	}

	public class EnumTester implements Rule {

		@Override
		public void evaluate(final PojoClass pojoClass) {
			if (pojoClass.isEnum()) {

				final Object[] enumConstants = pojoClass.getClazz().getEnumConstants();

				final PojoMethod valueMethod = findMethod(pojoClass, "value");
				final PojoMethod fromValueMethod = findMethod(pojoClass, "fromValue");
				if (valueMethod != null && fromValueMethod != null) {
					for (final Object object : enumConstants) {
						fromValueMethod.invoke(object, valueMethod.invoke(object));
					}

					try {
						fromValueMethod.invoke(enumConstants[0], "NoValidEnumStringValue");
					} catch (final RuntimeException e) {
						LoggerFactory.getLogger(this.getClass()).debug("Expected exception [{0}]", e);
					}
				}
			}
		}
	}

	private static PojoMethod findMethod(final PojoClass pojoClass, final String name) {
		final List<PojoMethod> methods = pojoClass.getPojoMethods();

		for (final PojoMethod pojoMethod : methods) {
			if (name.equalsIgnoreCase(pojoMethod.getName())) {
				return pojoMethod;
			}
		}
		return null;
	}

	private static final FilterPackageInfo FilterPackageInfo = new FilterPackageInfo();

	private static class FilterTestClasses implements PojoClassFilter {
		@Override
		public boolean include(final PojoClass pojoClass) {
			return !(pojoClass.getSourcePath().contains("/test-classes/")
					|| pojoClass.getClazz().getName().contains("_") || pojoClass.isEnum() || pojoClass.isAbstract())
					&& FilterPackageInfo.include(pojoClass);
		}
	}

	private static class FilterNonEnumClasses implements PojoClassFilter {
		@Override
		public boolean include(final PojoClass pojoClass) {
			return pojoClass.isEnum() && FilterPackageInfo.include(pojoClass);
		}
	}

}