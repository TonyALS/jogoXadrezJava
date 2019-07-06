package chess;

import boardgame.Board;
import boardgame.Position;
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
	
	private void iniciaPartida() {
		tabuleiro.colocarPeca(new Rook(tabuleiro, Color.WHITE), new Position(2, 1));
		tabuleiro.colocarPeca(new King(tabuleiro, Color.BLACK), new Position(0, 4));
	}
}
