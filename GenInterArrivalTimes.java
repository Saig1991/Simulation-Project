

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
 
 
public class GenInterArrivalTimes {
 
        public static GenerateRandNo gRN = new GenerateRandNo();
        public static int k = 10;
       
        public static void main(String[] args) throws InstantiationException, IllegalAccessException, IOException {
               
			   
				int numArrivals = Integer.parseInt(args[0]);
				int arrivalName = Integer.parseInt(args[1]);
                genInterArrivalSeq(numArrivals, arrivalName);
                //System.out.println("Gamma value = " + gRN.gammaDist());
                //System.out.println("Triangular Dis value = " + gRN.triangularDist());
        }
       
        public static void genInterArrivalSeq (int numArrivals, int arrivalName) throws InstantiationException, IllegalAccessException, IOException {
               
				String fileName = "arrivalTimes_" + arrivalName + ".txt";
                File file = new File (fileName);
                FileWriter fw = new FileWriter (file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter (fw);
                int i = 1;
                while (i <= numArrivals) {
                        double a1K = gRN.gammaDist();
                        double a2K = gRN.triangularDist();
                        double aK = (a1K + a2K) * Math.abs(a1K-a2K);
                        String toWrite = Double.toString(aK);
                        bw.write(toWrite);
                        bw.write("\n");
//                        System.out.println("a1k + a2k = " + gRN.gammaDist()+gRN.triangularDist());
  //                      System.out.println("a"+i+" = " + aK);
                        //arrival.add(aK);
                        i++;
                }
                bw.close();
        }
       
 
}


