import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 
    //Your BST (and AVL tree) implementations should obey the property that keys in the left subtree <= root.key < keys in the right subtree. How is this total order between blocks defined? It shouldn't be a problem when using key=address since those are unique (this is an important invariant for the entire assignment123 module). When using key=size, use address to break ties i.e. if there are multiple blocks of the same size, order them by address. Now think outside the scope of the allocation problem and think of handling tiebreaking in blocks, in case key is neither of the two. 
    public void Defragment() {
    	Dictionary current = this.freeBlk.getFirst();
    	ArrayList<Dictionary> arr = new ArrayList<>();
    	int numelements = 0;
    	while(current!=null){
    		arr.add(current);
    		numelements += 1;
    		current = current.getNext();
    	}
    	Collections.sort(arr, new sortByKey());
    	//for (int i=0;i<numelements;i++){
    	//	System.out.print(arr.get(i).address + " ");
    	//}
    	Dictionary curr;
    	if (numelements > 0) curr = arr.get(0);
    	else curr = null;
    	this.freeBlk = new BSTree();
		int index = 0;
    	while(curr!=null){
    		int index1 = index;
    		while(index+1<numelements && arr.get(index+1)!=null && ((curr.address + curr.size) == arr.get(index+1).address)){
    			index += 1;
    			curr = arr.get(index);
    		}
    		if (index > index1){
    			int s = arr.get(index).address + arr.get(index).size - arr.get(index1).address;
    			this.freeBlk.Insert(arr.get(index1).address, s, s);
    		}
    		else{
    			this.freeBlk.Insert(arr.get(index1).address, arr.get(index1).size, arr.get(index1).key);
    		}
    		index += 1;
    		if (index<numelements) curr = arr.get(index);
    		else break;
    	}
    	return;
    }
    class sortByKey implements Comparator<Dictionary>{

		@Override
		public int compare(Dictionary arg0, Dictionary arg1) {
			return arg0.address - arg1.address;
		}
    	
    }
}