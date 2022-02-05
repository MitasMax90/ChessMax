public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // создали поле 8х8 (board) для игры
    String nowPlayer; // текущий игрок

    public ChessBoard(String nowPlayer) { this.nowPlayer = nowPlayer; }

    public String nowPlayerColor() { return this.nowPlayer; }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {  // проверяем существование клетки на доске
            // если текущий игрок за белых не совпадает с цветом фигуры на клетке - ход невозможен!!! (нельзя играть чужими фигурами)
            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            // если данная фигура может быть сдвинута в указанную позицию
            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {

                // Уточняем про рокировку, если фигура - король или ладья, ...*
                if (board[startLine][startColumn].getSymbol().equals("K") || board[startLine][startColumn].getSymbol().equals("R")) {
                    board[startLine][startColumn].check = false; // *... если (K or R) двигались, передаём переменной check значение false
                }

                // Если была возможность, передвигаем фигуру с исходных точек (стартовых) на установленные
                board[endLine][endColumn] = board[startLine][startColumn];

                // и удаляем фигуру с исходного местоположения (откуда только что стартовали)
                board[startLine][startColumn] = null;
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  // выводим информацию о доске
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        if (nowPlayer.equals("White")) {
            if (board[0][0] == null || board[0][4] == null) return false;
            if (board[0][0].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // check that King and Rook
                    board[0][1] == null && board[0][2] == null && board[0][3] == null) {              // never moved
                if (board[0][0].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][0].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 2)) { // check that position not in under attack
                    board[0][4] = null;
                    board[0][1] = new King("White");   // движение королём
                    board[0][1].check = false;
                    board[0][0] = null;
                    board[0][2] = new Rook("White");   // движение ладьёй
                    board[0][2].check = false;
                    nowPlayer = "Black";  // передаём ход
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][0] == null || board[7][4] == null) return false;
            if (board[7][0].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // check that King and Rook
                    board[7][1] == null && board[7][2] == null && board[7][3] == null) {              // never moved
                if (board[7][0].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][0].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 2)) { // check that position not in under attack
                    board[7][4] = null;
                    board[7][1] = new King("Black");   // движение королём
                    board[7][1].check = false;
                    board[7][0] = null;
                    board[7][2] = new Rook("Black");   // движение ладьёй
                    board[7][2].check = false;
                    nowPlayer = "White";  // передаем ход
                    return true;
                } else return false;
            } else return false;
        }
    }

    public boolean castling7() {
        if (nowPlayer.equals("White")) {
            if (board[0][7] == null || board[0][4] == null) return false;
            if (board[0][7].getSymbol().equals("R") && board[0][4].getSymbol().equals("K") && // Проверяем ладью и короля на движения
                    board[0][6] == null && board[0][5] == null) {
                if (board[0][7].getColor().equals("White") && board[0][4].getColor().equals("White") &&
                        board[0][7].check && board[0][4].check &&
                        !new King("White").isUnderAttack(this, 0, 6)) { // проверяем позицию, не под атакой ли она
                    board[0][4] = null;
                    board[0][6] = new King("White");   // движение королем
                    board[0][6].check = false;
                    board[0][7] = null;
                    board[0][5] = new Rook("White");   // движение ладьёй
                    board[0][5].check = false;
                    nowPlayer = "Black";  // next turn
                    return true;
                } else return false;
            } else return false;
        } else {
            if (board[7][7] == null || board[7][4] == null) return false;
            if (board[7][7].getSymbol().equals("R") && board[7][4].getSymbol().equals("K") && // Проверяем ладью и короля на движения
                    board[7][6] == null && board[7][5] == null) {
                if (board[7][7].getColor().equals("Black") && board[7][4].getColor().equals("Black") &&
                        board[7][7].check && board[7][4].check &&
                        !new King("Black").isUnderAttack(this, 7, 6)) { // проверяем позицию, не под атакой ли она
                    board[7][4] = null;
                    board[7][6] = new King("Black");   // движение королем
                    board[7][6].check = false;
                    board[7][7] = null;
                    board[7][5] = new Rook("Black");   // движение ладьёй
                    board[7][5].check = false;
                    nowPlayer = "White";  // передаем ход
                    return true;
                } else return false;
            } else return false;
        }
    }
}

