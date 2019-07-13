package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {
	//Cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	//Cores de fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	//-----------------------------------------------------------------
	
	//C�digo para limpar tela:
	public static void limparTela() {
		System.out.println("\033[H\033[2J");
		System.out.flush();
	}
	
	public static ChessPosition lerPosicaoXadrez(Scanner sc) {
		try {
		String s = sc.nextLine();
		char coluna = s.charAt(0);
		int linha = Integer.parseInt(s.substring(1));
		return new ChessPosition(coluna, linha);
		}
		catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler posi��o. Valores v�lidos s�o de"
					+ "a1 at� h8");
		}
	}
	
	public static void imprimePartida(ChessMatch partida, List<ChessPiece> capturadas) {
		imprimeTabuleiro(partida.getPecas());
		System.out.println();
		imprimePecasCapturadas(capturadas);
		System.out.println();
		System.out.println("Turno: " + partida.getTurno());
		System.out.println("Aguardando jogador: " + partida.getCurrentPlayer());
	}
	
	public static void imprimeTabuleiro(ChessPiece[][] pecas) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimePeca(pecas[i][j], false);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	//Colorir movimentos poss�veis:
	public static void imprimeTabuleiro(ChessPiece[][] pecas, boolean[][] movimentosPossiveis) {
		for (int i = 0; i < pecas.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pecas.length; j++) {
				imprimePeca(pecas[i][j], movimentosPossiveis[i][j]);
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}
	
	private static void imprimePeca(ChessPiece peca, boolean corDeFundo) {
		if(corDeFundo) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (peca == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (peca.getCor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + peca + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + peca + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
	
	private static void imprimePecasCapturadas(List<ChessPiece> capturadas) {
		List<ChessPiece> white = capturadas.stream().filter(x -> x.getCor() == Color.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = capturadas.stream().filter(x -> x.getCor() == Color.BLACK).collect(Collectors.toList());
		
		System.out.println("Pecas capturadas: ");
		System.out.print("Brancas: ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));
		System.out.print(ANSI_RESET);
		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
}
