package tilePuzzle;

import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

//import javax.rmi.CORBA.Util;

public class Bdika {
	public static Queue <int[]> OPEN = new LinkedList<int []>();

	public static HashSet<int[]> CLOSED = new HashSet<int []>();
	public static boolean STATE = false;
	static int row=3;
	static int col=4;

	/*
	 * MOVEMENT UP
	 */
	public static int[] up(int [] s, int p) {
	   int [] str =new int[s.length];
	    		for(int i=0;i<s.length;i++) {
	    			str[i]=s[i];
	    		}
	  
	    if ((p < col*row-col)) {
	        int a = s[p + col];

	        str[p+col]=0;
	        str[p]=a;
	    }
	   
	    // Eliminates child of X if its on OPEN or CLOSED
	 /*   if (!OPEN.contains(s) && CLOSED.contains(s) == false)
	        return true;
	    else
	        return false ;*/
	  //  System.out.println(Arrays.toString(str));
	    return str;
	}

	/*
	 * MOVEMENT DOWN
	 */
	public static int[] down(int [] s, int p) {
	    int[] str = new int[s.length];
	    for(int i=0;i<s.length;i++) {
			str[i]=s[i];
		}
	   if ((p >col)) {
	        int a = s[p -col];
	        //int [] newS = str.substring(0, p) + a + str.substring(p + 1);
	        //str = newS.substring(0, (p - 3)) + '0' + newS.substring(p - 2);
	        str[p-col]=0;
	        str[p]=a;
	     //   System.out.println(Arrays.toString(str));
	    }

	        return str;
	}

	/*
	 * MOVEMENT LEFT
	 */
	public static int[] left(int[] s, int p) {
	   int [] str = new int[s.length];
	   for(int i=0;i<s.length;i++) {
			str[i]=s[i];
		}
	 
	   
	   if (p % col !=col-1) {
	        int a = s[p + 1];
	
	        str[p+1]=0;
	        str[p]=a;
	    }
	        return str;
	}

	/*
	 * MOVEMENT RIGHT
	 */
	public static int[] right(int[] s, int p) {
	   int [] str = new int [s.length];
	   for(int i=0;i<s.length;i++) {
			str[i]=s[i];
		}
	
	     if (p %col !=0) {
	        int a = s[p -1];

	        str[p-1]=0;
	        str[p]=a;
	    }
	    
	 
	        return str;
	}
	public static int where0(int [] a) {
		int pos=0;
	       
        for(int i=0;i<a.length;i++) {
     	   if (a[i]==0) {
     		   pos = i;
     		   break;
     	   }
        }
        return pos;
	}



	public static void main(String args[]) {

	  /*  int statesVisited = 0;

	    int [] start = {1,2,3,4,5,6,11,7,9,10,8,0};
	    		
	    int[] goal = {1,2,3,4,5,6,7,8,9,10,11,0};
	    int [] X = new int [start.length];
	  int [] temp =  new int [start.length];
	   // int [] eq =  new int [start.length];

	    OPEN.add(start);
	    int count=1;

	    while (!OPEN.isEmpty()  && STATE == false) {
            
	      //  X = OPEN.iterator().next();
	        //OPEN.remove(X);
	    	X=OPEN.remove();
	    	CLOSED.add(X);
	    	
	        int pos=where0(X);
	       
           
           temp=left(X,pos);
           
          if(!Arrays.equals(X, temp) ) {// temp change
        	  if(!OPEN.contains(temp) && !CLOSED.contains(temp)) {
        		  if(Arrays.equals(goal, temp)) {
        			  System.out.println("SUCCESS");
      	            STATE = true;
      	            break;
        		  }
        		  else {
        			  System.out.println("l:"+Arrays.toString(temp));
        			  OPEN.add(temp);
        			  count++;
        		  }
        	  }
          }
         // pos=where0(X);
          temp=up(X,pos);
          
          if(!Arrays.equals(X, temp)) {
        	  if(!OPEN.contains(temp) && !CLOSED.contains(temp)) {
        		  if(Arrays.equals(goal, temp)) {
        			  System.out.println("SUCCESS");
      	            STATE = true;
      	            break;
        		  }
        		  else {
        			  System.out.println("u:"+Arrays.toString(temp));
        			  OPEN.add(temp);
        			  count++;
        		  }
        	  }
          }
        //  pos=where0(X);
          temp=right(X,pos);
         
          if(!Arrays.equals(X, temp)) {
        	  if(!OPEN.contains(temp) && !CLOSED.contains(temp)) {
        		  if(Arrays.equals(goal, temp)) {
        			  System.out.println("SUCCESS");
      	            STATE = true;
      	          break;
        		  }
        		  else {
        			  System.out.println("r:"+Arrays.toString(temp));
        			  OPEN.add(temp);
        			  count++;
        		  }
        	  }
          }
         // pos=where0(X);
          temp=down(X,pos);
         
          if(!Arrays.equals(X, temp)) {
        	  
        	  if(!OPEN.contains(temp) && !CLOSED.contains(temp)) {
        		  if(Arrays.equals(goal, temp)) {
        			  System.out.println("SUCCESS");
      	            STATE = true;
      	          break;
        		  }
        		  else {
        			  System.out.println("d:"+Arrays.toString(temp));
        			  OPEN.add(temp);
        			  count++;
        		  }
        	  }
          }
           
           
 
	        }
	    System.out.println(count);
	    }

	

	//print(X);*/
		
	      System.out.println("j");
		
	}
}

