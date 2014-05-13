import java.util.ArrayList;


public class MultiServer {
    
    private ArrayList<Server> multiServers;
    private int n;

    public MultiServer(int m, int n, int seed){
        this.n = n;
        multiServers = new ArrayList<Server>();
        for(int i = 0; i < m; i++){
            Server newServer = new Server(seed);
            multiServers.add(newServer);
        }
    }

    private Server findIdleServer(SubRequest sr){
       Server retVal = multiServers.get(0);
       for(int i = 0; i < multiServers.size(); i++){
            Server temp = multiServers.get(i);
            if(temp.getCurrTime() < sr.getProcessTime()){
                retVal = temp;    
                break;
            }
       }

       return retVal;

    }

    public void processRequest(Request req){
       int numSubRequests = req.getNumSubRequests(); 
       for(int i = 0; i < numSubRequests; i++){
           SubRequest temp = req.getSubRequest(i);
           Server getIdleServer = findIdleServer(temp);
           getIdleServer.processSubRequest(n,temp);
       }

            
    }




}
