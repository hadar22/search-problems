package tilePuzzle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;

public class Ex1 {
  
	static String kindSearch;
	static String printTime;
	static String openList;
	static int row;
	static int col;
 
	static String red [];
	static String [] start;
	
	
	static Block [] s;
	static Block [] goalB;
	public static HashSet<Integer> blackH = new HashSet<Integer>();
	public static HashSet<Integer> redH = new HashSet<Integer>();
	public static int count=1;
	 public static String path;
	 public static int costOfResult;
	 public static long startTime;
	 public static long endTime;
	 public static double totalTime;
	
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
			String [] black=blackList.split(": |,");
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
         
		      s=new Block [row*col];
		      for(int z=0;z<start.length;z++) {
		    	  Block g;
		    	  if(start[z].equals("_")) {
		    		  g=new Block(0);
		    		  s[z]=g;
		    		  continue;
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
		    
		      // array of goal
		     
		      goalB=new Block[col*row];
		  
		      
		      
		      for(int j=0; j<goalB.length; j++) {
		    	
		    	  if(Ex1.s[j].num != 0) {
		    		 
		    		  Ex1.goalB[Ex1.s[j].num-1]=Ex1.s[j]; 
		    	  }
		    	  else if(Ex1.s[j].num == 0) {
		    		  Ex1.goalB[col*row-1]=new Block(0);
		    	  }
		    	 
		    	  
		    	  
		      
		      }
		     
		      br.close();
		   
		   
		      
			switch(kindSearch) {
			case "BFS":
				 startTime = System.currentTimeMillis();
				BFS.BFS1(s,goalB);
				 endTime = System.currentTimeMillis();
				 totalTime = (endTime - startTime)/1000.0; 
				
				break;
			case "DFID":
				startTime = System.currentTimeMillis();
				DFID.DFID2(s, goalB);
				endTime = System.currentTimeMillis();
				 totalTime = (endTime - startTime)/1000.0; 
				break;
			case "A*":
				startTime = System.currentTimeMillis();
				AStar.UCS(s, goalB);
				endTime = System.currentTimeMillis();
				 totalTime = (endTime - startTime)/1000.0; 
				break;
			case "IDA*":
				startTime = System.currentTimeMillis();
				idaStar.IDA(s, goalB);
				endTime = System.currentTimeMillis();
				 totalTime = (endTime - startTime)/1000.0; 
		     	break;
			case "DFBnB":
				startTime = System.currentTimeMillis();
				dfbnb.DFBnB(s, goalB);
				
				endTime = System.currentTimeMillis();
				 totalTime = (endTime - startTime)/1000.0; 
				break;
			  
			default:
				break;
			}
			
			try {
			      File myObj = new File("output.txt");
			      
			        System.out.println("File created: " + myObj.getName());
			     
			        PrintWriter writer = new PrintWriter("output.txt");
			        if(path==null) {
			        	writer.println("no path");
			        	writer.println("Num: "+count);
			        }
			        else {
			        	writer.println(path);
			        	writer.println("Num: "+count);
			        writer.println("Cost: "+costOfResult);
			        if( printTime.equalsIgnoreCase("with time")) {
			        	writer.println(totalTime+" seconds");
			        }
			       
			        }
			        
			        
			        writer.close();
			      
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
     }
     
    	 public static void main(String[] args) throws IOException{
    	 init();
     }
}
