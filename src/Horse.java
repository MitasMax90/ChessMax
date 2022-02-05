public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        // Если все координаты существуют!!!
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)) {
            // Стартовая позиция не равна конечной
            if (line != toLine && column != toColumn
                    // И       конечная клетка пустая             ИЛИ цвет фигуры в конечной клетке НЕ         равен текущему цвету
                    && (chessBoard.board[toLine][toColumn] == null || !chessBoard.board[toLine][toColumn].color.equals(this.color)
                          // И стартовая клетка не пустая       (не равна нулю)
                            && chessBoard.board[toLine][toColumn] != null)) {

                // если стартовая ячейка не равна Horse, не ходим
                if (!chessBoard.board[line][column].equals(this)) { return false; }

                // С помощью этого массива, задаем Horse правила прохода по доске
                int[][] positions = new int[][]{
                        {line - 2, column - 1}, {line - 2, column + 1},
                        {line + 2, column - 1}, {line + 2, column + 1},
                        {line - 1, column - 2}, {line - 1, column + 2},
                        {line + 1, column - 2}, {line + 1, column + 2}};

                // Проверяем, можно ли сходить в нужную нам позицию
                for (int i = 0; i < positions.length; i++) {
                    if (positions[i][0] == toLine && positions[i][1] == toColumn)
                        return true;

                }
            }

        } else return false;
        return true;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}

