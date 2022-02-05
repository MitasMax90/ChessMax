public class King extends ChessPiece {

    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        // Если все координаты существуют
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {

            // Проверяем, как ходит король. Если больше чем на 1 шаг, возвращаем  false
            if (Math.abs(line - toLine) > 1 || Math.abs(column - toColumn) > 1) return false;

            // Нельзя ходить, если объявлен шаг (король под атакой)
            if (isUnderAttack(chessBoard, toLine, toColumn)) return false;

            // Если клетка не пуста, вернёт true, и если фигура др цвета
            if (chessBoard.board[toLine][toColumn] != null) {
                return !chessBoard.board[toLine][toColumn].getColor().equals(color);
            }

            return true;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column) {

        // идём по всем клеткам
        if (checkPos(line) && checkPos(column)) {
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++) {
                    if (chessBoard.board[i][j] != null) {
                        if (!chessBoard.board[i][j].getColor().equals(color) && chessBoard.board[i][j].canMoveToPosition(chessBoard, i, j, line, column)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } else return false;
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}