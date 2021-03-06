package app;

import static spark.Spark.*;

import service.AgendaService;
import service.UsuarioService;

public class Aplicacao {
	
	private static UsuarioService usuarioService = new UsuarioService();
	private static AgendaService agendaService = new AgendaService();
	
    public static void main(String[] args) {
        port(6580);

        post("/usuario", (request, response) -> usuarioService.add(request, response));
        get("/agenda", (request, response) -> agendaService.add(request, response));

        get("/usuario/updateSenha/:id", (request, response) -> usuarioService.updateSenha(request, response));
        
        get("/usuario", (request, response) -> usuarioService.getAll(request, response));
        
        get("/usuario/updatePremium/:id", (request, response) -> usuarioService.updatePremium(request, response));
    }
}
