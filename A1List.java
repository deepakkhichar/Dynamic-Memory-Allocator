// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
    	A1List newnode = new A1List(address,size,key);
    	
    	if (this==null) {
    		A1List a = new A1List();
    		newnode.next = a.next;
    		a.next.prev=newnode;
    		a.next=newnode;
    		newnode.prev=a;
    		return newnode;
    		
    	}
//    	if(!this.sanity()) {
//    		return null;
 //   	}
    	if(this.next==null) {
    		return null;
    	}
    	
    	else {
    		newnode.next=this.next;
    		newnode.next.prev=newnode;
    		this.next=newnode;
    		newnode.prev=this;
    		return newnode;
    	}
    	
    }

    public boolean Delete(Dictionary d) 
    {
    	
    	A1List temp = this;
    	
//    	if(!temp.sanity()) {
//    		return false;
//    	}
    	while(temp.prev!=null) {
    		temp=temp.prev;
    	}
    	temp=temp.next;
    	while(temp.key!=d.key || temp.address!=d.address || temp.size!=d.size) {
    		temp=temp.getNext();
    		if(temp==null) {
    			return false;
    		}
    	
    	}
    	if(d.key==-1 && d.address==-1 && d.size==-1 && temp.next==null ) {
    		return false;
    	}
    	temp.prev.next=temp.next;
    	temp.next.prev=temp.prev;
    	return true;
    	
    	
    }

    public A1List Find(int k, boolean exact)
    { 
    	A1List temp = this;
    	
    	if(temp==null) {
    		return null;
    	}
//    	if(!this.sanity()) {
//    		return null;
//    	}
    	while(true) {
    		if(temp.prev==null) {
    			break;
    		}
    		else temp=temp.prev;
    	}
    	temp=temp.next;
    	
    	while(temp.key!=k && exact) {
    		temp = temp.next;
    		if(temp==null) {
    			return null;
    		}
    	}
    	while(temp.key<k && !exact) {
    		temp = temp.getNext();
    		if(temp==null) {
    			return null;
    		}
    		
    	}
    	return temp;
    		
    	
    }

    public A1List getFirst()
    {
    	A1List temp = this;
    	if(temp==null) {
    		return null;
    	}
//    	if(!this.sanity()) {
//    		return null;
//    	}
    	while(true) {
    	/*	if(temp.prev.key==-1 && temp.prev.address==-1 && temp.prev.size==-1) {
    			return temp;
    		}
    		else temp=temp.prev;
    	}
        return null; */
    		if(temp.prev==null && temp.next.next!=null) {
    			return temp.next;
    		}
    		else if(temp.prev==null && temp.next.next==null) {
    			return null;
    		}
    		else temp=temp.prev;
    	}
    }
    
    public A1List getNext() 
    {
    	if(this==null || this.next==null || this.next.next==null) {
    		return null;
    	}
    	
    	else {
    		return this.next;
    	}
    }

    private A1List getHeadsentinal()
    {
    	A1List temp = this;
    	if(temp==null) {
    		return null;
    	}
    	while(true) {
    	
    		if(temp.prev==null) {
    			return temp;
    		}
    		else temp=temp.prev;
    	}
    } 
    
    
    
    public boolean sanity()
    {
    	if(this==null) {
    		return true;
    	}
    	
    	
    	A1List pointer1a = this;
    	A1List pointer1b = this;
    	A1List pointer2a = this.prev;
    	A1List pointer2b = this.prev;
    	A1List pointer3 = this;
    	
    	if((pointer1a.next!=null && pointer1a.next.prev!=pointer1a) || (pointer1a.prev!=null && pointer1a.prev.next!=pointer1a)) {
			return false;
			
		}
    	
    	while(pointer1a.next!=null) {
    		pointer2a=pointer1a;
    		pointer1a=pointer1a.next;
    		if((pointer1a.next!=null && pointer1a.next.prev!=pointer1a) || (pointer1a.prev!=null && pointer1a.prev.next!=pointer1a)) {
    			return false;
    			
    		}
    		if(pointer2a!=pointer1a.prev || pointer1a==pointer3) {
    			return false;
    		}
    	}
    	
    	while(pointer2b!=null && pointer2b.prev != null) {
    		pointer1b=pointer2b;
    		pointer2b=pointer2b.prev;
    		if((pointer2b.next !=null && pointer1b.next.prev!=pointer1b) || ( pointer2b.prev!=null && pointer2b.prev.next!=pointer2b)) {
    			return false;
    			
    		}
    		if(pointer1b!=pointer2b.prev || pointer2b==pointer3) {
    			return false;
    		}
    	}
    	
    	Dictionary temp = this.getHeadsentinal();
    	if(temp.key!=-1 || temp.address!=-1 || temp.size!=-1) {
    		return false;
    	}
    	
    	while(pointer3.next!=null) {
    		pointer3 = pointer3.next;
    	}
    	if(pointer3.key!=-1 || pointer3.address!=-1 || pointer3.size!=-1) {
    		return false;
    	}
    	/*
    	A1list pointer1 = this;
    	A1list pointer2 = this;
    	//pointer2 = pointer2.getNext();
    	While(Pointer){
    		if(pointer1.next.prev!=pointer1) {
    			return false;
    		}
    		A1List pointer3 = pointer1.next;
    		A1List pointer4 = pointer2.next.next;
    		if(pointer3!=pointer2.prev) {
    			return false;
    		}
    	}
    	
    	
    	
    	
    	*/
    	
    	
    	
    	
    	
    	
    	/*
    	A1list temp = this.getFirst();
    	if(temp.key!=-1 || temp.address!=-1 || temp.size!=-1) {
    		return false;
    	}
    	while(temp.next!=null) {
    		temp=temp.getNext()
    	}
    	if(temp.key!=-1 || temp.address!=-1 || temp.size!=-1) {
    		return false;
    	}
    	
    	
        return true; */
    	
    	return true;
    }
    
    

}


