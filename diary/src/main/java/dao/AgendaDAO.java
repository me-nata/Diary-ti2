package dao;

import model.Agenda;

import java.sql.*;

public class AgendaDAO {
	private static Connection conexao;

	public AgendaDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "postgres";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

//----------------------------------------------------------O-CRUD------------------------------------------------//

	public boolean inserirAgenda(Agenda agenda) {
		boolean status = false;
		
		try {  
			Statement st = conexao.createStatement();
			
			st.executeUpdate("INSERT INTO agenda (id, texto, data) "
					       + "VALUES (default, '" + agenda.getTexto() + "', '"  
					       + agenda.getData() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarAgenda(Agenda agenda) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE agenda SET texto = '" + agenda.getTexto() + "', data = '" + agenda.getData() + "'"
					   + " WHERE id = " + agendae.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirAgenda(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM agenda WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Agenda[] getAgenda() {
		Agenda[] agendas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM agenda");		
	         if(rs.next()){
	             rs.last();
	             agendas = new Agenda[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                agendas[i] = new Agenda(rs.getInt("id"), rs.getString("texto"), rs.getString("data"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return agendas;
	}

}
