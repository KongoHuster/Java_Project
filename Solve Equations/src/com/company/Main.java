
package com.company;
import Jama.Matrix;


public class Main {

    public static void main(String[] args) {
        // write your code here
        double[][] array1 = {{2, 1, -5, 1}, {1, -3, 0, -6}, {0, 2, -1, 2}, {1, 4, -7, 6}};
        double[][] array2 = {{8}, {9}, {-5}, {0}};


        Solve_Equations(array1, array2);

    }

    public static double[] Solve_Equations(double[][] arrayA, double[][] arrayB)
    {

        if(arrayA[0].length != arrayA.length)
        {
            System.out.print("ArrayA's row and arrayA's line is not equal.");
            double[] arrayZero = {0};
            return arrayZero;
        }

        if(arrayB.length != arrayA.length)
        {
            System.out.print("arrayA's row and arrayB's row is not equal.");
            double[] arrayZero = {0};
            return arrayZero;
        }

        Matrix matrixA = new Matrix(arrayA);
        Matrix matrixB = new Matrix(arrayB);

        double D = matrixA.det();

        if(D == 0)
        {
            System.out.print("The rank of arrayA is 0.");
            double[] arrayZero = {0};
            return arrayZero;
        }

        double[] result = new double[arrayB.length];

        for (int i = 0; i < arrayB.length; i +=1)
        {
            Matrix matrixD = matrixA.copy();
            for(int j = 0; j < arrayB.length; j += 1)
            {
                matrixD.set(j, i, matrixB.get(j, 0));
            }

            result[i] = matrixD.det()/D;
        }

        return result;
    }

}


