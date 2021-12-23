package P3;

import java.util.Arrays;
import java.util.Scanner;

public class P3 {

    private String[] INFO = {};
    private int[][] LINK = {};

    public void Display() {
        System.out.println(Arrays.deepToString(LINK));
        System.out.println(Arrays.toString(INFO));
    }
    public void InsertJob(String description, int priority) {
        int newLength = (INFO.length)+1;
        String[] NewINFO = new String[newLength];
        int[][] NewLINK = new int[newLength][2];
        NewLINK[0][0] = priority;
        NewINFO[0] = description;
        for (int i=1;i<LINK.length+1;i++) {
            NewINFO[i] = INFO[i - 1];
            NewLINK[i][0] = LINK[i - 1][0];
        }
        for (int i=0;i<LINK.length;i++){
            if (NewLINK[i][0] >= NewLINK[i+1][0]) {
                int t = NewLINK[i][0];
                NewLINK[i][0] = NewLINK[i+1][0];
                NewLINK[i+1][0] = t;

                String temp = NewINFO[i];
                NewINFO[i] = NewINFO[i+1];
                NewINFO[i+1] = temp;
            }
        }
        for (int i=0;i<NewLINK.length;i++){
            NewLINK[i][1] = i;
        }
        INFO = NewINFO;
        LINK = NewLINK;

    }
    //public void InsertJobEnd(String description, int priority) {
    //    //Adds description
    //    int newLength = (INFO.length)+1;
    //    String[] NewINFO = new String[newLength];
    //    for (int i=0;i<INFO.length;i++){
    //        NewINFO[i] = INFO[i];
    //    }
    //    NewINFO[newLength-1] = description;
    //    INFO = NewINFO;
    //    //Adds priority
    //    newLength = LINK.length+1;
    //    int[][] NewLINK = new int[newLength][2];
    //    for (int i=0;i< LINK.length;i++) {
    //        NewLINK[i][0]=LINK[i][0];
    //        NewLINK[i][1]=i;
    //    }
    //    NewLINK[newLength-1][0] = priority;
    //    NewLINK[newLength-1][1] = newLength-1;
    //    LINK = NewLINK;
    //}
    public void RemoveJob(int priority) {
        int counter=0;
        for (int i=0;i<INFO.length;i++) {
            if (LINK[i][0] == priority) {
                counter++;
            }
        }
        int newLength = LINK.length-counter;
        String[] NewINFO = new String[newLength];
        int[][] NewLINK = new int[newLength][2];
        counter=0;
        int j;
        for (int i=0;i<LINK.length;i++){
            if (LINK[i][0] != priority){
                j=i-counter;
                NewINFO[j] = INFO[i];

                NewLINK[j][0] = LINK[i][0];
                NewLINK[j][1] = j;
            } else {
                counter++;
            }
        }
        INFO = NewINFO;
        LINK = NewLINK;
    }

    //public void InsertJobStart(String description, int priority) {
    //    //Adds description
    //    int newLength = (INFO.length)+1;
    //    String[] NewINFO = new String[newLength];
    //    for (int i=0;i<INFO.length;i++){
    //        NewINFO[i+1] = INFO[i];
    //    }
    //    NewINFO[0] = description;
    //    INFO = NewINFO;
    //    //Adds priority
    //    newLength = LINK.length+1;
    //    int[][] NewLINK = new int[newLength][2];
    //    for (int i=0;i< LINK.length;i++) {
    //        NewLINK[i+1][0]=LINK[i][0];
    //        NewLINK[i+1][1]=i;
    //    }
    //    NewLINK[0][0] = priority;
    //    NewLINK[0][1] = newLength-1;
    //    LINK = NewLINK;
    //}

    public static void main(String[] args) {
        P3 s = new P3();
        s.InsertJob("AAA", 1);
        s.InsertJob("BBB",2);
        s.InsertJob("CCC",2);
        s.InsertJob("DDD",4);
        s.InsertJob("EEE",4);
        s.InsertJob("FFF",4);
        s.InsertJob("GGG",5);

        s.InsertJob("XXX", 3);



        String Description;
        int Priority;
        Scanner input = new Scanner(System.in);
        int choice=0;
        while (choice!=9) {
            s.Display();
            System.out.println("\n1) Insert Job\n2) Delete Job\n3) Display Job Priority\n9) Exit\n-----------------------\n");
            choice = input.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter the description: \n");
                    Description = input.next();
                    System.out.println("Enter its Priority: \n");
                    Priority = input.nextInt();
                    s.InsertJob(Description, Priority);
                    System.out.println("Success: \n");
                }
                case 2 -> {
                    System.out.println("Enter Priority to remove: \n");
                    Priority = input.nextInt();
                    s.RemoveJob(Priority);
                    System.out.println("Success: \n");
                }
                case 3 -> {
                    System.out.println("Enter Job Description: \n");
                    Description = input.next();
                    s.GetPriority(Description);
                }
                case 9-> {
                    System.exit(0);
                }
            }
        }
        System.exit(0);
    }

    public void GetPriority(String Description) {
        for (int i=0;i<INFO.length;i++){
            if (Description.equals(INFO[LINK[i][1]])) {
                System.out.println(Description + ": " + LINK[i][0]);
            }
        }
    }
}
