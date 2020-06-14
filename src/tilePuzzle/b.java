package tilePuzzle;
import java.util.HashSet;
	import java.util.LinkedList;
	import java.util.Queue;
	import java.util.Set;
	import java.util.Stack;
	
public class b {
	

	

	

	    private static class Node {
	        private final Node previous;
	        private final String data;

	        @Override
	        public int hashCode() {
	            final int prime = 31;
	            int result = 1;
	            result = prime * result + ((data == null) ? 0 : data.hashCode());
	            return result;
	        }

	        public Node getPrevious() {
	            return previous;
	        }

	        public String getData() {
	            return data;
	        }

	        @Override
	        public boolean equals(Object obj) {
	            if (this == obj)
	                return true;
	            if (obj == null)
	                return false;
	            if (getClass() != obj.getClass())
	                return false;
	            Node other = (Node) obj;
	            if (data == null) {
	                if (other.data != null)
	                    return false;
	            } else if (!data.equals(other.data))
	                return false;
	            return true;
	        }

	        public Node(String data) {
	            this.data = data;
	            this.previous = null;
	        }

	        public Node(String data, Node previous) {
	            this.data = data;
	            this.previous = previous;
	        }
	    }

	    public static void main(String args[]) {
	        Queue<Block[]> open = new LinkedList<>();
	        Set<Block[]> closed = new HashSet<>();
	        Node start = new Node("120345678");
	        Node goal = new Node("012345678");

	        open.add(start);
	        boolean solving = true;
	        while (!open.isEmpty() && solving) {
	            Node current = open.poll();
	            int pos = current.getData().indexOf('0');
	            if (!closed.contains(current)) {
	                if (current.equals(goal)) {
	                    printPath(current);
	                    System.out.println("SUCCESS");
	                    solving = false;
	                } else {
	                    // generate children
	                    up(current, pos, open, closed);
	                    down(current, pos, open, closed);
	                    left(current, pos, open, closed);
	                    right(current, pos, open, closed);
	                    closed.add(current);
	                }
	            }
	        }
	    }

	    /*
	     * MOVEMENT UP
	     */
	    private static void up(Node current, int zeroPosition, Queue<Node> open, Set<Node> closed) {
	        if (zeroPosition >= 3) {
	            char substitutedChar = current.getData().charAt(zeroPosition - 3);
	            open.add(new Node(current.getData().substring(0, zeroPosition - 3) + '0'
	                    + current.getData().substring(zeroPosition - 2, zeroPosition) + substitutedChar
	                    + current.getData().substring(zeroPosition + 1), current));
	        }
	    }

	    /*
	     * MOVEMENT DOWN
	     */
	    private static void down(Node current, int zeroPosition, Queue<Node> open, Set<Node> closed) {
	        if (zeroPosition <= 5) {
	            char substitutedChar = current.getData().charAt(zeroPosition + 3);
	            open.add(new Node(current.getData().substring(0, zeroPosition) + substitutedChar
	                    + current.getData().substring(zeroPosition + 1, zeroPosition + 3) + '0'
	                    + current.getData().substring(zeroPosition + 4), current));
	        }
	    }

	    /*
	     * MOVEMENT LEFT
	     */
	    private static void left(Node current, int zeroPosition, Queue<Node> open, Set<Node> closed) {
	        if (zeroPosition % 3 != 0) {
	            char substitutedChar = current.getData().charAt(zeroPosition - 1);
	            open.add(new Node(current.getData().substring(0, zeroPosition - 1) + '0' + substitutedChar
	                    + current.getData().substring(zeroPosition + 1), current));
	        }
	    }

	    /*
	     * MOVEMENT RIGHT
	     */
	    private static void right(Node current, int zeroPosition, Queue<Node> open, Set<Node> closed) {
	        if (zeroPosition % 3 != 2) {
	            char substitutedChar = current.getData().charAt(zeroPosition - 1);
	            open.add(new Node(current.getData().substring(0, zeroPosition) + substitutedChar + '0'
	                    + current.getData().substring(zeroPosition + 2), current));
	        }
	    }

	    private static void printPath(Node current) {
	        Stack<String> stack = new Stack<>();
	        for (; current != null; current = current.getPrevious()) {
	            stack.push(current.getData());
	        }
	        while (!stack.isEmpty()) {
	            print(stack.pop());
	        }
	    }

	    private static void print(String s) {
	        System.out.println(s.substring(0, 3));
	        System.out.println(s.substring(3, 6));
	        System.out.println(s.substring(6, 9));
	        System.out.println();

	    }
	}


