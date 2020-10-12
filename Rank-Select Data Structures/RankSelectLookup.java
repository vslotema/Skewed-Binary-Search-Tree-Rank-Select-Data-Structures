import java.util.Arrays;

public class RankSelectLookup{

    //RankSelectNaive rsn;
    int[] rankArray;

    public RankSelectLookup(long[] vector){
        RankSelectNaive rsn = new RankSelectNaive(vector);
        rankArray = new int[vector.length * 64];

        for(int i = 0; i < rankArray.length; i++){
            rankArray[i] = rsn.rank(i+1);
            // System.out.println (i + " : " + rankArray[i]); //Prints vector values for checking
        }
    }

    public void runRank(int[] queries){
      for(int i = 0; i<queries.length;i++){
        rank(queries[i]);
      }
    }

    public void runSelect(int[] queries){
      for(int i = 0; i<queries.length;i++){
        select(queries[i]);
      }
    }

    public int rank(int rank_pos) {
        if(rank_pos < 1 || rank_pos > rankArray.length) return -1;
        return rankArray[rank_pos - 1];
    }

    public int select(int select) {
        // exactly like regular binary search, but:
        //1)  it returns index value of the wanted position
        //2) it keeps searching even though we hit the wanted value
        // if the value before it in the array is equal to it.
        //This accomodates repeated values and still guarantees O(log n)

        if (select < 1 || select > rankArray[rankArray.length -1]) return -1;
        int lo = 0, position = 0;
        int hi = rankArray.length - 1;
        int rankValue, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            rankValue = rankArray[mid];
            if      (select < rankValue) hi = mid - 1;
            else if (select > rankValue) lo = mid + 1;
            else if (rankValue == select) {
                hi = mid - 1;
                position = mid;
            }
            else System.out.println("Select went wrong....");
        }

        return position + 1;
    }
}
