package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Form;

public class RepositoryCountry extends Repository {

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

			close(resultSet);
			close(prepareStatement);
		}

		manager.close(conn);
		return listForm;
	}

}
