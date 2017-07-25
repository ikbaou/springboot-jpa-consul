package com.example.demo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.inject.spi.CDI;

/**
 * @param <D>
 *            the domain type
 * @param <E>
 *            the entity type
 *
 * @author Michael Markogiannakis
 */
public abstract class AbstractMapper<D extends AbstractDomain, E extends AbstractEntity> {
	
	/**
	 * The index of this class arguments that indicates the parameterized type
	 * of the domain object.
	 */
	private static final int DOMAIN_TYPE_INDEX = 0;

	/**
	 * The index of this class arguments that indicates the parameterized type
	 * of the entity object.
	 */
	private static final int ENTITY_TYPE_INDEX = 1;

	/**
	 * Fills the given entity with the corresponding domain data.
	 *
	 * @param entity
	 *            the target entity object
	 * @param domain
	 *            the source domain object
	 *
	 * @return the entity
	 */
	protected abstract E prepareEntity(E entity, D domain);

	/**
	 * Fills the given domain with the corresponding entity data.
	 *
	 * @param domain
	 *            the target domain object
	 * @param entity
	 *            the source entity object
	 *
	 * @return the entity
	 */
	protected abstract D prepareDomain(D domain, E entity);

	/**
	 * Returns the type of the supported domain.
	 *
	 * @return domain type
	 */
	public Class<D> getDomainType() {
		return ClassUtils.getType(DOMAIN_TYPE_INDEX, getClass());
	}

	/**
	 * Returns the type of the supported entity.
	 *
	 * @return entity type
	 */
	public Class<E> getEntityType() {
		return ClassUtils.getType(ENTITY_TYPE_INDEX, getClass());
	}

	/**
	 * Creates a new instance of the supported domain class.
	 *
	 * @return domain instance
	 */
	protected D newDomainInstance() {
		D instance = null;
		try {
			//important for domain objects to get the specialized implementation from CDI
			instance = CDI.current().select( this.getDomainType() ).get();
		} catch (Exception e) {
			//LOG.error("newDomainInstance", e);
		}
		return instance;
	}

	/**
	 * Creates a new instance of the supported entity class.
	 *
	 * @return entity instance
	 */
	protected E newEntityInstance() {
		E instance = null;
		try {
			instance =this.getEntityType().newInstance();
		} catch (Exception e) {
			//LOG.error("newEntityInstance", e);
		}
		return instance;
	}

	/**
	 * Converts the domain of type D to the corresponding entity of type E.
	 *
	 * @param domain
	 *            the source domain object
	 *
	 * @return the converted entity
	 */
	public E toEntity(final D domain) {
		return domain == null ? null : this.prepareEntity(this.newEntityInstance(), domain);
	}

	/**
	 * Converts the entity of type E to the corresponding domain of type D.
	 *
	 * @param entity
	 *            the source entity object
	 *
	 * @return the converted domain object
	 */
	public D toDomain(final E entity) {
		return entity == null ? null : this.prepareDomain(this.newDomainInstance(), entity);
	}
	
    /**
     * Converts an array of domain objects to a {@link Collection}
     * with the corresponding entities of type E. Note that the
     * underlying implementation of the returned {@link Collection}
     * may be a {@link LinkedHashSet} since this implementation
     * is used in the entities objects.
     *
     * @param domains the source domains
     *
     * @return a {@link Collection} with the converted entities
     */
    public Set<E> toEntities(final D[] domains) {
        final Set<E> result = new LinkedHashSet<E>();
        if (!ValidationUtils.isEmpty(domains)) {
            for (final D value : domains) {
                result.add(this.toEntity(value));
            }
        }
        return result;

    }


    /**
     * 
     * Converts an array of entity objects to a {@link List}
     * with the corresponding domains of type D. Note that the
     * underlying implementation of the returned {@link List}
     * may be a {@link ArrayList} since this implementation
     * is used in the domain objects.
     *       
     * @param entities
     * @return
     */
    public List<D> toDomains(final E[] entities) {
        final List<D> result = new ArrayList<D>();
        if (!ValidationUtils.isEmpty(entities)) {
            for (final E value : entities) {
                result.add(this.toDomain(value));
            }
        }
        return result;
    }
}
