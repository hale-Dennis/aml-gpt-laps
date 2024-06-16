import java.util.Scanner;

public class PeakColumn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Exception handling for when user inputs a non-digit value or when matrix elements entered does'nt match dimensions given
        try{
            //receiving input for matrix 
            System.out.print("Matrix A: ");
            String[] dimension = scanner.nextLine().split(",");            //receiving matrix dimensions
            int row = Integer.parseInt(dimension[0].trim());
            int col = Integer.parseInt(dimension[1].trim());
            int[][] matrix = new int[row][col];
            StringBuilder index = new StringBuilder();
            String[] line;

            //receiving matrix elemets 
            for(int i = 0; i<row; i++){
                line = scanner.nextLine().trim().split("\\s+");
                if(line.length != col)
                    throw new ArrayIndexOutOfBoundsException();
                for(int j=0; j<col; j++){
                    matrix[i][j] = Integer.parseInt(line[j].trim());
                }
            }

            //printing peak column
            printPeakColumn(matrix, row, col, index);

        }
        catch (NumberFormatException e){
            System.out.println("Input only digits for matrix elements");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Elements entered does not match the initial dimensions of the matrix given\nCheck input and restart program");
        }

        scanner.close();

    }
    public static void printPeakColumn(int[][] matrix, int rowLength, int columnLength, StringBuilder peakElement){
        //method finds and print peak column
        for(int i=0; i<rowLength; i++){
            for(int j=0; j<columnLength; j++){
                if(maxElementInRow(matrix,i) == minElementInColumn(matrix, j)){
                    peakElement = new StringBuilder("(" + (i + 1) + "," + (j + 1) + ")" + " = " + matrix[i][j]);
                    System.out.println(peakElement);
                }
            }
            peakElement = new StringBuilder("");
        }
    }
    public static int minElementInColumn(int[][] matrix, int columnIndex) {
        //method returns minimum element in a particular column
        int minElement = matrix[0][columnIndex];

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][columnIndex] < minElement) {
                minElement = matrix[i][columnIndex];
            }
        }

        return minElement;
    }
    public static int maxElementInRow(int[][] matrix, int rowIndex) {
        //method returns maximum element in a particular row 
        int maxElement = matrix[rowIndex][0];

        for (int j = 1; j < matrix[rowIndex].length; j++) {
            if (matrix[rowIndex][j] > maxElement) {
                maxElement = matrix[rowIndex][j];
            }
        }

        return maxElement;
    }
} 
