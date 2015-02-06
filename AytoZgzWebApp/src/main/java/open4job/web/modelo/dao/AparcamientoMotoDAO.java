package open4job.web.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import open4job.web.modelo.vo.AparcamientoMotoVO;

public class AparcamientoMotoDAO extends GenericDAO {

	public static final Logger logger = Logger
			.getLogger(AparcamientoMotoDAO.class.getName());

	public AparcamientoMotoDAO(String driver, String url, String user,
			String password) {
		super(driver, url, user, password);
	}

	// listado de aparcamiento de motos
	public List<AparcamientoMotoVO> getLstAparcamientoMoto() {
		List<AparcamientoMotoVO> lsitAparcamientoMotos = new ArrayList<AparcamientoMotoVO>();

		AparcamientoMotoVO motoVO = null;
		String query = "SELECT * FROM APARCAMIENTOSMOTOS";
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {

			this.abrirConexion();
			// stmt = connection.createStatement();
			stmt = connection.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				motoVO = new AparcamientoMotoVO(rs.getDouble("latitud"),
						rs.getDouble("longitud"), rs.getString("titulo"),
						rs.getString("icono"), rs.getString("descripcion"),
						rs.getString("last_update"), rs.getInt("plazas"),
						rs.getInt("id"));

				lsitAparcamientoMotos.add(motoVO);
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQLException : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE,
					"ClassNotFoundException : " + e.getMessage());
		}

		finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}

		}

		this.cerrarConexion();

		return lsitAparcamientoMotos;
	}

	// Detalles de Aparcamiento de motos

	public AparcamientoMotoVO getDetailAparcamientoMoto(int motoId) {

		AparcamientoMotoVO aptoMoto = null;
		String query = "SELECT * FROM APARCAMIENTOSMOTOS WHERE id=?";
		ResultSet rset = null;
		PreparedStatement stmt = null;

		try {
			this.abrirConexion();
			// stmt = connection.createStatement();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, motoId);
			rset = stmt.executeQuery();

			while (rset.next()) {
				AparcamientoMotoVO moto = new AparcamientoMotoVO(
						rset.getDouble("latitud"), rset.getDouble("longitud"),
						rset.getString("titulo"), rset.getString("icono"),
						rset.getString("descripcion"),
						rset.getString("last_update"), rset.getInt("plazas"),
						rset.getInt("id"));
				aptoMoto = moto;
			}
			stmt.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQLException : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE,
					"ClassNotFoundException : " + e.getMessage());

		} finally {

			if (rset != null) {
				try {
					rset.close();
				} catch (Exception e) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
			}

		}
		this.cerrarConexion();
		return aptoMoto;

	}
/*
	public void insertAparcamientoMoto() {

		// NUMERO_ID: para no tener que cambiar todos los datos del insert
		int NUMERO_ID = 777;

		String query = "INSERT INTO APARCAMIENTOSMOTOS"
				+ "(id, titulo, descripcion, plazas, last_update, longitud, latitud, icono) "
				+ "VALUES(?,?,?,?,?,?,?,?)";
		ResultSet rset = null;
		PreparedStatement stmt = null;
		Date fecha = new Date();

		int id = NUMERO_ID;
		String titulo = "Titulo" + NUMERO_ID;
		String descripcion = "Desc" + NUMERO_ID;
		int plazas = NUMERO_ID;
		String lastUpdate = fecha.getDate() + "/" + fecha.getMonth() + "/"
				+ fecha.getYear();
		Double latitud = (double) NUMERO_ID;
		Double longitud = (double) NUMERO_ID;
		String icono = "Icono" + NUMERO_ID;

		try {
			this.abrirConexion();
			stmt = connection.prepareStatement(query);
			stmt.setInt(1, id);
			stmt.setString(2, titulo);
			stmt.setString(3, descripcion);
			stmt.setInt(4, plazas);
			stmt.setString(5, lastUpdate);
			stmt.setDouble(6, latitud);
			stmt.setDouble(7, longitud);
			stmt.setString(8, icono);

			stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "SQLException : " + e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE,
					"ClassNotFoundException : " + e.getMessage());
		}

		this.cerrarConexion();
	}
*/
}
