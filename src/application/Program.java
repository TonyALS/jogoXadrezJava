package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		ChessMatch partidaChadrez = new ChessMatch();
		UI.imprimeTabuleiro(partidaChadrez.getPecas());
	}
}
