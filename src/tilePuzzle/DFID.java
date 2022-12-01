package tilePuzzle;


import java.util.HashSet;

import java.util.Set;


public class DFID {
	public static int col=Ex1.col;
    public static int row=Ex1.row;
  
    
    
	public static String DFID2(Block [] start, Block [] goal) {
		String result="";
		   Set<Node> open = new HashSet<>();
		Node sta=new Node(start);
		boolean flag=false;
		
		for(int depth=1;!flag;depth++) {
			
			result=Limited_DFS(sta,goal,depth,open);
			
			if(result!="cutoff" ) {
				flag=true;
				
				
				return result;
				
				
			}
			
			
		}
		return "fail";
		
	}
	public static String Limited_DFS(Node start,Block[] goal,int limit,Set<Node> h) {
		String result="";
		boolean isCutoff;
		
		Node ans;
		
		
		if(BFS.equalArrays(start.arrBlock, goal)) {
			Ex1.path=start.path;
			Ex1.costOfResult=start.cost;
		    return start.path; 
		}
		else if (limit==0) {
			
			return "cutoff";
		}
		else {
			h.add(start);
			isCutoff=false;
			int pos =BFS.where0(start);
		
			ans=left(start,pos,h);
		if (ans!=null) {
			if(!h.contains(ans)) {
		    	  Ex1.count++;
			}
			result=Limited_DFS(ans, goal, limit-1, h);
			
			if(result=="cutoff") {
				isCutoff=true;
			}
			  
			else if (result!="fail") {
				
				return result;
			
			  }
			}
		
		ans=up(start,pos,h);
		if (ans!=null) {
			if(!h.contains(ans)) {
		    	  Ex1.count++;
			}
			result=Limited_DFS(ans, goal, limit-1, h);
			
			if(result=="cutoff") {
				isCutoff=true;
			}
			  
			else if (result!="fail") {
				
				return result;
			
			  }
			}

			ans=right(start,pos,h);
		if (ans!=null) {
			if(!h.contains(ans)) {
		    	  Ex1.count++;
			}
			result=Limited_DFS(ans, goal, limit-1, h);
			
			if(result=="cutoff") {
				isCutoff=true;
			}
			  
			else if (result!="fail") {
				
				return result;
			
			  }
			}
	
		    ans=down(start,pos,h);
		   if (ans!=null) {
			   
			   if(!h.contains(ans)) {
			    	  Ex1.count++;
				}
			   result=Limited_DFS(ans, goal, limit-1, h);
				
				if(result=="cutoff") {
					isCutoff=true;
				}
				  
				else if (result !="fail") {
					
					return result;
				
				  }
				}
		   
			
		
		h.remove(start);
		
		if(Ex1.openList.equalsIgnoreCase("with open")) {
				System.out.println(h.toString());
			}
		
		if(isCutoff==true)
			return "cutoff";
		else 
			return "fail";
		}
		
	
		
	}
	public static Node up(Node s,int p, Set<Node> h) {
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
    
	 public static Node down(Node s,int p,Set<Node> h) {
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

	
	public static Node left(Node s,int p,Set<Node> h) {
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
	        
		   
		        
		   return null;
		        
		}

	
	public static Node right(Node s,int p, Set<Node> h) {
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


}