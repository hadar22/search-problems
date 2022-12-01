package tilePuzzle;



import java.util.HashSet;

import java.util.Set;
import java.util.Stack;






public class idaStar {	

	public static void IDA(Block[] start, Block[] goal){
		Node startN=new Node(start);
		Node ans;
		
		Stack <Node> open=new Stack<>();
		Set<Node> openH=new HashSet<>();
		int t=AStar.h(startN);
		
		
		
		
		while(t != Integer.MAX_VALUE) {
			
			Ex1.count=1;
			int minF=Integer.MAX_VALUE;
			open.push(startN);
			openH.add(startN);
			
			while(!open.isEmpty()) {
				Node tmp=open.pop();
				
				
				if(tmp.mark=="out") {
					
					tmp.mark=null; // For the next iteration
					openH.remove(tmp);
				}
				else {
					tmp.mark="out";
					open.push(tmp);
					int pos=BFS.where0(tmp);
					
					ans=AStar.left(tmp,pos);
					if(ans!=null) {
						
					
						if(ans.f>t) {
							minF=Math.min(minF, ans.f);
					
							continue;//to next operator
						}
						/*
						 * Checks all existing nodes in the hash and checks
						 *  for such a node
						 */
						for(Node g:openH) {
							if(BFS.equalArrays(g.arrBlock, ans.arrBlock)) {
								if(g.mark=="out") {
									continue; //to next operator
								}
								else {
									if(g.f>ans.f) {
										open.remove(g);
										openH.remove(g);
									}
									else
										 continue;
								}
							}
							
						}
						Ex1.count++;
						if(BFS.equalArrays(ans.arrBlock, goal)) {
							
							
							Ex1.path=ans.path;
							Ex1.costOfResult=ans.cost;
							return;
						}
						
						open.push(ans);
						openH.add(ans);
						
					
						
					}
					
					ans=AStar.up(tmp,pos);
					if(ans!=null) {
						
					
						if(ans.f>t) {
							minF=Math.min(minF, ans.f);
							
							continue;//to next operator
						}
						for(Node g:openH) {
							if(BFS.equalArrays(g.arrBlock, ans.arrBlock)) {
								if(g.mark=="out") {
									continue; //to next operator
								}
								else {
									if(g.f>ans.f) {
										open.remove(g);
										openH.remove(g);
									}
									else
										 continue;
								}
							}
							
						}
						Ex1.count++;
						if(BFS.equalArrays(ans.arrBlock, goal)) {
							
					
							Ex1.path=ans.path;
							Ex1.costOfResult=ans.cost;
							return;
						}
						
						open.push(ans);
						openH.add(ans);
						
					
						
					}
					ans=AStar.right(tmp,pos);
					if(ans!=null) {
						
					
						if(ans.f>t) {
							minF=Math.min(minF, ans.f);
						
							continue;//to next operator
						}
						for(Node g:openH) {
							if(BFS.equalArrays(g.arrBlock, ans.arrBlock)) {
								if(g.mark=="out") {
									continue; //to next operator
								}
								else {
									if(g.f>ans.f) {
										open.remove(g);
										openH.remove(g);
									}
									else
										 continue;
								}
							}
							
						}
						Ex1.count++;
						if(BFS.equalArrays(ans.arrBlock, goal)) {
							
							
							Ex1.path=ans.path;
							Ex1.costOfResult=ans.cost;
							return;
						}
						
						open.push(ans);
						openH.add(ans);
						
					
						
					}
					ans=AStar.down(tmp,pos);
					if(ans!=null) {
						
					
						if(ans.f>t) {
							minF=Math.min(minF, ans.f);
							
							continue;//to next operator
						}
						for(Node g:openH) {
							if(BFS.equalArrays(g.arrBlock, ans.arrBlock)) {
								if(g.mark=="out") {
									continue; //to next operator
								}
								else {
									if(g.f>ans.f) {
										open.remove(g);
										openH.remove(g);
									}
									else
										 continue;
								}
							}
							
						}
						Ex1.count++;
						if(BFS.equalArrays(ans.arrBlock, goal)) {
							
							
							Ex1.path=ans.path;
							Ex1.costOfResult=ans.cost;
							return;
						}
						
						open.push(ans);
						openH.add(ans);
						
					
						
					}
				
				}
				
				if(Ex1.openList.equalsIgnoreCase("with open")) {
					System.out.println(open.toString());
				}
			}
			
			t=minF;
			
		}
		
		
	}




	}



