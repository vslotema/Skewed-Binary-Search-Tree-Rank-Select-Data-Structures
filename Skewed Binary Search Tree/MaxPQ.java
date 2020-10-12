import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarException;

public class MaxPQ {

    //Max-PriorityQueue based on the MaxPQ class written by Sedgewick and Kevin Wayne

    private  Node[] pq;
    private  int indx;

    public MaxPQ(int N){
        pq = new Node[N+1];
        indx = 0;
    }

    public Node[] getPq(){
        return pq;
    }

    private int resize(int capacity) {
        assert capacity > indx;
        Node[] temp = new Node[capacity];
        for (int i = 1; i <= indx; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
        return 1;
    }

    public boolean isEmpty(){
        return indx == 0;
    }

    public double insert(Node item) {
        // double size of array if necessary
       if (indx == pq.length - 1) resize(2 * pq.length);
    //    System.out.println("node " + item.val + " index " + indx);
        pq[++indx] = item;
        swim(indx);
        return 1.0;
    }

    public Node delMax(){
        Node max = pq[1];
        exch(1,indx--);
        sink(1);
        pq[indx+1] = null;
        if ((indx > 0) && (indx == (pq.length - 1) / 4)) resize(pq.length / 2);
        return max;
    }



    private void sink(int k){
        while(2*k <= indx){
            int j = 2*k;
            if(j<indx && less(j,j+1)) j++;
            if(!less(k,j)) break;
            exch(k,j);
            k = j;
        }
    }

    private void swim(int k){

        while(k > 1 && less(k/2,k)){
            exch(k,k/2);
            k = k/2;
        }
    }

    private boolean less(int i, int j){
        return Integer.compare(pq[i].size,pq[j].size) <  0;
    }

    private void exch(int i, int j){
        Node swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }


    public void print(){
        for(int i = 0; i <= indx;i++){
            System.out.println(pq[i]);
        }
    }
}
