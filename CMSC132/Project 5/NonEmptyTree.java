package tree;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 7:53 PM 4/8/2015
 */

/**This class was made to hold a NonEmptyTree. This class can be used to add
 * an element to the BST, get the number of elements in the BST, look up a
 * value in the BST, copy the BST, return a sub-tree starting at the key
 * parameter passed in, remove a sub-tree starting at the key parameter passed
 * in, return the left-most key at the specified level, return the largest
 * key in the BST, return the smallest key in the BST, delete an element
 * based on its key, and print out a string representation of the BST in
 * increasing order.
 */

@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	private K key;
	private V value;
	private Tree<K, V> left;
	private Tree<K, V> right;

	/**The constructor that takes in and sets key to keyToAdd and value to
	 * valueToAdd. It also sets left and right to the singleton EmptyTree.
	 */
	public NonEmptyTree(K keyToAdd, V valueToAdd) {
		key = keyToAdd;
		value = valueToAdd;
		left = EmptyTree.getInstance();
		right = EmptyTree.getInstance();
	}

	/**Recursively adds an element with its key as keyToAdd and its value to
	 * valueToAdd based on if the keyToAdd is less than, greater than, or
	 * equal to the current key. Once the NonEmptyTree's add method reaches an
	 * EmptyTree object (Where it needs to add the element) it will run
	 * EmptyTree's add method.
	 */
	public NonEmptyTree<K, V> add(K keyToAdd, V valueToAdd) {
		
		if (valueToAdd == null){
			throw new NullPointerException();
		}
		// If keyToAdd is less than the current key
		if (key.compareTo(keyToAdd) > 0) {
			left = left.add(keyToAdd, valueToAdd);
			// the parent's left is equal to the version of it's left child
			// (it's left child is currently empty [an EmptyTree object]) with
			// the parameters added
		}
		// if keyToAdd is greater than the current key
		else if (key.compareTo(keyToAdd) < 0) {
			right = right.add(keyToAdd, valueToAdd);
			// the parent's right is equal to the version of it's right child
			// (it's right child is currently empty [an EmptyTree object])
			// with the parameters added
		} 
		else if (key.compareTo(keyToAdd) == 0){
			return add(key, valueToAdd);
		} else {
			return add(keyToAdd, valueToAdd);
		}
		
		return this; // returns the current tree
	}

	/**Recursively adds up the size with each recursive call. Once the
	 * NonEmptyTree's size method reaches an EmptyTree object it will run
	 * EmptyTree's size method.
	 */
	public int size() {
		return 1 + left.size() + right.size();
	}

	/**Recursively looks at the left sub-tree and the right sub-tree of the
	 * current element until it finds keyToLookFor. If the key is found it
	 * returns the value associated with keyToLookFor, otherwise it calls
	 * EmptyTree's lookUp method.
	 */
	public V lookup(K keyToLookFor) {
		V temp;
		if (key.compareTo(keyToLookFor) > 0) {
			temp = left.lookup(keyToLookFor);
		} else if (key.compareTo(keyToLookFor) < 0) {
			temp = right.lookup(keyToLookFor);
		} else {
			temp = value;
		}
		return temp;
	}

	/**Call's the subtree method on the root of the current tree
	 */
	public Tree<K, V> copy() {
		return subTree(key);
	}

	/**Call's the subtreeHelper method on the keyOfSubTree of the current
	 * tree
	 */
	public Tree<K, V> subTree(K keyOfSubTree) {
		Tree<K, V> subTree = subTreeHelper(keyOfSubTree);

		return subTree;
	}

	/**A helper method that recursively looks at the left sub-tree and the
	 * right sub-tree of the current element until it finds keyOfSubtree.
	 * If the keyOfSubtree key is found then it returns a tree with the
	 * element related to keyOfSubtree as the root. Otherwise, EmptyTree's
	 * subTreeHelper is called.
	 */
	public Tree<K, V> subTreeHelper(K keyOfSubTree) {
		if (keyOfSubTree.compareTo(key) > 0) {

			return right.subTreeHelper(keyOfSubTree);

		} else if (keyOfSubTree.compareTo(key) < 0) {

			return left.subTreeHelper(keyOfSubTree);
		} else {
			return this;
		}
	}

	/**Recursively looks in the left and right sub-trees for the key
	 * keyOfSubTree until it either finds it or calls EmptyTree's
	 * removeSubTree method. If it does find key keyOfSubTree then it sets
	 * the key and all of it's children to the singleton EmptyTree.
	 */
	public Tree<K, V> removeSubTree(K keyOfSubTree) {
		// Find the subtree and set the pointer to it to an EmptyTree object
		if (key.compareTo(keyOfSubTree) > 0) {
			left = left.removeSubTree(keyOfSubTree);
		} else if (key.compareTo(keyOfSubTree) < 0) {
			right = right.removeSubTree(keyOfSubTree);
		} else {
			return EmptyTree.getInstance();
		}
		return this;
	}

	/**Recursively searches from the root to the specified level (by counting
	 * down form level to 1) for the left-most element by first checking the
	 * left sub-tree and if it runs into an emptyTree element (keyToReturn is
	 * null) it will then check the right sub-tree for it.
	 */
	public K leftMostAtLevel(int level) {
		K keyToReturn;
		if (level == 1) {
			return key;
		}
		keyToReturn = left.leftMostAtLevel(level - 1);
		if (keyToReturn == null) {
			keyToReturn = right.leftMostAtLevel(level - 1);
		}
		return keyToReturn;

	}

	/**Recursively finds the maximum key in the tree by going to the
	 * right-most element, eventually hitting an emptyTree object which will
	 * throw an EmptyTreeException exception, which will recurse upwards and
	 * return the key of the right-most element (the element before the
	 * emptyTree element);;
	 */
	public K max() throws EmptyTreeException {
		K max = null;
		try {
			max = right.max();
		} catch (EmptyTreeException e) {
			return key;
		}
		return max;
	}

	/**Recursively finds the minimum key in the tree by going to the
	 * left-most element, eventually hitting an emptyTree object which will
	 * throw an EmptyTreeException exception, which will recurse upwards and
	 * return the key of the left-most element (the element before the
	 * emptyTree element);
	 */
	public K min() throws EmptyTreeException {
		K min = null;
		try {
			min = left.min();
		} catch (EmptyTreeException e) {
			return key;
		}
		return min;
	}

	/**Recursively finds the element to delete (indicated by it's
	 * key keyToDelete) by searching the right and then the left sub-tree
	 * until it finds the element. Once it finds the element to delete it
	 * searches for the maximum element in the left sub-tree and replaces the
	 * element to delete's key and value with that of the maximum element and
	 * sets the element's left reference to the recursive call of 
	 * left.delete(key). The recursive call of left.delete(key) searches for
	 * the original element that was used to replace the element to delete
	 * and sets it (the original element) to an EmptyTree element   
	 */
	public Tree<K, V> delete(K keyToDelete) {

		if (keyToDelete.compareTo(key) > 0) {
			right = right.delete(keyToDelete);
		} else if (keyToDelete.compareTo(key) < 0) {
			left = left.delete(keyToDelete);
		} else {
			try {
				key = left.max();
				value = left.lookup(key);
				left = left.delete(key);
			} catch (EmptyTreeException e) {
				try {
					key = right.min();
					value = right.lookup(key);
					right = right.delete(key); 
				} catch (EmptyTreeException e2) {
					return EmptyTree.getInstance();
				}
			}
		}
		return this;
	}

	/**Finds the max value in the sub-tree and then calls the
	 * toStringHelper(max) method.
	 */
	public String toString() {
		K max = null;
		try {
			max = max();
		} catch (EmptyTreeException e) {
		}
		return toStringHelper(max);
	}

	/**A helper method that recursively returns a string of the each
	 * element's left sub-tree, the element itself (adding a space unless
	 * it's on the last 'iteration of the tree), and the element's right
	 * sub-tree.
	 */
	public String toStringHelper(K max) {
		String tree = "";
		tree += left.toStringHelper(max);
		tree += key + "+" + value;
		if (key.compareTo(max) != 0) {
			tree += " ";
		}
		tree += right.toStringHelper(max);

		return tree;

	}

}
