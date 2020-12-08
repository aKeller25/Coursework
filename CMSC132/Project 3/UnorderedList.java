package list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 9:53 PM 3/12/2015
 */

/**
 * This class was made to hold an UnorderedList. This class can be used to add
 * an element to the list, get the length of the list, clear the list, return
 * a string representation, return an element at a certain position, count how
 * many of a certain element is in the list, append another UnorderedList to
 * the current list, compare the current list to another UnorderedList, 
 * iterate through the list, remove a certain number of occurrences in the 
 * list, and remove the element between two positions inclusively.
 */

public class UnorderedList<T> implements Iterable<T>,
		Comparable<UnorderedList<T>> {

	// you may ADD TO this inner class, but not CHANGE what's already here!
	protected class Node<D> {
		D data;
		Node<D> next;
	}

	protected Comparator<T> comparator;
	protected Node<T> head = null;

	// The constructor that sets the current comparator to the parameter
	// comparator
	public UnorderedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	// Searches the list and if the last element's next is null, it sets the
	// new element to the last elements next (adds it to the end of the list)
	// If the head reference is null, it just sets head to the new element
	public void add(T newElt) {
		Node<T> n = new Node<T>();
		n.data = newElt;
		n.next = null;

		if (head != null) {
			Node<T> current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = n;
		} else {
			head = n;
		}
	}

	// Returns the length of the linked list
	public int length() {
		Node<T> current = head;
		int length = 0;

		// For while there are still elements in the list
		while (current != null) {
			length++;
			current = current.next;
			// 'increments' (goes to the next one) current
		}
		return length;
	}

	// Clears the linked list of all elements
	public void clear() {
		head = null;
	}

	// Returns a string representation of the linked list. If the list is not
	// at the second-to-last element it adds a space after the element, 
	// otherwise it doesn't
	public String toString() {
		Node<T> current = head;
		String stringRep = "";

		while (current != null) {
			if (current.next != null) {
				stringRep += current.data.toString() + " ";
			} else {
				stringRep += current.data.toString();
			}
			current = current.next;
		}
		return stringRep;
	}

	// Returns the data of the node at index index if the index is less than
	// the length of the linked list, otherwise it throws an exception
	public T elementAtPos(int index) throws IndexOutOfBoundsException {
		T indexFound = null;
		if (length() < index) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<T> current = head;

			int i;
			for (i = 0; i < length() && current != null && 
					i != index + 1; i++) {
				indexFound = current.data;
				current = current.next;
			}
			return indexFound;
		}
	}

	// Counts the number of occurrences of element element
	public int countElement(T element) {
		Node<T> current = head;
		int numOccur = 0;

		while (current != null) {

			if (comparator.compare(current.data, element) == 0) {
				numOccur++;
			}

			current = current.next;
		}
		return numOccur;
	}

	// Appends otherList's elements onto the end of the current list, whie 
	// keeping both lists seperate 
	public void append(UnorderedList<T> otherList) {
		Node<T> current = otherList.head;

		while (current != null) {
			this.add(current.data);
			current = current.next;
		}
	}

	// Compares two UnorderedLists and returns -1, 0, or 1 under various
	// conditions described here.
	
	// If they're the same length and all of their elements in the nth position
	// are equal it returns 0. If the current list is shorter and the last 
	// element of the current list compared to the otherList is equal it
	// returns -1. If the otherList is shorter and the last element the of
	// otherList compared to the current list is equal it returns 1. If it
	// finds that an element in the current list is greater than the same
	// number element in otherList, before either list runs out it returns 1.
	// If it finds that an element in the current list is less than the same
	// number element in otherList, before either list runs out it returns -1.
	public int compareTo(UnorderedList<T> otherList) {
		Node<T> current = this.head;
		Node<T> otherCurrent = otherList.head;
		int compare = 0;

		// If current runs out, other runs out, or compare is not zero
		while (current != null && otherCurrent != null && compare == 0) {
			compare = comparator.compare(current.data, otherCurrent.data);

			current = current.next;
			otherCurrent = otherCurrent.next;
		}

		if ((this.length() < otherList.length()) && compare == 0) {
			return -1;
		} else if ((this.length() > otherList.length()) && compare == 0) {
			return 1;
		}

		return compare;

	}

	// This is an inner Iterator class that is used for the
	// UnorderedList class
	public class UnorderedIterator implements Iterator<T> {

		int counter = 0;
		Node<T> current = head;

		public boolean hasNext() {
			return current != null;
		}

		public T next() throws NoSuchElementException {
			Node<T> nextElt = null;

			if (hasNext()) {
				nextElt = current;
				current = current.next;
			} else {
				throw new NoSuchElementException();
			}

			return nextElt.data;
		}

	}

	// Creates a new UnorderedIterator iterator
	public Iterator<T> iterator() {
		return new UnorderedIterator();
	}

	// Removes the num, or less than num occurrences of element element. If
	// there are less occurrences of element element than num, it just removes
	// all occurrences of element element
	public int removeNumOccurrences(T element, int num) {
		int removed = 0;
		Node<T> current = head;
		int i = 0;

		while (current != null) {
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

	// Removes the elements from fromPos to toPos. If fromPos is less than 0,
	// it treats fromPos like it was 0 and if toPos is greater than the
	// last index in the linked list it treats toPos like it was set to the
	// last index in the linked list
	public void removeRange(int fromPos, int toPos) {
		int from = fromPos < 0 ? 0 : fromPos;
		int to = toPos > this.length() ? this.length() - 1 : toPos;

		if (!(fromPos < 0 && toPos < 0) && fromPos <= toPos) {
			if (!(fromPos > length() && toPos > length())) {

				if (from == 0) {
					head = nodeBeforePos(to).next.next;
				}

				else if (to == this.length() - 1) {
					nodeBeforePos(from).next = null;

				} else {
					nodeBeforePos(from).next = nodeBeforePos(to).next.next;
				}
			}
		}
	}

	// A helper method that returns the node before the index nodeNumber
	protected Node<T> nodeBeforePos(int nodeNumber) {
		Node<T> indexFound = null;
		Node<T> current = head;

		int i;
		for (i = 0; i < length() && current != null && 
				i != nodeNumber; i++) {
			indexFound = current;
			current = current.next;
		}
		return indexFound;
	}
}
