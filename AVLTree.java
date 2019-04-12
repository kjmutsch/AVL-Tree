/**
 * Filename:   AVLTree.java
 * Project:    p2
 * Authors:    Debra Deppeler, Snehita (Sneha) Polishetty, Kiara Mutschler 
 *
 * Semester:   Fall 2018
 * Course:     CS400
 * Lecture:    002 (Sneha) & 001 (Kiara) 
 * 
 * Due Date:   Before 10pm on October 01, 2018
 * Version:    1.0
 * 
 * Credits:    none
 * 
 * Bugs:       no known bugs, but not complete either
 * //////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// Partner Name: Snehita Polishetty
// Partner Email: spolishetty@wisc.edu
// Partner Lecturer's Name: Debra Deppeler
// // VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
 * ///////////////////////////////////////////////////////////////////////////////
 */

import java.lang.IllegalArgumentException;

/**
 * This class will make use of the implemented BSTNode<K> class down below and
 * simulates the functionality of a simple AVL Tree using a Binary Search Tree
 * Nodes.
 * 
 * @param <K>
 * @author Snehita (Sneha) Polishetty, Kiara Mutschler
 */
public class AVLTree<K extends Comparable<K>> implements AVLTreeADT<K> {
	private BSTNode<K> root; // represents top-most node
	private int size = 0; // represents number of nodes in AVL Tree

	/**
	 * Empty constructor for an AVL Tree.
	 */
	public AVLTree() {
	}

	/**
	 * This class creates an object instance of a Binary Search Tree node that will
	 * be used to represent nodes within an AVL Tree.
	 * 
	 * @param <K>
	 * @author Snehita (Sneha) Polishetty, Kiara Mutschler
	 */
	class BSTNode<K> {
		private K key; // represents the Comparable value in a node of the AVLTree
		private int height; // represents distance of a node from the bottom of the tree
		private BSTNode<K> left, right; // represents left and right children of the root key
		private int balanceFac; // height of the right subtree minus height of left subtree

		/**
		 * Constructor for a BST node.
		 * 
		 * @param K
		 *            key name for Comparable value
		 */
		public BSTNode(K key) {
			this.height = 0;
			this.key = key;
		}

		/**
		 * Accessor/Getter method that returns the value of private balance factor.
		 *
		 * @param none
		 * @return int balanceFac
		 */
		public int getBalanceFactor() {
			return this.balanceFac;
		}

		/**
		 * Mutator/Setter method that sets the value of private field balance factor
		 * with a value gotten from the parameter.
		 *
		 * @param int
		 *            balanceFac
		 * @return none
		 */
		public void setBalanceFactor(int balanceFac) {
			this.balanceFac = balanceFac;
		}

		/**
		 * Accessor/Getter method that returns the value of private field key.
		 *
		 * @param none
		 * @return K Key
		 */
		public K getKey() {
			return this.key;
		}

		/**
		 * Accessor/Getter method that returns the value of private field height.
		 *
		 * @param none
		 * @return int height
		 */
		public int getHeight() {
			return this.height;
		}

		/**
		 * Accessor/Getter method that returns the value of private field rightChild.
		 *
		 * @param none
		 * @return BSTNode<K> rightChild
		 */
		public BSTNode<K> getRightChild() {
			return this.right;
		}

		/**
		 * Accessor/Getter method that returns the value of private field leftChild.
		 *
		 * @param none
		 * @return BSTNode<K> leftChild
		 */
		public BSTNode<K> getLeftChild() {
			return this.left;
		}

		/**
		 * Mutator/Setter method that sets the value of private field key with a value
		 * gotten from the parameter.
		 *
		 * @param K
		 *            key
		 * @return none
		 */
		public void setKey(K key) {
			this.key = key;
		}

		/**
		 * Mutator/Setter method that sets the value of private field height with a
		 * value gotten from the parameter.
		 *
		 * @param int
		 *            height
		 * @return none
		 */
		public void setHeight(int height) {
			this.height = height;
		}

