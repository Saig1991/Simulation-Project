

import java.util.Random;
 
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.TriangularDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.AbstractRandomGenerator;
import org.apache.commons.math3.random.RandomGenerator;
 
 
public class GenerateRandNo {
       
        private double g_scale = 0.6;
        private double shape = 1.0;
               
        private double a = 0.1;
        private double b = 1.5;
        private double c = 0.8;
       
       
        //private double Fc = 0.5;
       
        public RandomGenerator rng;
       
        public double gammaDist () {
                GammaDistribution gD = new GammaDistribution (g_scale, shape);
                return gD.sample();
        }
       
        public double triangularDist () throws InstantiationException, IllegalAccessException {
                double U = 0.0;
                double X = 0.0;
                TriangularDistribution tD = new TriangularDistribution (a, c, b);
                Random rnd = new Random();
                U = rnd.nextDouble();
                //System.out.println("U = " + U);
                double Fc = (c-a)/(b-a);
               
                //Finally, get the random variable following the CDF
                if (U > 0 && U < Fc) {
                        X = a + Math.sqrt(U*((b-a)*(c-a)));
                } else if (U >= Fc && U < 1.0) {
                        X = b - Math.sqrt((1-U)*(b-a)*(b-c));
                }
                return (tD.density(X));
               
        }
       
}


