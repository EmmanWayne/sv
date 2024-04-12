package consultas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import clases.administradores;
import conexion.conexion;

public class consultas_administradores extends conexion {

	public boolean insertar(administradores administradores) {
		PreparedStatement ps = null;
		Connection con = conectar();
		String sql = "INSERT INTO administradores (nombre_administrador, dni_administrador) VALUES(?,?)";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, administradores.getNombre_administrador());
			ps.setString(2, administradores.getDni_administrador());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public boolean actualizar(administradores administradores) {
		PreparedStatement ps = null;
		Connection con = conectar();

		String sql = "UPDATE administradores SET nombre_administrador=?, dni_administrador=? WHERE id_administrador=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, administradores.getNombre_administrador());
			ps.setString(2, administradores.getDni_administrador());
			ps.setInt(3, administradores.getId_administrador());
			ps.execute();

			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}

	}


	public boolean buscarAdministrador(administradores administradores) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = conectar();

		String sql = "SELECT * FROM administradores WHERE dni_administrador=?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, administradores.getDni_administrador());
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

}
