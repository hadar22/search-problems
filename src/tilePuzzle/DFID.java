package tilePuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import javax.xml.stream.events.StartDocument;

public class DFID {
	
	
	
	static int col=BFS.col;
	static int row=BFS.row;
	static int count=1;
	public static Block [] goalB=BFS.goalB;
	
	
	public static String up(Node s, int p, Set<Node> h) {
		   Block [] str =new Block[s.arrBlock.length];
		    		
		    		
		    	if(s.pather=='d') {
		    		return null;
		    	}
		  
		        if ((p < col*row-col)) {
		        	for(int i=0;i<str.length;i++) {
		    			str[i]=s.arrBlock[i];
		    		}
			        Block a = s.arrBlock[p + col];

			        str[p+col]=s.arrBlock[p];
			        str[p]=a;
			    
			        Node tmp=new Node(str);
			        tmp.pather='u';
		       
		    	   System.out.println("U");
		    	  // return tmp;
		    	   if(!h.contains(tmp)) {
			    	   count++;
			    	   if (Arrays.equals(str, goalB)) {
			    		   System.out.println("success:"+count);
			    		   return "success";
			    	   }
		              return "cutoff";
			        }
		    	
		    }
		        
		   
		    return null;
		}

		/*
		 * MOVEMENT DOWN
		 */
	    
		public static String down(Node s, int p,Set<Node> h) {
		    Block[] str = new Block[s.arrBlock.length];
		    
		
		    if(s.pather=='u') {
		    	return null;
		    }
		    if ((p >col)) {
		    	for(int i=0;i<str.length;i++) {
				str[i]=s.arrBlock[i];
			}
		        Block a = s.arrBlock[p - col];
		       
		        str[p-col]=s.arrBlock[p];
		        str[p]=a;
		   
		        Node tmp= new Node(str);
		        tmp.pather='d';
		      
		        	
		        	System.out.println("D");
		        	//return tmp;
		        	if(!h.contains(tmp)) {
				    	   count++;
				    	   if (Arrays.equals(str, goalB)) {
				    		   System.out.println("success:"+count);
				    		   return "success";
				    	   }
			              return "cutoff";
				        }
		        } 
		   
		    
		    
		        return null;
		}

		/*
		 * MOVEMENT LEFT
		 */
		public static String left(Node s, int p,Set<Node> h) {
			 Block [] str = new Block[s.arrBlock.length];
			  
			 if (s.pather=='r') {
				 return null;
			 }
			    
			        if (p % col !=col-1) { 
			        	for(int i=0;i<str.length;i++) {
					       str[i]=s.arrBlock[i];
				        }
			
			        	
				        Block a = s.arrBlock[p + 1];
				
				        str[p+1]=s.arrBlock[p];
				        str[p]=a;
				    
				        Node tmp= new Node(str);
				        tmp.pather='l';
			       
		        	
		        	System.out.println("L");
		        	//return tmp;
		        	if(!h.contains(tmp)) {
				    	   count++;
				    	   if (Arrays.equals(str, goalB)) {
				    		   System.out.println("success:"+count);
				    		   return "success";
				    	   }
			              return "cutoff";
				        }
			        }
		        
			   
			        
			   return null;
			        
			}

		/*
		 * MOVEMENT RIGHT
		 */
		public static String right(Node s, int p,Set<Node> h) {
			   Block [] str = new Block [s.arrBlock.length];
			   
			
			   if (s.pather=='l') {
				   return null;
			   }
			
			   if (p %col !=0) {
				   for(int i=0;i<str.length;i++) {
					str[i]=s.arrBlock[i];
				}
			        Block a = s.arrBlock[p -1];

			        str[p-1]=s.arrBlock[p];
			        str[p]=a;
			    
			        Node tmp= new Node(str);
			        tmp.pather='r';
			     
		        	System.out.println("R");
		        	//return tmp;
		            if(!h.contains(tmp)) {
				    	   count++;
				    	   if (Arrays.equals(str, goalB)) {
				    		   System.out.println("success:"+count);
				    		   return "success";
				    	   }
			              return "cutoff";
				        }
		        }
		
			    return null;    
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
	
	public static String DFID2(Block  [] start, Block [] goal) {
		String result="";
		   Set<Node> H = new HashSet<>();
		Node sta=new Node(start);
		
		for(int depth=1;depth<6;depth++) {
			
			result=Limited_DFS(sta,goal,depth,H);
			if(result != "cutoff") {
				return result;
				
			}
			
			System.out.println("incres");
			//depth++;
		    	
		}
		return "fail";
		
	}
	public static String Limited_DFS(Node start,Block[] goal,int limit,Set<Node> h) {
		String result="";
		boolean isCutoff;
		//Block [] temp =new Block[goal.length];
		//Block [] X =new Block[goal.length];
		
		if(Arrays.equals(start.arrBlock, goal)) {
		    return "find"; // נדפיס את התור ששומר לאן הלכנו כל פעם
		}
		else if (limit==0) {
			System.out.println("j");
			return "cutoff";
		}
		else {
			h.add(start);
			isCutoff=false;
			int pos =where0(start);
		if (left(start,pos,h)!=null) {
			//count++;
			if(left(start,pos,h)=="success")
				return "g";
			
			  
			  if (result=="cutoff")
				isCutoff=true;
			result=Limited_DFS(start, goal, limit-1, h);
			}
		if (up(start,pos,h)!=null) {
			//count++;
			if(up(start,pos,h)=="success") 
				return "g";
			System.out.println("-u");
			
			if (result=="cutoff")
				isCutoff=true;
			result=Limited_DFS(start, goal, limit-1, h);
			}
			
		if (right(start,pos,h)!=null) {
			count++;
			if(right(start,pos,h)=="success") 
				return "g";
			System.out.println("-r");
			
			if (result=="cutoff")
				isCutoff=true;
			result=Limited_DFS(start, goal, limit-1, h);
			}
		if (down(start,pos,h)!=null) {
			count++;
			if(down(start,pos,h)=="success") 
				return "g";
			System.out.println("-d");
			
			if (result=="cutoff")
				isCutoff=true;
			result=Limited_DFS(start, goal, limit-1, h);
		}
		System.out.println(count);
		h.remove(start);
		if(isCutoff==true)
			return "cutoff";
		else 
			return "fail";
		}
		
	
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
