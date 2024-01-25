package tree;

import java.util.LinkedList;
import java.util.Queue;

//Note: You will need to uncomment this when we implement Level-Order traversal
//import java.util.LinkedList;
//import java.util.Queue;

// TODO: Auto-generated Javadoc
/**
 * The Class MyBST.
 *
 * @param <E> the element type
 */
public class MyBST<E extends Comparable<E>> {
	
	/** The root of the BST. */
	private BSTNode<E> root;
	
	/** The size of the BST. */
	int size;
	
	/** The str order. */
	String strOrder;

	/**
	 * Instantiates a new MyBST .
	 */
	public MyBST() {
		size = 0;
		strOrder = "";
		root = null;
	}
	
	// Part 1 - code and validate the insert and search methods
	
	/**
	 * Gets the root.
	 *
	 * @return the root node of the Binary Search Tree
	 */
	public BSTNode<E> getRoot() {
		return root;
	}

	
	/**
	 * Gets the size of the Binary Search Tree.
	 *
	 * @return the size of the Binary Search Tree
	 */
	public int getSize() {
		return size;
	}
	/**
	 * Insert.
	 *
	 * @param e the element to insert into the BST
	 * @return true, if successful; false if e already exists in the BST
	 */
	public boolean insert(E e) {
		if (root == null) {
			root = new BSTNode(e, null);
			size++;
			return true;
		}
		BSTNode<E> n = root;
		BSTNode<E> parent = null;
		while (n != null) {
			int compare = e.compareTo(n.getData());
			if (compare == 0)
				return false;
			parent = n;
			if (compare > 0) {
				n = n.getRightChild();
				if (n == null)
					parent.addRightChild(e);
			}
			if (compare < 0) {
				n = n.getLeftChild();
				if (n == null)
					parent.addLeftChild(e);
			}
		}
		size++;
		return true;
	}
	
	/**
	 * Search the BST.
	 *
	 * @param e the element to search for
	 * @return true, if the element was found in the list...
	 */
	public boolean search(E e) {
		BSTNode<E> n = getMatchingNode(e);
		return n != null;
	}
	

	// Part 2: Pre/Post/In order traversals
	
	/**
	 * Debug method to dump the results of a traversal as a string
	 *
	 * @return the str order
	 */
	public String getStrOrder() {
		return(strOrder);
	}
	
	/**
	 * Preorder - traverse the BST using the preorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public void preorder() {
		strOrder = "";
		if (root == null)
			return;
		preorder(root);
	}
	
	/**
	 * Preorder traversal - process node, then left then right.
	 * Update strOrder with node.getData() when processed
	 *
	 * @param node the node
	 */
	private void preorder(BSTNode<E> node) {
		strOrder += node.getData().toString() + ",";
		if (node.hasLeft())
			preorder(node.getLeftChild());
		if (node.hasRight())
			preorder(node.getRightChild());
	}

	/**
	 * Inorder - traverse the BST using the inorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public void inorder() {
		strOrder = "";
		if (root == null)
			return;
		inorder(root);
	}
	
	/**
	 * Inorder traversal - process left, node, then right
	 * Update strOrder with node.getData() when processed
	 *
	 * @param node the node being traversed
	 */
	private void inorder(BSTNode<E> node) {
		if (node.hasLeft())
			inorder(node.getLeftChild());
		strOrder += node.getData().toString() + ",";
		if (node.hasRight())
			inorder(node.getRightChild());
	}
	
	/**
	 * Postorder - traverse the BST using the postorder search algorithm.
	 * This should be written recursively, and will require two overloaded
	 * methods
	 */
	public void postorder() {
		strOrder = "";
		if (root == null)
			return;
		postorder(root);
	}
	
	/**
	 * Postorder traversal - process left, then right then node.
	 * Update strOrder with node.getData() when processed
	 *
	 * @param node the node being traversed
	 */
	private void postorder(BSTNode<E> node) {
		if (node.hasLeft())
			postorder(node.getLeftChild());
		if (node.hasRight())
			postorder(node.getRightChild());
		strOrder += node.getData().toString() + ",";
	}
	
