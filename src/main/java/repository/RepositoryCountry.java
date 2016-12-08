package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionH2;
import connection.ConnectionManager;
import model.Form;
import model.Language;

public class RepositoryCountry {

	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";
	ConnectionManager manager = new ConnectionH2();

	private void close(PreparedStatement prepareStatement) {
		try {
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private void close(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// insert
	public void insert(Form form, int id) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = conn.prepareStatement("INSERT INTO PAIS (PAIS,IDIOMA)" + "VALUES (?,?)");
			preparedStatement.setString(1, form.getCountry());
			preparedStatement.setInt(2, id);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		close(preparedStatement);
		manager.close(conn);
	}

	

	
	
	public void deleteCountry(int IdIdioma) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {

			preparedStatement = conn.prepareStatement("DELETE FROM PAIS WHERE IDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		manager.close(conn);
	}

	// Listado
	public List<Form> searchAll() {
		List<Form> listForm = new ArrayList<Form>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT PAIS.PAIS, IDIOMA.IDIOMA "
					+ "FROM PAIS INNER JOIN IDIOMA ON PAIS.IDIOMA = IDIOMA.IDIDIOMA");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				Form formulary = new Form();
				formulary.setCountry(resultSet.getString(1));
				formulary.setLanguage(resultSet.getString(2));

				listForm.add(formulary);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				close(resultSet);
				close(prepareStatement);
			} catch (Exception e) {
			}

		}

		manager.close(conn);
		return listForm;
	}

	
}