		/**
		 * Mutator/Setter method that sets the value of private field rightChild with a
		 * value gotten from the parameter.
		 *
		 * @param BSTNode<K>
		 *            rightChild
		 * @return none
		 */
		public void setRightChild(BSTNode<K> right) {
			this.right = right;
		}

		/**
		 * Mutator/Setter method that sets the value of private field leftChild with a
		 * value gotten from the parameter.
		 *
		 * @param BSTNode<K>
		 *            leftChild
		 * @return none
		 */
		public void setLeftChild(BSTNode<K> left) {
			this.left = left;
		}
	}

	/**
	 * Accessor/Getter method that returns the value of private field size.
	 *
	 * @param none
	 * @return int size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * This method checks whether the AVL Tree is empty or not.
	 * 
	 * @param none
	 * @return true if AVL Tree instance is empty.
	 * 
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * This method calls a private helper method in order to operate. The method
	 * also updates the size of the AVL Tree and checks if null or a duplicate key
	 * is passed as a parameter.
	 * 
	 * @param K
	 *            key name of Comparable a value
	 * @return none
	 * @throws IllegalArgumentException
	 * @throws DuplicateKeyException
	 */
	@Override
	public void insert(K key) throws DuplicateKeyException, IllegalArgumentException {
		if (key == null)
			throw new IllegalArgumentException("Cannot insert null into AVL tree");
		if (search(key))
			throw new DuplicateKeyException("This data already exists in AVL tree");
		else {
			root = insertHelper(root, new BSTNode<K>(key));
			size++;
		}
	}

	/**
	 * The method inserts an instance of BSTNode with the given key into the AVT
	 * Tree by conforming to the order property of a BST. The method also updates
	 * the height and balance factor of the current node and its left and right
	 * children (if applicable).
	 * 
	 * @param BSTNode<K>
	 *            node name for current node
	 * @param BSTNode<K>
	 *            bstNode name for node to be inserted
	 * @return BSTNode<K> returns root of the AVL Tree
	 */
	private BSTNode<K> insertHelper(BSTNode<K> node, BSTNode<K> bstNode) {
		if (node == null)
			return bstNode;
		if (((Comparable<K>) bstNode.getKey()).compareTo((K) node.getKey()) < 0) { // current node is greater than
			// inputed node
			if (node.getLeftChild() != null) {
				node.setLeftChild(insertHelper(node.getLeftChild(), bstNode)); // keep recurring until left child is
				// empty
			} else {
				node.setLeftChild(bstNode); // left child is empty and can insert a new node
			}
		} else {
			if (node.getRightChild() != null) { // current node is less than inputed node
				node.setRightChild(insertHelper(node.getRightChild(), bstNode)); // keep recurring until right child is
				// empty
			} else {
				node.setRightChild(bstNode); // right child is empty and can insert a new node
			}
		}
		int leftNodeHeight = (node.getLeftChild() == null) ? -1 : node.getLeftChild().getHeight();
		int rightNodeHeight = (node.getRightChild() == null) ? -1 : node.getRightChild().getHeight();
		node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
		node.setBalanceFactor(rightNodeHeight - leftNodeHeight);
		if (checkForBalancedTree())
			return node;
		else {
			return balance(node);
		}
	}

	/**
	 * This method utilized the balance factor field to see check whether it is a
	 * left heavy or right heavy case. Once the tree is determined as either right
	 * heavy or left heavy, then it is further determined if the node has a left or
	 * right child and the nodes are rotated from there.
	 * 
	 * @param BSTNode<K>
	 *            node name for current node
	 * @return BSTNode<K> returns re-balanced node
	 */
	private BSTNode<K> balance(BSTNode<K> node) {
		if (node.getBalanceFactor() == -2) { // isLeftHeavy
			if (node.getLeftChild().getBalanceFactor() <= 0) {
				node = leftHeavy(node);
			} else {
				node = leftRightCase(node);
			}
		} else if (node.getBalanceFactor() == 2) { // is right heavy
			if (node.getRightChild().getBalanceFactor() <= 0) {
				node = rightLeftCase(node);
			} else {
				node = rightHeavy(node);
			}
		}
		return node;
	}

