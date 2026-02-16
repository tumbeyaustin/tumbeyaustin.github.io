
    public static double[][] matrixMultiply(double[][] matrixA, double[][] matrixB) {
        // Get the dimensions
        int aRows = matrixA.length;
        int aCols = matrixA[0].length;
        int bRows = matrixB.length;
        int bCols = matrixB[0].length;

        // Make the result matrix
        double[][] matrixC = new double[aRows][bCols];

        // Do the multiplication
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                double sum = 0;
                for (int k = 0; k < aCols; k++) {
                    sum = sum + matrixA[i][k] * matrixB[k][j];
                }
                matrixC[i][j] = sum;
            }
        }

        return matrixC;
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Test matrices
        double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
        double[][] matrixB = {{7, 8}, {9, 10}, {11, 12}};

        printMatrix(matrixA);
        printMatrix(matrixB);
        double[][] matrixC = matrixMultiply(matrixA, matrixB);
        printMatrix(matrixC);
    }

