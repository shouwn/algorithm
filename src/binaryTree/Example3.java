package binaryTree;

public class Example3 {

	static class Node {
		public static final NullNode NULL = new NullNode();

		int value;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			this.left = NULL;
			this.right = NULL;
		}

		public Node add(int value) {
			if (value < this.value) left = left.add(value);
			else if (value > this.value) right = right.add(value);
			return this;
		}

		public void print() {
			left.print();
			System.out.printf("%d ", value);
			right.print();
		}

		public boolean contains(int value) {
			if (value < this.value) return left.contains(value);
			else if (value > this.value) return right.contains(value);
			return true;
		}

		public int getLeftMostValue() {
			if (this.left == NULL) return this.value;
			return left.getLeftMostValue();
		}

		public Node remove(int value) {
			if (value < this.value) left = left.remove(value);
			else if (value > this.value) right = right.remove(value);
			else {
				if (left != NULL && right != NULL) {
					int temp = right.getLeftMostValue();
					this.value = temp;
					right = right.remove(temp);
				} else
					return (left != NULL) ? left : right;
			}
			return this;
		}
	}

	static class NullNode extends Node {

		public NullNode() {
			super(0);
		}

		@Override
		public Node add(int value) {
			return new Node(value);
		}

		@Override
		public void print() {
		}

		@Override
		public boolean contains(int value) {
			return false;
		}

		@Override
		public int getLeftMostValue() {
			return 0;
		}

		@Override
		public Node remove(int value) {
			return this;
		}
	}

	static class BinaryTree {
		Node root = Node.NULL;

		public void add(int value) {
			root = root.add(value);
		}

		public void print() {
			root.print();
		}

		public boolean contains(int value) {
			return root.contains(value);
		}

		public void remove(int value) {
			root = root.remove(value);
		}
	}

	public static void main(String[] args) {
		int[] a = { 5, 18, 1, 12, 7, 2, 14, 0, 6, 9, 15, 0};
		BinaryTree binaryTree = new BinaryTree();
		for (int i = 0; i < a.length; ++i)
			binaryTree.add(a[i]);
		binaryTree.remove(14);
		binaryTree.remove(15);
		binaryTree.remove(5);
		binaryTree.remove(10);
		binaryTree.remove(0);
		binaryTree.remove(0);
		binaryTree.print();
		System.out.println();
	}
}
