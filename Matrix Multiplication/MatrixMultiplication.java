import java.util.Scanner;


public class MatrixMultiplication{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        //Exception handling for when user inputs a non-digit value or when matrix elements entered does'nt match dimensions given
        try{
            //receiving input for matrix 
            System.out.print("Matrix A: ");
            String[] dimensionA = scanner.nextLine().split(",");            //receiving matrix dimensions
            int rowA = Integer.parseInt(dimensionA[0].trim());
            int colA = Integer.parseInt(dimensionA[1].trim());
            int[][] matrixA = new int[rowA][colA];
            
            //receive elements for matrixA
            receiveMatrixElements(matrixA, scanner);

            //receiving input for matrix B
            System.out.print("Matrix B: ");
            String[] dimensionB = scanner.nextLine().split(",");            //receiving matrix dimensions
            int rowB = Integer.parseInt(dimensionB[0].trim());
            int colB = Integer.parseInt(dimensionB[1].trim());
            int[][] matrixB = new int[rowB][colB];
           
            //receive elements for matrixB
            receiveMatrixElements(matrixB, scanner);


            int[][] matrixC = multiplyMatrices(matrixA, matrixB);
            printMatrix(matrixC);                                                   //print result of the multiplication
       
        }
        catch (NumberFormatException e){
            System.out.println("Input only digits for matrix elements");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Elements entered does not match the initial dimensions of the matrix given\nCheck input and restart program");
        }
        catch (IllegalArgumentException e){
            System.out.println("Matrices cannot be multiplied");
        }

        scanner.close();

    }

    public static void receiveMatrixElements(int[][] matrix, Scanner scanner){
        //method to receive matrix elements from user input
        String[] line;
        int row = matrix.length;
        int col = matrix[0].length;
        for(int i = 0; i < row; i++){
            line = scanner.nextLine().trim().split("\\s+");
            if(line.length != col)
                throw new ArrayIndexOutOfBoundsException();
            for(int j=0; j<col; j++){
                matrix[i][j] = Integer.parseInt(line[j].trim());
            }
        }


    }

    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        //method multiplies matrix1 and matrix2
        int matrix1Rows = matrix1.length;
        int matrix1Columns = matrix1[0].length;
        int matrix2Rows = matrix2.length;
        int matrix2Columns = matrix2[0].length;
        
        if (matrix1Columns != matrix2Rows) {                                            //checks if col of matrix 1 is equal to row of matrix 2
            throw new IllegalArgumentException("Matrices cannot be multiplied");
        }
        
        int[][] result = new int[matrix1Rows][matrix2Columns];
        
        for (int i = 0; i < matrix1Rows; i++) {
            for (int j = 0; j < matrix2Columns; j++) {
                for (int k = 0; k < matrix1Columns; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        //method to print resulting matrix
        System.out.println("Matrix C:");
        for (int[] row : matrix) {
            System.out.print("| ");
            for (int element : row) {
                System.out.printf("%d %s", element, " ");
            }
            System.out.println("|");
        }
    }
}

