package chess;

public class ChessException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	//Repassa para o construtor da superclasse a mensagem de erro.
	public ChessException(String msg) {
		super(msg);
	}
}
