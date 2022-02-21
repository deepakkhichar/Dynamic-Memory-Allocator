// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).
    // While inserting into the list, only call insert at the head of the list
    // Please note that ALL insertions in the DLL (used either in A1DynamicMem or used independently as the â€œdictionaryâ€ class implementation) are to be made at the HEAD (from the front).
    // Also, the find-first should start searching from the head (irrespective of the use for A1DynamicMem). Similar arguments will follow with regards to the ROOT in the case of trees (specifying this in case it was not so trivial to anyone of you earlier)
    public int Allocate(int blockSize) {
    	Dictionary node = freeBlk.Find(blockSize,false);
    	if(node==null) {
    		return -1;
    	}
    	else {
    		if(node.size==blockSize) {
    			allocBlk.Insert(node.address,blockSize,blockSize);
    			freeBlk.Delete(node);
    			return node.address;
    		}
    		else {
    			int a = node.size-blockSize;
    			//A1List nodeforfree = new A1List(node.address+blockSize,a,a);    // address is correct?
    			allocBlk.Insert(node.address, blockSize, blockSize);
    			freeBlk.Delete(node);
    			freeBlk.Insert(node.address+blockSize, a, a);
    			
    			
//    			nodefreeBlk.prev=node.next.prev.prev;
//    			node.next.prev.prev.next=nodefreeBlk;   
//    			nodefreeBlk.next=node.next;
//    			node.next.prev=nodefreeBlk;
//    			
//    			allocBlk.Insert(node.address,blockSize,blockSize);
    			return node.address;
    		}
    	}
    } 
    // return 0 if successful, -1 otherwise
    public int Free(int startAddr) {
    	/*
    	Dictionary temp = allocBlk.Find(startAddr, true);*/
    	
    	Dictionary temp = allocBlk.getFirst();
    	
    	if(temp==null) {
    		return -1;
    	}
    	/*
    	if(temp.address==startAddr) {
    		freeBlk.Insert(startAddr,temp.key,temp.size);
        	allocBlk.Delete(temp);
        	return 0;
    	}*/
    	while(temp.address!=startAddr) {
    		temp = temp.getNext();
    		if(temp==null) {
    			return -1;
    		}
    		
    	}
    	
    	
    	freeBlk.Insert(startAddr,temp.key,temp.size);
    	allocBlk.Delete(temp);
    	return 0;

    	
    }
    	   
}