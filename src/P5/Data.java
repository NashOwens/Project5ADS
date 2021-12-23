package P5;

import java.io.*;
import java.util.ArrayList;



public class Data {

    static ArrayList<Double> avr = new ArrayList<>();

    public static ArrayList<Double> readFile (String fileName){

        ArrayList<Double> res = new ArrayList<>();

        try {
            BufferedReader br;
            String line;
            int i=0;
            br = new BufferedReader (new FileReader (fileName));

            while ((line = br.readLine()) != null)
            {
                String [] column = line.split(",");
                for (String s : column) {
                    res.add(Double.parseDouble(s));
                }
                i++;
            }
        }
        catch(Exception e) {
            System.err.print("Error");
        }
        return res;
    }

    public static void writeAverage(String file, GeneticAlgo.Population result) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            double average = 0;

            for (int i = 0; i < result.population.size(); i++) {
                average += result.population.get(i).fitness;
            }
            String res;
            res = Double.toString((average/result.population.size()));
            avr.add(average/result.population.size());
            System.out.println(avr);
            bw.write(res);
            bw.close();
            fw.close();
        } catch(Exception e) {
        System.err.print("Error");
        }
    }

    public static void writeResult(String file, GeneticAlgo.Population result) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter (fw);

            for (int i=0; i<result.population.size(); i++) {
                bw.write(Double.toString(result.population.get(i).fitness));
                bw.newLine();
            }
            bw.close();
            fw.close();
        }
        catch(Exception e) {
            System.err.print("Error");
        }


    }

}