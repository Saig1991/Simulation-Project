import java.util.Random;

class Tester {
    
    public static void main(String[] args){
        testPreProcessor();

    }

    private static void testPreProcessor(){
        double n = 1;
        double arrivalTime = 0;
        System.out.println("Testing preprocessor...");
        // Created a new preprocessor, at this point the system has basically just started implying the current time is 0.
        for(int i = 0; i < 100000; i++){
            for(double j = 1; j < 11; j++){
                n = j;
                Random rand = new Random(System.nanoTime());
                int seed = rand.nextInt();
                PreProcessor test1 = new PreProcessor((int)n,seed);
                Request testReq = new Request(arrivalTime);
                double preProcessTime = test1.processRequest(testReq, arrivalTime);
                assert(preProcessTime > 0) : "PreProcess time is not a positive double";
            }
        }
        System.out.println("PreProcessor tests are successfull!!!!");
    }



}
