
package co.edu.poli.examen2_Romero.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.edu.poli.examen2_Romero.modelo.Apartamento;
import co.edu.poli.examen2_Romero.modelo.Casa;
import co.edu.poli.examen2_Romero.modelo.Inmueble;
import co.edu.poli.examen2_Romero.modelo.Propietario;
import co.edu.poli.examen2_Romero.servicios.DAOInmueble;

public class TestDAOInmueble {

	DAOInmueble dao = new DAOInmueble();

	@Test
	void create_casa_y_readone() throws Exception {
		Propietario propietario = new Propietario(1, "Test Propietario");
		Casa casa = new Casa(9001, "2025-12-25", "disponible", propietario, 3);
		String result = dao.create(casa);
		assertTrue(result.contains("guardad"));

		Inmueble i = dao.readone(9001);
		assertNotNull(i);
		assertTrue(i instanceof Casa);
		Casa c = (Casa) i;
		assertEquals(3, c.getCantidadPisos());
	}

	@Test
	void create_apartamento_y_readone() throws Exception {
		Propietario propietario = new Propietario(1, "Test Propietario");
		Apartamento apartamento = new Apartamento(9002, "2025-12-25", "disponible", propietario, 5);
		String result = dao.create(apartamento);
		assertTrue(result.contains("guardad"));

		Inmueble i = dao.readone(9002);
		assertNotNull(i);
		assertTrue(i instanceof Apartamento);
		Apartamento a = (Apartamento) i;
		assertEquals(5, a.getNumeroPiso());
	}

	@Test
	void readone_noExiste() throws Exception {
		Inmueble i = dao.readone(0);
		assertNull(i);
	}
}
