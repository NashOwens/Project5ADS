package P2;
import java.util.Scanner;

public class P2 {

    public static void main(String[] args) {
        // write your code here

        int n=0,i = 0,j = 0,k =0;
        Scanner input = new Scanner(System.in);

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
        System.out.println((i-1)*(j-1)*(k-1));
        System.out.println((n-1)*(n-2)*(n-3));
        System.out.println((n*n*n)-(6*n*n)+(11*n)-6);
    }
}