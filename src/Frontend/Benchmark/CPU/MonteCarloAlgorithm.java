package Frontend.Benchmark.CPU;

import Frontend.Benchmark.IBenchmark;

public class MonteCarloAlgorithm implements IBenchmark {
    @Override
    public void run() {
        //Monte Carlo algorithm

        int INTERVAL = 10000;
        double rand_x, rand_y, origin_dist, pi=0;
        int circle_points = 0, square_points = 0;

        for (int i = 0; i < (INTERVAL * INTERVAL); i++) {
            rand_x = (Math.random()*2)-1;
            rand_y = (Math.random()*2)-1;
            origin_dist = ((rand_x * rand_x) + (rand_y * rand_y));
            if (origin_dist <= 1)
                circle_points++;
            square_points++;
            pi = ((4.0 * circle_points) / square_points);
            // For visual understanding (Optional)
            //System.out.println(rand_x+" "+rand_y+" "+circle_points+" "+square_points+" - "+pi);
        }
        // Final Estimated Value
        //System.out.println("Final Estimation of Pi = " + pi);
    }

    @Override
    public void run(Object... params) {
        // TODO Auto-generated method stub

        int INTERVAL = (Integer)params[0];
        double rand_x, rand_y, origin_dist, pi=0;
        int circle_points = 0, square_points = 0;

        for (int i = 0; i < (INTERVAL * INTERVAL); i++) {
            rand_x = (Math.random()*2)-1;
            rand_y = (Math.random()*2)-1;
            origin_dist = ((rand_x * rand_x) + (rand_y * rand_y));
            if (origin_dist <= 1)
                circle_points++;
            square_points++;
            pi = ((4.0 * circle_points) / square_points);
            // For visual understanding (Optional)
            //System.out.println(rand_x+" "+rand_y+" "+circle_points+" "+square_points+" - "+pi);
        }
        // Final Estimated Value
       //System.out.println("Final Estimation of Pi = " + pi);
    }

    @Override
    public void initialize(Object... params) {
        // TODO Auto-generated method stub
    }

    @Override
    public void clean() {
        // TODO Auto-generated method stub
    }

    @Override
    public void cancel() {
        // TODO Auto-generated method stub
    }

    @Override
    public void warmUp() {
        // TODO Auto-generated method stub

        int INTERVAL = 3000;
        double rand_x, rand_y, origin_dist, pi=0;
        int circle_points = 0, square_points = 0;

        for (int i = 0; i < (INTERVAL * INTERVAL); i++) {
            rand_x = (Math.random()*2)-1;
            rand_y = (Math.random()*2)-1;
            origin_dist = ((rand_x * rand_x) + (rand_y * rand_y));
            if (origin_dist <= 1)
                circle_points++;
            square_points++;
            pi = ((4.0 * circle_points) / square_points);
            // For visual understanding (Optional)
            //System.out.println(rand_x+" "+rand_y+" "+circle_points+" "+square_points+" - "+pi);
        }
        // Final Estimated Value
        //System.out.println("Final Estimation of Pi = " + pi);
    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }
}
