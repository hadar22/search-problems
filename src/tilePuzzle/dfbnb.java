package tilePuzzle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class dfbnb {
	public static String DFBnB (Block[] start, Block[] goal) {
		Node startN=new Node(start);
		Stack <Node> open=new Stack<>();
		Set<Node> openH=new HashSet<>();
		/*
		 * For each node, I develop his boys and put them in the array
		 */
		Node [] operators=new Node[3];
		
		String result= null;
		/*
		 * Setting the barrier
		 */
		int sumNode=Ex1.col*Ex1.row-Ex1.blackH.size();
		int t;
		if(sumNode<=12) {
			
			t=factorial(sumNode);
		}
		else {
			t=Integer.MAX_VALUE;
		}
		
		open.push(startN);
		openH.add(startN);
		while(!open.empty()) {
			Node tmp=open.pop();
			
			if(tmp.mark=="out") {
				tmp.mark=null;
				openH.remove(tmp);
			}
			else {
				tmp.mark="out";
				open.push(tmp);
				int pos=BFS.where0(tmp);
				int i=0;
				Node ans=AStar.left(tmp,pos);
				if(ans!= null) {
					
					Ex1.count++;
					operators[i]=ans;
					i++;
				}
				ans=AStar.up(tmp,pos);
				if(ans!=null) {
					
					Ex1.count++;
					operators[i]=ans;
					i++;
				}
				ans=AStar.right(tmp,pos);
				if (ans!=null) {
					
					Ex1.count++;
					operators[i]=ans;
					i++;
					
				}
				ans=AStar.down(tmp,pos);
				if(ans!= null) {
					
					Ex1.count++;
					operators[i]=ans;
					
				}
				/*
				 * I sort by the f of each node
				 */
				Arrays.sort(operators, new comp());
				
				
				for (int j=0;j<operators.length;j++) {
					if (operators[j]!= null) {
						if (operators[j].f>=t) {
							int k=j;
							while(k<operators.length) {
								operators[k]=null;
								k++;
							}
						}
						else if(openH.contains(operators[j])) {
						   for(Node g:openH) {
							if(BFS.equalArrays(g.arrBlock, operators[j].arrBlock)) {
								if(g.mark=="out") {
									operators[j]=null; //to next operator
									break;
								}
								else {
									if(g.f <= operators[j].f) {
										operators[j]=null;
										break;
									}
									else {
										open.remove(g);
										openH.remove(g);
										break;
									}
										
								}
							}
							
						}
						}
						else if(BFS.equalArrays(goal, operators[j].arrBlock)) {
							t=operators[j].f;
							result="success";
							Ex1.path=operators[j].path;
							Ex1.costOfResult=operators[j].cost;
							
							int k=j;
							while(k<operators.length) {
								operators[k]=null;
								k++;
							}
							
						}
							
					}
				}
				for(int j=2;j>=0;j--) {
					if(operators[j]!=null) {
					    open.push(operators[j]);
						openH.add(operators[j]);
					}
					
				}
				
				
				
			}
			if(Ex1.openList.equalsIgnoreCase("with open")) {
				System.out.println(open.toString());
			}
			
		}
		
		return "finish";
	}

	public static int factorial(int n){
		if (n <= 1){
			return 1;
		}
	return n * factorial(n-1);
	}
}
