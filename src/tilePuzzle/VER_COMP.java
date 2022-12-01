package tilePuzzle;

import java.util.Comparator;

public class VER_COMP implements Comparator<Node> {
	public VER_COMP() {;}
	
	 @Override
	    public int compare(Node v2,Node v1)
	        {
	            int res=0;
	            if((v1.f)-(v2.f)<0) res=1;
	            else if (((v1.f)-(v2.f))>0) res = -1;
	            return res;
	        }

}
