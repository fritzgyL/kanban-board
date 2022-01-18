package repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDaoJpaImpl<T, PK extends Serializable> implements GenericDao<T, PK>, RefreshingDao<T, PK> {

	protected Class<T> entityClass;

	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDaoJpaImpl() {
		this.entityManager = EntityManagerHelper.getEntityManager();
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T save(T t) {
		entityManager.getTransaction().begin();
		this.entityManager.persist(t);
		entityManager.getTransaction().commit();
		return t;
	}

	@Override
	public T read(PK id) {
		return this.entityManager.find(entityClass, id);
	}

	@Override
	public T update(T t) {
		entityManager.getTransaction().begin();
		T managed = this.entityManager.merge(t);
		entityManager.getTransaction().commit();
		return managed;
	}

	@Override
	public void delete(T t) {
		this.entityManager.merge(t);
		entityManager.getTransaction().begin();
		this.entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	@Override
	@Transactional
	public void refresh(T t) {
		entityManager.refresh(t);
	}

}