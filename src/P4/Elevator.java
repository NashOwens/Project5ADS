package P4;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Elevator {
     public static int currentLevel = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread t2 = new Thread() {
            @Override
            public void run() {
                Scanner input = new Scanner(System.in);
                System.out.println("Enter number of floors to go to: \n");
                int NumOfFloors = input.nextInt();
                LinkedList<Integer> levelList = new LinkedList<Integer>();
                Random r = new Random();
                int[] floors = new int[NumOfFloors];
                floors[0] = 0;
                levelList.add(0);
                int mean=0;
                for (int i=1;i<NumOfFloors;i++){
                    int j = r.nextInt(11);
                    levelList.add(j);
                    floors[i] = j;
                    mean+=j;
                }

                System.out.println("Mean: "+mean/floors.length);
                int length = floors.length;
                System.out.println("Duration for MyAlgo: "+minTime(length, floors) + " Seconds");

                System.out.println("The elevator will go to these floors : " + levelList);
                try {
                    FIFO(levelList);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.start();
    }

    public static void FIFO(LinkedList<Integer> floors) throws InterruptedException {
        int time = 0;

        System.out.println("The current floor is : " + currentLevel);
        while (!floors.isEmpty()) {
            int l = floors.getFirst();
            while (l > currentLevel) {
                String string = String.format("%s", currentLevel);
                System.out.print(string);
                time++;
                currentLevel++;
            }
            while (l < currentLevel) {
                String string = String.format("%s", currentLevel);
                System.out.print(string);
                time++;
                currentLevel--;
            }
            System.out.println("\nYou have arrived level " + currentLevel);
            floors.removeFirst();
        }
        System.out.println("Duration for FIFO : " + time + " seconds");
    }



    static int minTime(int length, int[] a)
    {
        //descending order sort
        int temp;
        for(int i = 0; i < length; i++)
        {
            for(int j = i + 1; j < length; j++)
            {
                if(a[i] < a[j])
                {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        // accounts for start floor
        int minTime = 0;
        minTime += a[0];

        for (int i = 1; i < length; i++) {
            //System.out.println(a[i-1]);
            minTime += (a[i-1] - a[i]);
            if (a[i-1]  == a[i]){
                minTime += 1;
            }
        }

        // Return the total time taken
        return minTime;
    }

}
