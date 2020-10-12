// Notes:
// Select(20) is wrong.




public class RankSelectSpaceEfficient {

    long[] bitVector;
    int bitVectorLength;
    int k;
    int block_size;
    byte[] popc = new byte[256];
    int[] rankArray;
    //long[] longBytes;

    public RankSelectSpaceEfficient(long[] vector, int k) {
        this.bitVector = vector;
        this.bitVectorLength = bitVector.length;
        this.k = k;
        this.block_size = 64 * k;
        createPopC();
        createRankArray();
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

    // creates an array, popc, that takes a byte and returns the number of 1's in that byte.
    private void createPopC() {
        for (int i = 0; i < 256; i++)
            popc[i] = (byte) Integer.bitCount(i & 0xff);
    }

    // takes a long and, using popc, returns the number of 1's in the binary representation of the long.
    private int popcount(long x) {
        int numOfOnes = popc[(int) (x & 0xFF)];
       for (int i = 1; i < 8; i++) {
            x >>= 8;
            numOfOnes += popc[(int) (x & 0xFF)];
        }
        return numOfOnes;
    }

    // takes a long and returns the number of 1's in the right most byte.
    private int popcountbyte(long x) {
        return popc[(int) (x & 0xFF)];
    }

    // creates an array, rankArray, with the total number of 1's in that block and all the blocks preceeding it.
    private void createRankArray() {

        int rankArrLen = (int) Math.ceil(1.0*bitVectorLength/k);  // no. of blocks.
        rankArray = new int[rankArrLen];    // instantiate block array.
        int bit_sum = 0;    // running sum.
        int ithLong = 0;    // which long in array bitVector.

        for(int i = 0; i < rankArrLen; i++){
            for(int j = 0; j < k; j++){
                if(ithLong >= bitVectorLength) break;
                bit_sum += popcount(bitVector[ithLong]);
                ithLong += 1;
            }
            rankArray[i] = bit_sum;
        }
    }

    public int rank(int rank_num) {

        if(rank_num < 1 || rank_num > bitVectorLength * 64) return -1;

        int bit_sum = 0;
        int block = (int) Math.ceil(1.0*rank_num/block_size);   // so much casting.
        // System.out.println("rank_num: " + rank_num);
        // System.out.println("Block size: " + block_size);

        if (block >= 2) {
            bit_sum += rankArray[block-2];  // -2 because of array index
            //System.out.println("Block - 2  : " + rankArray[block - 2]);
        }
        int block_idx = (block - 1) * k; // starting index of the block
        int block_pos = (rank_num % block_size); // idx in block
        if (block_pos == 0) block_pos = block_size;
        int block_long = (int) Math.ceil(1.0 * block_pos / 64) - 1; // which long in the block is the rank_num in.

        for(int i = 0; i < block_long; i++){
            long tempLong = bitVector[block_idx+i];
            bit_sum += popcount(tempLong);
        }

        long tempLong = bitVector[block_idx + block_long];
        tempLong >>>= (64 - block_pos);

        bit_sum += popcount(tempLong);
        return bit_sum;
    }

    public int select(int select) {
        //System.out.println(select);

        if (select < 1 || select > rankArray[rankArray.length - 1]) return -1;

        int lo = 0, position = 0, bit_sum = 0;
        int hi = rankArray.length - 1;
        int rankValue, mid;

        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            rankValue = rankArray[mid];
            if      (select <= rankValue) {
                hi = mid - 1;
                position = mid;
            }
            else if (select > rankValue) lo = mid + 1;
            else System.out.println("Select went wrong....");
        }

        if(position > 0) bit_sum += rankArray[position-1];
        int longIndex = position * k;
        int index = 0;

        for( int i = longIndex; i < longIndex + k; i++){
            long bitLong = bitVector[i];
            long leadingZeroes = Long.numberOfLeadingZeros(bitLong);

            while(leadingZeroes < 64){
                bitLong <<= (leadingZeroes + 1);
                index += leadingZeroes + 1;
                bit_sum += 1;

                if(bit_sum == select) return (index + (i * 64));

                leadingZeroes = Long.numberOfLeadingZeros(bitLong);
            }
            index = 0;
        }
        return -1;
    }
}
