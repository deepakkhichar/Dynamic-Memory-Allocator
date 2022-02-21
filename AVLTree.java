// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }
    
    

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    private int getheight(AVLTree root) {
    	if(root==null) {
    		return -1;
    	}
    	else {
    		return root.height;
    	}
    }
    
    private int getbalance(AVLTree a) {
    	if(a==null) {
    		return 0;
    	}
    	else {
    		return getheight(a.left)-getheight(a.right);
    	}
    }
    private AVLTree Insert1(AVLTree root, int address, int size, int key) {
    	
    	
    	if(root==null) {
    		return new AVLTree(address, size, key);
    	}
    	if (key<root.key) {
			
    		root.left = Insert1(root.left, address, size, key); 
    		root.left.parent = root;
    		
    	}
    	else if(key>root.key) {
    		root.right =Insert1(root.right, address, size, key); 
    		root.right.parent = root;
    	}
    	else if (address<root.address) {
    		root.left =Insert1(root.left, address, size, key);
    		root.left.parent = root;
    	}
    	else if(address>root.address){
    		root.right =Insert1(root.right, address, size, key); 
    		root.right.parent = root;
    	}
    	
    	root.height = Math.max(getheight(root.left),getheight(root.right))+1;
    	
    	if(root.parent!=null && getbalance(root)>1 && (key<root.left.key || (key==root.left.key && address<root.left.address))) {
    		return leftleftcase(root);
    	}
    	if(root.parent!=null && getbalance(root)<-1 && (key>root.right.key || (key==root.right.key && address>root.right.address))) {
    		return rightrightcase(root);
    	}
    	if(root.parent!=null && getbalance(root)>1 && (key>root.left.key || (key==root.left.key && address>root.left.address))){
    		return leftrightcase(root);
    	}
    	if(root.parent!=null && getbalance(root)<-1 && (key<root.right.key || (key==root.right.key && address<root.right.address))) {
    		return rightleftcase(root);
    	}
    	return root;
    }
    public AVLTree Insert(int address, int size, int key) 
    { 
    	AVLTree a = new AVLTree(address, size, key);
    	AVLTree b = getroot(this);
    	if(b==null) {
    		
    		this.right=a;
    		a.parent=this;
    		return a;
    	}
    	Insert1(b,address,size,key);
    	return a;
    	
    }

    private AVLTree leftleftcase(AVLTree root) {
    	AVLTree z = root;
    	AVLTree y = root.left;
    	AVLTree x = root.left.left;
    	
    	z.height = Math.max(getheight(z.right), getheight(z.left.right))+1;
    	y.height = Math.max(getheight(y.left), z.height)+1;
    	
    	
    	
    	if(z.parent.right == z) {
    		y.parent =z.parent;
    		z.parent.right=y;
    	}
    	else if(z.parent.left==z) {
    		y.parent = z.parent;
    		z.parent.left = y;
    	}
    	z.left = y.right;
    	if(y.right!=null) {
    		y.right.parent =z;
    	}
    	z.parent =y;
    	y.right = z;
    	
    	
    	
    	return y;
    }
    
    private AVLTree rightrightcase(AVLTree root) {
    	AVLTree z = root;
    	AVLTree y = root.right;
    	AVLTree x = root.right.right;
    	
    	z.height = Math.max(getheight(z.left), getheight(z.right.left))+1;
    	y.height = Math.max(getheight(y.right), z.height)+1;
    	
    	
    	
    	if(z.parent.right == z) {
    		y.parent =z.parent;
    		z.parent.right=y;
    	}
    	else if(z.parent.left==z) {
    		y.parent = z.parent;
    		z.parent.left = y;
    	}
    	z.right = y.left;
    	if(y.left!=null) {
    		y.left.parent =z;
    	}
    	z.parent =y;
    	y.left = z;
    	
    	
    	
    	return y;
    }
    
    private AVLTree leftrightcase(AVLTree root) {
    	AVLTree z =root;
    	AVLTree y =root.left;
    	AVLTree x =root.left.right;
    	
    	y.height = Math.max(getheight(y.left),getheight(x.left))+1;
    	z.height = Math.max(getheight(x.right), getheight(z.right))+1;
    	x.height = Math.max(y.height, z.height)+1;
    	
    	
    	
    	if(z.parent.right == z) {
    		x.parent = z.parent;
    		z.parent.right = x;
    	}
    	else if(z.parent.left == z) {
    		x.parent = z.parent;
    		z.parent.left = x;
    	}
    	
    	y.right = x.left;
    	if(x.left!=null) {
    		x.left.parent = y;
    	}
    	x.left = y;
    	y.parent = x;
    	
    	z.left = x.right;
    	if(x.right!=null) {
    		x.right.parent = z;
    	}
    	
    	x.right = z;
    	z.parent = x;
    	
    	
    	return x;
    }
    
    private AVLTree rightleftcase(AVLTree root) {
    	AVLTree z =root;
    	AVLTree y =root.right;
    	AVLTree x =root.right.left;
    	
    	y.height = Math.max(getheight(y.right),getheight(x.right))+1;
    	z.height = Math.max(getheight(x.left), getheight(z.left))+1;
    	x.height = Math.max(y.height, z.height)+1;
    	
    	
    	
    	if(z.parent.right == z) {
    		x.parent = z.parent;
    		z.parent.right = x;
    	}
    	else if(z.parent.left == z) {
    		x.parent = z.parent;
    		z.parent.left = x;
    	}
    	
    	y.left = x.right;
    	if(x.right!=null) {
    		x.right.parent = y;
    	}
    	
    	x.right = y;
    	y.parent = x;
    	
    	z.right = x.left;
    	if(x.left!=null) {
    		x.left.parent = z;
    	}
    	
    	x.left = z;
    	z.parent = x;
    	
    	
    	return x;
    }
    
    
    public AVLTree getroot(AVLTree root) {
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
    
    
    private AVLTree Delete1(AVLTree root , int address, int size, int key) {
    	
    	if(root==null) {
    		return root;
    	}
    	if(key<root.key) {
    		root.left = Delete1(root.left, address, size, key);
    	}
    	else if(key>root.key) {
    		root.right = Delete1(root.right, address, size, key);
    	}
    	else if(address<root.address) {
    		root.left = Delete1(root.left, address, size, key);
    	}
    	else if(address>root.address) {
    		root.right = Delete1(root.right, address, size, key);
    	}
    	else {
    		//node has utmost one child
    		if(root.left==null || root.right==null) {
    			if(root.left ==null && root.right==null) {
    				root=null;
    			}
    			else if(root.left==null) {
    				root.key =root.right.key;
    				root.address = root.right.address;
    				root.size = root.right.size;
    				root.right.parent = null;
    				root.right = null;
    			}
    			else if(root.right==null) {
    				root.key =root.left.key;
    				root.address = root.left.address;
    				root.size = root.left.size;
    				root.left.parent = null;
    				root.left = null;
    			}
    		}
    		//node has bot children
    		else {
    			root.key = findsuccessor(root.right).key;
    			root.address = findsuccessor(root).address;
    			root.size = findsuccessor(root).size;
    			
    			root.right = Delete1(root.right,root.address,root.size,root.key);
    		}
    	}
    	
    	if(root==null) {
    		return root;
    	}
    	root.height = Math.max(getheight(root.left),getheight(root.right))+1;
    	
    	if(getbalance(root)>1 && getbalance(root.left)>=0) {
    		return leftleftcase(root);
    	}
    	if(getbalance(root)>1 && getbalance(root.left)<0) {
    		return leftrightcase(root);
    	}
    	if(getbalance(root)<-1 && getbalance(root.right)<=0) {
    		return rightrightcase(root);
    	}
    	if(getbalance(root)<-1 && getbalance(root.right)>0) {
    		return rightleftcase(root);
    	}
    	
    	return root;
    }
    
    private AVLTree findsuccessor(AVLTree root) {
    	AVLTree temp = root;
    	while(temp.left!=null) {
    		temp = temp.left;
    	}
    	return temp;
    }
    
    public boolean Delete(Dictionary e)
    {
    	AVLTree temp = getroot(this);
    	
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
    	
    	Delete1(getroot(this),e.address,e.size,e.key);
        return true;
    }
        
    private AVLTree findpredecessor(AVLTree root) {

    	if(root==null) {
    		return null;
    	}
    	while(root.right!=null) {
    		root=root.right;
    	}
    	return root;
    }
    
    public AVLTree Find(int k, boolean exact)
    { 
    	AVLTree temp = getroot(this);
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
    		else if(findpredecessor(temp.left).key<key && temp.left.key<key && temp.left.right!=null){
    			break;
    		
    		}
    		else if(findpredecessor(temp.left).key>=key && temp.left.key<key && temp.left.right!=null){
    			temp = temp.left;
    		
    		}
    		else{
    		
    			temp = temp.left;
    		}
    	}
    	
        return temp;
    }

    

    public AVLTree getFirst()
    { 
	    AVLTree temp = getroot(this);       
	   	 // doubt what to do if subtree is empty
	   	while(temp!=null && temp.left!=null) {
	   		temp = temp.left;
	   	}
	   	
	       return temp;
        
    }

    public AVLTree getNext()
    {
    	if (this.parent == null) {
    		return null;
    	}
    	else {
    		AVLTree temp = this;
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
    	AVLTree temp1 = this;
    	AVLTree temp2 = this;
    	
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
    	
    	if(!sanity3(temp1.right)) {
    		return false;
    	}
    	
        return true;
    }
    
    
    private boolean sanity1(AVLTree root) {
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

   
    private boolean sanity2(AVLTree root) {
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
    
    
    private boolean sanity3(AVLTree root) {
    	
   
    	if(root==null) {
    		return true;
    	}
    	int leftheight = getheight(root.left);
    	int rightheight = getheight(root.right);
    	
    	if(Math.abs(leftheight - rightheight)<=1 && sanity3(root.right) && sanity3(root.left)) {
    		return true;
    	}
    	return false;
    }
}


