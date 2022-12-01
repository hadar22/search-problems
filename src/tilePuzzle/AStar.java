package tilePuzzle;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

public class AStar {

	 public static int col=Ex1.col;
	 public static int row=Ex1.row;
	
	/*
	 * The heuristic function that calculates the distances
	 *  of the current location to the real location - the number
	 *   of moves to the real location with the cost of that block
	 */
	
	public static int h(Node n) {
		
		int sum=0;
		
		 Node.initMat(n);
		
		for(int i=0;i<row*col;i++) {
		       if(n.arrBlock[i].num==0) {
		    	   continue;
		       }
			   if(n.arrBlock[i].num != (i+1)) {
				    int tmpR=n.arrBlock[i].num-1;
				   
				       int x=n.arrBlock[tmpR].mat[0]-n.arrBlock[i].mat[0];
				       int y=n.arrBlock[tmpR].mat[1]-n.arrBlock[i].mat[1];
				        if(n.arrBlock[i].color!="red") {
				            sum=sum+Math.abs(x)+Math.abs(y);
				            
				         }
				        else {
				    	   sum=sum+(Math.abs(x)+Math.abs(y))*30;
				    	   
				    }
	         
			}
			  
		}
		
		return sum;
	}
	
	public static void UCS(Block[] start,Block []goal) {
	
		Node startN=new Node(start);
		Node ans;
		
		PriorityBlockingQueue<Node> open = new PriorityBlockingQueue<>(100, new VER_COMP());
		Set<Node> openH=new HashSet<>();
		Set<Node> close=new HashSet<>();
		
		open.add(startN);
		openH.add(startN);
		while(!open.isEmpty()) {
			
			Node tmp=open.remove();
			openH.remove(tmp);
			
			if(BFS.equalArrays(tmp.arrBlock, goal)) {
				
				Ex1.path=tmp.path;
				Ex1.costOfResult=tmp.cost;
				return;
			}
			close.add(tmp);
			int pos=BFS.where0(tmp);
			ans=left(tmp,pos);
			if(ans!=null) {
				
			    if(!close.contains(ans) && !openH.contains(ans)) {
				open.add(ans);
				openH.add(ans);
				Ex1.count++;
			   }
			}
			
			ans=up(tmp,pos);
			if(ans!= null) {
				
			    if(!close.contains(ans) && !openH.contains(ans)) {
			    	
				   open.add(ans);
				   openH.add(ans);
				   Ex1.count++;
			    }
			}
			ans=right(tmp,pos);
			if(ans!=null) {
				
			    if(!close.contains(ans) && !openH.contains(ans)) {
			    	
				   open.add(ans);
				   openH.add(ans);
				 Ex1.count++;
			    }
			}
			ans=down(tmp,pos);
			if(ans!= null) {
				
			    if(!close.contains(ans) && !openH.contains(ans)) {
				    open.add(ans);
				    openH.add(ans);
				    Ex1.count++;
			    }
			}
			
			if(Ex1.openList.equalsIgnoreCase("with open")) {
				System.out.println(open.toString());
			}
		}
		
	}
	 
	public static Node up(Node s,int p) {
		   Block [] str =new Block[s.arrBlock.length];
		   
 		
	    	if(s.pather=='d') {
	    		return null;
	    	}
	  
	        if ((p < col*row-col)) {
	        	if(s.arrBlock[p+col].color=="black") {
			         return null;
			       }
	        	for(int i=0;i<str.length;i++) {
	    			str[i]=s.arrBlock[i];
	    		}
		        Block a = s.arrBlock[p + col];

		        str[p+col]=s.arrBlock[p];
		        str[p]=a;
		    
		        Node tmp=new Node(str);
		        tmp.pather='u';
		        int costBlock=0;
		        if(tmp.arrBlock[p].color=="red") {
		        	costBlock=30;
		        }
		        else {
		        	costBlock=1;
		        }
		        tmp.cost=s.cost+costBlock;
		        tmp.f=tmp.cost+h(tmp);
		        if(s.path==null) {
			    	tmp.path=a.num+"U";
			    }
			    else {
			    	tmp.path=s.path+"-"+a.num+"U";
			    }
			       
		     
        	
        	return tmp;
	    	
	    }
	        
	   
	    return null;
	}

