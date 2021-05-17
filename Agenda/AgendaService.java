package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.AgendaDAO;
import model.Agenda;
import spark.Request;
import spark.Response;


public class AgendaService {

	private AgendaDAO agendaDAO;

	public AgendaService() {
		try {
			agendaDAO = new DAO("agenda.dat");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public Object add(Request request, Response response) {
		String email = request.queryParams("texto");
		String senha = request.queryParams("data");
                
		Agenda agenda = new Agenda(id, texto, data);

		AgendaDAO.inserirAgenda(agenda);

		response.status(201); // 201 Created
		return id;
	}

	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Agenda agenda = (Agenda) agendaDAO.get(id);
		
		if (agenda != null) {
    	        response.header("Content-Type", "application/xml");
    	        response.header("Content-Encoding", "UTF-8");

            return "<agenda>\n" + 
            		"\t<id>" + agenda.getId() + "</id>\n" +
            		"\t<texto>" + agenda.getDescricao() + "</texto>\n" +
            		"\t<data>" + agenda.getPreco() + "</data>\n" +
            		"</agenda>\n";
        } else {
            response.status(404); // 404 Not found
            return "Agenda " + id + " não encontrada.";
        }

	}

	public Object update(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));
        
		Agenda agenda = (Agenda) agendaDAO.get(id);

        if (agenda != null) {
        	//agenda.setId(request.queryParams("id"));
        	agenda.setTexto(request.queryParams("texto")));
        	agenda.setData(request.queryParams("data")));

        	agendaDAO.atualizarAgenda(agenda);
        	
            return id;
        } else {
            response.status(404); // 404 Not found
            return "Agenda não encontrada.";
        }

	}

	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Agenda agenda = (Agenda) agendaDAO.get(id);

        if (agenda != null) {

            agendaDAO.excluirAgenda(agenda);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Agenda não encontrada.";
        }
	}

	public Object getAll(Request request, Response response) {
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
	}
}
