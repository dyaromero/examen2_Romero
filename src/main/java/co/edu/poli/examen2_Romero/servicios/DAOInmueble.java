package co.edu.poli.examen2_Romero.servicios;

import co.edu.poli.examen2_Romero.modelo.Apartamento;
import co.edu.poli.examen2_Romero.modelo.Casa;
import co.edu.poli.examen2_Romero.modelo.Inmueble;
import co.edu.poli.examen2_Romero.modelo.Propietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOInmueble implements CRUD<Inmueble> {

	@Override
	public String create(Inmueble i) throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();

		String SQL_INSERT_INMUEBLE = "INSERT INTO inmueble (numero, fecha_compra, estado, propietario_id) VALUES (?, ?, ?, ?)";

		String SQL_INSERT_CASA = "INSERT INTO casa (numero, cantidad_pisos) VALUES (?, ?)";

		String SQL_INSERT_APARTAMENTO = "INSERT INTO apartamento (numero, numero_piso) VALUES (?, ?)";

		PreparedStatement psInmueble = null;
		PreparedStatement psHijo = null;

		try {
			con.setAutoCommit(false);

			// INSERT PADRE
			psInmueble = con.prepareStatement(SQL_INSERT_INMUEBLE);
			psInmueble.setInt(1, i.getNumero());
			psInmueble.setString(2, i.getFechaCompra());
			psInmueble.setString(3, i.getEstado());
			psInmueble.setInt(4, i.getPropietario().getId());
			psInmueble.executeUpdate();

			// INSERT HIJO
			if (i instanceof Casa) {
				psHijo = con.prepareStatement(SQL_INSERT_CASA);
				psHijo.setInt(1, i.getNumero());
				psHijo.setInt(2, ((Casa) i).getCantidadPisos());

			} else {
				psHijo = con.prepareStatement(SQL_INSERT_APARTAMENTO);
				psHijo.setInt(1, i.getNumero());
				psHijo.setInt(2, ((Apartamento) i).getNumeroPiso());
			}

			psHijo.executeUpdate();

			con.commit();
			return "OK " + i.getClass().getSimpleName() + " guardado correctamente";

		} catch (Exception e) {
			con.rollback();
			return "ERROR: " + e.getMessage();

		} finally {
			if (psInmueble != null)
				psInmueble.close();
			if (psHijo != null)
				psHijo.close();
			con.setAutoCommit(true);
		}
	}

	@Override
	public <K> Inmueble readone(K numero) throws Exception {

		Connection con = ConexionBD.getInstancia().getConexion();
		int num = (Integer) numero;

		PreparedStatement ps = null;
		ResultSet rs = null;

		// 🔥 BUSCAR CASA
		String SQL_CASA = "SELECT i.numero, i.fecha_compra, i.estado, "
				+ "p.id AS propietario_id, p.nombre, c.cantidad_pisos " + "FROM casa c "
				+ "JOIN inmueble i ON c.numero = i.numero " + "JOIN propietario p ON i.propietario_id = p.id "
				+ "WHERE c.numero = ?";

		try {
			ps = con.prepareStatement(SQL_CASA);
			ps.setInt(1, num);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new Casa(rs.getInt("numero"), rs.getString("fecha_compra"), rs.getString("estado"),
						new Propietario(rs.getInt("propietario_id"), rs.getString("nombre")),
						rs.getInt("cantidad_pisos"));
			}

			rs.close();
			ps.close();

			// 🔥 BUSCAR APARTAMENTO
			String SQL_APTO = "SELECT i.numero, i.fecha_compra, i.estado, "
					+ "p.id AS propietario_id, p.nombre, a.numero_piso " + "FROM apartamento a "
					+ "JOIN inmueble i ON a.numero = i.numero " + "JOIN propietario p ON i.propietario_id = p.id "
					+ "WHERE a.numero = ?";

			ps = con.prepareStatement(SQL_APTO);
			ps.setInt(1, num);
			rs = ps.executeQuery();

			if (rs.next()) {
				return new Apartamento(rs.getInt("numero"), rs.getString("fecha_compra"), rs.getString("estado"),
						new Propietario(rs.getInt("propietario_id"), rs.getString("nombre")), rs.getInt("numero_piso"));
			}

		} finally {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		}

		return null;
	}

	@Override
	public List<Inmueble> readall() {
		return null;
	}
}