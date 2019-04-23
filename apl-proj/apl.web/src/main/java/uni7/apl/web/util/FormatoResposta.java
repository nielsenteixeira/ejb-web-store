package uni7.apl.web.util;

public enum FormatoResposta {
	JSON("json"),
	HTML("html");
	
	private final String text;
	
	FormatoResposta(final String text) {
        this.text = text;
    }

	@Override
    public String toString() {
        return text;
    }
}
