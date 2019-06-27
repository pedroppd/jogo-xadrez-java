package boardgame;

public class Board {

	private Integer rows;
	private Integer columns;
	private Piece pieces[][];

	public Board() {

	}

	public Board(Integer rows, Integer columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("Erro criando tabuleiro: é necessário que haja pelo menos uma linha e uma coluna");
		}
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}

	public boolean therelsAPiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não encontrada: " + position);
		}
		return piece(position) != null;
	}

	public void placePiece(Piece piece, Position position) {
		if (therelsAPiece(position)) {
			throw new BoardException("Posição indisponível:" + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	public Piece piece(int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Posição não encontrada");
		}
		return pieces[row][column];
	}

	public Piece piece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição não encontrada");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	public Piece removePiece(Position position) {
		if (!positionExists(position)) {
			throw new BoardException("Posição inexistente!");
		}

		if (piece(position) == null) {
			return null;
		}

		Piece aux = piece(position);
		aux.position = null;
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	public Integer getRows() {
		return rows;
	}

	public Integer getColumns() {
		return columns;
	}

}
