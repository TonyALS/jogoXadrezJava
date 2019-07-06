package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

//Essa é a classe principal, nela estão todas as regras do jogo de xadrez

public class ChessMatch {
	
	private Board tabuleiro;
	
	//Construtor que define o tamanho do tabuleiro:
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		iniciaPartida();
	}
	
	//Método para retornar uma matriz de peças de xadrez correspondente a esta partida (ChessMatch):
	public ChessPiece[][] getPecas() {
		ChessPiece[][] mat = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (ChessPiece) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	private void colocarNovaPeca(char coluna, int linha, ChessPiece peca) {
		tabuleiro.colocarPeca(peca, new ChessPosition(coluna, linha).paraPosicao());
	}
	
	private void iniciaPartida() {
		colocarNovaPeca('c', 1, new Rook(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 2, new Rook(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 2, new Rook(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 2, new Rook(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 1, new Rook(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 1, new King(tabuleiro, Color.WHITE));

		colocarNovaPeca('c', 7, new Rook(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 8, new Rook(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 7, new Rook(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 7, new Rook(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 8, new Rook(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 8, new King(tabuleiro, Color.BLACK));
	}
}
