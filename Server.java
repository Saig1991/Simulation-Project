import java.lang.Math;
//import org.apache.commons.math3.distribution.ParetoDistribution;

public class Server {
    private static final double shape = 2.08;
    private static double prevSubReqTime;

    public void processSubRequest(int n, SubRequest newReq){
        double ts = ParetoCalc(n);
        double procTime = newReq.getProcessTime();
        if(procTime < prevSubReqTime){
            procTime = prevSubReqTime;
        }
        procTime += ts;
        newReq.addProcessTime(procTime);
        newReq.serverFinished();
        prevSubReqTime = procTime;


    }

    private double ParetoCalc(int n){
        double tm = ( 20 * (shape - 1) )/ (shape);
        double scale = tm/(Math.pow(n,1.7));
        double ts = 0;


        ParetoDistribution pd = new ParetoDistribution(scale, shape);
        ts = pd.sample();

        return ts;
    }

}
