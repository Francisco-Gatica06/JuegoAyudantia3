package org.example;

public class MatrixGame {
    public static void main(String[] args) {
        String[][] matrix = new String[10][10];
        showMap(matrix);

    }

    public static void showMap(String[][] matrix) {
        createOutlineMap(matrix);
        createPlayZoneMap(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void createOutlineMap (String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = "#";
            matrix[i][9] = "#";
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[0][j] = "#";
                matrix[9][j] = "#";
            }
        }
    }

    public static void createPlayZoneMap (String[][] matrix) {
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                matrix[i][j] = "-";
            }
        }
    }
}

