package ar.edu.unlam.tallerweb1.modelo;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.SpringTest;

public class TestFarmaciaPorTurno extends SpringTest{
	
	@Test
	@Transactional
	@Rollback(true)
	public void test() {
		
		Farmacia farmacia1 = new Farmacia();
		Farmacia farmacia2 = new Farmacia();
		Farmacia farmacia3 = new Farmacia();
		Farmacia farmacia4 = new Farmacia();
		
		Session session = getSession();
		
		farmacia1.setDiaDeTurno("Lunes");
		farmacia2.setDiaDeTurno("Martes");
		farmacia3.setDiaDeTurno("Miércoles");
		farmacia4.setDiaDeTurno("Martes");
		
		session.save(farmacia1);
		session.save(farmacia2);
		session.save(farmacia3);
		session.save(farmacia4);
		
		List<Farmacia> farmacias = session.createCriteria(Farmacia.class)
				.add(Restrictions.eq("TurnoDeFarmacia", "Martes")).list();
		
		assertThat(farmacias).hasSize(2);
		
		for (Farmacia farm : farmacias) {
			assertEquals("Martes",farm.getDiaDeTurno());
		}
	}
}