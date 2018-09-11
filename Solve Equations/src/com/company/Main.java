
package com.company;
import Jama.Matrix;


public class Main {

    public static void main(String[] args) {
        // write your code here
        double[][] array1 = {{2, 1, -5, 1}, {1, -3, 0, -6}, {0, 2, -1, 2}, {1, 4, -7, 6}};
        double[][] array2 = {{8}, {9}, {-5}, {0}};

        double[] result = Solve_Equations(array1, array2);
        for (int k =0; k < result.length; k += 1)
        {
            System.out.print(result[k]);
            System.out.print("\n");

        }
    }

    /**
     * 解多元一次方程组，采用克莱姆法则，先求出系数矩阵的解，再求出
     * @param arrayA 系数数组
     * @param arrayB 常数项数组
     * @return 一个一维数组，其中包含对应的解；如果返回的是数组值包含0，则可根据打印看出是没有解，还是方程书不够导致无数解
     */
    public static double[] Solve_Equations(double[][] arrayA, double[][] arrayB)
    {
        //判断方程数是否足够解方程
        if(arrayA[0].length != arrayA.length)
        {
            System.out.print("ArrayA's row and arrayA's line is not equal.");
            double[] arrayZero = {0};
            return arrayZero;
        }

        //判断常数项矩阵是否与系数矩阵长度相同
        if(arrayB.length != arrayA.length)
        {
            System.out.print("arrayA's row and arrayB's row is not equal.");
            double[] arrayZero = {0};
            return arrayZero;
        }

        Matrix matrixA = new Matrix(arrayA);
        Matrix matrixB = new Matrix(arrayB);

        //判断系数矩阵的值是否为0，为0则说明方程组没有解
        if(matrixA.det() == 0)
        {
            System.out.print("The rank of arrayA is 0.");
            double[] arrayZero = {0};
            return arrayZero;
        }

        //拼接系数矩阵和常数项矩阵，判断新拼接的矩阵的rank是否和系数矩阵相等，只有相等才有解
        double[][] arrayAdd = addArray(arrayA, arrayB);
        Matrix matrixAdd = new Matrix(arrayAdd);

        if(matrixA.rank() != matrixAdd.rank())
        {
            System.out.print("These equations do not have solution");
            double[] arrayZero = {0};
            return arrayZero;
        }

        double[] result = new double[arrayB.length];

        //常数项替换每一列进而计算出解
        for (int i = 0; i < arrayB.length; i +=1)
        {
            Matrix matrixD = matrixA.copy();
            for(int j = 0; j < arrayB.length; j += 1)
            {
                matrixD.set(j, i, matrixB.get(j, 0));
            }

            result[i] = matrixD.det()/matrixA.det();
        }

        return result;
    }

    /**
     * 拼接系数数组和常数项数组
     * @param arrayA 系数数组
     * @param arrayB 常数项数组
     * @return 拼接二维数组
     */
    public static double[][] addArray(double[][] arrayA, double[][] arrayB)
    {
        double[][] arrayAdd;
        arrayAdd = new double[arrayA.length + 1][arrayA.length + 1];
        int  a = arrayA.length;

        for (int k = 0; k < arrayB.length; k += 1)
        {
            for (int m = 0; m < arrayB.length; m += 1) {
                arrayAdd[k][m] = arrayA[k][m];
            }
        }

        for (int k = 0; k < arrayB.length; k += 1)
        {
            arrayAdd[arrayB.length][k] = arrayB[k][0];
        }

        return arrayAdd;
    }

}


