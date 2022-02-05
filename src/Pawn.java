public class Pawn extends ChessPiece {

    public Pawn(String color) { super(color); }

    @Override
    public String getColor() { return this.color; }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {

        // Если все координаты существуют!!!
        if (checkPos(line) && checkPos(column) && checkPos(toLine) && checkPos(toColumn)
                // И в данной клетке что-то есть (она не пустая, не равна null)
                && chessBoard.board[line][column] != null) {

            // Сначала описываем ходьбу пешки прямо, в рамках своей колонки. Для белых ход будет положительный вектор движения +, для черных отрицательный -
            if (column == toColumn) {
                int dir; // на сколько клеток вперёд
                int start;

                if (color.equals("White")) {  // для белых фигур
                    dir = 1; // белые идут
                    start = 1; // начальная линия
                } else { // для чёрных
                    dir = -1; // черные идут
                    start = 6; // начальная позиция черных пешек - линия 6
                }
                // проверяем, можно ли ходить в конечную клетку
                if (line + dir == toLine) {
                    // если клетка свободна, то можно в неё шагнуть
                    return chessBoard.board[toLine][toColumn] == null;
                }
                // а если мы со старта идём пешкой на две клетки, 2 мы умножаем на направление вектора (+1 или -1), т.е. в зависимости от цвета идем на +2 or -2
                if (line == start && line + 2 * dir == toLine) {
                    // проверяем, если клетка свободна и на её пути нет фигур
                    return chessBoard.board[toLine][toColumn] == null && chessBoard.board[line + dir][column] == null;

                }
            } else { // если пошла возня (драка фигур), мы должны описать путь пешки по диагонали на 1 клетку
            // если по колонке (линии) шагаем...*
                 if ((column - toColumn == 1 || column - toColumn == -1) &&
                        (line - toLine == 1 || line - toLine == -1) &&
                         // *... и там что-либо стоит, то...
                        chessBoard.board[toLine][toColumn] != null) {
                         // цвет фигуры не текущий, вернём true
                    return !chessBoard.board[toLine][toColumn].getColor().equals((color));
                 } else return false; // иначе false

            }
        }
        return false;
    }

    @Override
    public String getSymbol() { return "P"; }
}