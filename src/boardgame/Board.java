package boardgame;

public class Board {

	private int linhas;
	private int colunas;

	// Matriz do tipo Piece (peças):
	private Piece[][] pecas;

	public Board(int linhas, int colunas) {
		// Tratamento de erro ao criar tabuleiro:
		if (linhas < 1 || colunas < 1) {
			throw new BoardException(
					"Erro ao criar tabuleiro: é necessário que haja pelo " + "menos uma linha e uma coluna");
		}
		// -------------------------------------

		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Piece[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	// Método para retornar a matriz pecas na linha e coluna indicadas no argumento:
	public Piece peca(int linha, int coluna) {
		// Tratamento de erro ao acessar posição de uma peça:
		if (!posicaoExiste(linha, coluna)) {
			throw new BoardException("Posição inexistente.");
		}
		// -------------------------------------------------

		return pecas[linha][coluna];
	}

	// Sobrecarga do anterior. Método para retornar a peça pela posição:
	public Piece peca(Position posicao) {
		// Tratamento de erro ao acessar posição de uma peça:
		if (!posicaoExiste(posicao)) {
			throw new BoardException("Posição inexistente.");
		}
		// -------------------------------------------------

		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	// Coloca peca no tabuleiro:
	public void colocarPeca(Piece peca, Position posicao) {
		// Tratamento de erro caso tenha uma peça na posição:
		if (posicaoTemPeca(posicao)) {
			throw new BoardException("Já existe uma peça nessa posição " + posicao);
		}
		// -------------------------------------------------

		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	public Piece removePeca(Position posicao) {
		// Tratamento de erro ao acessar posição de uma peça:
		if (!posicaoExiste(posicao)) {
			throw new BoardException("Posição inexistente.");
		}
		// -------------------------------------------------
		
		if(peca(posicao) == null) {
			return null;
		}
		Piece aux = peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		
		return aux;
	}

	// Métodos para testar se a posição digitada existe dentro do tabuleiro:
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	public boolean posicaoExiste(Position posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}

	// Método para testar se há peça na posição digitada:
	public boolean posicaoTemPeca(Position posicao) {
		// Tratamento de erro ao acessar posição de uma peça:
		if (!posicaoExiste(posicao)) {
			throw new BoardException("Posição inexistente.");
		}
		// -------------------------------------------------

		// O método peca() está declarado acima e retorna a posição quando há alguma
		// peça, portanto,
		// para testarmos se está ocupada basta receber o retorno, se for diferente de
		// null tem peça na posição.
		return peca(posicao) != null;
	}
}
