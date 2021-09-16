package pack1;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import pack1.Model.User;

import javax.inject.Inject;
import javax.persistence.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	final static String CFG_FILE = "local.properties";
	final static Properties persistenceProperties = new Properties();

	static {
		InputStream is = null;

		try {
			is = Main.class.getClassLoader().getResourceAsStream(CFG_FILE);
			if (is == null)
				throw new IOException(String.format("File %s not found... ", CFG_FILE));
			persistenceProperties.load(is);
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ignored) {
				}
			}
		}
	}


	@Produces
	//@MySQLDatabase
	public EntityManager createEntityManager() {
		return Persistence
				.createEntityManagerFactory("my-persistence-unit")
				.createEntityManager();
	}


	@Inject
	@PersistenceContext(unitName = "my-app", properties = persistenceProperties)
	private EntityManager entityManager;


	public static void main(String[] args) {
		EntityManagerFactory entityManFactory = Persistence.createEntityManagerFactory("my-app", persistenceProperties);
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("my-app");

		EntityManager entityManager = entityManFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();

		Weld weld = new Weld();
		WeldContainer container = weld.initialize();
		//ImageFileProcessor imageFileProcessor = container.select(ImageFileProcessor.class).get();

		//System.out.println(imageFileProcessor.openFile("file1.png"));

		container.shutdown();

		try {
			transaction.begin();


			User user = new User();
			entityManager.persist(user);

			transaction.commit();

			int a = 1;
		}
		catch (Exception ex)
		{
			int a  = 2;
		}
		finally {
//			if (transaction.isActive())
//				transaction.rollback();
//			entityManager.clear();
			entityManFactory.close();
		}
	}
}
