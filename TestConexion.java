
import co.edu.poli.examen2_Romero.servicios.ConexionBD;
import java.sql.Connection;

public class TestConexion {
	public static void main(String[] args) {
		try {
			Connection conn = ConexionBD.getInstancia().getConexion();
			System.out.println("✅ CONECTADO A POSTGRESQL");
		} catch (Exception e) {
			System.out.println("❌ ERROR DE CONEXIÓN");
			e.printStackTrace();
		}
	}
}
