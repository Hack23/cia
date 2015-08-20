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
package com.hack23.cia.service.data.impl.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Hibernate;
import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;

/**
 * The Class LoadHelper.
 */
public class LoadHelper {

	/**
	 * Recursive initliaze.
	 *
	 * @param <T>
	 *            the generic type
	 * @param obj
	 *            the obj
	 * @return the t
	 */
	public static <T> T recursiveInitliaze(final T obj) {
		if (obj != null) {

			final Set<Object> dejaVu = Collections.newSetFromMap(new IdentityHashMap<Object, Boolean>());
			try {
				recursiveInitliaze(obj, dejaVu);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				handleReflectionException(e);
			}
		}
		return obj;
	}

	/**
	 * Recursive initliaze.
	 *
	 * @param obj
	 *            the obj
	 * @param dejaVu
	 *            the deja vu
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InvocationTargetException
	 *             the invocation target exception
	 * @throws NoSuchMethodException
	 *             the no such method exception
	 */
	private static void recursiveInitliaze(final Object obj, final Set<Object> dejaVu) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (dejaVu.contains(obj)) {
			return;
		} else {
			dejaVu.add(obj);

			if (!Hibernate.isInitialized(obj)) {
				Hibernate.initialize(obj);
			}

			if ( obj instanceof HibernateProxy || obj instanceof PersistentCollection ) {

				final PropertyDescriptor[] properties = PropertyUtils.getPropertyDescriptors(obj);
				for (final PropertyDescriptor propertyDescriptor : properties) {

					if (PropertyUtils.getReadMethod( propertyDescriptor) != null) {

						final Object origProp = PropertyUtils.getProperty(obj, propertyDescriptor.getName());


						if (origProp != null) {
							recursiveInitliaze(origProp, dejaVu);
						}
						if (origProp instanceof Collection && origProp != null) {
							for (final Object item : (Collection<?>) origProp) {
								recursiveInitliaze(item, dejaVu);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Handle reflection exception.
	 *
	 * @param ex
	 *            the ex
	 */
	private static void handleReflectionException(final Exception ex) {
		if (ex instanceof NoSuchMethodException) {
			throw new IllegalStateException("Method not found: " + ex.getMessage());
		}
		if (ex instanceof IllegalAccessException) {
			throw new IllegalStateException("Could not access method: " + ex.getMessage());
		}
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		throw new UndeclaredThrowableException(ex);
	}

}
