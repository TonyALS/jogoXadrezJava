package boardgame;

public class Board {

	private int linhas;
	private int colunas;
	
	//Matriz do tipo Piece (peças):
	private Piece[][] pecas;

	public Board(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Piece[linhas][colunas];
	}
	
	
}
