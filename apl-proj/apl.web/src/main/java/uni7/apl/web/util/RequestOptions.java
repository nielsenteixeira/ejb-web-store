package uni7.apl.web.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;

public class RequestOptions {
	public FormatoResposta getFormatoResposta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("formato") != null) {
			try	{
				return FormatoResposta.valueOf(request.getParameter("formato").toUpperCase());
			} catch (IllegalArgumentException e) {
				response.sendError(HttpStatus.SC_BAD_REQUEST, "Escolha um formato válido: 'json' ou 'html'.");
			}
		} 
		
		return FormatoResposta.HTML;
	}
	
	public Acao getAcao(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (request.getParameter("acao") != null) {
			try {
				return Acao.valueOf(request.getParameter("acao").toUpperCase());
			} catch (IllegalArgumentException e) {
				response.sendError(HttpStatus.SC_BAD_REQUEST, "Escolha uma ação válida: 'listar'.");
			}
		} 
		
		return Acao.LISTAR;
	}
}
