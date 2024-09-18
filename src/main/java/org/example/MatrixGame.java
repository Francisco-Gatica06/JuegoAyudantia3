package org.example;
import java.util.Random;
import java.util.Scanner;

public class MatrixGame {
    public static void main (String[] args) {
        String[][] matrix = new String[10][10];
        try (Scanner scanner = new Scanner(System.in)) {
            startGame(matrix);
            moveMainCharacter(matrix, scanner);
        }
    }

    public static void startGame(String[][] matrix) {
        createOutlineMap(matrix);
        createPlayZoneMap(matrix);
        generateObstacle(matrix);
        generateChest(matrix);
        mainCharacter(matrix);
        enemy(matrix);
    }

    public static void showMap (String[][] matrix) {
        for (String[] strings : matrix) {
            for (String string : strings) {
                System.out.print(string + " ");
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
                matrix[i][j] = ".";
            }
        }
    }

    public static void generateObstacle (String[][] matrix) {
        Random random = new Random();
        int counter = 0;
        int max = 8;
        int min = 1;
        while (counter < 17) {
            int random1 = random.nextInt((max - min) + 1) + min;
            int random2 = random.nextInt((max - min) + 1) + min;
            matrix[random1][random2] = "#";
            counter++;
        }
    }

    public static void generateChest (String[][] matrix) {
        Random random = new Random();
        int max = 8;
        int min = 1;
        int random1 = random.nextInt((max - min) + 1) + min;
        int random2 = random.nextInt((max - min) + 1) + min;
        matrix[random1][random2] = "C";
    }

    public static int[] mainCharacter (String[][] matrix) {
        matrix[1][1] = "P";
        int[] dataCharacter = {1, 1, 100, 15};
        return dataCharacter;
    }

    public static void moveMainCharacter(String[][] matrix, Scanner scanner) {
        int[] characterPosition = getPosition(matrix);
        int x = characterPosition[0];
        int y = characterPosition[1];

        while (true) {
            showMap(matrix);
            String moveDirection = getMoveDirection(scanner);

            if (moveDirection.equals("q")) {
                System.out.println("Saliendo del juego...");
                return;
            } else if (moveDirection.equals("h")) {
                printHelp();
                continue;
            }

            int[] newPosition = updatePosition(x,y,moveDirection);

            if (isMoveValid(matrix, newPosition)) {
                matrix[x][y] = ".";
                x = newPosition[0];
                y = newPosition[1];
                matrix[x][y] = "P";
            } else {
                System.out.println("Movimiento fuera de los límites o hacia un obstáculo.");
            }
        }
    }

    public static int[] getPosition(String[][] matrix) {
        for (int i = 1; i < matrix.length - 1; i++) {
            for (int j = 1; j < matrix[i].length - 1; j++) {
                if (matrix[i][j].equals("P")) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void enemy(String[][] matrix) {
        Random random = new Random();
        int max = 8;
        int min = 1;
        int random1 = random.nextInt((max - min) + 1) + min;
        int random2 = random.nextInt((max - min) + 1) + min;
        matrix[random1][random2] = "E";
    }

    public static boolean isMoveValid(String[][] matrix, int[] newPosition) {
        int newX = newPosition[0];
        int newY = newPosition[1];
        return !matrix[newX][newY].equals("#");
    }

    public static String getMoveDirection(Scanner scanner) {
        System.out.print("Movimientos: (w/a/s/d), h (help) para ayuda, q para salir): ");
        if (scanner.hasNext()) {
            return scanner.next().toLowerCase();
        }
        return "";
    }

    public static int[] updatePosition (int x, int y, String direction) {
        int newX = x;
        int newY = y;

        switch (direction) {
            case "w":
                newX = x - 1;
                break;
            case "s":
                newX = x + 1;
                break;
            case "a":
                newY = y - 1;
                break;
            case "d":
                newY = y + 1;
                break;
            default:
                System.out.println("Not valid interacction. ");
                break;
        }
        return new int[]{newX, newY};
    }

    public static void printHelp () {
        System.out.println("Movimientos:");
        System.out.println("w: Mover arriba");
        System.out.println("a: Mover izquierda");
        System.out.println("s: Mover abajo");
        System.out.println("d: Mover derecha");
        System.out.println("q: Salir del juego");
    }
}

