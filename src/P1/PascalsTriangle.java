package P1;

import java.util.Arrays;

public class PascalsTriangle {

    public static void main(String[] args) {

        int[] Array = {7,5,4,3,8,9,6,4,2,7,9,1,21,13,15,17,7,99};
        int[] insertArray = insertionSort(Array, Array.length);
        int[] selectArray = selectionSort(Array, Array.length);
        int[] bubbleArray = bubbleSort(Array, Array.length);
        int[] shellArray = shellSort(Array, Array.length);
        System.out.println("\nInsert sort: " + Arrays.toString(insertArray));
        System.out.println("\nSelect Sort: " + Arrays.toString(selectArray));
        System.out.println("\nBubble Sort: " + Arrays.toString(bubbleArray));
        System.out.println("\nShell Sort: " + Arrays.toString(shellArray));
        aPascalTriangle(30);
    }

    public static int[] insertionSort(int[] A, int n) {
        int i = 1, j;
        while (i < n) {
            j = i;
            while (((j > 0) && (A[j - 1] > A[j]))) {
                //Swap function
                int temp;
                temp = A[j];
                A[j] = A[j-1];
                A[j-1] = temp;
                //
                j = j - 1;
            }
            i = i + 1;
        }
        return A;
    }
    public static int[] selectionSort(int[] A, int n){
        int min, i , j;

        for (i=0; i<n; n--){
            min = i;
            for (j=i+1; j<n; n--) {
                if (A[j] < A[min]) {
                    min = j;
                }
            }
            //Swap function
            int temp;
            temp = A[min];
            A[min] = A[i];
            A[i] = temp;
            //
        }
        return A;
    }
    public static int[] bubbleSort(int[] A, int n) {
        int i = 0, j;
        while (i-- < n) {
            j = i+1;
            for (i=0; i<n; i++) {
                if (A[i] < A[j]) {
                    int temp;
                    temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }
        return A;
    }
    public static int[] shellSort(int[] A, int n) {
        int i, j;
        for (i=0; i<n ;i++) {
            for (j=n/2; j>n ;j++) {
                if (A[i] < A[j]) {
                    int temp;
                    temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }
        return A;
    }
    public static void aPascalTriangle(int n) {
        System.out.println("""
                 Welcome to pascals triangle using an array:\s
                """);      // declared as doubles as they can hold a larger value so i
        // can have a larger pascals triangle
        double i, j, k;
        double [] A = {0,0};                         // 1d double array to assign "n" and "k" (n,k)
        for (i=0; i<n; i++) {                        // loops through the number of rows (n) entered at the call
            for (k=0; k<=n-i; k++)                   // creates the triangle correctly by creating a space before values
                System.out.print(" ");               // makes a space in between values to be distinguished
            for (j=0; j<=i; j++) {               //
                A[0] = i;                        // Assigns row number of triangle to the first int in array (A)
                A[1] = j;                        // Assigns row index to the second int in array (A)
                if ((int)Result(A) == 0) {       // Prevents 0's being printed to the screen
                    break;                       //
                }
                System.out.print(" " + (int)Result(A)); // Prints the values after being calculated
            }
            System.out.print("\n");                   // begins a new row to continue building the triangle
        }
    }
    public static double Result(double[] A) {          //calculates the result for each value in the triangle
        double i;
        i = factorial(A[0])/(factorial(A[1]) * factorial(A[0] - A[1])); // n!/k!(n-k)! where n=A[0] and k=A[1]
        return i;                                       // returns the value calculated
    }
    public static double factorial(double n) {          // factorials the value passed through
        double f = 1;                                   // defines f
        for (int i=2; i<= n;i++) {                      // starting at 2, i increments by 1 n number of times
            f = f * i;                                  // multiplies 1 by i, n number of times
        }
        return f;                                       // returns the factorial number
    }

}