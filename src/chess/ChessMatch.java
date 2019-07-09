package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

//Essa � a classe principal, nela est�o todas as regras do jogo de xadrez

public class ChessMatch {
	
	private Board tabuleiro;
	
	//Construtor que define o tamanho do tabuleiro:
	public ChessMatch() {
		tabuleiro = new Board(8, 8);
		iniciaPartida();
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
	
	public ChessPiece movimentaPeca(ChessPosition posicaoOrigem, ChessPosition posicaoDestino) {
		Position origem = posicaoOrigem.paraPosicao();
		Position destino = posicaoDestino.paraPosicao();
		validarPosicaoOrigem(origem);
		Piece pecaCapturada = realizaMovimento(origem, destino);
		
		//Downcasting (Convers�o) de peca capturada j� que ela era do tipo Piece.
		return (ChessPiece)pecaCapturada;
	}
	
	private Piece realizaMovimento(Position origem, Position destino) {
		//Remove a pe�a da posi��o de origem:
		Piece p = tabuleiro.removePeca(origem);
		
		//Remove a poss�vel pe�a que esteja na posi��o de destino:
		Piece pecaCapturada = tabuleiro.removePeca(destino);
		
		//Coloca a pe�a p na posi��o de destino:
		tabuleiro.colocarPeca(p, destino);
		
		return pecaCapturada;
	}
	
	private void validarPosicaoOrigem(Position posicao) {
		if(!tabuleiro.posicaoTemPeca(posicao)) {
			throw new ChessException("N�o existe pe�a na posi��o de origem.");
		}
		if(!tabuleiro.peca(posicao).existeMovimentoPossivel()) {
			throw new ChessException("N�o h� movimentos poss�veis para esta pe�a.");
		}
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
