package com.company;

// Based On Idea On https://www.geeksforgeeks.org/n-queen-problem-backtracking-3/; But I raised it to reach the requested problem :)

public class Eight_Minister {
    static int solutions1 = 0;
    final int MAX_SOLUTIONS = 100000;
    int N = 8;
    int [][][] stored_boards = new int[MAX_SOLUTIONS][N][N];

    int board_template[][] = {
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 2, 0, 0, 2, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 2, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    int [][] board = board_template;

     void printSolution(int board[][], String toPrint)
    {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(toPrint);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();

    }

    boolean isSafe( int row, int col) {
        int i, j;

        /* Check this row on left side */
        /*
        **i |
        ___ | ____
            |
            |
         */

        int obstacle_index = -1;
        int minister = -1;
        for (i = 0; i < col; i++) {
            if (board[row][i] == 2) {
                obstacle_index = i;
            } else if (board[row][i] == 1)
                minister = i;
        }
        if (obstacle_index < minister){
            return false;
        }

        /* Check this row on left side */
        /*
        i** |
        ___ | ____
            |
            |
         */

        obstacle_index = -1;
        minister = -1;
        for (i = col; i < N; i++) {
            if (board[row][i] == 2) {
                obstacle_index = i;
            } else if (board[row][i] == 1)
                minister = i;
        }
        if (obstacle_index < minister){
            return false;
        }

        /* Check this column on up side */
        /*
          * |
        __* | ____
          i |
            |
         */
        obstacle_index = -1;
        minister = -1;
        for (i = 0; i < row; i++) {
            if (board[i][col] == 2) {
                obstacle_index = i;

            } else if (board[i][col] == 1){
                minister = i;

            }

        }
        if (obstacle_index < minister){
            return false;
        }

        /* Check this column on down side */
        /*
            |
        __i | ____
          * |
          * |
         */
        obstacle_index = -1;
        minister = -1;
        for (i = row; i < N; i++) {
            if (board[i][col] == 2) {
                obstacle_index = i;
            } else if (board[i][col] == 1)
                minister = i;
        }
        if (obstacle_index < minister){
            return false;
        }

        /* Check upper diagonal on left side */
        /*
          i |
        ___o|____
            |
            |
         */
        int i_obstacle = -1;
        int j_obstacle = -1;
        int i_minister = -1;
        int j_minister = -1;
        boolean i_flag = true;
        boolean o_flag = true;

        for (i = row, j = col; i >= 0 && j >= 0 ; i--, j--) {
            if (board[i][j] == 2 && i_flag) {
                i_obstacle = i;
                j_obstacle = j;
                i_flag = false;
            } else if (board[i][j] == 1 && o_flag) {
                i_minister = i;
                j_minister = j;
                o_flag = false;
            }
        }
        if (i_minister > i_obstacle && j_minister > j_obstacle)
            return false;

        // Check lower diagonal on left side
        /*
            |
        ____|____
           i|
          o |
         */
        i_obstacle = -1;
        j_obstacle = -1;
        i_minister = -1;
        j_minister = -1;
        i_flag = true;
        o_flag = true;

        for (i = row, j = col; i < N && j >= 0; i++, j--) {
            if (board[i][j] == 2 && o_flag) {
                i_obstacle = i;
                j_obstacle = j;
                o_flag = false;
            } else if (board[i][j] == 1 && i_flag) {
                i_minister = i;
                j_minister = j;
                i_flag = false;
            }
        }
        if (!o_flag) {
            if (i_minister < i_obstacle && j_minister > j_obstacle)
                return false;
        } else {
            if (!i_flag)
                return false;
        }


        /* Check upper diagonal on left side */
        /*
           |
        ___|____
           |i
           | o
         */
         i_obstacle = -1;
         j_obstacle = -1;
         i_minister = -1;
         j_minister = -1;
         i_flag = true;
         o_flag = true;

        for (i = row, j = col; i < N && j < N ; i++, j++) {
            if (board[i][j] == 2 && i_flag) {
                i_obstacle = i;
                j_obstacle = j;
                i_flag = false;
            } else if (board[i][j] == 1 && o_flag) {
                i_minister = i;
                j_minister = j;
                o_flag = false;
            }
        }
        if (i_minister > i_obstacle && j_minister > j_obstacle)
            return false;

        // Check lower diagonal on left side
        /*
            | o
        ____|i____
            |
            |
         */
        i_obstacle = -1;
        j_obstacle = -1;
        i_minister = -1;
        j_minister = -1;
        i_flag = true;
        o_flag = true;

        for (i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 2 && o_flag) {
                i_obstacle = i;
                j_obstacle = j;
                o_flag = false;
            } else if (board[i][j] == 1 && i_flag) {
                i_minister = i;
                j_minister = j;
                i_flag = false;
            }
        }
        if (!o_flag) {
            if (i_minister < i_obstacle && j_minister > j_obstacle)
                return false;
        } else
        if (!i_flag)
            return false;



        return true;
    }

     void solve8minister(int [][] board, int col, int [][][] stored_boards) {
        if (full_replacement(board)) {
            if (notDuplicated(board, stored_boards)) {
                addToList(board, stored_boards, solutions1);
                solutions1++;
            }
            return;
        }
        if (col < N) {
            int j;
            int flag1 = 0;
            for (int i = 0; i < N; i++) {
                if (board[i][col] == 2 || !isSafe(i, col)) {
                    flag1++;
                    continue;
                }
                board[i][col] = 1;
                int flag2 = 0;
                for (j= i; j < N; j++) {
                    if (board[j][col] == 2 || !isSafe(j, col)) {
                        flag2++;
                        continue;
                    }

                    //placement the minister
                    board[j][col] = 1;
                    //go through the next column to check the placement of others
                    solve8minister(board, col+1, stored_boards);
                    board[j][col] = 0;
                }
                if (flag2 == N)
                    solve8minister(board, col+1, stored_boards);
                solve8minister(board, col+1, stored_boards);
                board[i][col] = 0;
            }
            if (flag1 == N)
                solve8minister(board, col+1, stored_boards);

        }


    }

    public void addToList(int [][] board, int [][][] stored_boards, int num){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                stored_boards[num][i][j] = board[i][j];
            }
        }
    }

    public boolean notDuplicated(int [][] board, int [][][] stored_boards){
        if (solutions1 == 0)
            return true;
        int flag = 0;
        boolean flag2 ;
        for (int i = 0; i < solutions1; i++) {
            flag2 = true;
            for (int j = 0; j < N && flag2; j++) {
                for (int k = 0; k < N && flag2; k++) {
                    if (stored_boards[i][j][k] != board[j][k]) {
                        flag++;
                        flag2 = false;
                        break;
                    }
                }
            }
        }
        if (flag == solutions1)
            return true;
        return false;
    }

    public boolean full_replacement(int [][] board){
         int num = 0;
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1)
                    num++;
            }
        }
        if (num == N)
            return true;
        return false;
    }

    public boolean main_func() {
        reloadBoard(board_template);
        printSolution(board, "Template Board:");

        System.out.println("Placement Modes Of 8 Minister In This Board:");
        for (int i = 0; i < N; i++) {
            solve8minister(board,i,stored_boards);
        }

        int num = 1;
        for (int i = 0; i < solutions1; i++)
            printSolution(stored_boards[i], num++ + ". ");

        System.out.println("Number Of Solutions:");
        System.out.println(solutions1);
        return true;
    }

     void reloadBoard(int[][] board1){
        board = board1;
    }

}

