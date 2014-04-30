import java.util.ArrayList;

public class Request {

      private double arrivalTime; 
      private double systemTime; 
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

      public void createSubRequests(int n, int preProcessingTime){

          for(int i = 0; i < n; i++){
              SubRequest obj = new SubRequest(arrivalTime, preProcessingTime);  
              subRequests.add(obj); 
          }
      }

}
