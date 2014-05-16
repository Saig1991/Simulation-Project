import java.util.ArrayList;
import java.lang.String;
import java.lang.Integer;
import java.lang.Double;
import java.io.IOException;
import java.io.FileWriter;


public class Simulator {

    // number of sub-requests made out of a singular request.
    public static  int n = 1;
    // The number of servers in this configuration.
    public static final int m = 10;

    public static void main(String[] args){

        
        ArrivalTimes getTimes = new ArrivalTimes();
        ArrayList<Double> arrivalTimes = getTimes.getInterArrivalTimes(args[0]);

        n = Integer.parseInt(args[1]);

        int seed = Integer.parseInt(args[2]);
        PreProcessor preProcessor = new PreProcessor(n,seed);
        MultiServer multiServer = new MultiServer(m,n,seed);
        JoinPoint joiner  = new JoinPoint();

        double meanResponseTime = 0;
        double meanPreProcessingTime = 0;
        double numRequests = arrivalTimes.size();

        double arrivalTime  =  0;
        for(int i = 0; i < arrivalTimes.size(); i++){
            arrivalTime += arrivalTimes.get(i);
//            System.out.println("arrives " + arrivalTime);
            Request newRequest = new Request(arrivalTime);

            double currTime = preProcessor.processRequest(arrivalTime);
  //          System.out.println("preprocess " + currTime);
            newRequest.createSubRequests(n,currTime - arrivalTime);

            meanPreProcessingTime += currTime - arrivalTime;

            multiServer.processRequest(newRequest);
            joiner.findFinalProcessTime(newRequest);
            
//          System.out.println(newRequest.responseTime);
            meanResponseTime += newRequest.responseTime;
        }
        meanResponseTime = meanResponseTime / numRequests;
        meanPreProcessingTime = meanPreProcessingTime / numRequests;
        System.out.println("Mean response time for n = " + n );
        System.out.println(meanResponseTime);
    //    System.out.println("Mean preprocess time for n = " + n );
//        System.out.println(meanPreProcessingTime);

        try
        {
                String filename= "Output_" + args[1] + "_" + args[2] + ".txt";
                FileWriter fw = new FileWriter(filename,true); //the true will append the new data
                fw.write(Double.toString(meanResponseTime) + "\r\n");//appends the string to the file
                fw.close();
        }
        catch(IOException ioe)
        {
                System.err.println("IOException: " + ioe.getMessage());
        }
    }



}
