
package co.edu.poli.examen2_Romero.servicios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import co.edu.poli.examen2_Romero.modelo.Propietario;

public class DAOPropietario implements CRUD<Propietario> {

	@Override
	public String create(Propietario p) {
		return null;
	}

	@Override
	public <K> Propietario readone(K id) throws Exception {
		return null;
	}

	@Override
	public List<Propietario> readall() throws Exception {
		Connection con = ConexionBD.getInstancia().getConexion();
		List<Propietario> lista = new ArrayList<>();
		String SQL_SELECT_PROPIETARIO = "SELECT p.id AS propietario_id, p.nombre AS propietario_nombre "
				+ "FROM propietario p;";
		PreparedStatement ps = con.prepareStatement(SQL_SELECT_PROPIETARIO);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Propietario p = new Propietario(rs.getInt("propietario_id"), // int, no String como Titular
					rs.getString("propietario_nombre"));
			lista.add(p);
		}
		return lista;
	}
}
