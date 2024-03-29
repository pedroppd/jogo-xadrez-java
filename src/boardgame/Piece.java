package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;

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

	public abstract boolean[][] possibleMoves();

	public boolean possibleMoves(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}

	public boolean isThereAnyPossibleMove(Position position) {
		boolean[][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] == true) {
					return true;
				}
			}
		}
		return false;
	}

}
