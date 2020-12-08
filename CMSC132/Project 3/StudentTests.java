package tests;

import list.CharacterComparator;
import list.IntegerComparator;
import list.OrderedList;
import list.UnorderedList;

import org.junit.*;

import static org.junit.Assert.*;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 9:53 PM 3/12/2015
 */

/**
 * This class was made to and is used to test things that the PublicTests 
 * class doesn't test, as well as test every method in the UnorderedList and 
 * the OrderedList class.
 */

public class StudentTests {

	static IntegerComparator intComparator = new IntegerComparator();
	static CharacterComparator charComparator = new CharacterComparator();

	// Tests if an unordered list can be created, added to, cleared, and the
	// length be returned correctly
	@Test
	public void testStudent1() {
		UnorderedList<Integer> testList = new UnorderedList<Integer>(
				intComparator);

		assertEquals(0, testList.length());

		testList.add(132);
		testList.add(426);
		testList.add(917);

		assertEquals(3, testList.length());

		testList.clear();

		assertEquals(0, testList.length());
	}

	// Tests if an unordered list can return it's string representation
	@Test
	public void testStudent2() {
		UnorderedList<Character> testList = new UnorderedList<Character>(
				charComparator);

		testList.add('b');
		testList.add('r');
		testList.add('e');
		testList.add('a');
		testList.add('d');

		assertEquals("b r e a d", testList.toString());
	}

	// Tests if an unordered list can return an element in a specific place
	@Test
	public void testStudent3() {
		UnorderedList<Character> testList = new UnorderedList<Character>(
				charComparator);

		testList.add('b');
		testList.add('r');
		testList.add('e');
		testList.add('a');
		testList.add('d');

		assertEquals("e", testList.elementAtPos(2).toString());
	}

	// Tests if an unordered list can count how many of a certain element
	// there is
	@Test
	public void testStudent4() {
		UnorderedList<Character> testList = new UnorderedList<Character>(
				charComparator);

		testList.add('e');
		testList.add('a');
		testList.add('t');
		testList.add(' ');
		testList.add('b');
		testList.add('r');
		testList.add('e');
		testList.add('a');
		testList.add('d');

		assertEquals(2, testList.countElement('e'));
		assertEquals(2, testList.countElement('a'));
		assertEquals(1, testList.countElement('t'));
		assertEquals(0, testList.countElement('w'));
	}

	// Tests if you can append a list to an UnorderedList both with elements
	// and without elements. It also tests to see if the list being appended
	// and the current list are separate
	@Test
	public void testStudent5() {

		UnorderedList<Character> testList1 = new UnorderedList<Character>(
				charComparator);
		UnorderedList<Character> testList2 = new UnorderedList<Character>(
				charComparator);
		UnorderedList<Character> testList3 = new UnorderedList<Character>(
				charComparator);

		testList1.add('c');
		testList1.add('a');
		testList1.add('t');
		testList2.add('n');
		testList2.add('i');
		testList2.add('p');

		testList1.append(testList2);
		testList1.append(testList3);

		assertEquals("c a t n i p", testList1.toString());
		assertEquals("n i p", testList2.toString());

	}

	// Tests if the compare method works for the condition in which the lists
	// are the same length and all of their elements are equal in their
	// respective positions
	@Test
	public void testStudent6() {
		UnorderedList<Integer> testList = unorderedIntegerList1();
		UnorderedList<Integer> testList2 = testList;

		assertEquals(0, testList.compareTo(testList2));

	}

	// Tests if the compare method works for the condition in which the lists
	// are the same length and an element in the parameter list is greater
	// than the same element in the current list and vice versa
	@Test
	public void testStudent7() {
		UnorderedList<Integer> testList = unorderedIntegerList1();
		UnorderedList<Integer> testList2 = unorderedIntegerList2();

		assertEquals(-1, testList.compareTo(testList2));
		assertEquals(1, testList2.compareTo(testList));

	}

	// Tests if the compare method works for the condition in which the lists
	// compare equally until the current list is over and vice versa
	@Test
	public void testStudent8() {
		UnorderedList<Character> testList = unorderedCharacterList1();
		UnorderedList<Character> testList2 = unorderedCharacterList2();

		assertEquals(-1, testList.compareTo(testList2));
		assertEquals(1, testList2.compareTo(testList));

	}

	// Tests the removeNumOccurrences method for when the one of the ones
	// being removed is head
	@Test
	public void testStudent9() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeNumOccurrences(1, 2);

