import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;

// The class that will compute the pre-processing of a multi-server system.
public class PreProcessor {

    private static int numSubTasks;
    private static double currentTime;
    private static int seed;

    public PreProcessor(int n, int seed){
        numSubTasks = n;
        currentTime = 0;
        this.seed = seed;
    }


   // Time it takes the pre-processor to process a request into n sub-requests.
   // n has to be less than the number of servers m.
   public double  processRequest (double arrivalTime) {

        double ExpDistRate = 10/numSubTasks;
        double ExpDistMean = 1/ExpDistRate;

        JDKRandomGenerator rng = new JDKRandomGenerator();
        rng.setSeed(seed);

        ExponentialDistribution ed = new ExponentialDistribution(rng,ExpDistMean, ExponentialDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
        double processTime = ed.sample();

        if(currentTime < arrivalTime) currentTime = arrivalTime;
        currentTime += processTime;
        return currentTime;

/*        for(int i = 0; i < numSubTasks; i++){
            SubRequest newSub = new SubRequest(currentTime);
            newRequest.add(newSub);
        }*/

   }


}
