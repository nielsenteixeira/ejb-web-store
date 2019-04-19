package uni7.apl.web;
public enum Acao {
	LISTAR("listar");
	
	private final String text;
	
	Acao(final String text) {
        this.text = text;
    }

	@Override
    public String toString() {
        return text;
    }
	
}