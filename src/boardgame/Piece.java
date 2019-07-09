package boardgame;

public abstract class Piece {
	
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

	//M�todo abstrato:
	public abstract boolean[][] movimentosPossiveis();
	
	//M�todo concreto:
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
