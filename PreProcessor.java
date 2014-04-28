import org.apache.commons.math3.distribution.ExponentialDistribution;

// The class that will compute the pre-processing of a multi-server system.
public class PreProcessor {

    private static int numSubTasks;
    private static int currentTime;

    public PreProcessor(int n){
        numSubTasks = n;
        currentTime = 0;
    }


   // Time it takes the pre-processor to process a request into n sub-requests.
   // n has to be less than the number of servers m.
   public void  processRequest (Request newRequest, double arrivalTime) {

        double ExpDistRate = 10/numSubTasks;
        ExponentialDistribution ed = new ExponentialDistribution(ExpDistRate);
        double processTime = ed.sample();

        if(currentTime < arrivalTime) currentTime = arrivalTime;
        currentTime += processTime;

        for(int i = 0; i < numSubTasks; i++){
            SubRequest newSub = new SubRequest(currentTime);
            newRequest.add(newSub);
        }

   }


}
