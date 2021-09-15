package pack1;

import pack1.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("def1");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction transaction = entityManager.getTransaction();

		try {
			//transaction.begin();


//			User user = new User("user1");
//			entityManager.persist(user);

			//transaction.commit();
		} finally {
//			if (transaction.isActive())
//				transaction.rollback();
//			entityManager.clear();
			entityManagerFactory.close();
		}
	}
}
