import java.util.Random;

class Tester {
    private static final int num_iterations = 100000;
    
    public static void main(String[] args){
        testSubRequest();
        testRequest();
        testPreProcessor();
        testServers();
        testJoinPoint();

    }

    private static void testSubRequest(){
        System.out.println("Testing subrequest objects...");
        System.out.println("Test 1: Ensure all the methods work correctly.");
        for(int cnt = 0; cnt < num_iterations; cnt++){
            Random rand = new Random(System.nanoTime());
            double arrivalTime = rand.nextDouble();
            double preProcessTime = rand.nextDouble();
            SubRequest sObj = new SubRequest(arrivalTime, preProcessTime);
            assert(sObj.getProcessTime() == (arrivalTime + preProcessTime));
            double processTime = rand.nextDouble();
            sObj.addProcessTime(processTime);
            assert(sObj.getProcessTime() == processTime);
            assert(sObj.isServerFinished() == false);
            sObj.serverFinished();
            assert(sObj.isServerFinished() == true);
        }
        System.out.println("Test 1 passed!!!");
        System.out.println("Subrequest objects testing passed!!!");
    }

    private static void testRequest(){
        System.out.println("Testing request objects...");
        System.out.println("Test 1: Test that all the object methods work correctly");
        for(int cnt = 0; cnt < num_iterations; cnt++){
            double arrivalTime = 0;
            int n = 10;
            double preProcessingTime = 0;
            Request newReq = new Request(arrivalTime);
            newReq.createSubRequests(n,preProcessingTime);
            assert(newReq.getNumSubRequests() == n);
            Random rand = new Random(System.nanoTime());
            double processTime  = rand.nextDouble();
            newReq.setFinalProcessTime(processTime);
            assert(newReq.systemTime == processTime);
            assert(newReq.responseTime  == processTime);

        }
        System.out.println("Passed Test 1.");

        System.out.println("Test 2: Ensure that the system time and response time differ");
        for(int cnt = 0; cnt < num_iterations; cnt++){
            Random rand = new Random(System.nanoTime());
            double arrivalTime = rand.nextDouble();
            int n = 10;
            double preProcessingTime = 0;
            Request newReq = new Request(arrivalTime);
            newReq.createSubRequests(n,preProcessingTime);
            assert(newReq.getNumSubRequests() == n);
            double processTime  = rand.nextDouble();
            newReq.setFinalProcessTime(processTime);
            assert(newReq.systemTime == processTime);
            assert(newReq.responseTime  == processTime - arrivalTime);

        }
        System.out.println("Passed Test 2.");
        System.out.println("Request object tests have been completed successfully!!!!");
    }

    private static void testJoinPoint(){
        System.out.println("Testing Join Point...");
        System.out.println("Test 1: Ensure it picks the max value of the sub requests");
        for(int cnt = 0; cnt < num_iterations; cnt++){
            double arrivalTime = 0;
            double preProcessTime = 0;
            int n = 10;
            int m = 10;
            Random rand = new Random(System.nanoTime());
            int seed = rand.nextInt();
            MultiServer newSys = new MultiServer(m,n,seed);
            Request testReq = new Request(arrivalTime);
            testReq.createSubRequests(n,preProcessTime);
            newSys.processRequest(testReq);
            JoinPoint newJoiner = new JoinPoint();
            newJoiner.findFinalProcessTime(testReq);
            double greatestTime = 0;
            for(int i = 0; i < testReq.getNumSubRequests(); i++){
                SubRequest sReq = testReq.getSubRequest(i);
                double processTime = sReq.getProcessTime();
                assert(processTime > 0);
                if(processTime > greatestTime){
                    greatestTime = processTime;
                }
            }

            assert(greatestTime == testReq.responseTime);
        }
        System.out.println("Join Point tests have been completed successfully!!!!");

    }

    private static void testServers(){
        System.out.println("Testing servers...");
        System.out.println("Test 1: make sure that the server actually processes the subrequest");
        for(int cnt = 0; cnt < num_iterations; cnt++){
            int m_1 = 10;
            int n_1 = 1;
            Random rand = new Random(System.nanoTime());
            int seed = rand.nextInt();
            int arrivalTime = 0;
            MultiServer newSys = new MultiServer(m_1,n_1,seed);
            Request testReq = new Request(arrivalTime);
            testReq.createSubRequests(n_1,arrivalTime);
            newSys.processRequest(testReq);
            assert(testReq.getNumSubRequests() == 1);
            SubRequest onlySub  = testReq.getSubRequest(0);
            assert(onlySub.getProcessTime() > 0);

        }

        System.out.println("Test 1 Passed!!!");
        System.out.println("Test 2: 11 subrequests with 10 servers, the last one should be queued.");
        for(int cnt = 0; cnt < num_iterations; cnt++){
            int m_2 = 10;
            int n_2 = 11;
            Random rand = new Random(System.nanoTime());
            int seed = rand.nextInt();
            int arrivalTime = 0;
            MultiServer newSys = new MultiServer(m_2,n_2,seed);
            Request testReq = new Request(arrivalTime);
            testReq.createSubRequests(n_2,arrivalTime);
            newSys.processRequest(testReq);
            SubRequest lastSub = testReq.getSubRequest(testReq.getNumSubRequests() - 1);
            double leastProcessTime = 0;
            for(int i = 0; i < testReq.getNumSubRequests() - 1; i++){
                SubRequest newSub = testReq.getSubRequest(i);
                assert(newSub.getProcessTime() > 0 );
                double processTime = newSub.getProcessTime();
                if(leastProcessTime > processTime ){
                    leastProcessTime = processTime;
                } else if (leastProcessTime == 0){
                    leastProcessTime = processTime;
                }   
                
            }
            // This assert is to ensure that the last subrequest (no.11) does not have the smallest processing time.
            // It shouldn't have the least processing time because it should have been queued until another
            // sub request completes as it's no.11 for 10 servers.
            assert(lastSub.getProcessTime() >= leastProcessTime);
        }
        System.out.println("Test 2 passed!!!");



        System.out.println("Server tests have been completed successfully!!!");
    }

    private static void testPreProcessor(){
        double n = 1;
        double arrivalTime = 0;
        System.out.println("Testing preprocessor...");
        // Created a new preprocessor, at this point the system has basically just started implying the current time is 0.
        for(int i = 0; i < num_iterations; i++){
            for(double j = 1; j < 11; j++){
                n = j;
                Random rand = new Random(System.nanoTime());
                int seed = rand.nextInt();
                PreProcessor test1 = new PreProcessor((int)n,seed);
                double preProcessTime = test1.processRequest(arrivalTime);
                assert(preProcessTime > 0) : "PreProcess time is not a positive double";
            }
        }
        System.out.println("PreProcessor tests are successfull!!!!");
    }



}
