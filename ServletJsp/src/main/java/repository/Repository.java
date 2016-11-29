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

public class Repository {
	
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
	public void insert(Form formulary) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int id=0;
		try {
			preparedStatement = conn.prepareStatement("SELECT * FROM IDIOMA WHERE IDIOMA = ?");
			preparedStatement.setString(1, formulary.getLanguage());
			System.out.println(preparedStatement);
			System.out.println(formulary.getLanguage());
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){	
				preparedStatement = conn.prepareStatement("INSERT INTO IDIOMA (IDIOMA)" +
						"VALUES (?)");
				preparedStatement.setString(1, formulary.getLanguage());
				
				preparedStatement.executeUpdate();
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			
			try {
				preparedStatement = conn.prepareStatement("SELECT * FROM IDIOMA WHERE IDIOMA = ?");
				preparedStatement.setString(1, formulary.getLanguage());
				System.out.println(preparedStatement);
				System.out.println(formulary.getLanguage());
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next()){	
					
					id = resultSet.getInt(1);
					System.out.println(id);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					preparedStatement = conn.prepareStatement("INSERT INTO PAIS (PAIS,IDIOMA)" +
							"VALUES (?,?)");
					preparedStatement.setString(1, formulary.getCountry());
					preparedStatement.setInt(2, id);
					preparedStatement.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
				
			}
			close(preparedStatement);
		
		
		
		manager.close(conn);
	}
	
	// Borrado
	public void delete(int IdIdioma) {
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM IDIOMA WHERE" +
					"IDIDIOMA = ?");
			preparedStatement.setInt(1, IdIdioma);
			
			preparedStatement.executeUpdate();
			
			preparedStatement = conn.prepareStatement("DELETE FROM PAIS WHERE" +
					"IDIOMA = ?");
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
		List<Form> listForm= new ArrayList<Form>();
		Connection conn = manager.open(jdbcUrl);
		ResultSet resultSet = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement("SELECT PAIS.PAIS, IDIOMA.IDIOMA "
					+ "FROM PAIS INNER JOIN IDIOMA ON PAIS.IDIOMA = IDIOMA.IDIDIOMA");
			resultSet = prepareStatement.executeQuery();
			while(resultSet.next()){
				System.out.println(resultSet.getString(1));
				Form formulary = new Form();
				formulary.setCountry(resultSet.getString(1));
				formulary.setLanguage(resultSet.getString(2));
				
				
				listForm.add(formulary);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			try{
			close(resultSet);
			close(prepareStatement);
			}catch(Exception e){}
			
		}
		
		
		manager.close(conn);
		return listForm;
	}
}