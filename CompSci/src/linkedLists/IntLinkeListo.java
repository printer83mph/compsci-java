package linkedLists;

public class IntLinkeListo {
	
	private Node last;
	private int length;
	
	private class Node {
		
		public Node prev;
		public int data;
		
		public Node(int num) {
			data = num;
		}
		
	}
	
	public IntLinkeListo() {
		length = 0;
	}
	
	private Node getNode(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		}
		Node out = last;
		for (int i = 0; i < index; i++) {
			if (out.prev == null) {throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");}
			out = out.prev;
		}
		return out;
	}
	
	public int get(int index) {
		return getNode(length-index-1).data;
	}
	
	public void push(int i) {
		if (last == null) {
			last = new Node(i);
			length++;
			return;
		}
		Node newlast = new Node(i);
		newlast.prev = last;
		last = newlast;
		length++;
	}
	
	public int pop() {
		if (length < 1) {
			throw new NullPointerException("List is empty!");
			// Should I use this exception?
		}
		int prevLastData = last.data;
		last = last.prev;
		length--;
		return prevLastData;
	}
	
	public int shift() {
		if (length < 1) {
			throw new NullPointerException("List is empty!");
			// Should I use this exception?
		}
		if (length < 2) {
			int data = last.data;
			last = null;
			length--;
			return data;
		}
		Node prevFirst = getNode(length-2);
		int data = prevFirst.prev.data;
		prevFirst.prev = null;
		length--;
		return data;
	}
	
	public void unshift(int i) {
		if (last == null) {
			last = new Node(i);
			length++;
			return;
		}
		Node firstNode = getNode(length-1);
		firstNode.prev = new Node(i);
		length++;
	}
	
	public void splice(int index, int amt) {
		if (index + amt > length) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
		}
		Node startingPlace = getNode(index-1);
		Node endingPlace = startingPlace;
		for (int i = 0; i < amt + 1; i++) {
			endingPlace = endingPlace.prev;
		}
		startingPlace.prev = endingPlace;
		length -= amt;
	}
	
	public String toString() {
		String mid = "";
		for (int i = 0; i < length; i++) {
			mid += get(i);
			mid += i < length-1 ? ", " : "";
		}
		return "[" + mid + "]";
	}
}
