import java.lang.Math;
//import org.apache.commons.math3.distribution.ParetoDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.JDKRandomGenerator;

public class Server {
    private static final double shape = 2.08;
    private double prevSubReqTime;
    private static int seed = 3;

    public Server(int seed){
        prevSubReqTime = 0;
        this.seed = seed;
    }

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
    
    public double getCurrTime(){
        return prevSubReqTime;
    }

    // Generate a random number using the pareto distribution.
    // ts = tm/(n^1.7 * logk(1-u)) by using the inverse method on a pareto distribution with CDF.
    // let U = 1-u which is a uniformly distributed random number.
    private double ParetoCalc(int n){
        double tm = ( 20 * (shape - 1) )/ (shape);
        double scale = tm/(Math.pow(n,1.7));
        double ts = 0;

        // Setting a predefined see for simulation purposes.
        JDKRandomGenerator rng = new JDKRandomGenerator();
        //int temp = (int)System.nanoTime();
        // Change this line later
        rng.setSeed(seed);
        //rng.setSeed(time(seed));

        UniformRealDistribution urd = new UniformRealDistribution(rng,0,1);
        double urdRand = 0;

        // Get a uniformly distributed random number (0,1).
        while(urdRand == 0){
            urdRand = urd.sample();
        }

        ts = -scale/logOfBase(urdRand,shape);

        //System.out.println("ts :" + ts);
        return ts;
    }


    public double logOfBase(double base, double num) {
            return Math.log(num) / Math.log(base);
    }

}
