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

public class RepositoryLanguage extends Repository{
	
	public int findLanguageId(String language) {
		int idLanguage = 0;
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT IDIDIOMA FROM IDIOMA WHERE IDIOMA = ?");
			prepareStatement.setString(1, language);
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				idLanguage = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return idLanguage;
		

	}
	
	public List<Language> searchLanguage() {
		List<Language> listLanguage = new ArrayList<Language>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("" + "SELECT IdIdioma, IDIOMA FROM IDIOMA");
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(2));
				Language language = new Language();
				language.setIdLanguage(resultSet.getInt(1));
				language.setLanguage(resultSet.getString(2));

				listLanguage.add(language);
			}
			
			close(resultSet);
			close(prepareStatement);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		manager.close(conn);
		return listLanguage;
	}
	
	public void deleteLanguage(int IdIdioma) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM IDIOMA WHERE IDIDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);

			preparedStatement.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		manager.close(conn);
	}
	
	private boolean existLanguage(String language){
		boolean exist = false;
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
		preparedStatement = conn.prepareStatement("SELECT * FROM IDIOMA WHERE IDIOMA = ?");
		preparedStatement.setString(1, language);
		resultSet = preparedStatement.executeQuery();
		
			if (resultSet.next()) {
				exist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
	
	public void insert(Form form) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		int id = 0;
		try {
			if (!existLanguage(form.getLanguage())) {
				preparedStatement = conn.prepareStatement("INSERT INTO IDIOMA (IDIOMA)" + "VALUES (?)");
				preparedStatement.setString(1, form.getLanguage());
				preparedStatement.executeUpdate();
				close(preparedStatement);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		manager.close(conn);
	}
}
