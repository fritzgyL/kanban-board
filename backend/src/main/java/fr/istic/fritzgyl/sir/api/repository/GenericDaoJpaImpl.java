package fr.istic.fritzgyl.sir.api.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

public abstract class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK> {

	protected Class<T> entityClass;

	// protected EntityManager entityManager ;

	@SuppressWarnings("unchecked")
	public GenericDaoJpaImpl() {
		// this.entityManager = EntityManagerHelper.getEntityManager();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T save(T t) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return t;
	}

	@Override
	public T read(PK id) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		T t = em.find(entityClass, id);
		if (t != null) {
			em.refresh(t);
		}
		EntityManagerHelper.closeEntityManager();
		return t;
	}

	@Override
	public T update(T t) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		T res = em.merge(t);
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
		return res;
		// return this.entityManager.merge(t);
	}

	@Override
	public void delete(T t) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.contains(t) ? t : em.merge(t));
		em.getTransaction().commit();
		EntityManagerHelper.closeEntityManager();
//           t = this.entityManager.merge(t);
//           this.entityManager.remove(t);
	}
}