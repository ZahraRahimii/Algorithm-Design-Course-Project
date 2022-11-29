package com.company;

public class Main {
    public static void main(String [] args){
        Weighted_Colored_Cube colored_weighted_cube = new Weighted_Colored_Cube();
        colored_weighted_cube.main_func();
    }
    private static void this_example(int n) {

        if (n<=1)
            return;

        for (int i=0; i < n*n; i++){
            this_example(n / 2);
            this_example(n / 4);
            this_example(n / 4);
        }
    }


}




