package algorithm;

import java.util.Scanner;

public class H0314 {

	public static void main(String[] args) {
		int count;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("add할 원소의 갯수 : ");
		count = scan.nextInt();
		
		BTree bTree = new BTree(null);
		
		for(int i = 0; i < count; i++) {
			int input;
			System.out.print("추가할 원소 : ");
			input = scan.nextInt();
			bTree.addW(input);
		}
		
	}

}

class BTree{
	
	Node root;
	
	public BTree(Node root) {
		super();
		this.root = root;
	}
	
	public boolean addW(int item) {
		if(root == null) {
			root = new Node(item, null, null);
			System.out.println("root ���");
			return true;
		}
		
		Node pre = root;
		Node parent = root;
		
		while(parent != null) {
			if(parent.item == item) {
				System.out.println("���� ���� : �̹� ���Ұ� ����");
				return false;
			}
			pre = parent;
			
			if(parent.compareTo(item) > 0) 
				parent = parent.rightChild;
			
			else
				parent = parent.leftChild;
			
		}
		
		if(pre.compareTo(item) > 0) 
			pre.rightChild = new Node(item, null, null);
		
		else
			pre.leftChild = new Node(item, null, null);
		
		System.out.println("���� ���� / �θ� ��� : " + pre.item + "�� " +
		(pre.compareTo(item) > 0 ? "������" : "����"));
		
		return true;
	}

	public boolean add(int item) {
		if(root == null) {
			root = new Node(item, null, null);
			System.out.println("root ���");
			return true;
		}
		
		return add(root, item);
	}
	
	private boolean add(Node parent, int item) {
		if(parent.item == item) {
			System.out.println("���� ���� : �̹� ���Ұ� ����");
			return false;
		}
		if(parent.compareTo(item) > 0) {
			
			if(parent.haveChildOf(Node.RIGHT)) {
				return add(parent.rightChild, item);
			}
			else
				parent.rightChild = new Node(item, null, null);
		}
		else {
			
			if(parent.haveChildOf(Node.LEFT)) {
				return add(parent.leftChild, item);
			}
			else
				parent.leftChild = new Node(item, null, null);
		}
		
		System.out.println("���� ���� / �θ� ��� : " + parent.item + "�� " +
		(parent.compareTo(item) > 0 ? "������" : "����"));
		return true;
	}
	
	class Node{
		
		static final int LEFT = -1;
		static final int RIGHT = 1;
		
		int item;
		Node leftChild;
		Node rightChild;

		public Node(int item, Node leftChild, Node rightChild) {
			super();
			this.item = item;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		public boolean haveChildOf(int who) {
			if(who == LEFT)
				return leftChild != null;
			else
				return rightChild != null;
		}

		public int compareTo(int item) {
			return item - this.item;
		}

	}
}

