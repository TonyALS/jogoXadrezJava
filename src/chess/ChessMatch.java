package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Rook;

//Essa � a classe principal, nela est�o todas as regras do jogo de xadrez

public class ChessMatch {
	
	private int turno;
	private Color currentPlayer;
	private Board tabuleiro;
	
	//Por padr�o um boolean come�a com false:
	private boolean check;
	private boolean checkMate;
	
	private List<Piece> pecasNoTabuleiro = new ArrayList<>();
	private List<Piece> pecasCapturadas = new ArrayList<>();
	
	//Construtor que define o tamanho do tabuleiro:
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		turno = 1;
		currentPlayer = Color.WHITE;
		iniciaPartida();
	}
	
	public int getTurno() {
		return turno;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	//M�todo para retornar uma matriz de pe�as de xadrez correspondente a esta partida (ChessMatch):
	public ChessPiece[][] getPecas() {
		ChessPiece[][] mat = new ChessPiece[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for (int i = 0; i < tabuleiro.getLinhas(); i++) {
			for (int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (ChessPiece) tabuleiro.peca(i, j);
			}
		}
		return mat;
	}
	
	//M�todo para colorir as movimenta��es poss�veis de cada pe�a
	public boolean[][] movimentosPossiveis(ChessPosition posicaoOrigem){
		Position posicao = posicaoOrigem.paraPosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	//performChessMove
	public ChessPiece movimentaPeca(ChessPosition posicaoOrigem, ChessPosition posicaoDestino) {
		Position origem = posicaoOrigem.paraPosicao();
		Position destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Piece pecaCapturada = realizaMovimento(origem, destino);
		
		if(testCheck(currentPlayer)) {
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new ChessException("Voce nao pode fazer um movimento que coloque seu Rei em xeque");
		}
		
		//Se o teste check do oponente for verdadeiro, ent�o check receber� true, : (sen�o) receber� false:
		check = (testCheck(oponente(currentPlayer))) ? true : false;
		
		if(testCheckMate(oponente(currentPlayer))) {
			checkMate = true;
		}else {
			proximoTurno();
		}
		//Downcasting (Convers�o) de peca capturada j� que ela era do tipo Piece.
		return (ChessPiece)pecaCapturada;
	}
	
	//makeMovie
	private Piece realizaMovimento(Position origem, Position destino) {
		//Remove a pe�a da posi��o de origem:
		ChessPiece p = (ChessPiece)tabuleiro.removePeca(origem);
		p.incrementaContadorMovimento();
		//Remove a poss�vel pe�a que esteja na posi��o de destino:
		Piece pecaCapturada = tabuleiro.removePeca(destino);
		
		//Coloca a pe�a p na posi��o de destino:
		tabuleiro.colocarPeca(p, destino);
		
		if(pecaCapturada != null) {
			pecasNoTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		return pecaCapturada;
	}
	
	private void desfazerMovimento(Position origem, Position destino, Piece pecaCapturada) {
		ChessPiece p = (ChessPiece)tabuleiro.removePeca(destino);
		p.decrementaContadorMovimento();
		tabuleiro.colocarPeca(p, origem);
		
		//Adiciona uma poss�vel pe�a capturada novamente a sua posi��o de origem:
		if(pecaCapturada != null) {
			tabuleiro.colocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasNoTabuleiro.add(pecaCapturada);
		}
	}
	
	private void validarPosicaoOrigem(Position posicao) {
		if(!tabuleiro.posicaoTemPeca(posicao)) {
			throw new ChessException("N�o existe pe�a na posi��o de origem.");
		}
		if(currentPlayer != ((ChessPiece)tabuleiro.peca(posicao)).getCor()) {
			throw new ChessException("Voce nao pode selecionar uma peca adversaria");
		}
		if(!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new ChessException("N�o h� movimentos poss�veis para esta pe�a.");
		}
	}
	
	private void validarPosicaoDestino(Position origem, Position destino) {
		if(!tabuleiro.peca(origem).movimentoPossivel(destino)) {
			throw new ChessException("A peca escolhida n�o pode se mover para a posicao destino.");
		}
	}
	
	private void proximoTurno() {
		turno++;
		//Se o jogador atual � == Color.WHITE ent�o agora vai ser Color.BLACK. Caso contr�rio ":" Color.WHITE.
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color oponente(Color cor) {
		return (cor == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king (Color cor) {
		List<Piece> list = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getCor()==cor).collect(Collectors.toList());
		for(Piece p : list) {
			//Se a pe�a for uma inst�ncia de Rei(King):
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		//Se ocorrer esta exce��o h� um problema no jogo como um todo pois sempre haver� um rei antes do fim do jogo.
		throw new IllegalStateException("N�o h� nenhum rei da cor " + cor + "no tabuleiro");
	}
	
	//Teste do rei em xeque:
	private boolean testCheck(Color cor) {
		Position posicaoDoRei = king(cor).getPosicaoPeca().paraPosicao();
		List<Piece> pecasOponente = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getCor()==oponente(cor)).collect(Collectors.toList());
		for(Piece p : pecasOponente) {
			boolean[][] mat = p.movimentosPossiveis();
			if(mat[posicaoDoRei.getLinha()][posicaoDoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color cor) {
		
		if(!testCheck(cor)) {
			return false;
		}
		List<Piece> list = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece)x).getCor()==cor).collect(Collectors.toList());
		for(Piece p : list) {
			boolean[][] mat = p.movimentosPossiveis();
			for(int i = 0; i < tabuleiro.getLinhas(); i++) {
				for(int j = 0; j < tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Position origem = ((ChessPiece)p).getPosicaoPeca().paraPosicao();
						Position destino = new Position(i, j);
						Piece pecaCapturada = realizaMovimento(origem, destino);
						boolean testeCheck = testCheck(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if(!testeCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void colocarNovaPeca(char coluna, int linha, ChessPiece peca) {
		tabuleiro.colocarPeca(peca, new ChessPosition(coluna, linha).paraPosicao());
		pecasNoTabuleiro.add(peca);
	}
	
	private void iniciaPartida() {
		
		colocarNovaPeca('a', 1, new Rook(tabuleiro, Color.WHITE));
		colocarNovaPeca('b', 1, new Knight(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 1, new Bishop(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 1, new King(tabuleiro, Color.WHITE));
		colocarNovaPeca('f', 1, new Bishop(tabuleiro, Color.WHITE));
		colocarNovaPeca('g', 1, new Knight(tabuleiro, Color.WHITE));
		colocarNovaPeca('h', 1, new Rook(tabuleiro, Color.WHITE));
		colocarNovaPeca('a', 2, new Pawn(tabuleiro, Color.WHITE));
		colocarNovaPeca('b', 2, new Pawn(tabuleiro, Color.WHITE));
		colocarNovaPeca('c', 2, new Pawn(tabuleiro, Color.WHITE));
		colocarNovaPeca('d', 2, new Pawn(tabuleiro, Color.WHITE));
		colocarNovaPeca('e', 2, new Pawn(tabuleiro, Color.WHITE));
		colocarNovaPeca('f', 2, new Pawn(tabuleiro, Color.WHITE));
		colocarNovaPeca('g', 2, new Pawn(tabuleiro, Color.WHITE));
		colocarNovaPeca('h', 2, new Pawn(tabuleiro, Color.WHITE));

		colocarNovaPeca('a', 8, new Rook(tabuleiro, Color.BLACK));
		colocarNovaPeca('b', 8, new Knight(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 8, new Bishop(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 8, new King(tabuleiro, Color.BLACK));
		colocarNovaPeca('f', 8, new Bishop(tabuleiro, Color.BLACK));
		colocarNovaPeca('g', 8, new Knight(tabuleiro, Color.BLACK));
		colocarNovaPeca('h', 8, new Rook(tabuleiro, Color.BLACK));
		colocarNovaPeca('a', 7, new Pawn(tabuleiro, Color.BLACK));
		colocarNovaPeca('b', 7, new Pawn(tabuleiro, Color.BLACK));
		colocarNovaPeca('c', 7, new Pawn(tabuleiro, Color.BLACK));
		colocarNovaPeca('d', 7, new Pawn(tabuleiro, Color.BLACK));
		colocarNovaPeca('e', 7, new Pawn(tabuleiro, Color.BLACK));
		colocarNovaPeca('f', 7, new Pawn(tabuleiro, Color.BLACK));
		colocarNovaPeca('g', 7, new Pawn(tabuleiro, Color.BLACK));
		colocarNovaPeca('h', 7, new Pawn(tabuleiro, Color.BLACK));
	}
}
