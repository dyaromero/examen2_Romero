
package co.edu.poli.examen2_Romero.tests.unitaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import co.edu.poli.examen2_Romero.modelo.Apartamento;
import co.edu.poli.examen2_Romero.modelo.Casa;
import co.edu.poli.examen2_Romero.modelo.Inmueble;
import co.edu.poli.examen2_Romero.modelo.Propietario;

public class TestInmueble {

	@Test
	void arrendar_cambiaEstadoYRetornaMensaje() {
		Propietario p = new Propietario(1, "Test");
		Inmueble i = new Casa(101, "2025-12-25", "disponible", p, 3);

		String mensaje = i.arrendar();

		assertEquals("ARRENDADO", i.getEstado());
		assertTrue(mensaje.contains("ARRENDADO"));
		assertTrue(mensaje.contains("101"));
	}

	@Test
	void vender_cambiaEstadoYRetornaMensaje() {
		Propietario p = new Propietario(1, "Test");
		Inmueble i = new Casa(101, "2025-12-25", "disponible", p, 3);

		String mensaje = i.vender();

		assertEquals("VENDIDO", i.getEstado());
		assertTrue(mensaje.contains("VENDIDO"));
		assertTrue(mensaje.contains("101"));
	}

	@Test
	void getters_retornaValoresCorrectos() {
		Propietario p = new Propietario(1, "Test");
		Inmueble i = new Casa(101, "2025-12-25", "disponible", p, 3);

		assertEquals(101, i.getNumero());
		assertEquals("2025-12-25", i.getFechaCompra());
		assertEquals("disponible", i.getEstado());
		assertEquals(p, i.getPropietario());
	}

	@Test
	void setters_modificanValores() {
		Propietario p = new Propietario(1, "Test");
		Propietario nuevo = new Propietario(2, "Nuevo");
		Inmueble i = new Casa(101, "2025-12-25", "disponible", p, 3);

		i.setNumero(999);
		i.setFechaCompra("2030-01-01");
		i.setEstado("vendido");
		i.setPropietario(nuevo);

		assertEquals(999, i.getNumero());
		assertEquals("2030-01-01", i.getFechaCompra());
		assertEquals("vendido", i.getEstado());
		assertEquals(nuevo, i.getPropietario());
	}

	@Test
	void casa_cantidadPisos_correcta() {
		Propietario p = new Propietario(1, "Test");
		Casa casa = new Casa(101, "2025-12-25", "disponible", p, 3);

		assertEquals(3, casa.getCantidadPisos());
		casa.setCantidadPisos(5);
		assertEquals(5, casa.getCantidadPisos());
	}

	@Test
	void apartamento_numeroPiso_correcto() {
		Propietario p = new Propietario(1, "Test");
		Apartamento apto = new Apartamento(102, "2025-12-25", "disponible", p, 7);

		assertEquals(7, apto.getNumeroPiso());
		apto.setNumeroPiso(10);
		assertEquals(10, apto.getNumeroPiso());
	}

	@Test
	void toString_contieneDatos() {
		Propietario p = new Propietario(1, "Test");
		Inmueble i = new Casa(101, "2025-12-25", "disponible", p, 3);

		String texto = i.toString();
		assertTrue(texto.contains("101"));
		assertTrue(texto.contains("2025-12-25"));
		assertTrue(texto.contains("disponible"));
	}
}
