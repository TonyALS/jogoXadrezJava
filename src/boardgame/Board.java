package boardgame;

public class Board {

	private int linhas;
	private int colunas;

	// Matriz do tipo Piece (pe�as):
	private Piece[][] pecas;

	public Board(int linhas, int colunas) {
		// Tratamento de erro ao criar tabuleiro:
		if (linhas < 1 || colunas < 1) {
			throw new BoardException(
					"Erro ao criar tabuleiro: � necess�rio que haja pelo " + "menos uma linha e uma coluna");
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

	// M�todo para retornar a matriz pecas na linha e coluna indicadas no argumento:
	public Piece peca(int linha, int coluna) {
		// Tratamento de erro ao acessar posi��o de uma pe�a:
		if (!posicaoExiste(linha, coluna)) {
			throw new BoardException("Posi��o inexistente.");
		}
		// -------------------------------------------------

		return pecas[linha][coluna];
	}

	// Sobrecarga do anterior. M�todo para retornar a pe�a pela posi��o:
	public Piece peca(Position posicao) {
		// Tratamento de erro ao acessar posi��o de uma pe�a:
		if (!posicaoExiste(posicao)) {
			throw new BoardException("Posi��o inexistente.");
		}
		// -------------------------------------------------

		return pecas[posicao.getLinha()][posicao.getColuna()];
	}

	// Coloca peca no tabuleiro:
	public void colocarPeca(Piece peca, Position posicao) {
		// Tratamento de erro caso tenha uma pe�a na posi��o:
		if (posicaoTemPeca(posicao)) {
			throw new BoardException("J� existe uma pe�a nessa posi��o " + posicao);
		}
		// -------------------------------------------------

		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}

	public Piece removePeca(Position posicao) {
		// Tratamento de erro ao acessar posi��o de uma pe�a:
		if (!posicaoExiste(posicao)) {
			throw new BoardException("Posi��o inexistente.");
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

	// M�todos para testar se a posi��o digitada existe dentro do tabuleiro:
	private boolean posicaoExiste(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}

	public boolean posicaoExiste(Position posicao) {
		return posicaoExiste(posicao.getLinha(), posicao.getColuna());
	}

	// M�todo para testar se h� pe�a na posi��o digitada:
	public boolean posicaoTemPeca(Position posicao) {
		// Tratamento de erro ao acessar posi��o de uma pe�a:
		if (!posicaoExiste(posicao)) {
			throw new BoardException("Posi��o inexistente.");
		}
		// -------------------------------------------------

		// O m�todo peca() est� declarado acima e retorna a posi��o quando h� alguma
		// pe�a, portanto,
		// para testarmos se est� ocupada basta receber o retorno, se for diferente de
		// null tem pe�a na posi��o.
		return peca(posicao) != null;
	}
}
