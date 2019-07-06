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

	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}
	
	//Método para retornar a matriz pecas na linha e coluna indicadas no argumento:
	public Piece peca(int linha, int coluna) {
		return pecas[linha][coluna];
	}
	
	//Sobrecarga do anterior. Método para retornar a peça pela posição:
	public Piece peca(Position posicao) {
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
}
