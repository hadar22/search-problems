package tilePuzzle;


import java.util.HashSet;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class BFS {
	
	
	public static int col=Ex1.col;
	public static int row=Ex1.row;
     
     public static int counter=1;
     
     
      public static void BFS1(Block [] start,Block [] goal) {
    	 
    	
         Queue<Node> open = new LinkedList<>();
          Set<Node> openH = new HashSet<>();
	        Set<Node> closed = new HashSet<>();
	       
	       open.add(new Node(start));
	        openH.add(new Node(start));
	        boolean solving = true;
	       
	        Node ans;
	        
	        
	       while (!open.isEmpty() && solving) {
	  
	            Node current = open.poll();
	            openH.remove(current);
	            closed.add(current);
	          
	            int pos = where0(current);
	            /*
	             * Send to each operator the current node and
	             *  the zero position at the same node
	             */
	                    ans=left(current, pos);
	                	if (ans!= null) {
	                		counter++;
	                		if(!closed.contains(ans) && !openH.contains(ans)) {
	                			if(equalArrays(goal,ans.arrBlock )) {
	                				solving = false;
	                				Ex1.path=ans.path;
	                				Ex1.costOfResult=ans.cost;
	                				Ex1.count=counter;
	                				break;
	                			}
	                			open.add(ans);
	                			openH.add(ans);
	                		}
	                		
	                		
	                	}
	                	ans=up(current, pos);
	                	if (ans!= null) {
	                		counter++;
	                		if(!closed.contains(ans) && !openH.contains(ans)) {
	                			if(equalArrays(goal,ans.arrBlock )) {
	                				solving = false;
	                				Ex1.path=ans.path;
	                				Ex1.costOfResult=ans.cost;
	                				Ex1.count=counter;
	                				break;
	                			}
	                			open.add(ans);
	                			openH.add(ans);
	                		}
	                		
	                		
	                	}
	                	
	                	ans=right(current, pos);
	                	if (ans!= null) {
	                		counter++;
	                		if(!closed.contains(ans) && !openH.contains(ans)) {
	                			if(equalArrays(goal,ans.arrBlock )) {
	                				solving = false;
	                				Ex1.path=ans.path;
	                				Ex1.costOfResult=ans.cost;
	                				Ex1.count=counter;
	                				break;
	                			}
	                			open.add(ans);
	                			openH.add(ans);
	                		}
	                		
	                		
	                	}
	                	ans=down(current, pos);
	                	if (ans!= null) {
	                		counter++;
	                		if(!closed.contains(ans) && !openH.contains(ans)) {
	                			if(equalArrays(goal,ans.arrBlock )) {
	                				solving = false;
	                				Ex1.path=ans.path;
	                				Ex1.costOfResult=ans.cost;
	                				Ex1.count=counter;
	                				break;
	                			}
	                			open.add(ans);
	                			openH.add(ans);
	                		}
	                		
	                		
	                	}
	                    
	                 
	                	if(Ex1.openList.equalsIgnoreCase("with open")) {
	    					System.out.println(open.toString());
	    				}   
	                }
	          //  }
	       // }
	      
	          
     }
      
	
      public static int where0(Node a) {
  		int pos=0;
  	       
          for(int i=0;i<a.arrBlock.length;i++) {
       	   if (a.arrBlock[i].num==0) {
       		   pos = i;
       		   break;
       	   }
          }
          return pos;
  	}
  	public static Node up(Node s, int p) {
		   Block [] str =new Block[s.arrBlock.length];
		    		
		    		
		    	if(s.pather=='d') {
		    		return null;
		    	}
		  
		        if ((p < col*row-col)) {
		        	/*
		        	 * If the color of the block is black
		        	 *  you cannot move so there is no answer and we will return null
		        	 */
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
		public static Node down(Node s, int p) {
		    Block[] str = new Block[s.arrBlock.length];
		    
		 ;
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
		public static Node left(Node s, int p) {
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
			      
			        if(s.path==null) {
			        	tmp.path=a.num+"L";
			        }
			        else {
			        	tmp.path=s.path+"-"+a.num+"L";
			        }
			       
			        return tmp;	
			        
		       
	        }
		   // } 
		        
		   return null;
		        
		}

		/*
		 * MOVEMENT RIGHT
		 */
		public static Node right(Node s, int p) {
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
		/*
		 * We will check if two arrays are the same according to 
		 * the locations of the block number
		 */
		 public static boolean equalArrays(Block[] a, Block []b) {
			 for(int i=0;i<a.length;i++) {
				 if(a[i].num!=b[i].num)
					 return false;
			 }
			 return true;
		 }
	
	} 
	

