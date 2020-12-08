package tree;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 7:53 PM 4/8/2015
 */

/**This Singleton class was made to hold 1 instance of EmptyTree. This class
 * is used for the base cases of NonEmptyTree and for adding an element to the
 * BST, getting the number of elements in the BST, looking up a value in the
 * BST, copying the BST, returning a sub-tree starting at the key parameter
 * passed in, removing a sub-tree starting at the key parameter passed in,
 * returning the left-most key at the specified level, returning the largest
 * key in the BST, returning the smallest key in the BST, deleting an element
 * based on its key, and printing out a string representation of the BST in
 * increasing order.
 */

@SuppressWarnings({ "unchecked", "rawtypes" })
public final class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V>
{

	// The only instantiation of this class;
	private static EmptyTree only = new EmptyTree();

	/**An empty constructor to override object no-parameter constructor
	 */
	private EmptyTree() {
	}

	/**Returns the only instance of EmptyTree
	 */
	public static EmptyTree getInstance() {
		return only;
	}

	/**Returns a NonEmptyTree object with key keyToAdd and value valueToAdd.
	 * It's also the base case for NonEmptyTree's add method.
	 */
	public NonEmptyTree<K, V> add(K keyToAdd, V valueToAdd) {
		return new NonEmptyTree<K, V>(keyToAdd, valueToAdd);
	}

	/**Returns 0 because an emptyTree is empty
	 * It's also the base case for NonEmptyTree's size method.
	 */
	public int size() {
		return 0;
	}

	/**Returns null because an emptyTree has no elements to look up
	 * It's also the base case for NonEmptyTree's lookup method.
	 */
	public V lookup(K keyToLookFor) {
		return null;
	}

	/**Returns this because an emptyTree has only itself to return
	 * It's also the base case for NonEmptyTree's copy method.
	 */
	public Tree<K, V> copy() {
		return this;
	}

	/**Returns this because an emptyTree has only itself to return
	 * It's also the base case for NonEmptyTree's subTree method.
	 */
	public Tree<K, V> subTree(K keyOfSubtree) {
		return this;
	}

	/**Returns this because an emptyTree has only itself to return
	 * It's also the base case for NonEmptyTree's subTreeHelper method.
	 */
	public Tree<K, V> subTreeHelper(K keyOfSubTree) {
		return this;
	}

	/**Returns this because an emptyTree has only itself to return
	 * It's also the base case for NonEmptyTree's removeSubTree method.
	 */
	public Tree<K, V> removeSubTree(K keyOfSubtree) {
		return this;
	}

	/**Returns null because an emptyTree has no left-most element
	 * It's also the base case for NonEmptyTree's leftMostAtLevel method.
	 */
	public K leftMostAtLevel(int level) {
		return null;
	}

	/**Returns an EmptyTreeException because an emptyTree has no maximum
	 * value. It's also the base case for NonEmptyTree's max method.
	 */
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	/**Returns an EmptyTreeException because an emptyTree has no minimum
	 * value. It's also the base case for NonEmptyTree's max method.
	 */
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	/**Returns an EmptyTreeException because an once an EmptyTree element is
	 * deleted, all that's left is an EmptyTree. It's also the base case for
	 * NonEmptyTree's delete method.
	 */
	public Tree<K, V> delete(K keyToDelete) {
		return this;
	}

	/**Returns an EmptyTreeException because an EmptyTree has no data to put
	 * into a string. It's also the base case for NonEmptyTree's toString
	 * method.
	 */
	public String toString() {
		return "";
	}

	/**Returns an EmptyTreeException because an EmptyTree has no data to put
	 * into a string. It's also the base case for NonEmptyTree's
	 * toStringHelper method.
	 */
	public String toStringHelper(K max) {
		return "";
	}
}
