import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.h2.engine.User;

import java.sql.*;

public class Servlet extends HttpServlet{
	
	private ConnectionManager manager = new ConnectionH2();
	private static final String jdbcUrl = "jdbc:h2:file:./src/main/resources/test";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Pais pais = assemblePaisFromRequest(req);
		try {
			if(existeEnBaseDeDatos(pais)){
				actualizar(pais);
			}else{
				insertar(pais);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		redirect(req,resp);	
	}

	private void insertar(Pais pais) {
		Connection conn = manager.open(jdbcUrl);
		// codigo sql que  inserta un pais
		manager.close(conn);
	}

	private void actualizar(Pais pais) {
		Connection conn = manager.open(jdbcUrl);
		// codigo sql que  inserta un pais
		manager.close(conn);
	}

	private boolean existeEnBaseDeDatos(Pais pais) throws SQLException{
		Connection conn = manager.open(jdbcUrl);
		PreparedStatement prepareStatement;
		prepareStatement = conn.prepareStatement("SELECT * FROM PAIS WHERE PAIS = '" + pais.getPais()+"'");
		ResultSet rs = prepareStatement.executeQuery();
		
		
		if (rs.next()) {
			// En caso de que exista (rs.next() sea true) updateamos el registro
			System.out.println("Update");
			prepareStatement.close();
			
			// Creamos una SQL de update de los nuevos datos 
			prepareStatement = conn.prepareStatement("UPDATE PAIS set IDIOMA = " + pais.getIdioma() + ",IDIOMA = "
					+ pais.getIdioma()  + " where PAIS = '" + pais.getPais() + "'");
			
			// Lo ejecutamos y cerramos la conexion
			prepareStatement.execute();
			prepareStatement.close();
			System.out.println("Updated");
			manager.close(conn);
			return true;
			
		} else {
			// En caso de que no exista creamos un registro nuevo
			System.out.println("Insert");
			prepareStatement.close();
			// Creamos una SQL de inserccion en el fichero
			String sql = "INSERT INTO PAIS(PAIS,IDIOMA) VALUES('" + pais.getPais() + "','"
					+ pais.getIdioma() + "')";
			try {
				// Ejecutamos la consulta y cerramos la conexion
				prepareStatement = conn.prepareStatement(sql);
				prepareStatement.execute();
				prepareStatement.close();
				System.out.println("insertado");
			} catch (SQLException e) {

				throw new RuntimeException(e);
			}
			try {
				prepareStatement.close();
			} catch (SQLException e) {

			}
		}
		manager.close(conn);
		return false;
	}

	private Pais assemblePaisFromRequest(HttpServletRequest req) {
		Pais pais = new Pais();
		String pais = req.getParameter("pais");
		String idioma = req.getParameter("idioma");
		String idioma2 = req.getParameter("idioma2");
		
		pais.setPais(pais);
		pais.setIdioma(idioma);
		pais.setIdioma2(idioma2);
		
		
		return pais;
	}

	private void closeConnection(Connection connectToDatabase) {
		try {
			connectToDatabase.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void executeSqlQuery(Connection connectToDatabase, String sql)  {
		PreparedStatement prepareStatement;
		try {
			prepareStatement = connectToDatabase.prepareStatement(sql);
			prepareStatement.execute();
			prepareStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Connection connectToDatabase()  {
		Connection conn ;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/test.mv", "sa", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return conn;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		redirect(req,resp);
	}
	

	private void redirect(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/fin.jsp");
		dispatcher.forward(req,resp);
	}
}