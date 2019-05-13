package uni7.apl.web.exception;
public class ReposicaoEstoqueException extends Exception { 
	private static final long serialVersionUID = 3061900357710598699L;

	public ReposicaoEstoqueException(String errorMessage) {
        super(errorMessage);
    }
}