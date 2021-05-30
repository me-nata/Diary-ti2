package dao;

import model.Usuario;

import java.sql.*;

public class UsuarioDAO {
	private  static Connection conexao;
	private static int maxId = 0;                
	
	public static int getMaxId() {
		return maxId;
	}
	
	public UsuarioDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "Usuario";
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

	public static boolean inserirUsuario(Usuario usuario) {
		boolean status = false;
	/*	String s = "";
		boolean b = false;*/
		try {  
			Statement st = conexao.createStatement();
			/*b = usuario.getPremium();
			s = ""+b;*/
			
			st.executeUpdate("INSERT INTO usuario (id, nome, email, senha, idade, premium) "
					       + "VALUES ("+usuario.getId()+ ", '" + usuario.getNome() + "', '"  
					       + usuario.getEmail() + "', '" + usuario.getSenha() + "', " + usuario.getIdade() + ", '" 
					       + usuario.getPremium() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarUsuario(Usuario usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuario SET nome = '" + usuario.getNome() + "', email = '" + usuario.getEmail() 
			           + "', senha = '" + usuario.getSenha() + "', idade = '" + usuario.getIdade() 
			           + "', premium = '" + usuario.getPremium() + "'"
					   + " WHERE id = " + usuario.getId();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	/*public boolean excluirUsuario(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	*/
	public Usuario get(int id) {
		Usuario[] usuarios = getUsuarios(); 
		for (Usuario usuario : usuarios) {
			if (id == usuario.getId()) {
				return usuario;
			}
		}
		return null;
	}
	
	public Usuario[] getUsuarios() {
		Usuario[] usuarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM usuario");		
	         if(rs.next()){
	             rs.last();
	             usuarios = new Usuario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                usuarios[i] = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"),
	                		                  rs.getString("senha"), rs.getInt("idade"), rs.getBoolean("premium"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

}
