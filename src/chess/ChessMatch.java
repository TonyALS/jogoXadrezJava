package chess;

import boardgame.Board;

//Essa é a classe principal, nela estão todas as regras do jogo de xadrez

public class ChessMatch {
	
	private Board tabuleiro;
	
	//Construtor que define o tamanho do tabuleiro:
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
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
}
