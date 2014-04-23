import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.String;
import java.lang.Double;
import org.apache.commons.math3.distribution.ExponentialDistribution;

// The class that will compute the pre-processing of a multi-server system.
public class PreProcessor {

   /*
    * Read in the inter arrival times from the file specified in the input.
    */
   public ArrayList<Double> getInterArrivalTimes(String fileName) {
      BufferedReader br  = null;
      String currentLine;
      ArrayList<Double> interArrivalTimes = new ArrayList<Double>();


      try {
            br = new BufferedReader(new FileReader(fileName));
            while((currentLine = br.readLine()) != null) {
                double time = Double.parseDouble(currentLine);
                interArrivalTimes.add(time);
            }

      } catch (IOException e){
        e.printStackTrace();
      } finally {
        try {
            if (br != null)br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
      }

      return interArrivalTimes;
   }

   // Time it takes the pre-processor to process a request into n sub-requests.
   public double  processRequest (int n) {
        double ExpDistRate = 10/n;
        ExponentialDistribution ed = new ExponentialDistribution(ExpDistRate);
        double processTime = ed.sample();

        return processTime;
   }


}
