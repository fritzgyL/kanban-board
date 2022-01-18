package repository;

import java.util.List;

import entity.Tag;

public class TagRepository extends GenericDaoJpaImpl<Tag, Long> {
	public List<Tag> findAll() {
		return EntityManagerHelper.getEntityManager().createQuery("select t from board t", Tag.class).getResultList();
	}

}