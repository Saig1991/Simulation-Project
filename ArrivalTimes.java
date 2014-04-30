import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.String;
import java.lang.Double;

// The class that will compute the pre-processing of a multi-server system.
public class ArrivalTimes {

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
}
