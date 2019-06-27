package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	private Integer turn;
	private Color currentPlayer;

	public ChessMatch() {
		this.board = new Board(8, 8);
		this.turn = 1;
		this.currentPlayer = Color.WHITH;
		initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}

		return mat;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITH));
		placeNewPiece('c', 2, new Rook(board, Color.WHITH));
		placeNewPiece('d', 2, new Rook(board, Color.WHITH));
		placeNewPiece('e', 2, new Rook(board, Color.WHITH));
		placeNewPiece('e', 1, new Rook(board, Color.WHITH));
		placeNewPiece('d', 1, new King(board, Color.WHITH));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Piece capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece) capturedPiece;
	}

	private void validateTargetPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("A pe�a escolhida n�o pode se mover para a posi��o de destino");
		}
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;

	}

	private void validateSourcePosition(Position position) {
		if (!board.therelsAPiece(position)) {
			throw new ChessException("N�o existe pe�a na posi��o de origem");
		}
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("a pe�a escolhida n�o e sua!");
		}
		if (!board.piece(position).isThereAnyPositionMove()) {
			throw new ChessException("N�o existe movimentos poss�veis para a pe�a escolhida");
		}
	}
	
	private void nextTurn() {
		turn ++;
		currentPlayer = (currentPlayer == Color.WHITH ) ? Color.BLACK:Color.WHITH;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public Integer getTurn() {
		return turn;
	}

}