	/*
	 * MOVEMENT DOWN
	 */
 
	public static Node down(Node s,int p) {
	    Block[] str = new Block[s.arrBlock.length];
	    
	
	    if(s.pather=='u') {
	    	return null;
	    }
	    if ((p >col)) {
	    	if(s.arrBlock[p-col].color=="black") {
		         return null;
		       }
	    	 
	    	for(int i=0;i<str.length;i++) {
			str[i]=s.arrBlock[i];
		}
	        Block a = s.arrBlock[p - col];
	       
	        str[p-col]=s.arrBlock[p];
	        str[p]=a;
	   
	        Node tmp= new Node(str);
	        tmp.pather='d';
	        int costBlock=0;
	        if(tmp.arrBlock[p].color=="red") {
	        	costBlock=30;
	        }
	        else {
	        	costBlock=1;
	        }
	        tmp.cost=s.cost+costBlock;
	        tmp.f=tmp.cost+h(tmp);
	        if(s.path==null) {
		    	tmp.path=a.num+"D";
		    }
		    else {
		    	tmp.path=s.path+"-"+a.num+"D";
		    }
		       
	     
    	
    	return tmp;
	   
	    
	    }
	        return null;
	}

	/*
	 * MOVEMENT LEFT
	 */
	public static Node left(Node s,int p) {
		 Block [] str = new Block[s.arrBlock.length];
		  
		 
		 if (s.pather=='r') {
			 return null;
		 }
		 if (p % col !=col-1) { 
		      if(s.arrBlock[p+1].color=="black") {
		         return null;
		       }
		      
		       for(int i=0;i<str.length;i++) {
				    str[i]=s.arrBlock[i];
			     }
		
		       Block a = s.arrBlock[p + 1];
			    str[p+1]=s.arrBlock[p];
			    str[p]=a;
			    
			        Node tmp= new Node(str);
			        tmp.pather='l';
			        int costBlock=0;
			        if(tmp.arrBlock[p].color=="red") {
			        	costBlock=30;
			        }
			        else {
			        	costBlock=1;
			        }
			        tmp.cost=s.cost+costBlock;
			        tmp.f=tmp.cost+h(tmp);
			        if(s.path==null) {
				    	tmp.path=a.num+"L";
				    }
				    else {
				    	tmp.path=s.path+"-"+a.num+"L";
				    }
				       
			     
	        	
	        	return tmp;
			      }
		 
		        
		   return null;
		        
		}

	/*
	 * MOVEMENT RIGHT
	 */
	public static Node right(Node s,int p) {
		   Block [] str = new Block [s.arrBlock.length];
		  
		
		   if (s.pather=='l') {
			   return null;
		   }
		
		   if (p %col !=0) {
			   if(s.arrBlock[p-1].color=="black") {
			         return null;
			       }
			   for(int i=0;i<str.length;i++) {
				str[i]=s.arrBlock[i];
			}
		        Block a = s.arrBlock[p -1];

		        str[p-1]=s.arrBlock[p];
		        str[p]=a;
		    
		        Node tmp= new Node(str);
		        tmp.pather='r';
		        int costBlock=0;
		        if(tmp.arrBlock[p].color=="red") {
		        	costBlock=30;
		        }
		        else {
		        	costBlock=1;
		        }
		        tmp.cost=s.cost+costBlock;
		        tmp.f=tmp.cost+h(tmp);
		        if(s.path==null) {
			    	tmp.path=a.num+"R";
			    }
			    else {
			    	tmp.path=s.path+"-"+a.num+"R";
			    }
			       
		     
        	
        	return tmp;
		      
	        }
	
		    return null;    
		}
	
	 
}
