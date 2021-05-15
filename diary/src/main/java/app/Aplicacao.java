package app;

import static spark.Spark.*;

import service.UsuarioService;

public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	
    public static void main(String[] args) {
        //port(6789);

        post("/usuario", (request, response) -> usuarioService.add(request, response));

        //get("/produto/:id", (request, response) -> usuarioService.get(request, response));

        get("/usuario/updateSenha/:id", (request, response) -> usuarioService.updateSenha(request, response));
        
        get("/usuario/updatePremium/:id", (request, response) -> usuarioService.updatePremium(request, response));

        //get("/produto/delete/:id", (request, response) -> produtoService.remove(request, response));

        //get("/produto", (request, response) -> produtoService.getAll(request, response));
               
    }
}
