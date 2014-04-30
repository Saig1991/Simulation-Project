import java.util.ArrayList;


public class MultiServer {
    
    private ArrayList<Server> multiServers;

    public MultiServer(int m){
        for(int i = 0; i < m; i++){
            Server newServer = new Server();
            multiServers.add(newServer);
        }
    }



}
