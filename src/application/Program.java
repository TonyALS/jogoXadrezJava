package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch partidaChadrez = new ChessMatch();
		
		while(true) {
			UI.imprimeTabuleiro(partidaChadrez.getPecas());
			System.out.println();
			System.out.print("Origem: ");
			ChessPosition origem = UI.lerPosicaoXadrez(sc);
			
			System.out.println();
			System.out.print("Destino: ");
			ChessPosition destino = UI.lerPosicaoXadrez(sc);
			
			ChessPiece pecaCapturada = partidaChadrez.movimentaPeca(origem, destino);
		}
		
	}
}
