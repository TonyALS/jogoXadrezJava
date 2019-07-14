package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board tabuleiro, Color cor) {
		super(tabuleiro, cor);
	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		// Cria uma matriz de boolean com tamanho do tabuleiro.
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		// Posi��es ACIMA da pe�a:
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posi��es � ESQUERDA da pe�a:
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posi��es � DIREITA da pe�a:
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posi��es ABAIXO da pe�a:
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posi��es NOROESTE da pe�a:
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posi��es � NORDESTE da pe�a:
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posi��es � SUDESTE da pe�a:
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posi��es SUDOESTE da pe�a:
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);

		// Enquanto a posi��o 'p' existir e n�o tiver uma pe�a, fa�a:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posi��o recebe true pois as pe�as s� se movimentar�o pelas posi��es true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		return mat;
	}
}
