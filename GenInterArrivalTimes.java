

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
 
 
public class GenInterArrivalTimes {
 
        public static GenerateRandNo gRN = new GenerateRandNo();
        public static int k = 10;
       
        public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
               
                genInterArrivalSeq();
                //System.out.println("Gamma value = " + gRN.gammaDist());
                //System.out.println("Triangular Dis value = " + gRN.triangularDist());
        }
       
        public static void genInterArrivalSeq () throws InstantiationException, IllegalAccessException, IOException {
               
                File file = new File ("arrivalTimes.txt");
                FileWriter fw = new FileWriter (file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter (fw);
                int i = 1;
                while (i <= 10) {
                        double a1K = gRN.gammaDist();
                        double a2K = gRN.triangularDist();
                        double aK = (a1K + a2K) * Math.abs(a1K-a2K);
                        String toWrite = Double.toString(aK);
                        bw.write(toWrite);
                        bw.write("\n");
                        //System.out.println("a1k + a2k = " + gRN.gammaDist()+gRN.triangularDist());
                        System.out.println("a"+i+" = " + aK);
                        //arrival.add(aK);
                        i++;
                }
                bw.close();
        }
       
 
}


