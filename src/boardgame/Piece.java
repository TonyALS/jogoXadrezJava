package boardgame;

public class Piece {
	
	//Está encapsulada como protected para não ser visível na camada de xadrez.
	protected Position posicao;
	private Board tabuleiro;
	
	
	public Piece(Board tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		//Não precisaria da declaração abaixo pois por padrão já seria null:
		posicao = null;
	}

	//Deixamos protected para que somente classes do mesmo pacote ou subclasses possam acessar.
	//Não será visível na camada de xadrez (exceto se subclasse desta), somente na de tabuleiro (boardgame).
	protected Board getTabuleiro() {
		return tabuleiro;
	}


	
}
