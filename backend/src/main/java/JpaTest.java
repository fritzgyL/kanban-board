import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Board;
import model.Card;
import model.Section;
import repository.BoardRepository;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createEmployees();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		//test.listEmployees();

		manager.close();
		System.out.println(".. done");
	}

	private void createEmployees() {
		Board board = new Board("My board");
		Section section = new Section("To do");
		Card card = new Card("create login system");

		board.addSection(section);
		section.addCard(card);
		
		manager.persist(board);
		manager.persist(section);
		manager.persist(card);
		
		
//		int numOfEmployees = manager.createQuery("Select a From Employee a", Employee.class).getResultList().size();
//		if (numOfEmployees == 0) {
//			Department department = new Department("java");
//			manager.persist(department);
//
//			manager.persist(new Employee("Jakab Gipsz", department));
//			manager.persist(new Employee("Captain Nemo", department));
//
//		}
	}

	private void listEmployees() {
//		List<Employee> resultList = manager.createQuery("Select a From Employee a", Employee.class).getResultList();
//		System.out.println("num of employess:" + resultList.size());
//		for (Employee next : resultList) {
//			System.out.println("next employee: " + next);
//		}

	}
}
