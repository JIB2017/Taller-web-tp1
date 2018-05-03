package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;
import java.util.List;
import org.hibernate.Session;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;

public class TestFarmaciaPorBarrio extends SpringTest{

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
		
		Barrio barrio1 = new Barrio();
		Barrio barrio2 = new Barrio();
		Barrio barrio3 = new Barrio();
		Barrio barrio4 = new Barrio();
		
		Session session = getSession();
		
		barrio1.setNombre("Ituzaingó");
		barrio2.setNombre("Castelar");
		barrio3.setNombre("Morón");
		barrio4.setNombre("Haedo");
		
		direccion1.setBarrio(barrio1);
		direccion2.setBarrio(barrio2);
		direccion3.setBarrio(barrio3);
		direccion4.setBarrio(barrio4);
		
		farmacia1.setDireccion(direccion1);
		farmacia2.setDireccion(direccion2);
		farmacia3.setDireccion(direccion3);
		farmacia4.setDireccion(direccion4);
		
		session.save(farmacia1);
		session.save(farmacia2);
		session.save(farmacia3);
		session.save(farmacia4);
		
		session.save(direccion1);
		session.save(direccion2);
		session.save(direccion3);
		session.save(direccion4);
		
		session.save(barrio1);
		session.save(barrio2);
		session.save(barrio3);
		session.save(barrio4);
		
		List<Farmacia> farmacias = session.createCriteria(Farmacia.class)
				.createAlias("direccion", "dire")
				.createAlias("dir.barrio", "Ituzaingó")
				.add(Restrictions.eq("barrio", "Ituzaingó")).list();
		
		assertThat(farmacias).hasSize(1);
		
		for (Farmacia farm : farmacias) {
			assertEquals("Ituzaingó", farm.getDireccion().getBarrio().getNombre());
		}
	}
}






