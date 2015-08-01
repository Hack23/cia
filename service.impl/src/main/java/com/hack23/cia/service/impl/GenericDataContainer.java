package com.hack23.cia.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.metamodel.SingularAttribute;

import com.hack23.cia.service.api.DataContainer;
import com.hack23.cia.service.data.api.DataViewer;

/**
 * The Class GenericDataContainer.
 *
 * @param <T>
 *            the generic type
 * @param <ID>
 *            the generic type
 */
final class GenericDataContainer<T extends Serializable, ID extends Serializable> implements
DataContainer<T, ID> {



	/** The data proxy. */
	private final DataViewer dataProxy;

	/** The clazz. */
	private final Class<T> clazz;

	/**
	 * Instantiates a new generic data container.
	 *
	 * @param clazz
	 *            the clazz
	 * @param dataViewer
	 *            the data viewer
	 */
	public GenericDataContainer(final Class<T> clazz, final DataViewer dataViewer) {
		this.clazz = clazz;
		dataProxy = dataViewer;
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAll()
	 */
	@Override
	public List<T> getAll() {
		return dataProxy.getAll(clazz);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#getAllBy(javax.persistence.metamodel.SingularAttribute, java.lang.Object)
	 */
	@Override
	public List<T> getAllBy(
			final SingularAttribute<T, ? extends Object> property,
			final Object value) {
		return dataProxy.findListByProperty(clazz, property, value);
	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#load(java.io.Serializable)
	 */
	@Override
	public T load(final ID id) {
		return dataProxy.load(clazz, id);

	}

	/* (non-Javadoc)
	 * @see com.hack23.cia.service.api.DataContainer#findListByProperty(java.lang.Object[], javax.persistence.metamodel.SingularAttribute[])
	 */
	@Override
	public List<T> findListByProperty(final Object[] values,
			final SingularAttribute<T, ? extends Object>... properties) {
		return dataProxy.findListByProperty(clazz, values, properties);
	}

	@Override
	public <T, V> T findByQueryProperty(final Class<T> clazz,
			final SingularAttribute<T, ? extends Object> property,
			final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataProxy.findByQueryProperty(clazz, property, clazz2, property2, value);
	}

	@Override
	public <T, V> List<T> findListByEmbeddedProperty(final Class<T> clazz,
			final SingularAttribute<T, V> property,
			final Class<V> clazz2,
			final SingularAttribute<V, ? extends Object> property2, final Object value) {
		return dataProxy.findListByEmbeddedProperty(clazz, property, clazz2, property2, value);
	}
}