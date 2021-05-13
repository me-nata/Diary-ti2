package service;

//import java.io.IOException;

import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;


public class UsuarioService {

	private UsuarioDAO usuarioDAO;

	public UsuarioService() {
		//try {
			usuarioDAO = new UsuarioDAO( );
		/*} catch (IOException e) {
			System.out.println(e.getMessage());
		}*/
	}

	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		String email = request.queryParams("email");
		String senha = request.queryParams("senha");
		int idade = Integer.parseInt(request.queryParams("idade"));
		
                boolean premium = false;
                
               int id = UsuarioDAO.getMaxId() + 1;
               
		Usuario usuario = new Usuario(id, nome, email, senha, idade, premium);

		UsuarioDAO.inserirUsuario(usuario);

		response.status(201); // 201 Created
		return id;
	}

	/*public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Usuario usuario = (Usuario) UsuarioDAO.get(id);
		
		if (usuario != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<produto>\n" + 
            		"\t<id>" + produto.getId() + "</id>\n" +
            		"\t<descricao>" + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + produto.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + produto.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + produto.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade>" + produto.getDataValidade() + "</validade>\n" +
            		"</produto>\n";
        } else {
            response.status(404); // 404 Not found
            return "Produto " + id + " não encontrado.";
        }

	}*/

	public Object updateSenha(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Usuario usuario = (Usuario) usuarioDAO.get(id);

        if (usuario != null) {
        	//usuario.setNome(request.queryParams("nome"));
        	//usuario.setEmail(request.queryParams("email"));
        	usuario.setSenha(request.queryParams("senha"));
        	//usuario.setIdade(Integer.parseInt(request.queryParams("idade")));
        	//usuario.setPremium(LocalDateTime.parse(request.queryParams("premium")));

        	usuarioDAO.atualizarUsuario(usuario);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }

	}
	
	public Object updatePremium(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Usuario usuario = (Usuario) usuarioDAO.get(id);

        if (usuario != null) {
        	//usuario.setNome(request.queryParams("nome"));
        	//usuario.setEmail(request.queryParams("email"));
        	//usuario.setSenha(request.queryParams("senha"));
        	//usuario.setIdade(Integer.parseInt(request.queryParams("idade")));
        	usuario.setPremium(Boolean.parseBoolean(request.queryParams("premium")));

        	usuarioDAO.atualizarUsuario(usuario);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Usuario não encontrado.";
        }

	}

	/*public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Produto produto = (Produto) produtoDAO.get(id);

        if (produto != null) {

            produtoDAO.remove(produto);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Produto não encontrado.";
        }
	}*/

	/*public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<produtos type=\"array\">");
		for (Produto produto : produtoDAO.getAll()) {
			returnValue.append("\n<produto>\n" + 
            		"\t<id>" + produto.getId() + "</id>\n" +
            		"\t<descricao>" + produto.getDescricao() + "</descricao>\n" +
            		"\t<preco>" + produto.getPreco() + "</preco>\n" +
            		"\t<quantidade>" + produto.getQuant() + "</quantidade>\n" +
            		"\t<fabricacao>" + produto.getDataFabricacao() + "</fabricacao>\n" +
            		"\t<validade>" + produto.getDataValidade() + "</validade>\n" +
            		"</produto>\n");
		}
		returnValue.append("</produtos>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}*/
}
