
public class SubRequest{
    

   private double processingTime;
   private boolean finished;

   public SubRequest (double arrivalTime, double preProcessingTime) {
        processingTime = arrivalTime + preProcessingTime;
        finished = false;
   }

   public double getProcessTime(){
        return processingTime;
   }

   public void addProcessTime(double time){
        processingTime = time;
   }

   public void serverFinished(){
        finished = true;
   }

   public boolean isServerFinished(){
        return finished;
   }
   
   


}
