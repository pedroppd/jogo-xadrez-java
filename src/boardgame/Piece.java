package boardgame;

public class Piece {

	protected Position position;
	private Board board;

	public Piece[][] possibleMoves() {
		return null;
	}

	public boolean possibleMove(Position position) {
		return true;
	}
	
	public boolean isThereAnyPositionMove() {
		return true;
	}

	public Piece() {

	}

	public Piece(Board board) {
		this.board = board;
		this.position = null;
	}


	protected Board getBoard() {
		return board;
	}

	

}
