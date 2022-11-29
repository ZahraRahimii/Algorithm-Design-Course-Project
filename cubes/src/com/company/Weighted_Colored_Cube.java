package com.company;

import java.util.*;

public class Weighted_Colored_Cube {
    public int MAX_SOLUTIONS = 1000;
    static final int N = 18;
    public Cube[] cubes;
    // to hash weight index in max heap to the cube index
    public  HashMap<Integer, Integer> cudexToweiex;
    public  String colors[] = {"red", "blue", "green", "yellow", "gray", "black", "pink", "silver", "purple", "white", "lime", "brown"};
    public  int[][] cube_face = new int[6][N];
    public  int [][][] stored_cubes = new int[MAX_SOLUTIONS][6][N];
    public static int solutions = 0;
    public static int max_sum = 0;
    public int [] max_weight_array;

    public void main_func() {
        // make colored cubes and add their weights to max heap
        cubes = new Cube[N];
        set_cubes();

        for (int i = 0; i < N; i++) {
            solve_cube(cube_face, 0, stored_cubes, i);
        }

        show_best_solutions(stored_cubes);
    }


    public void heapToArray(){
        max_weight_array = new int[N];
        for (int i = 0; i < cubes.length; i++) {
            if (cubes[i] != null){
                max_weight_array[i] = cubes[i].getWeight();
            }
        }
        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < max_weight_array.length - 1; i++) {
                if (max_weight_array[i] < max_weight_array[i+1]) {
                    swap(i, i+1, max_weight_array);
                    sorted = false;
                }
            }
        }
    }

    private void swap(int fpos, int spos, int [] Heap) {
        int fpos_preId = cudexToweiex.get(fpos);
        int spos_preId = cudexToweiex.get(spos);

        int tmp = Heap[fpos];
        Heap[fpos] = Heap[spos];
        Heap[spos] = tmp;

        cudexToweiex.put(fpos, spos_preId);
        cudexToweiex.put(spos, fpos_preId);
    }


    public void show_best_solutions(int [][][] stored_cubes) {
        for (int i = 0; i < solutions; i++) {
            if (getSum(stored_cubes[i]) == max_sum)
                print_cube_tower(stored_cubes[i]);
        }
    }


    public void solve_cube(int [][] cube_face, int col, int[][][] cubes_list, int from){

        if (col == N){
            if (notDuplicated(cube_face, cubes_list) && hasAtLeastMaxSum(cube_face)) {

                int sum = addToList(cube_face, cubes_list, solutions++);
                if (sum > max_sum)
                    max_sum = sum;
            }
            return;
        }

        String now_top = "";
        int [][] seen = new int[6][N];

        int cube_id;
        cube_id = cudexToweiex.get(col);

        if (col != from){
            now_top = getTopColor(cube_face, col);
            int flag = 0;
            for (int i = 0; i < 6; i++) {
                seen[i][col] = 1;
                if (now_top != getColor(i, cube_id) || cube_face[i][col] != 0){
                    flag++;
                    continue;
                }
                cube_face[i][col] = 1;
                solve_cube(cube_face, col + 1, cubes_list, from);
                cube_face[i][col] = 0;
            }

            if (flag == 6) {
                solve_cube(cube_face, col + 1, cubes_list,from);
            }

        } else {
            for (int i = 0; i < 6; i++) {
                cube_face[i][col] = 1;
                solve_cube(cube_face, col+1, cubes_list, from);
                cube_face[i][col] = 0;
            }
        }

        return;
    }

    public int getSum(int [][] cube_face){
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < N; j++) {
                if (cube_face[i][j] == 1){
                    sum++;
                }
            }
        }
        return sum;
    }

    public boolean notDuplicated(int [][] cube_face, int [][][] cube_list){
        if (solutions == 0)
            return true;
        int flag = 0;
        boolean flag2 ;
        for (int i = 0; i < solutions; i++) {
            flag2 = true;
            for (int j = 0; j < 6 && flag2; j++) {
                for (int k = 0; k < N && flag2; k++) {
                    if (cube_list[i][j][k] != cube_face[j][k]) {
                        flag++;
                        flag2 = false;
                        break;
                    }
                }
            }
        }
        if (flag == solutions)
            return true;
        return false;
    }

    public boolean hasAtLeastMaxSum(int [][] cube_face){
        if (solutions == 0)
            return true;
        boolean flag = false;
        int sum = getSum(cube_face);
        for (int i = 0; i < solutions; i++) {
            if (sum > max_sum) {
                flag = true;
                solutions--;
            }
        }
        return flag;
    }

    public int addToList(int [][] cube_face, int [][][] cube_list, int num){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < N; j++) {
                cube_list[num][i][j] = cube_face[i][j];
            }
        }
        return getSum(cube_face);
    }

    public void print_cube_tower(int [][] cube_face){
        int sum = 0;
        System.out.println();
        System.out.println();
        for (int j = 0; j < N; j++){
            for (int i = 0; i < 6; i++) {
                if (cube_face[i][j] == 1){
                    int cube_id = cudexToweiex.get(j);
                    String side = getSide(i);
                    System.out.println("*******************");
                    System.out.println("    Cube "+ cube_id);
                    System.out.println("   " + side);
                    System.out.println("*******************");
                    sum++;
                }
            }
        }
        System.out.println("Tower Of Cubes With Length Of " + sum );
        System.out.println();

    }

    private String getSide(int side){
        switch (side){
            case 0:
                return "From Bottom";
            case 1:
                return "From Top";
            case 2:
                return "From Left";
            case 3:
                return "From Right";
            case 4:
                return "From Front";
            case 5:
                return "From Back";
            default:
                return "";
        }
    }

    public String getTopColor(int [][] cube_face,int col){

        for (int j = col-1; j >= 0 ; j--) {
            for (int i = 0; i < 6; i++){
                if (cube_face[i][j] == 1) {
                    return getTopOfTop(cudexToweiex.get(j), i);
                }
            }
        }
        return "";
    }

    private String getTopOfTop(int cube, int i) {
        switch (i){
            case 0:
                return cubes[cube].getTop();
            case 1:
                return cubes[cube].getBottom();
            case 2:
                return cubes[cube].getRight();
            case 3:
                return cubes[cube].getLeft();
            case 4:
                return cubes[cube].getBack();
            case 5:
                return cubes[cube].getFront();
            default:
                return "";
        }
    }


    public String getColor(int num, int cube){
        switch (num){
            case 0:
                return cubes[cube].getBottom();
            case 1:
                return cubes[cube].getTop();
            case 2:
                return cubes[cube].getLeft();
            case 3:
                return cubes[cube].getRight();
            case 4:
                return cubes[cube].getFront();
            case 5:
                return cubes[cube].getBack();
            default:
                return "";
        }
    }


    public void set_cubes(){
        int i = 0;

        Cube cube0 = new Cube("silver", "blue", "lime", "yellow", "pink", "black", 8);
        cubes[i++] = cube0;
        Cube cube1 = new Cube("red", "red", "red", "red", "red", "red", 32);
        cubes[i++] = cube1;
        Cube cube2 = new Cube("silver", "blue", "lime", "yellow", "pink", "black", 40);
        cubes[i++] = cube2;
        Cube cube3 = new Cube("red", "blue", "green", "yellow", "gray", "black", 28);
        cubes[i++] = cube3;
        Cube cube4 = new Cube("white", "blue", "green", "pink", "gray", "black", 26);
        cubes[i++] = cube4;
        Cube cube5 = new Cube("red", "blue", "pink", "green", "gray", "black", 24);
        cubes[i++] = cube5;
        Cube cube6 = new Cube("lime", "pink", "green", "white", "gray", "black", 34);
        cubes[i++] = cube6;
        Cube cube7 = new Cube("red", "blue", "silver", "yellow", "gray", "lime", 26);
        cubes[i++] = cube7;
        Cube cube8 = new Cube("yellow", "pink", "green", "white", "gray", "black", 18);
        cubes[i++] = cube8;
        Cube cube9 = new Cube("silver", "blue", "lime", "yellow", "pink", "black", 42);
        cubes[i++] = cube9;
        Cube cube10 = new Cube("red", "blue", "green", "yellow", "gray", "black", 32);
        cubes[i++] = cube10;
        Cube cube11 = new Cube("blue", "yellow", "red", "green", "black", "silver", 30);
        cubes[i++] = cube11;
        Cube cube12 = new Cube("red", "blue", "green", "yellow", "gray", "black", 10);
        cubes[i++] = cube12;
        Cube cube13 = new Cube("white", "blue", "green", "pink", "gray", "black", 12);
        cubes[i++] = cube13;
        Cube cube14 = new Cube("red", "blue", "pink", "green", "gray", "black", 24);
        cubes[i++] = cube14;
        Cube cube15 = new Cube("lime", "pink", "green", "white", "gray", "black", 35);
        cubes[i++] = cube15;
        Cube cube16 = new Cube("red", "blue", "silver", "yellow", "gray", "lime", 25);
        cubes[i++] = cube16;
        Cube cube17 = new Cube("yellow", "pink", "green", "white", "gray", "black", 15);
        cubes[i++] = cube17;


        // initialize the hash map with i -> i
        cudexToweiex = new HashMap<Integer, Integer>();
        for (int j = 0; j < cubes.length; j++) {
            cudexToweiex.put(j, j);
        }

        heapToArray();
    }


}

class Cube {
    private String bottom;
    private String top ;
    private String left ;
    private String right ;
    private String front;
    private String back;
    private int weight;

    Cube(String bottom1, String top1, String left1, String right1, String front1, String back1, int w){
        bottom = bottom1;
        top = top1;
        left = left1;
        right = right1;
        front = front1;
        back = back1;
        weight = w;
    }

    public int getWeight() {
        return weight;
    }

    public String getTop() {
        return top;
    }

    public String getBottom() {
        return bottom;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

}
