
package co.edu.poli.examen2_Romero.tests.integracion;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.edu.poli.examen2_Romero.modelo.Propietario;
import co.edu.poli.examen2_Romero.servicios.DAOPropietario;
import java.util.List;

public class TestDAOPropietario {

	DAOPropietario dao = new DAOPropietario();

	@Test
	void readall_retorna_lista_no_nula() throws Exception {
		List<Propietario> lista = dao.readall();
		assertNotNull(lista);
	}

	@Test
	void readall_elementos_tienen_datos() throws Exception {
		List<Propietario> lista = dao.readall();
		if (!lista.isEmpty()) {
			Propietario p = lista.get(0);
			assertTrue(p.getId() > 0);
			assertNotNull(p.getNombre());
			assertFalse(p.getNombre().isBlank());
		}
	}
}
