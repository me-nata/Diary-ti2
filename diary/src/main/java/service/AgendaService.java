package service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import dao.AgendaDAO;
import dao.UsuarioDAO;
import model.Agenda;
import spark.Request;
import spark.Response;


public class AgendaService {

	private UsuarioDAO usuarioDAO;
	private AgendaDAO agendaDAO;

	public AgendaService() {
		agendaDAO = new AgendaDAO();
		agendaDAO.conectar();
	}
	
	
	private String tratarDado(String dado) {
		String novo_dado = new String();
		for(int i = 0; i < dado.length(); i++) {
			if(dado.charAt(i) == '[' && dado.charAt(i+1) == '{') {
				i += 1;
			}
			if(dado.charAt(i) == '%' && dado.charAt(i+1) == '2' && dado.charAt(i+2) == '2') {
				i += 3;
			} 
		
			if(dado.charAt(i) == '}' && dado.charAt(i+1) == ']') {
				novo_dado += dado.charAt(i);
				i = dado.length();
			} else {
				novo_dado += dado.charAt(i);				
			}
			
		}
		
		return novo_dado;
	}
	private String[] separarCards(String dado) {
		return dado.split("},");
	}
	private String[] separarQuery(String dado) {
		int index_titulo = dado.indexOf("titulo:");
		int index_texto  = dado.indexOf("texto:");
		
		int index_final_data   = dado.lastIndexOf("data:");
		int index_final_titulo = dado.lastIndexOf("titulo:");
		int index_final_texto  = dado.lastIndexOf("texto:");
		
		String data = new String();
		String titulo = new String();
		String texto = new String();
		
		while(index_final_data++ < index_titulo-1) {
			data += dado.charAt(index_final_data);
		}
		while(index_final_titulo++ < index_texto-1) {
			titulo += dado.charAt(index_final_titulo);
		}
		while(index_final_texto++ < dado.length()) {
			texto += dado.charAt(index_final_texto);
		}
		
		String[] Query = {data, titulo, texto};
		return Query;
	
	}
	
	public Object add(Request request, Response response) {
		String dado_total = request.queryString();
		dado_total = tratarDado(dado_total);
		
		String []dados = separarCards(dado_total);
		System.out.println(dados[1]);
//		for(int i = 0; i < dados.length; i++) {			
//			String []querys = separarQuery(dados[i]);
//			for(int j = 0; j < 3; j++) {
//				System.out.println(querys[j]);				
//			}
//		}
		
		//Agenda agenda = new Agenda(0, texto, data);

		//agendaDAO.inserirAgenda(agenda);

		response.status(201); // 201 Created
		return 0;
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
            return "Agenda " + id + " n??o encontrada.";
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
            return "Agenda n??o encontrada.";
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
            return "Agenda n??o encontrada.";
        }
	}
}