	// Part 3: Level order Traversal and node removal 
	// DO NOT CODE UNTIL ASSIGNED

	/**
	 * Levelorder. Processes the nodes of a binary tree by level, starting at the root. 
	 * Note that this is not recursive. Update strOrder when a node is removed from the
	 * queue.
	 */
	public void levelorder() {
		strOrder = "";
		Queue<BSTNode<E>> queue = new LinkedList<BSTNode<E>>();
		BSTNode<E> n;
		queue.add(root);
		while(!queue.isEmpty()) {
			n = queue.poll();
			if (n != null) {
				queue.add(n.getLeftChild());
				queue.add(n.getRightChild());
				strOrder += n.getData() + ",";
			}
			
		}
	}
	
	/**
	 * Returns the BSTNode whose data contains the given element.
	 *
	 * @param e the element to be matched
	 * @return the matching BSTNode if element was found; null otherwise.
	 */
	private BSTNode<E> getMatchingNode(E e) {
		BSTNode<E> n = root;
		while (n != null) {
			int compare = e.compareTo(n.getData());
			if (compare == 0)
				return n;
			if (compare > 0) {
				n = n.getRightChild();
			}
			if (compare < 0) {
				n = n.getLeftChild();
			}
		}
		return null;
	}

	/**
	 * Connect to parent node to the child node in both directions.
	 * Must handle the case where the parent is null - connect to root
	 * Must handle the case where the child is null and NOT attempt to
	 * set the parent of the child!
	 *
	 * @param left - if true, connect the child to parent->left, otherwise parent->right
	 * @param parent the parent
	 * @param child the child
	 */

	private void connectToParent(boolean left, BSTNode<E> parent, BSTNode<E> child) {
		if (parent == null) {
			root = child;
			if (child != null)
				child.setParent(null);
		} else {
			if (child != null)
				child.setParent(parent);
			if (left) {
				parent.setLeftChild(child);
			} else {
				parent.setRightChild(child);
			}
		}
	}
	
	/**
	 * Finds left-most node of the specified node.
	 *
	 * @param node the node
	 * @return the BST node
	 */
	private BSTNode<E> findLeftMostNode(BSTNode<E> node) {
		BSTNode<E> n = node;
		while(n.getLeftChild() != null) {
			n = n.getLeftChild();
		}
		return n;
	}

	/**
	 * Remove the BST node that contains the supplied element
	 *
	 * @param e the element to be searched for in the BST
	 * @return true if the element was found and deleted; false otherwise
	 */
	public boolean remove(E e) {
		BSTNode<E> n = getMatchingNode(e);
		if (n == null)
			return false;
		BSTNode<E> p = n.getParent();
		reconnect(n, p);
		n.setLeftChild(null);
		n.setRightChild(null);
		n.setParent(null);
		size--;
		return true;
	}
	
	/**
	 * Rearrange the connections depending on the node and the parent
	 *
	 * @param The node and parent
	 * @return null
	 */
	public void reconnect(BSTNode<E> n, BSTNode<E> p) {
		boolean left = p != null ? p.getData().compareTo(n.getData()) > 0 : false;
		if (n.hasRight()) {
			BSTNode<E> right = n.getRightChild();
			if (!n.hasLeft()) {
				connectToParent(left, p, right);
			} else {
				BSTNode<E> lmn = findLeftMostNode(n.getRightChild());
				if (right.equals(lmn)) {
					connectToParent(true, lmn, n.getLeftChild());
					connectToParent(left, p, lmn);
				} else {
					connectToParent(true, lmn.getParent(), lmn.getRightChild());
					connectToParent(true, lmn, n.getLeftChild());
					connectToParent(false, lmn, n.getRightChild());
					connectToParent(left, p, lmn);
				}
				
			}	
		} else if (n.hasLeft()) {
			connectToParent(left, p, n.getLeftChild());
		} else {
			connectToParent(left, p, null);
		}
	}
}



