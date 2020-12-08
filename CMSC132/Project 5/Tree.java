package tree;

public interface Tree<K extends Comparable<K>, V> {
  public NonEmptyTree<K, V> add(K keyToAdd, V valueToAdd);
  public int size();
  public V lookup(K keyToLookFor);
  public Tree<K, V> copy();
  public Tree<K, V> subTree(K keyOfSubtree);
  public Tree<K, V> removeSubTree(K keyOfSubtree);
  public K leftMostAtLevel(int level);
  public K max() throws EmptyTreeException;
  public K min() throws EmptyTreeException;
  public Tree<K, V> delete(K keyToDelete);
  public String toStringHelper(K max);
  public Tree<K, V> subTreeHelper(K keyOfSubTree);  
}