		assertEquals("78 18 56 560", testList.toString());
	}

	// Tests the removeNumOccurrences method for when one of the ones being
	// removed is the end and the number or occurrences to remove is higher
	// than the number of occurrences in the list
	@Test
	public void testStudent10() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeNumOccurrences(560, 2);

		assertEquals("1 1 78 18 56", testList.toString());
	}

	// Tests the removeRange method for positions are neither next
	// to each other nor at the far-edges
	@Test
	public void testStudent11() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(1, 4);

		assertEquals("1 560", testList.toString());
	}

	// Tests the removeRange method for positions not next to each
	// other and the from position as the first element
	@Test
	public void testStudent12() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(0, 4);

		assertEquals("560", testList.toString());
	}

	// Tests the removeRange method for if the from index is less
	// than 0
	@Test
	public void testStudent13() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(-5, 4);

		assertEquals("560", testList.toString());
	}

	// Tests the removeRange method for positions not next to each
	// other and the to position as the last index
	@Test
	public void testStudent14() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(1, 5);

		assertEquals("1", testList.toString());
	}

	// Tests the removeRange method for if the to and from index are less
	// than zero
	@Test
	public void testStudent15() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(-1, -5000);

		assertEquals("1 1 78 18 56 560", testList.toString());
	}

	// Tests the removeRange method for if the to and from index are greater
	// than zero
	@Test
	public void testStudent16() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(500, 5000);

		assertEquals("1 1 78 18 56 560", testList.toString());
	}

	// Tests the removeRange method for if the the from index is greater than
	// the to index
	@Test
	public void testStudent17() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(78, 2);

		assertEquals("1 1 78 18 56 560", testList.toString());
	}

	// Tests the removeRange method for if the the from index is the same as
	// the to index
	@Test
	public void testStudent18() {

		UnorderedList<Integer> testList = unorderedIntegerList1();

		testList.removeRange(2, 2);

		assertEquals("1 1 18 56 560", testList.toString());
	}

	// Tests if an ordered list can count how many of a certain element
	// there whether the elements are at the beginning or the end of the list
	// efficiently
	@Test
	public void testStudent19() {
		OrderedList<Integer> testList = orderedStudentList1();
		OrderedList<Integer> testList2 = orderedStudentList2();

		assertEquals(2, testList.countElement(78));
		assertEquals(2, testList2.countElement(5));
	}

	// Tests if an ordered list can remove numOccurences efficiently whether
	// the elements are at the beginning or the end of the list efficiently
	@Test
	public void testStudent20() {
		OrderedList<Integer> testList = orderedStudentList1();
		OrderedList<Integer> testList2 = orderedStudentList2();

		testList.removeNumOccurrences(78, 2);
		testList2.removeNumOccurrences(5, 2);

		assertEquals("5 10 24 56", testList.toString());
		assertEquals("10 56 56 78", testList2.toString());
	}

	// adds all elements of an array of any type to a list of the same type-
	// which could be an UnorderedList, or an OrderedList
	private static <T> UnorderedList<T> initList(
			UnorderedList<T> list, T[] arr) {
		for (T elt : arr)
			list.add(elt);

		return list;
	}

	// returns an UnorderedList with several Integer elements
	private static UnorderedList<Integer> unorderedIntegerList1() {
		UnorderedList<Integer> list = 
				new UnorderedList<Integer>(intComparator);

		initList(list, new Integer[] { 1, 1, 78, 18, 56, 560 });

		return list;
	}

	// returns an UnorderedList with several Integer elements
	private static UnorderedList<Integer> unorderedIntegerList2() {
		UnorderedList<Integer> list = 
				new UnorderedList<Integer>(intComparator);

		initList(list, new Integer[] { 1, 1, 78, 53, 56, 560 });

		return list;
	}

	// returns an UnorderedList with several Character elements
	private static UnorderedList<Character> unorderedCharacterList1() {
		UnorderedList<Character> list = new UnorderedList<Character>(
				charComparator);

		initList(list, new Character[] { 'b', 'i', 'l', 'l' });

		return list;
	}

	// returns an UnorderedList with several Character elements
	private static UnorderedList<Character> unorderedCharacterList2() {
		UnorderedList<Character> list = new UnorderedList<Character>(
				charComparator);

		initList(list, new Character[] { 'b', 'i', 'l', 'l', 'y' });

		return list;
	}

	// returns an OrderedList with several Integer elements
	private static OrderedList<Integer> orderedStudentList1() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		initList(list, new Integer[] { 10, 24, 78, 5, 78, 56 });

		return list;
	}

	// returns an OrderedList with several Integer elements
	private static OrderedList<Integer> orderedStudentList2() {
		OrderedList<Integer> list = new OrderedList<Integer>(intComparator);

		initList(list, new Integer[] { 5, 10, 78, 5, 56, 56 });

		return list;
	}
}
