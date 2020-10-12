import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OtherArray{
  private SearchTree tree;
  private int[] otherArr;
  private int i;  //index for OtherArray
  private List<Integer> parents;  // keeping track of parent nodes while traversing through OtherArray


  public OtherArray(SearchTree tree,int n){
    this.tree = tree;
    otherArr = new int[n];
    i = 1;
  }

  public void runPred(int[] queries){

    for(int i = 0; i<queries.length; i++){
      int x = pred(queries[i]);
    }

  }

  public int[] getOtherArray(){
         return otherArr;
  }


  /**
   * Creates a skewed binary otherArray according to Priority Queue Depth First
   * Search right Lay-Out
   * @param  p the size of the blocks
   */
  public void create_OA_pqDFS(int p){
    create_OA_pqDFS(tree.getRoot(),p);
  }

  private void create_OA_pqDFS(Node node,int p){

    MaxPQ pq = new MaxPQ(node.size);
    pq.insert(node);

    for(int j = 0; j<p; j++){
      if(pq.isEmpty()) return;
      Node max = pq.delMax();
      otherArr[i] = max.val;
      max.otherArr_indx = i;
      insertIndexChild(max);

      i = i+3;
      if(max.left != null) pq.insert(max.left);
      if(max.right != null) pq.insert(max.right);


    }

      while(!pq.isEmpty()){
        Node next = pq.delMax();
        create_OA_pqDFS(next,p);
      }
  }

  public void insertIndexChild(Node node){
    if(node != tree.getRoot()){
      int parent = node.parent.otherArr_indx;
      if(node.val > otherArr[parent]) otherArr[parent+1] = node.otherArr_indx;
      if(node.val < otherArr[parent]) otherArr[parent-1] = node.otherArr_indx;
    }
    if(node.left == null) otherArr[node.otherArr_indx-1] = -1;
    if (node.right == null) otherArr[node.otherArr_indx+1]= -1;
  }


  /**
   * Returns a value <= querie
   * @param  val the searched querie
   * @return The value <= querie or if not found returns -1;
   */
  public int pred(int val){

      int closest = pred2(val);
      if(closest == -1 || closest > val) return -1;
      else return closest;

   }

  /**
   * Returns a value <= querie or if not found prevents it from returning null.
   * Instead it will return the former node if that node was smaller.
   * @param  val the searched querie
   * @return The value <= querie or if not found returns last traversed index value.
   */
public int pred2(int val){
  int root = 1;
  int former = otherArr[1];
//  System.out.println("-------------");
//  System.out.println("parent " + former);

  while(root!=-1){
    if   (val == otherArr[root]) return otherArr[root];
    if   (val > otherArr[root]){
      former = otherArr[root];
      root=otherArr[root+1];
    }
    else root = otherArr[root-1];
//    System.out.print("root " + root + " val " + val);
//    if(root != -1) System.out.println(  " otherArr " + otherArr[root]);
  }
//  System.out.println("former " + former);
  return former;
}




/*
      public void create_OA_DFSr(){
         create_OA_DFSr(tree.getRoot(),-1,1);
       }

      private void create_OA_DFSr(Node x,int childIndex,int keyIndex){
          if(x == null){
            otherArr[childIndex] = -1;
            return;
          }
      /*    System.out.println("---------------");
          System.out.println("node " + x.val);
          System.out.println("childIndex " + childIndex);
          System.out.println("keyIndex " + keyIndex);
          System.out.println(" i " + i);*/
/*
          i+=3;
          otherArr[keyIndex] = x.val;
          if (childIndex != -1) otherArr[childIndex] = keyIndex;

          create_OA_DFSr(x.right, keyIndex + 1,i);
          create_OA_DFSr(x.left, keyIndex - 1, i);

        }*/

  /*      public static void main(String[] args)throws Exception{

          BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // n is the size of set S
         int n = Integer.parseInt(br.readLine());

          // read in set S into an int[]
         String[] stringSetS = br.readLine().split(" ");
        //  n = stringSetS.length;*/
  /*        int[] arr = new int[n];
          for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(stringSetS[i]);

          }

          Arrays.sort(arr);

          SearchTree st = new SearchTree(arr,0.5);
          OtherArray oa = new OtherArray(st,n*3);
          st.createTree(null,0,n-1);
          oa.create_OA_pqDFS(3);

        String[] stringQueries = br.readLine().split(" ");
          int q = stringQueries.length;
          int[] queries = new int[q];

        for(int i = 0; i < q; i++){
            queries[i] = Integer.parseInt(stringQueries[i]);
          //  st.pred(queries[i])
            int x = oa.pred(queries[i]);
            if(x != -1) System.out.print(x + " ");
            else        System.out.print("None ");
          }

          System.out.println();
/*
        int[] o_arr = oa.getOtherArray();
         System.out.println(o_arr.length);
          for(int i = 1; i < o_arr.length; i+=3){
            System.out.print(o_arr[i-1] + " " + o_arr[i] + " " + o_arr[i+1] + " | ");
          }*/




      //  }







}
