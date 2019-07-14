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

		// Posições ACIMA da peça:
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posições à ESQUERDA da peça:
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posições à DIREITA da peça:
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posições ABAIXO da peça:
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posições NOROESTE da peça:
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() - 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posições à NORDESTE da peça:
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posições à SUDESTE da peça:
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
			// matriz do xadrez.
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() + 1);
		}
		if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		// ---------------------------------

		// Posições SUDOESTE da peça:
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);

		// Enquanto a posição 'p' existir e não tiver uma peça, faça:
		while (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {

			// A posição recebe true pois as peças só se movimentarão pelas posições true da
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
