import java.lang.Math;
import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//Written code inspired by Robert Sedgewick's BST class.
public class SearchTree
{
  private Node root;
  private int[] arr;
  private double alpha;



  public SearchTree(int[] arr, double alpha){
    this.arr = arr;
    this.alpha = alpha;

  }

  public Node getRoot(){
    return root;
  }

  public void createTree(Node p, int lo, int hi){

    if (hi < lo) return;
  // System.out.println("lo " + lo + " hi " + hi);
    int left = (int) (alpha * (hi-lo));
    int indx = lo + left;
    Node child = put(p,arr[indx]);

    createTree(child,indx+1,hi);
    createTree(child,lo,indx-1);
  }

  private Node put(Node p, int val){

      if (p == null){ root = new Node(val,1); return root;}
      Node child = new Node(val,1);
      if(val >= p.val) p.right = child;
      if(val < p.val) p.left = child;
      child.parent = p;
      updateSizes(p);//updates the sizes of the subtrees
      root.size++;
      return child;
    }

    private void updateSizes(Node p){
      Node P = p;
      while(P != root){
        P.size++;
        P = P.parent;
      }
    }

    public void runPred(int[] queries){
      for(int i = 0; i<queries.length; i++){
        int x = pred(queries[i]);
      //  if(x != -1) System.out.print(x + " ");
      //  else        System.out.print("None ");
      }

    }

    public int pred(int v) {
      Node closest = pred2(v);
      if(closest == null || closest.val > v) return -1;
      else return closest.val;
     }

     public Node pred2(int val){
       Node current = root;
       Node former = current;
       while(current!=null){
         if   (val == current.val) return current;
         if   (val > current.val){
           former = current;
           current = current.right;
         }
         else current = current.left;
       }
       return former;
     }




  /*    public static void main(String[] args)throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

          // n is the size of set S
       int n = Integer.parseInt(br.readLine());

        // read in set S into an int[]
       String[] stringSetS = br.readLine().split(" ");
      //  n = stringSetS.length;*/
  /*      int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
          arr[i] = Integer.parseInt(stringSetS[i]);

        }

        Arrays.sort(arr);

        SearchTree st = new SearchTree(arr,0.0);

        st.createTree(null,0,n-1);


      String[] stringQueries = br.readLine().split(" ");
        int q = stringQueries.length;
        int[] queries = new int[q];

      for(int i = 0; i < q; i++){
          queries[i] = Integer.parseInt(stringQueries[i]);
        //  st.pred(queries[i])
          int x = st.pred(queries[i]);
          if(x != -1) System.out.print(x + " ");
          else        System.out.print("None ");
        }

        System.out.println();

      /* int[] o_arr = st.getOtherArray();
       System.out.println(o_arr.length);
        for(int i = 1; i < o_arr.length; i+=3){
          System.out.print(o_arr[i-1] + " " + o_arr[i] + " " + o_arr[i+1] + " | ");
        }*/




  //    }




}
