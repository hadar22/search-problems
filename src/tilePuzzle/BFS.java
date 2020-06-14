package tilePuzzle;

	import java.io.*;
import java.sql.Blob;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.omg.CORBA.Current;




    

public class BFS {
	//public static Queue <Block[]> OPEN = new LinkedList<Block []>();
	public static Queue<Node> f = new LinkedList<>();
	//public static HashSet<Block[]> CLOSED = new HashSet<Block []>();
	//public static boolean STATE = false;
	static String kindSearch;
	static String printTime;
	static String openList;
	static int row;
	static int col;
 
	static String red [];
	static String [] start;
	static String [] goal;
	
	static Block [] s;
	static Block [] goalB;
	public static HashSet<Integer> blackH = new HashSet<Integer>();
	public static HashSet<Integer> redH = new HashSet<Integer>();
	public static int count=1;
	
     public static void init() throws IOException {
    	 File file = new File("input.txt"); 
    	 BufferedReader br = new BufferedReader(new FileReader(file)); 
    	  kindSearch=br.readLine(); // switch for to kind search
    	  printTime=br.readLine();
    	   openList=br.readLine();
    	   String stringSize=br.readLine();
    	   String  split[]=stringSize.split("x");

   		
			 row=Integer.parseInt(split[0]);
			 col=Integer.parseInt(split[1]);
			
			String blackList=br.readLine();
			String [] black=blackList.split(":|,");
			 for(int z=1;z<black.length;z++) {
				 if(black[z]!=null) {
					 String u=black[z];
					
				int g=Integer.parseInt(u);
				 blackH.add(g);
				 }
				 
			 }
			
			String redList=br.readLine();
			 red=redList.split(": |,");
			 for(int z=1;z<red.length;z++) {
				 if(red[z]!=null) {
					 String u=red[z];
					 int g=Integer.parseInt(u);
				     redH.add(g);
				 }
				
			 }
			
			String line = null;
            int i=0;
            start=new String [row*col];
            while ((line = br.readLine()) != null) {
           	 String[] values = line.split(",");
           	 for(int j=0;j<col;j++) {
           		 start[i]=values[j];
           		 i++;
           	 }
           	 
            } 
           goal=new String [row*col];
            goal[row*col-1]="0";
		      for (int k=1;k<(row*col);k++) {
	            	goal[k-1]=Integer.toString(k);
	            
	            }
		      s=new Block [row*col];
		      for(int z=0;z<start.length;z++) {
		    	  Block g;
		    	  if(start[z].equals("_")) {
		    		  g=new Block(0);
		    		  s[z]=g;
		    		  break;
		    		  }
		    	  int num=Integer.parseInt(start[z]);
		    	  
		    	  if(redH.contains(num)) {
		    		 g=new Block(num, "red");
		    		 s[z]=g;
		    	  }
		    	  else if(blackH.contains(num)) {
		    		  g=new Block(num, "black");
		    		  s[z]=g;
		    	  }
		    	  else {
		    		  g=new Block(num);
		    		  s[z]=g;
		    	  }
		    	
		    	  
		      }
		     // System.out.println(Arrays.toString(s));
		      goalB=new Block [row*col];
		      for(int j=0;j<start.length;j++) {
		    	  Block tmp=s[j];
		    	  
		    	  if(tmp.num==0) {
		    		  goalB[row*col-1]=tmp;
		    	  }
		    	  else {
		    		 goalB[tmp.num-1]=tmp; 
		    	  }
		    	  
		      
		      }
		   //   System.out.println(Arrays.toString(goalB));
		      
			switch(kindSearch) {
			case "BFS":
				System.out.println("na");
				BFS1(s,goalB);
				break;
			case "DFID":
				DFID2(s, goalB);
				break;
			  
			default:
				break;
			}
     }
      public static void BFS1(Block [] sta,Block [] go) {
    	  double startTime = System.nanoTime();
    	
         Queue<Node> open = new LinkedList<>();
          Set<Node> openH = new HashSet<>();
	        Set<Node> closed = new HashSet<>();
	       
	       open.add(new Node(sta));
	        openH.add(new Node(sta));
	        boolean solving = true;
	        int i=0;
	       while (!open.isEmpty() && solving) {
	      //while (i<3) { 	
	            Node current = open.remove();
	            openH.remove(current);
	            closed.add(current);
	           // Node t=new Node(current);
	            int pos = where0(current);
	             //  if (!closed.contains(current)) {
	            	//tcount++;
	            	   
	               /* if (Arrays.equals(current.arrBlock, go)) {
	                    //printPath(current);
	                	//System.out.println(t.path);
	                	System.out.println(Arrays.toString(current.arrBlock));
	                    System.out.println("SUCCESS");
	                    System.out.println(count);
	                  //  System.out.println(current[pos].path);
	                    solving = false;
	                    break;
	                } else {
	                	*/
	                    // generate children
	                	if (left(current, pos,open, openH,closed)==1) {
	                		
	                		
	    	                    solving = false;
	    	                    break;
	                		
	                	}
	                   if( up(current, pos,open, openH,closed)== 1){
	                	   
	                   
	    	                    solving = false;
	    	                    break;
	                		
	                	}
	                    
	                   if( right(current, pos,open, openH,closed)==1){
	                	   
	            
	    	                    solving = false;
	    	                    break;
	                		
	                	}
	                    
	                   if( down(current, pos,open, openH,closed)==1){
	                    	
	                  
	    	                    solving = false;
	    	                    break;
	                		}
	                	
	                    
	                    
	                  i++;
	                    
	                }
	          //  }
	       // }
	       double endTime   = System.nanoTime();
	       double totalTime = (endTime - startTime)/1_000_000_000.0;
	       System.out.println(totalTime);
	          
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
  	public static int up(Node s, int p,Queue<Node> open, Set<Node> openH,Set<Node> closed) {
		   Block [] str =new Block[s.arrBlock.length];
		    		
		    		//Node y=new Node(str);
		    	if(s.pather=='d') {
		    		return -1;
		    	}
		  
		        if ((p < col*row-col)) {
		        	for(int i=0;i<str.length;i++) {
		    			str[i]=s.arrBlock[i];
		    		}
			        Block a = s.arrBlock[p + col];

			        str[p+col]=s.arrBlock[p];
			        str[p]=a;
			       // y.path=str[p].num;
		      //  str[p-col].path=str[p-col].path+str[p].num+"U-";
	        //if(y.path!=t.path) {
			        Node tmp=new Node(str);
			        tmp.pather='u';
		       if(!closed.contains(tmp)) {
		    	   count++;
		    	   if (Arrays.equals(str, goalB)) {
		    		   System.out.println("success:"+count);
		    		   return 1;
		    	   }
		        	openH.add(tmp);
		        	open.add(tmp);
		        	//t.path+=str[p].num+"U";
		        	System.out.println("U");
		        	return 2;
		        	
		        }
	       // }
		       
		    }
		        
		   
		    return -1;
		}

		/*
		 * MOVEMENT DOWN
		 */
		public static int down(Node s, int p,Queue<Node> open,Set<Node> openH,Set<Node> closed) {
		    Block[] str = new Block[s.arrBlock.length];
		    
		  //  Node y=new Node(str);
		    if(s.pather=='u') {
		    	return -1;
		    }
		    if ((p >col)) {
		    	for(int i=0;i<str.length;i++) {
				str[i]=s.arrBlock[i];
			}
		        Block a = s.arrBlock[p - col];
		       
		        str[p-col]=s.arrBlock[p];
		        str[p]=a;
		       // y.path=str[p].num;
		       // str[p+col].path=str[p+col].path+str[p].num+"D-";
		 //  if(y.path!=t.path) {
		        Node tmp= new Node(str);
		        tmp.pather='d';
		      if(!closed.contains(tmp) ) {
		    	  count++;
		    	  if (Arrays.equals(str, goalB)) {
		    		   System.out.println("success:"+count);
		    		   return 1;
		    	   }
		        	openH.add(tmp);
		        	open.add(tmp);
		        //	t.path+=str[p].num+"D";
		        	System.out.println("D");
		        	return 2;
		        } 
		   } 
		    //}
		    
		        return -1;
		}

		/*
		 * MOVEMENT LEFT
		 */
		public static int left(Node s, int p,Queue<Node> open,Set<Node> openH,Set<Node> closed) {
		 Block [] str = new Block[s.arrBlock.length];
		  
		 if (s.pather=='r') {
			 return -1;
		 }
		    
		        if (p % col !=col-1) { 
		        	for(int i=0;i<str.length;i++) {
				       str[i]=s.arrBlock[i];
			        }
		        //	Node y=new Node(str);
		        	
			        Block a = s.arrBlock[p + 1];
			
			        str[p+1]=s.arrBlock[p];
			        str[p]=a;
			     //   y.path=str[p].num;
		      //  str[p-1].path=str[p-1].path+str[p].num+"L-";
		     //  if(y.path!=t.path) { 
			        Node tmp= new Node(str);
			        tmp.pather='l';
		        if(!closed.contains(tmp)) {
		        	count++;
		        	 if (Arrays.equals(str, goalB)) {
			    		   System.out.println("success:"+count);
			    		   return 1;
			    	   }
	        	openH.add(tmp);
	        	open.add(tmp);
	        	//t.path+=str[p].num+"L";
	        	System.out.println("L");
	        	return 2;
		        }
	        }
		   // } 
		        
		   return -1;
		        
		}

		/*
		 * MOVEMENT RIGHT
		 */
		public static int right(Node s, int p,Queue<Node> open,Set<Node> openH,Set<Node> closed) {
		   Block [] str = new Block [s.arrBlock.length];
		   
		//   Node y=new Node(str);
		   if (s.pather=='l') {
			   return -1;
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
		        if(!closed.contains(tmp)) {
		        	count++;
		        	 if (Arrays.equals(str, goalB)) {
			    		   System.out.println("success:"+count);
			    		   return 1;
			    	   }
	        	openH.add(tmp);
	        	open.add(tmp);
	        
	        	System.out.println("R");
	        	return 2;
	        }
	
		   }
		 
		    return -1;    
		}
		
		public static String DFID2(Block [] start, Block [] goal) {
			String result="";
			   Set<Node> H = new HashSet<>();
			Node sta=new Node(start);
			boolean flag=false;
			
			for(int depth=1;!flag;depth++) {
				
				result=Limited_DFS(sta,goal,depth,H);
				System.out.println("dfid:"+result);
				if(result!="cutoff" ) {
					flag=true;
					System.out.println(flag);
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
			String temp="";
			//Block [] temp =new Block[goal.length];
			//Block [] X =new Block[goal.length];
			
			if(Arrays.equals(start.arrBlock, goal)) {
			    return "success"; // נדפיס את התור ששומר לאן הלכנו כל פעם
			}
			else if (limit==0) {
				System.out.println("j");
				return "cutoff";
			}
			else {
				h.add(start);
				isCutoff=false;
				int pos =where0(start);
				if (result != "success") {
				result=left(start,pos,h);
			if (result!=null) {
				result=Limited_DFS(f.remove(), goal, limit-1, h);
				System.out.println(temp);
				if(result=="cutoff") {
					isCutoff=true;
				}
				  
				else if (result=="success") {
					//result="success";
					return result;
				
				  }
				}
			}
			if(result !="success") {
			temp=up(start,pos,h);
			if (temp!=null) {
				result=Limited_DFS(f.remove(), goal, limit-1, h);
				System.out.println(temp);
				if(result=="cutoff") {
					isCutoff=true;
				}
				  
				else if (result=="success") {
					//result="success";
					return result;
				
				  }
				}
			}
			if(result!="success") {
				temp=right(start,pos,h);
			if (temp!=null) {
				result=Limited_DFS(f.remove(), goal, limit-1, h);
				System.out.println(temp);
				if(result=="cutoff") {
					isCutoff=true;
				}
				  
				else if (result=="success") {
					//result="success";
					return result;
				
				  }
				}
			}
			if(result!="success") {
			    temp=down(start,pos,h);
			   if (temp!=null) {
				   result=Limited_DFS(f.remove(), goal, limit-1, h);
					System.out.println(temp);
					if(result=="cutoff") {
						isCutoff=true;
					}
					  
					else if (result=="success") {
						//result="success";
						return result;
					
					  }
					}
				}
			System.out.println(count);
			h.remove(start);
			if(isCutoff==true)
				return "cutoff";
			else 
				return "fail";
			}
			
		
			
		}
		public static String up(Node s,int p, Set<Node> h) {
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
			        f.add(tmp);
		       
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
	    
		public static String down(Node s,int p,Set<Node> h) {
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
		        f.add(tmp);
		      
		        	
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
		public static String left(Node s,int p,Set<Node> h) {
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
				        f.add(tmp);
			       
		        	
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
		public static String right(Node s,int p, Set<Node> h) {
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
			        f.add(tmp);
			     
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
	
	 
	 public static void main(String[] args) throws IOException
	  { 
	  // We need to provide file path as the parameter: 
	  // double backquote is to avoid compiler interpret words 
	  // like \test as \t (ie. as a escape sequence) 
	 /*  File file = new File("input.txt"); 
	  
	  BufferedReader br = new BufferedReader(new FileReader(file)); 
	  
	    String kindSearch=br.readLine();
	    //System.out.println(kindSearch);
	    String printTime=br.readLine();
	    //System.out.println(printTime);
	    String openList=br.readLine();
	    String stringSize=br.readLine();
	    
	    String  split[]=stringSize.split("x");

		
			int row=Integer.parseInt(split[0]);
			int col=Integer.parseInt(split[1]);
			String blackList=br.readLine();
			//System.out.println(blackList);
		String black []=blackList.split(":|,");
		//if black[1]==null -> empty list
		String redList=br.readLine();
		//System.out.println(redList);
		String red []=blackList.split(":|,");
		//check
		
		//String start[][]=new String [row][col];
	
		
				String line = null;
                 int i=0;
			   while ((line = br.readLine()) != null) {
			    	
			      String[] values = line.split(",");
			     
			        int k=0;
			        
			    	  for (int j=0; j<col;j++) {
			    	      
			    		  start[i][j]=values[k];
			    	     k++;
			      }
			    i++;
			}*/
               /*  String [] start=new String [row*col];
                 while ((line = br.readLine()) != null) {
                	 String[] values = line.split(",");
                	 for(int j=0;j<col;j++) {
                		 start[i]=values[j];
                		 i++;
                	 }
                	 
                 } 
			      System.out.println(Arrays.toString(start));
			      String [] goal=new String [col*row];
			      goal[row*col-1]="0";
			      for (int k=1;k<(row*col);k++) {
		            	goal[k-1]=Integer.toString(k);
		            	
		            	
		            }
			      System.out.println(Arrays.toString(goal));*/
			      init();
			      
	  }
	  
	
	} 
	

