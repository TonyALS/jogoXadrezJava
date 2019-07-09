package boardgame;

public abstract class Piece {
	
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

	//Método abstrato:
	public abstract boolean[][] movimentosPossiveis();
	
	//Método concreto:
	public boolean movimentoPossivel(Position posicao) {
		return movimentosPossiveis()[posicao.getLinha()][posicao.getColuna()];
	}
	
	public boolean existeMovimentoPossivel() {
		boolean[][] mat = movimentosPossiveis();
		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
	}
}
