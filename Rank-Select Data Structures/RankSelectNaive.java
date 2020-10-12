import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class RankSelectNaive{

    long[] longVector;
    int[] bitVector;

    public RankSelectNaive(long[] vector){
        this.longVector = vector;
        this.bitVector = new int[vector.length * 64];

        createBitVector();
    }

    public void runRank(int[] queries){
      for(int i = 0; i<queries.length;i++){
        rank(queries[i]);
      }
    }

    public void runBitRank(int[] queries){
      for(int i = 0; i<queries.length;i++){
        bit_rank(queries[i]);
      }
    }

    public void runSelect(int[]queries){
      for(int i = 0; i<queries.length;i++){
        select(queries[i]);
      }
    }

    public void runBitSelect(int[]queries){
      for(int i = 0; i<queries.length;i++){
          bit_select(queries[i]);
      }
    }
    private void createBitVector() {
        // keeps track of position in in
        int longVecLen = longVector.length;
        for (int i = 0; i < longVecLen; i++) {
            long bitLong = longVector[i];
            int idx = 0;

            int leadingZeroes = Long.numberOfLeadingZeros(bitLong);

            while (leadingZeroes != 64) {
                idx += leadingZeroes + 1;
                bitVector[idx + (i*64) - 1] = 1;
                if(leadingZeroes!=63) bitLong <<= (leadingZeroes + 1);
                else bitLong = 0;
                leadingZeroes = Long.numberOfLeadingZeros(bitLong);
            }

          }
        //For checking vector
        /*
        for(int j = 0; j < bitVector.length; j++){
            System.out.print(bitVector[j]);
            if(j == 63) System.out.println();
        }
        System.out.println();
        */
    }

    public int rank(int rank_pos) {
        if(rank_pos < 1 || rank_pos > longVector.length * 64) return -1;
        int rank = 0;
        for (int i = 0; i < rank_pos; i++) {
            if(bitVector[i] == 1) rank++;
        }
        return rank;
    }

    public int select(int select) {
        if (select < 1 || select > bitVector.length)
            return -1;

        int ones = 0;
        int idx = 0;
        while(ones != select && idx != bitVector.length) {
            if(bitVector[idx] == 1) ones++;
            idx++;
        }
        if(ones == select) return idx;
        else return -1;
    }

    public int bit_select(int numSelect){
        int ones = 0;
        int idx = 0;
        int longVecLen = longVector.length;
        //int numOfLongsUsed = 0;

        for( int i = 0; i < longVecLen; i++){
            long bitLong = longVector[i];
            long leadingZeroes = Long.numberOfLeadingZeros(bitLong);

            while(leadingZeroes < 64){
                //System.out.println(bitLong);
                bitLong <<= (leadingZeroes + 1);
                idx += leadingZeroes + 1;
                ones += 1;

                if(ones == numSelect){
                    return idx + i*64;
                }

                leadingZeroes = Long.numberOfLeadingZeros(bitLong);
            }
            idx = 0;

        }
        return -1;
    }

    public int bit_rank(int numRank){
        // System.out.println("Ranking input: " + numRank);
        int whichVector = (int)Math.ceil(numRank/ 64.0)-1;
        int idx = numRank % 64;
        int bitCount = 0;

        for(int i = 0; i < whichVector; i ++) {
            bitCount += Long.bitCount(longVector[i]);
        }

        long bitLong = longVector[whichVector];

        long shift = 64 - idx;
        bitLong >>>= (shift);

        return Long.bitCount(bitLong) + bitCount;
    }


}
