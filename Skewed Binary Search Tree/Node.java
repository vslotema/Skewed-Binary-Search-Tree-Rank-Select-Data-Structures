public class Node
{
   int val;
   Node parent;
   Node left, right;
   int size;
   int otherArr_indx; //index of the node in otherArray

  public Node(int val, int size){
   this.val = val; this.size = size; 
  }

}