	/**
	 * This method calls a private helper method in order to operate. The method
	 * also updates the size of the AVL Tree and checks if null is passed as a
	 * parameter.
	 * 
	 * @param K
	 *            key name of Comparable value
	 * @return none
	 * @throws IllegalArgumentException
	 */
	@Override
	public void delete(K key) throws IllegalArgumentException {
		if (key == null)
			throw new IllegalArgumentException("Cannot delete null value from the AVL Tree.");
		if (search(key)) {
			root = deleteHelper(root, key);
			size--;
		}
	}

	/**
	 * This method deletes a specified node from the AVL Tree. If the node is not in
	 * the tree then the method does nothing. If a node is deleted and the height of
	 * its left subtree is bigger then the predecessor is taken in place, otherwise
	 * if the right subtree is then the successor is taken.
	 * 
	 * @param BSTNode<K>
	 *            node name for current node
	 * @param K
	 *            key name of Comparable value @ return BSTNode<K> returns the root
	 *            of AVL Tree
	 * @return BSTNode<K> returns root of AVL Tree
	 */
	public BSTNode<K> deleteHelper(BSTNode<K> node, K key) {
		if (node == null)
			return null;
		if (key.compareTo(node.getKey()) < 0) // dig into left subtree if key smaller
			node.setLeftChild(deleteHelper(node.getLeftChild(), key));
		else if (key.compareTo(node.getKey()) > 0) // dig into right if key larger
			node.setRightChild(deleteHelper(node.getRightChild(), key));
		else { // if reached this else then the node we looked for was found
			if (node.getLeftChild() == null) // if only has a right subtree
				return node.getRightChild();
			else if (node.getRightChild() == null) // if only has a left subtree
				return node.getLeftChild();
			else {
				// remove from left subtree
				if (node.getLeftChild().getHeight() > node.getRightChild().getHeight()) {
					K predecessor = max(node.getLeftChild());
					node.setKey(predecessor);
					node.setLeftChild(deleteHelper(node.getLeftChild(), predecessor));
				} else {
					// remove from right subtree
					K successor = min(node.getRightChild());
					node.setKey(successor);
					node.setRightChild(deleteHelper(node.getRightChild(), successor));
				}
			}
		}
		int leftNodeHeight = (node.getLeftChild() == null) ? -1 : node.getLeftChild().getHeight();
		int rightNodeHeight = (node.getRightChild() == null) ? -1 : node.getRightChild().getHeight();
		node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight)); // update the height
		node.setBalanceFactor(rightNodeHeight - leftNodeHeight); // update the balance factor
		if (checkForBalancedTree())
			return node;
		return balance(node);
	}

	/**
	 * This method finds the smallest node in a tree or subtree by recursively going
	 * to the left.
	 * 
	 * @param BSTNode<K>
	 *            node name for current node
	 * @return K key name of Comparable value
	 */
	private K min(BSTNode<K> node) {
		while (node.getLeftChild() != null)
			node = node.getLeftChild();
		return node.getKey();
	}

	/**
	 * This method finds the greatest node in a tree or subtree by recursively going
	 * to the right.
	 * 
	 * @param BSTNode<K>
	 *            node name for current node
	 * @return K key name of Comparable value
	 */
	private K max(BSTNode<K> node) {
		while (node.getRightChild() != null)
			node = node.getRightChild();
		return node.getKey();
	}

	/**
	 * The method returns the private helper method rightRotation because if a tree
	 * is left heavy it must be rotated right.
	 * 
	 * @param BSTNode<K>
	 *            node is the node undergoing the rotation
	 * @return BSTNode<K>
	 */
	private BSTNode<K> leftHeavy(BSTNode<K> node) {
		return rightRotation(node);
	}

	/**
	 * The method first rotates a node's left child to the left and then rotates the
	 * node to the right.
	 * 
	 * @param BSTNode<K>
	 *            node
	 * @return BSTNode<K>
	 * 
	 */
	private BSTNode<K> leftRightCase(BSTNode<K> node) {
		node.setLeftChild(leftRotation(node.getLeftChild()));
		return leftHeavy(node);
	}

	/**
	 * The method first rotates a node's right child to the right and then rotates
	 * the node to the left.
	 * 
	 * @param BSTNode<K>
	 *            node
	 * @return BSTNode<K>
	 */
	private BSTNode<K> rightLeftCase(BSTNode<K> node) {
		node.setRightChild(rightRotation(node.right));
		return rightHeavy(node);
	}

	/**
	 * If a tree is right heavy then the node needs to undergo a left rotation.
	 * 
	 * @param BSTNode<K>
	 *            node
	 * @return BSTNode<K> left rotated version of node
	 */
	private BSTNode<K> rightHeavy(BSTNode<K> node) {
		return leftRotation(node);
	}

	/**
	 * This method makes a left rotation of the grandparent node and does so by
	 * setting the parent's left child to the grandparent and the grandparent node's
	 * right child to whatever the left child of the parent was.
	 * 
	 * @param BSTNode<K>
	 *            node
	 * @return BSTNode<K> p
	 */
	private BSTNode<K> leftRotation(BSTNode<K> node) {
		BSTNode<K> p = node.getRightChild();
		node.setRightChild(p.getLeftChild());
		p.setLeftChild(node);
		changeHeightBalance(node, p);
		return p;
	}

	/**
	 * This method makes a right rotation of the grandparent node and does so by
	 * setting the parent's right child to the grandparent and the grandparent
	 * node's left child to whatever the right child of the parent node was.
	 * 
	 * @param BSTNode<K>
	 *            node
	 * @return BSTNode<K> p
	 */
	private BSTNode<K> rightRotation(BSTNode<K> node) {
		BSTNode<K> p = node.getLeftChild();
		node.setLeftChild(p.getRightChild());
		p.setRightChild(node);
		changeHeightBalance(node, p);
		return p;
	}

	/**
	 * This method is here to ensure that the height and balance factor are up to
	 * date with the proper values.
	 * 
	 * @param BSTNode<K>
	 *            node is the child of p
	 * @param BSTNode<K>
	 *            p parent of node
	 * @return none
	 */
	private void changeHeightBalance(BSTNode<K> node, BSTNode<K> p) {
		int leftNodeHeight; // height of current node's left Child
		int rightNodeHeight; // height of current node's right Child
		int leftPHeight; // height of parent's left child
		int rightPHeight; // height of parent's right child
		leftNodeHeight = (node.getLeftChild() == null) ? -1 : node.getLeftChild().getHeight();
		rightNodeHeight = (node.getRightChild() == null) ? -1 : node.getRightChild().getHeight();
		// if a value of a child is null, set it to -1 so the Math.max feature can be
		// used, otherwise
		// assign its height normally
		node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));
		node.setBalanceFactor(rightNodeHeight - leftNodeHeight);
		leftPHeight = (p.getLeftChild() == null) ? -1 : p.getLeftChild().getHeight();
		rightPHeight = (p.getRightChild() == null) ? -1 : p.getRightChild().getHeight();
		p.setHeight(1 + Math.max(leftPHeight, rightPHeight));
		p.setBalanceFactor(rightPHeight - node.getHeight());
	}

	/**
	 * This method calls a private helper method in order to operate. The method
	 * also checks if null is passed as a parameter.
	 * 
	 * @param K
	 *            key name of Comparable value
	 * @return returns true if inputed key matches another key within the AVL Tree
	 * @throws IllegalArgumentException
	 */
	@Override
	public boolean search(K key) throws IllegalArgumentException {
		if (key == null) {
			throw new IllegalArgumentException("Cannot search for a null value inside AVL Tree");
		} else {
			return searchHelper(this.root, key);
		}
	}

	/**
	 * This method searches for the given searchKey within the AVL Tree and informs
	 * whether the key is present or not.
	 * 
	 * @param K
	 *            searchKey name for inputed key by user
	 * @param BSTNode<K>
	 *            node name for current node
	 * @return returns true if inputed key matches another key within the AVL Tree
	 */
	private boolean searchHelper(BSTNode<K> node, K searchKey) {
		if (node == null) {
			return false;
		}
		if (node.getKey().compareTo(searchKey) < 0 && node.getRightChild() != null) { // searchKey value is greater than
			// current node
			return searchHelper(node.getRightChild(), searchKey);
		}
		if (node.getKey().compareTo(searchKey) > 0 && node.getLeftChild() != null) { // searchKey value is less than
			// current
			// node
			return searchHelper(node.getLeftChild(), searchKey);
		}
		if (node.getKey().compareTo(searchKey) == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method calls a private helper method in order to operate.
	 * 
	 * @param none
	 * @return String containing all of the AVL Tree's nodes' keys with a single
	 *         space between each key.
	 */
	@Override
	public String print() {
		if (this.root == null) {
			return "";
		} else {
			return printHelper(this.root, root.getKey());
		}
	}

	/**
	 * This method performs an in-order traversal of the AVL Tree and prints each
	 * node's key followed by a single space.
	 * 
	 * @param BSTNode<K>
	 *            node name for current node,
	 * @param K
	 *            key name of Comparable value
	 * @return String containing all of the AVL Tree's nodes' keys with a single
	 *         space between each key.
	 */
	private String printHelper(BSTNode<K> node, K key) {
		// in-order traversal (LVR)
		String output = "";
		if (node.getLeftChild() != null) {
			output += printHelper(node.getLeftChild(), node.getLeftChild().getKey()); // recurse left
		}
		output += String.valueOf(node.getKey()) + " "; // visit current node
		if (node.getRightChild() != null) {
			output += printHelper(node.getRightChild(), node.getRightChild().getKey()); // recurse right
		}
		return output;
	}

	/**
	 * This method calls a private helper method in order to operate.
	 * 
	 * @param none
	 * @return true if AVL Tree structure conforms to the expectations of a
	 *         height-balanced tree.
	 */
	@Override
	public boolean checkForBalancedTree() {
		if (this.root == null) {
			return true;
		} else {
			return checkForBalancedTreeHelper(this.root);
		}
	}

	/**
	 * This method essentially checks if the AVL tree is height balanced by checking
	 * every single node in an AVL Tree and ensuring each node's balance factor is
	 * within the range of -1 to 1, if not then the method returns false.
	 * 
	 * @param BSTNode<K>
	 *            name for current node
	 * @return true if AVL Tree structure conforms to the expectations of a
	 *         height-balanced tree.
	 */
	private boolean checkForBalancedTreeHelper(BSTNode<K> node) {
		if (node.getLeftChild() != null) {
			checkForBalancedTreeHelper(node.getLeftChild());
		}
		if (node.getBalanceFactor() <= -2 || node.getBalanceFactor() >= 2) { // not height balanced
			return false;
		}
		if (node.getRightChild() != null) {
			checkForBalancedTreeHelper(node.getRightChild());
		}
		if (node.getBalanceFactor() <= -2 || node.getBalanceFactor() >= 2) { // not height balanced
			return false;
		}
		return true;
	}

	/**
	 * This method calls a private helper method in order to operate.
	 * 
	 * @param none
	 * @return true if AVL Tree structure conforms to the expectations of a BST.
	 */
	@Override
	public boolean checkForBinarySearchTree() {
		if (this.root == null) {
			return true;
		} else {
			return checkForBinarySearchTreeHelper(this.root);
		}
	}

	/**
	 * This method essentially checks if each node's left child (if applicable) is
	 * less than the current node and each node's right child (if applicable) is
	 * greater than the current node.
	 * 
	 * @param BSTNode<K>
	 *            name for current node
	 * @return true if AVL Tree structure conforms to the expectations of a BST.
	 */
	private boolean checkForBinarySearchTreeHelper(BSTNode<K> node) {
		if (node.getLeftChild() != null) {
			checkForBinarySearchTreeHelper(node.getLeftChild());
		}
		if (node.getLeftChild() != null && (node.getLeftChild().getKey().compareTo(node.getKey())) > 0) {
			// if left child is greater than current node
			return false;
		}
		if (node.getRightChild() != null) {
			checkForBinarySearchTreeHelper(node.getRightChild());
		}
		if (node.getRightChild() != null && (node.getRightChild().getKey().compareTo(node.getKey())) < 0) {
			// if right child is less than current node
			return false;
		}
		return true;
	}
}
