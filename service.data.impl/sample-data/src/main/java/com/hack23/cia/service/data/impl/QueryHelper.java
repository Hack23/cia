/*
 * Copyright 2010-2025 James Pether Sörling
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
package com.hack23.cia.service.data.impl;

import java.util.Locale;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

/**
 * The Class QueryHelper.
 */
public final class QueryHelper {

	/**
	 * Instantiates a new query helper.
	 */
	private QueryHelper() {
		super();
	}

	/**
	 * Equals ignore case if string predicate.
	 *
	 * @param <T>
	 *            the generic type
	 * @param criteriaBuilder
	 *            the criteria builder
	 * @param path
	 *            the path
	 * @param value
	 *            the value
	 * @param property
	 *            the property
	 * @return the predicate
	 */
	@SuppressWarnings("unchecked")
	public static <T> Predicate equalsIgnoreCaseIfStringPredicate(final CriteriaBuilder criteriaBuilder,final Path<T> path,
			final Object value, final SingularAttribute<T, ? extends Object> property) {
		Predicate condition;
		if (value instanceof String strValue) {
			final Expression<String> propertyObject = (Expression<String>) path.get(property);
			condition = criteriaBuilder.equal(criteriaBuilder.upper(propertyObject),strValue.toUpperCase(Locale.ENGLISH));

		} else {
			condition = criteriaBuilder.equal(path.get(property), value);
		}
		return condition;
	}

}
