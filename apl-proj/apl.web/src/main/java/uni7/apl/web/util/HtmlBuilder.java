package uni7.apl.web.util;

import java.util.List;

import uni7.apl.ejb.estocagem.Produto;

public class HtmlBuilder {
	
	 StringBuilder html;
	
	public HtmlBuilder() {
		html = new StringBuilder();
	}

	public StringBuilder getHtml() {
		return html;
	}

	public void setHtml(StringBuilder html) {
		this.html = html;
	}

	public String buildHtmlList(String tituloLista, List<Produto> produtos) {
		openHtml();
		
		
		if (produtos == null) {
			this.html.append("<h3>"+ tituloLista +" vazio!</h3>");
		} else {
			this.html.append("<h3>"+ tituloLista +"</h3>");
			this.html.append("<ul>");
			produtos.forEach(produto -> {
				this.html.append("<li>");
				this.html.append("Código: " + produto.getCodigo() + " | ");
				this.html.append("Produto: " + produto.getNome() + " | ");
				this.html.append("Descricao: " + produto.getDescricao() + " | ");
				this.html.append("Preço: " + produto.getPreco() + " | ");
				this.html.append("Nome: " + produto.getNome() + " | ");
				this.html.append("</li>");
			});
		}
		this.html.append("</ul>");
		closeHtml();
		return this.html.toString();
	}

	private void openHtml() {
		this.html.append("<html>");
		this.html.append("<body>");
	}
	
	private void closeHtml() {
		this.html.append("</body>");
		this.html.append("</html>");
	}
}
