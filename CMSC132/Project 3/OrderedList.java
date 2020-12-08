package list;

import java.lang.Iterable;
import java.util.Comparator;
import list.UnorderedList.Node;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 9:53 PM 3/12/2015
 */

/**
 * This class was made to hold an OrderedList and to overwrite methods
 * inherited from UnorderedList that should be done differently and methods 
 * that could be done more efficiently. This class can be used to add an 
 * element to the list, count how many of a certain element is in the list 
 * efficiently, remove a certain number of occurrences in the list
 * efficiently, and remove the element between two positions inclusively and 
 * efficiently.
 */

public class OrderedList<T> extends UnorderedList<T> {

	public OrderedList(Comparator<T> comparator) {
		super(comparator);
	}

	/*
	 * Implement (i.e., override) whatever methods from the superclass that 
	 * you find are necessary to have this list be sorted, as well as any 
	 * methods that would be more efficient if overridden in this subclass.
	 */

	// A more efficient version on UnorderedList's add that sorts the elements
	// in increasing order as they're added
	public void add(T newElt) {
		Node<T> n = new Node<T>();
		n.data = newElt;
		n.next = null;
		boolean flag = false;

		if (head != null) {
			Node<T> current = head;

			// If there is only one element
			if (current.next == null) {
				// If the new element is less than current.data
				if (comparator.compare(current.data, newElt) > 0) {
					setElementToFront(n, current);
				} else {
					current.next = n;

				}
			} else {
				// If there is more than one element
				while (current.next != null && !flag) {
					// If the new element is less than current.data
					if (comparator.compare(current.data, newElt) > 0) {
						setElementToFront(n, current);
						flag = true;
					} else if (comparator.compare(current.next.data, newElt)
							> 0) {
						n.next = current.next;
						current.next = n;
						flag = true;
					} else
						current = current.next;
				}
				if (current.next == null && !flag) {
					current.next = n;
				}
			}

		} else { // If there are no elements
			head = n;
		}
	}

	// A more efficient version on UnorderedList's countElement that 
    // stops if it has found that the next element isn't element element
	public int countElement(T element) {
		Node<T> current = head;
		int numOccur = 0;
		boolean flag = false;

		while (current != null && !flag) {

			if (comparator.compare(current.data, element) == 0) {
				numOccur++;
				if (current.next != null
						&& comparator.compare(current.next.data, element) 
						!= 0) {
					flag = true;
				}
			}

			current = current.next;
		}
		return numOccur;
	}

	// A more efficient version on UnorderedList's removeNumOccurrences that 
	// stops if the number of occurrences is equal to the of number removed 
	// elements
	public int removeNumOccurrences(T element, int num) {
		int removed = 0;
		Node<T> current = head;
		int i = 0;
		int numOccur = countElement(element);

		while (current != null && numOccur > removed) {
			if (comparator.compare(element, current.data) == 0 
					&& num > removed) {
				if (i == 0) {
					head = head.next;
					removed++;
				} else {
					nodeBeforePos(i).next = nodeBeforePos(i).next.next;
					removed++;
				}
				i--;
			}
			i++;
			current = current.next;
		}

		return removed;
	}

	//A healper method that sets newNode to head and current to node.next
	private void setElementToFront(Node<T> newNode, Node<T> current) {
		newNode.next = current;
		head = newNode;
	}

}
