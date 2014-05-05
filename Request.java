import java.util.ArrayList;

public class Request {

      private double arrivalTime; 
      public double systemTime; 
      private double responseTime; 
      private boolean finished;
      private ArrayList<SubRequest> subRequests;

      public Request(double arrivalTime){
            this.arrivalTime = arrivalTime;
            systemTime = 0;
            responseTime = 0;
            finished = false;
            subRequests = new ArrayList<SubRequest>();
      }

      public void setFinalProcessTime(double processTime){
        systemTime = processTime;
        responseTime = processTime - arrivalTime;
        finished = true;
      }

      public int getNumSubRequests(){
        return subRequests.size();
      }     

      public SubRequest getSubRequest(int i){
            return subRequests.get(i);
      }

      public void createSubRequests(int n, double preProcessingTime){

          for(int i = 0; i < n; i++){
              SubRequest obj = new SubRequest(arrivalTime, preProcessingTime);  
              subRequests.add(obj); 
          }
      }

}
