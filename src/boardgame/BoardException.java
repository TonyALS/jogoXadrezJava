package boardgame;

//RuntimeException = exce��o opcional de ser tratada.

public class BoardException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	//O construtor dessa classe repassa a mensagem para o construtor da superclasse (RuntimeException);
	public BoardException(String msg) {
		super(msg);
	}
}
