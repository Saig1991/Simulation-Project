

import java.util.Random;
 
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.TriangularDistribution;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.JDKRandomGenerator;
 
 
public class GenerateRandNo {
       
        private double g_scale = 0.6;
        private double shape = 1.0;
               
        private double a = 0.1;
        private double b = 1.5;
        private double c = 0.8;

        private static final int seed = 3;
       
       
        //private double Fc = 0.5;
       
       
        public double gammaDist () {
                JDKRandomGenerator rng = new JDKRandomGenerator();
                rng.setSeed(seed);

                GammaDistribution gD = new GammaDistribution (rng,g_scale, shape,GammaDistribution.DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
                return gD.sample();
        }
       
        public double triangularDist () throws InstantiationException, IllegalAccessException {
                double U = 0.0;
                double X = 0.0;

                TriangularDistribution tD = new TriangularDistribution (a, c, b);
                Random rnd = new Random();
                rnd.setSeed(seed);
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


