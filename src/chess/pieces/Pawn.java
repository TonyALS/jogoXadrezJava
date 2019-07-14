package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	private ChessMatch chessMatch;

	public Pawn(Board tabuleiro, Color cor, ChessMatch chessMatch) {
		super(tabuleiro, cor);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] movimentosPossiveis() {
		// Cria uma matriz de boolean com tamanho do tabuleiro.
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];

		Position p = new Position(0, 0);

		if (getCor() == Color.WHITE) {
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Position p2 = new Position(posicao.getLinha() - 1, posicao.getColuna());

			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().posicaoTemPeca(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}

			// En passant - WHITE:
			if (posicao.getLinha() == 3) {
				Position esquerda = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && pecaDoOponente(esquerda)
						&& getTabuleiro().peca(esquerda) == chessMatch.getEnPassantVulnerable()) {
					mat[esquerda.getLinha() - 1][esquerda.getColuna()] = true;
				}
				Position direita = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && pecaDoOponente(direita)
						&& getTabuleiro().peca(direita) == chessMatch.getEnPassantVulnerable()) {
					mat[direita.getLinha() - 1][direita.getColuna()] = true;
				}
			}
		} else {
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Position p2 = new Position(posicao.getLinha() + 1, posicao.getColuna());

			if (getTabuleiro().posicaoExiste(p) && !getTabuleiro().posicaoTemPeca(p) && getTabuleiro().posicaoExiste(p2)
					&& !getTabuleiro().posicaoTemPeca(p2) && getMoveCount() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExiste(p) && pecaDoOponente(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			
			// En passant - BLACK:
			if (posicao.getLinha() == 4) {
				Position esquerda = new Position(posicao.getLinha(), posicao.getColuna() - 1);
				if (getTabuleiro().posicaoExiste(esquerda) && pecaDoOponente(esquerda)
						&& getTabuleiro().peca(esquerda) == chessMatch.getEnPassantVulnerable()) {
					mat[esquerda.getLinha() + 1][esquerda.getColuna()] = true;
				}
				Position direita = new Position(posicao.getLinha(), posicao.getColuna() + 1);
				if (getTabuleiro().posicaoExiste(direita) && pecaDoOponente(direita)
						&& getTabuleiro().peca(direita) == chessMatch.getEnPassantVulnerable()) {
					mat[direita.getLinha() + 1][direita.getColuna()] = true;
				}
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		return "P";
	}
}
