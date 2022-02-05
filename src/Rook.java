public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        // Если все координаты существуют
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {

            // если ходим в рамках колонки
            if (column == toColumn) {

                // идём сверху вниз (или наоборот)
                for (int i = getMin(line, toLine); i < getMax(line, toLine); i++) {

                    // Если следующая клетка занята
                    if (chessBoard.board[i][column] != null) {

                        // нельзя идти туда где ты стоишь
                        if (chessBoard.board[i][column] == this && i == getMax(line, toLine)) return false;

                        // нельзя идти на ту клетку, где стоит фигура твоего цвета
                        else if (chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return false;

                        // можно съесть фигуру не твоего цвета
                        else if (!chessBoard.board[i][column].getColor().equals(this.color) && i == toLine)
                            return true;

                        // иначе ничего не получится ВООБЩЕ!!!
                        else if (i != toLine && i != line) return false;
                    }
                }

                // если на конечной клетке стоит фигура
                if (chessBoard.board[toLine][column] != null) {

                    // нельзя двигаться на клетку, где стоит фигура твоего цвета
                    if (chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this)
                        return false;

                    // true если на конечной клетке стоит фигура другого цвета и конечная точка не совпадает с начальной
                    else return !chessBoard.board[toLine][column].getColor().equals(this.color) && chessBoard.board[toLine][column] != this;
                } else return true;

            } else if (line == toLine) {

                // Аналогично как и для колонок
                for (int i = getMin(toColumn, column); i < getMax(column, toColumn); i++) {
                    if (chessBoard.board[line][i] != null) {
                        if (chessBoard.board[line][i] == this && i == getMax(column, toColumn)) return false;
                        else if (chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn)
                            return false;
                        else if (!chessBoard.board[line][i].getColor().equals(this.color) && i == toColumn)
                            return true;
                        else if (i != toLine && i != column) return false;
                    }
                }

                if (chessBoard.board[toLine][toColumn] != null) {
                    if (chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this)
                        return false;
                    else return !chessBoard.board[toLine][toColumn].getColor().equals(this.color) && chessBoard.board[toLine][toColumn] != this;
                } else return true;
            } else return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    public int getMax(int a, int b) {
        return Math.max(a, b);
    }

    public int getMin(int a, int b) {
        return Math.min(a, b);
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}