package boardgame;

public class Piece {
	
	//Est� encapsulada como protected para n�o ser vis�vel na camada de xadrez.
	protected Position posicao;
	private Board tabuleiro;
	
	
	public Piece(Board tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		//N�o precisaria da declara��o abaixo pois por padr�o j� seria null:
		posicao = null;
	}

	//Deixamos protected para que somente classes do mesmo pacote ou subclasses possam acessar.
	//N�o ser� vis�vel na camada de xadrez (exceto se subclasse desta), somente na de tabuleiro (boardgame).
	protected Board getTabuleiro() {
		return tabuleiro;
	}


	
}
