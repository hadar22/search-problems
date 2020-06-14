package tilePuzzle;

 class Block {
    
	 int num;
	 String color ; // red,blue,green
	// int visit; //0,1,2
	
	 int cost;
	
	 
	/* public Block() {
		 this.name= null;
			this.color=0;
			this.visit=0;
	}*/
	
	 Block( int num){
		this.num= num;
		//this.visit=0;
		this.color="green";
		
		this.cost=1;
		
	 }
	 Block( int num, String c){
			this.num= num;
			//this.visit=0;
			this.color=c;
			
			if(c=="red")
				this.cost=30;
			else 
				this.cost=0;
			
		 }
	
	 
	
	 public String toString() {
		 return "num: "+num+" color: "+color;
	 }
}
 class Node{
	 Block [] arrBlock;
	 int path;
	 int cost;
	 char pather;
	 
	 public Node(Block [] arr) {
		 this.arrBlock=arr;
		// this.path=0;
		 //this.cost=0;
		 
		
	}
	 
	 public String toString() {
		 return "path: "+path+" ,cost: "+cost+" ,p: "+pather+"|";
	 }
	 @Override
	 public boolean equals(Object obj) {
		 if (this == obj)
		        return true;
		    if (obj == null)
		        return false;
		 
		    Node other = (Node) obj;
		    if (path == other.path)
		        return true;
		   
		  
		    return false;
	 }
 }
public class MatBlock{
	Block [][] matBlock;
	
	
	 public MatBlock(int r, int c){
		matBlock=new Block[r][c];
	}
	 public void add(int n, String c) {
		 Block h= new Block(n, c);
		
		 for (int g=0; g<2 ; g++) {
			 for (int t=0; t<2 ;t++) {
				 
				  if (matBlock[g][t] ==null) {
			 
			       matBlock[g][t]=h;
			       System.out.println(h.toString());
			     
			      return;
		 }
 
			 }
			
		 }
		 
		
	 }

public static void main(String[] args) {
	MatBlock k=new MatBlock(2, 2);

	k.add( 3,"r");
	k.add(4,"j");
	
}
}

