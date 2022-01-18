package repository;

import java.util.List;

import entity.Card;

public class CardRepository extends GenericDaoJpaImpl<Card, Long> {

	public List<Card> findAll() {
		return EntityManagerHelper.getEntityManager().createQuery("select c from card c", Card.class).getResultList();
	}

}