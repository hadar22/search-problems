package tilePuzzle;

import java.util.Comparator;

public class comp implements Comparator<Node> {
	public comp() {;}
	
	 @Override
	    public int compare(Node v2,Node v1)
	        {
		     if(v2==null)
		    	 return v1.f;
		     else if (v1==null)
		    	 return v2.f;
			else {
	            int res=(v2.f)-(v1.f);
	       
	            return res;
		     }
	        }

}
