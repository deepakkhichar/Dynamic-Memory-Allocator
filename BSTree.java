// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    
    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    
//    private BSTree Insert(BSTree root, int address, int size, int key) {
//    	
//    	if (root==null) {
//    		BSTree a = new BSTree(address, size, key);
//    		root = a;
//    		return root;
//    	}
//    	
//    	if (key<root.key) {
//    		root.left = Insert(root.left, address, size, key);
//    	}
//    	else if(key>root.key) {
//    		root.right = Insert(root.right, address, size, key);
//    	}
//    	else if (address<root.address) {
//    		root.left = Insert(root.left, address, size, key);
//    	}
//    	else if(address>root.address) {
//    		root.right = Insert(root.right, address, size, key);
//    	}
//    	
//    	return root;
//    }

    public BSTree Insert(int address, int size, int key) 
    { 
    	//this.Insert(this, address, size, key);
    	
    	
    	BSTree a = new BSTree(address, size, key);
    	BSTree b = getroot(this);
    	if(b==null) {
    		this.right=a;
    		a.parent=this;
    		return a;
    	}
//    	int c=1;
    	while(true) {
//    		System.out.print("i");
//    		System.out.println(c);
//    		if(c>10) {
//    			break;
//    		}
    		if (key<b.key) {
    			if(b.left==null) {
    				b.left = a;
    				a.parent=b;
    				return b.left;
    			}
        		b = b.left; 
        		//c+=1;
        	}
        	else if(key>b.key) {
        		if(b.right==null) {
        			b.right = a;
        			a.parent=b;
        			return b.right;
    			}
        		b = b.right;
        		//c+=1;
        	}
        	else if (address<b.address) {
        		if(b.left==null) {
        			b.left = a;
        			a.parent = b;
    				return b.left;
    			}
        		b = b.left;
        		//c+=1;
        	}
        	else {
        		if(b.right==null) {
        			b.right = a;
        			a.parent = b;
    				return b.right;
    			}
        		b = b.right;
        		//c+=1;
        	}
    	}
    	//return null;
    	
    	
        
    }

    private BSTree getroot(BSTree root) {
    	while (root.parent!=null) {
    		root=root.parent;
    	}
    	//the condition of 'if' will never be true because the tree will always have sentinal node
    	if (root.address!=-1 || root.key!=-1 || root.size!=-1) {
    		return root;
    	}
    	else {
    		return root.right;
    	}
    }
    
    public boolean Delete(Dictionary e)
    { 
    	BSTree temp = getroot(this);
    	
    	if(e==null) {
    		return true;
    	}
    	//search the node to be deleted
    	while(temp!=null) {
    		if(temp.address==e.address && temp.key==e.key && temp.size==e.size) {
    			break;
    		}
    		if(e.key<temp.key) {
    			temp = temp.left;
    		}
    		else if(e.key>temp.key) {
    			temp = temp.right;
    		}
    		else if(e.address<temp.address) {
    			temp = temp.left;
    		}
    		else if(e.address>temp.address) {
    			temp = temp.right;
    		}
    	}
    	
    	//node not found
    	if(temp==null) {
    		return false;
    	}
    	
    	//doubt why sentinal node is not there
    	if (temp.parent==null) {
    		this.right.parent=null;
    		return true;
    	}
    	
    	
    	
    	//node to be deleted has atmost one child
    	if(temp.left==null || temp.right==null) {
    		
    		if(temp.key<temp.parent.key) {
    			if(temp.left==null && temp.right==null) {
    				temp.parent.left = null;
    				return true;
    			}
    			else if(temp.left==null) {
    				temp.parent.left = temp.right;
    				temp.right.parent=temp.parent;
    				return true;
    			}
    			else {
    				temp.parent.left = temp.left;
    				temp.left.parent=temp.parent;
    				return true;
    			}
    		}
    		else if(temp.key>temp.parent.key) {
    			if(temp.left==null && temp.right==null) {
    				temp.parent.right = null;
        			return true;
    			}
    			else if(temp.left==null) {
    				temp.parent.right = temp.right;
    				temp.right.parent=temp.parent;
    				return true;
    			}
    			else {
    				temp.parent.right = temp.left;
    				temp.left.parent=temp.parent;
    				return true;
    			}
    			
    		}
    		else if(temp.address<temp.parent.address) {
    			if(temp.left==null && temp.right==null) {
    				temp.parent.left = null;
        			return true;
    			}
    			else if(temp.left==null) {
    				temp.parent.left = temp.right;
    				temp.right.parent=temp.parent;
    				return true;
    			}
    			else {
    				temp.parent.left = temp.left;
    				temp.left.parent=temp.parent;
    				return true;
    			}
    			
    		}
    		else if(temp.address>temp.parent.address) {
    			if(temp.left==null && temp.right==null) {
    				temp.parent.right = null;
    				
    				return true;
    			}
    			else if(temp.left==null) {
    				temp.parent.right = temp.right;
    				temp.right.parent=temp.parent;
    				return true;
    			}
    			else {
    				temp.parent.right = temp.left;
    				temp.left.parent=temp.parent;
    				return true;
    			}
    		}
    			
    	}
    	
    	
    	//node to be deleted has two children
    	else {
    		BSTree successor = success(temp.right);
			temp.key = successor.key;
			temp.address = successor.address;
			temp.size = successor.size;
			return true;
    	}
    	
        return false;
    }
    
    private BSTree success(BSTree right2) {
		while(right2.left !=null){
			right2 = right2.left;
		}
		if (right2.left == null && right2.right == null){
			if (right2.parent.left == right2) {
				right2.parent.left = null;
				right2.parent = null;
			}
			else{
				right2.parent.right = null;
				right2.parent = null;
			}
		}
		else if (right2.left == null){
			if (right2.parent.left == right2) {
				right2.parent.left = right2.right;
				right2.parent = null;
			}
			else{
				right2.parent.right = right2.right;
				right2.parent = null;
			}
		}
		return right2;
	}


	private BSTree predecessor(BSTree root) {
    	if(root.right==null) {
    		if(root.left == null) {
    			root.parent.left = null;
    			return root;
    		}
    		else {
    			root.parent.left = root.left;
    			return root;
    		}
    		
    	}
    	
    	while(root.right!=null) {
    		root=root.right;
    	}
    	if(root.left==null) {
    		root.parent.right = null;
    		return root;
    	}
    	else {
    		root.parent.right = root.left;
    		return root;
    	}
    }
      
    private BSTree findpredecessor(BSTree root) {

    	if(root==null) {
    		return null;
    	}
    	while(root.right!=null) {
    		root=root.right;
    	}
    	return root;
    }
   
    public BSTree Find(int key, boolean exact)
    { 
    	BSTree temp = getroot(this);
    	if(temp==null) {
    		return null;
    	}
    	while(temp.key!=key && exact) {
    		if(key<temp.key) {
    			temp=temp.left;
    			if(temp==null) {
    				return null;
    			}
    		}
    		else {
    			temp = temp.right;
    			if(temp==null){
    				return null;
    			}
    		}
    	}
    	
    	while(exact && findpredecessor(temp.left)!=null && findpredecessor(temp.left).key==temp.key) {
    		temp = findpredecessor(temp.left);
    	
    	}
    	
    	while(!exact && temp!=null) {
    		if(temp.key<key) {
    			temp=temp.right;
    			if(temp==null) {
    				return null;
    			}
    		}
    		else if(temp.key==key) {
    			while( findpredecessor(temp.left)!=null && findpredecessor(temp.left).key==temp.key) {
    	    		temp = findpredecessor(temp.left);
    	    	
    	    	}
    			break;
    		}
    		else if(temp.left==null || (temp.left.key<key && temp.left.right==null)){
    			break;
    		}
    		else if(temp.left.key<key && temp.left.right!=null){
    			temp = temp.left;
    		
    		}	
    		else{
    		
    			temp = temp.left;
    		}
    	}
    	
        return temp;
    }

    public BSTree getFirst()
    { 
    	//BSTree temp = getroot(this);
    	BSTree temp = getroot(this);       
    	 // doubt what to do if subtree is empty
    	while(temp!=null && temp.left!=null) {
    		temp = temp.left;
    	}
    	
        return temp;
    }

    public BSTree getNext()
    { 
    	if (this.parent == null) {
    		return null;
    	}
    	else {
    		BSTree temp = this;
    		if(temp.right!=null) {
    			temp = temp.right;
    			while(temp.left!=null) {
    				temp = temp.left;
    			}
    			return temp;
    			
    		}
    		else {
    			while(temp.parent!=null && temp.parent.right==temp) {
    				temp = temp.parent;
    			}
    			return temp.parent;
    		}
    	}
        
    }

    public boolean sanity()
    { 
    	BSTree temp1 = this;
    	BSTree temp2 = this;
    	
    	if(temp1.parent!=null) {
    		temp1 = temp1.parent;
    		while(temp1.parent!=null ) {
    			if(temp1==temp2) {
    				return false;
    			}
    			temp1=temp1.parent;
    		}
    	}
    	if(temp1.parent==null && (temp1.left!=null || temp1.address!=-1 || temp1.size!=-1 || temp1.key!=-1)) {
    		return false;
    	}
    	
    	if(!sanity1(temp1.right)) {
    		return false;
    	}
    	if(!sanity2(temp1.right)) {
    		return false;
    	}
    	
        return true;
    }
    
    
    private boolean sanity1(BSTree root) {
    	if(root==null || (root.left==null && root.right==null )) {
    		return true;
    	}
    	if(root.left==null && root.right!=null && root.right.parent==root) {
    		return sanity1(root.right);
    	}
    	if(root.right==null && root.left!=null && root.left.parent==root) {
    		return sanity1(root.left);
    	}
    	
    	if(root.left!=null && root.right!=null && root.left.parent==root && root.right.parent==root) {
    		boolean a = sanity1(root.left);
    		if(a==false) {
    			return false;
    		}
    		boolean b = sanity1(root.right);
    		if(b==false) {
    			return false;
    		}
    		return true;
    		
    	}
    	return false;
    }

   
    private boolean sanity2(BSTree root) {
    	if (root==null || (root.left==null && root.right==null )) {
    		return true;
    	}
    	else if(root.left!=null && root.right!=null) {
    		if(root.left.key>root.key || root.right.key<root.key) {
    			return false;
    		}
    		boolean a = sanity2(root.left);
    		if(a==false) {
    			return false;
    		}
    		boolean b = sanity2(root.right);
    		if(b==false) {
    			return false;
    		}
    		return true;
    			
    		
    	
    	}
    	else if(root.left!=null) {
    		if(root.left.key>root.key) {
    			return false;
    		}
    		return sanity2(root.left);
    		
    	}
    	else if(root.right!=null) {
    		if(root.right.key<root.key) {
    			return false;
    		}
    		return sanity2(root.right);
    	}
    	return false;
    }
}