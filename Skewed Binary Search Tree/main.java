import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.function.IntToDoubleFunction;
import java.util.*;

class Run_part1 {

    // 1.2
    private static void exer_two(int[] values, double alpha, int[] queries,int p) {
        SortedArray sorted = new SortedArray(values,alpha);
    /*   Mark8("SortedArray alpha = " + alpha,""+queries.length,new Benchmarkable(){
          public void setup(){}
          public double applyAsDouble(int i){
            sorted.runPred(queries); return 0.0;
          }
        } );*/
       SearchTree tree = new SearchTree(values, alpha);
        tree.createTree(null,0,values.length-1);
    /*     Mark8("SearchTree alpha = " + alpha,""+queries.length,new Benchmarkable(){
      //    public void setup(){}
          public double applyAsDouble(int i){
            tree.runPred(queries); return 0.0;
          }
        } );*/

        OtherArray other = new OtherArray(tree,values.length*3);
        other.create_OA_pqDFS(p);
        Mark8("OtherArray," + p,""+queries.length,new Benchmarkable(){
      //    public void setup(){
      //    }
          public double applyAsDouble(int i){
            other.runPred(queries); return 0.0;
          }
        } );
      }


    public static double Mark8(String msg, String info, Benchmarkable f) {
      return Mark8(msg, info, f, 10, 0.25);
    }

    public static double Mark8(String msg, String info, Benchmarkable f,int n, double minTime) {
      int count = 1, totalCount = 0;
      double dummy = 0.0, runningTime = 0.0, st = 0.0, sst = 0.0;
      do {
          count*=2;st = sst = 0.0;
          for (int j=0; j<n; j++) {
            Timer t = new Timer();
            for (int i=0; i<count; i++){
            /*  t.pause();
              f.setup();
              t.play();*/
              dummy += f.applyAsDouble(i);}
            runningTime = t.check();
            double time = runningTime * 1e9 / count;
            st += time; sst += time * time;
            totalCount += count;
          }

        } while (runningTime < minTime && count < Integer.MAX_VALUE/2);
        double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
        System.out.printf("%s%s%.1f,%.2f,%d%n", msg + ",", info + ",", mean, sdev, count);
        return dummy / totalCount;
      }

        public static void SystemInfo() {
          System.out.printf("# OS:   %s; %s; %s%n",
                            System.getProperty("os.name"),
                            System.getProperty("os.version"),
                            System.getProperty("os.arch"));
          System.out.printf("# JVM:  %s; %s%n",
                            System.getProperty("java.vendor"),
                            System.getProperty("java.version"));
          // The processor identifier works only on MS Windows:
          System.out.printf("# CPU:  %s; %d \"cores\"%n",
                            System.getenv("PROCESSOR_IDENTIFIER"),
                            Runtime.getRuntime().availableProcessors());
          java.util.Date now = new java.util.Date();
          System.out.printf("# Date: %s%n",
            new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(now));

      }


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n is the size of set S
        //uncomment these lines of code to test on various alphas // comment out int n = Integer.parseInt(br.readLine());
       String[] init = br.readLine().split(" ");
        int n = Integer.parseInt(init[0]);
        int p = Integer.parseInt(init[1]);
        double alpha = Double.parseDouble(init[2]);

        String[] stringSetS = br.readLine().split(" ");
        int[] arraySetS = new int[n];
        for (int i = 0; i < stringSetS.length; i++) {
            arraySetS[i] = Integer.parseInt(stringSetS[i]);
            // System.out.println(arraySetS[i]);
        }
        // q is the number of queries
        // queries read into an int[]
        String[] stringQueries = br.readLine().split(" ");
        int q = stringQueries.length;
        int[] queries = new int[q];
        for(int i = 0; i < q; i++){
            queries[i] = Integer.parseInt(stringQueries[i]);
        }
        Arrays.sort(arraySetS);
    //    SystemInfo();
      exer_two(arraySetS, alpha, queries, p);

    System.out.println();
    }
}
