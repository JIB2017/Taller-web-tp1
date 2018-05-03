package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;

public class TestFarmaciaPorCalle extends SpringTest{

	@Test
	@Transactional
	@Rollback(true)
	public void test() {
		
		Farmacia farmacia1 = new Farmacia();
		Farmacia farmacia2 = new Farmacia();
		Farmacia farmacia3 = new Farmacia();
		Farmacia farmacia4 = new Farmacia();
		
		Direccion direccion1 = new Direccion();
		Direccion direccion2 = new Direccion();
		Direccion direccion3 = new Direccion();
		Direccion direccion4 = new Direccion();
		
		Session session = getSession();
		
		direccion1.setCalle("Avenida Siempreviva");
		direccion2.setCalle("Avenida Rosario");
		direccion3.setCalle("Avenida Rivadavia");
		direccion4.setCalle("Avenida de Mayo");
		
		farmacia1.setDireccion(direccion1);
		farmacia2.setDireccion(direccion2);
		farmacia3.setDireccion(direccion3);
		farmacia4.setDireccion(direccion4);
		
		session.save(direccion1);
		session.save(direccion2);
		session.save(direccion3);
		session.save(direccion4);
		
		session.save(farmacia1);
		session.save(farmacia2);
		session.save(farmacia3);
		session.save(farmacia4);
		
		List<Farmacia> farmacias = session.createCriteria(Farmacia.class)
				.createAlias("direccion", "dire")
				.add(Restrictions.eq("calle", "Avenida Rosario")).list();
		
		assertThat(farmacias).hasSize(1);
		
		for (Farmacia farm : farmacias) {
			assertEquals("Avenida Rosario", farm.getDireccion().getCalle());
		}
	}
}