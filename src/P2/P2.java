package P2;
import java.util.Scanner;

public class P2 {

    public static void main(String[] args) {
        // write your code here

        int n=0,i = 0,j = 0,k =0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number: \n");

        n = input.nextInt();
        int x=0;
        for (i=1;i<n;i++) {
            for (j =1; j < i; j++) {
                for (k = 1; k < j; k++) {
                    x = x + 1;
                    //System.out.println(x);
                }
            }
        }
        System.out.println("\nCalculated using i,j,k: "+(i-1)*(j-1)*(k-1));
        System.out.println("\nCalculated in terms of n factorised: "+(n-1)*(n-2)*(n-3));
        System.out.println("\nCalculated in terms of polynomial n: "+((n*n*n)-(6*n*n)+(11*n)-6));
    }
}