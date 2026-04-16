package co.edu.poli.examen2_Romero.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import io.github.cdimascio.dotenv.Dotenv;

public class ConexionBD {

	private static ConexionBD instancia;
	private Connection conexion;
	private String url, user, pass;

	private ConexionBD() throws Exception {
		Dotenv dotenv = Dotenv.load();
		url = dotenv.get("DB_URL");
		user = dotenv.get("DB_USER");
		pass = dotenv.get("DB_PASSWORD");

		if (url == null || user == null || pass == null) {
			throw new RuntimeException("Faltan variables en el .env");
		}

		Class.forName("org.postgresql.Driver");
		conexion = DriverManager.getConnection(url, user, pass);
	}

	public static ConexionBD getInstancia() throws Exception {
		if (instancia == null) {
			instancia = new ConexionBD();
		}
		return instancia;
	}

	// ✅ Nuevo método para forzar reconexión
	public void reconectar() throws Exception {
		try {
			if (conexion != null && !conexion.isClosed()) {
				conexion.close();
			}
		} catch (Exception ignored) {
		}
		conexion = DriverManager.getConnection(url, user, pass);
	}

	public Connection getConexion() throws Exception {
		if (conexion == null || conexion.isClosed()) {
			reconectar();
		}
		return conexion;
	}
}