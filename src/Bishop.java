public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Стартовая позиция не равна конечной И
        if (line != toLine && column != toColumn &&
                // Ходит по диагонали И
                getMax(line, toLine) - getMin(line, toLine) == getMax(column, toColumn) - getMin(column, toColumn) &&
                // все координаты существуют И
                checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn) &&
                // и конечная точка пуста или на ней стоит фигура другого цвета И
                (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)) &&
                // стартовая клетка пуста
                chessBoard.board[line][column] != null) {
            // Сверху слева - вниз направо
            if ((column == getMin(column, toColumn) && line == getMax(line, toLine)) ||
                    (toColumn == getMin(column, toColumn) && toLine == getMax(line, toLine))) {
                // max и min для того, чтобы делать обратный ход
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);

                // возможние ходы для слона
                int[][] positions = new int[toC - fromC][1];

                // число колонок = числу линий, пройденных слоном
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                        positions[i - 1] = new int[]{fromL - i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            } else {
            // сверху справа - вниз налево
                int fromL = getMax(line, toLine);
                int fromC = getMin(column, toColumn);
                int toL = getMin(line, toLine);
                int toC = getMax(column, toColumn);
                //positions which bishops walk by
                int[][] positions = new int[toC - fromC][1];
                // число колонок = числу линий, пройденных слоном
                for (int i = 1; i < toC - fromC; i++) {
                    if (chessBoard.board[fromL - i][fromC + i] == null) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else if (!chessBoard.board[fromL - i][fromC + i].color.equals(this.color) && fromL - i == toLine) {
                        positions[i - 1] = new int[]{fromL + i, fromC + i};
                    } else {
                        return false;
                    }
                }
                return true;
            }
        } else return true;

    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
