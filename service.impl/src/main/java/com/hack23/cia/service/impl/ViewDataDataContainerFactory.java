/*
 * Copyright 2010-2024 James Pether Sörling
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
package com.hack23.cia.service.impl;

import java.io.Serializable;

import com.hack23.cia.service.api.DataContainer;

/**
 * A factory for creating ViewDataDataContainer objects.
 */
@FunctionalInterface
public interface ViewDataDataContainerFactory {

	/**
	 * Creates a new ViewDataDataContainer object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param <I>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @return the data container< t, i d>
	 */
	<T extends Serializable,I extends Serializable> DataContainer<T,I> createDataContainer(Class<T> clazz);
}
