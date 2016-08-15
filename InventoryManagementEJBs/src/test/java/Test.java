
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.test.entity.Product;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("InventoryManagementEJBs");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Product product = new Product();
		product.setName("product333");
		product.setThreshold(0);
		product.setQuantity(1);
		product.setExpiryDate(new Date());
		product.setStatus((byte) 1);
		product.setWeight(22);
		product.setThresholdAlarm((byte) 1);
		em.persist(product);
		em.getTransaction().commit();

	}

}
