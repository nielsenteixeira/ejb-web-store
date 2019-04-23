package uni7.apl.web.util;

public enum Acao {
	LISTAR("listar"),
	FINALIZAR("finalizar"),
	ESVAZIAR("esvaziar"),
	REMOVER("remover"),
	ADICIONAR("adicionar");
	
	private final String text;
	
	Acao(final String text) {
        this.text = text;
    }

	@Override
    public String toString() {
        return text;
    }
	
}