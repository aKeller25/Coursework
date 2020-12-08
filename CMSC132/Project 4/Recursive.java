package recursion;

import java.util.ArrayList;
import java.util.List;

public class Recursive {

	public static <T extends Comparable<T>> boolean hasNoneSmaller(
			List<T> list, T elt) {
		return hasNoneSmallerAux(list, elt, 0);
	}

	private static <T extends Comparable<T>> boolean hasNoneSmallerAux(
			List<T> list, T elt, int index) {
		return (index == list.size())
				|| ((elt.compareTo(list.get(index)) > 0) ? false
						: hasNoneSmallerAux(list, elt, index + 1));
	}

	public static <T> int lastIdxOf(List<T> list, T element) {
		return lastIdxOfAux(list, element, list.size() - 1);
	}

	private static <T> int lastIdxOfAux(List<T> list, T element, int index) {
		return  (list.size() == 0 || (index < 0)) ? -1 : 
			list.get(index).equals(element) ? 
					index : lastIdxOfAux(list, element, index - 1);
	}

	public static <T> List<T> insertAfter(List<T> list, T elt, T newElt) {
		List<T> inserted = new ArrayList<T>();
		return insertAfterAux(list, elt, newElt, inserted, 0);
	}

	private static <T> List<T> insertAfterAux(List<T> list, T elt, T newElt,
			List<T> inserted, int index) {
		if (list.size() != 0 ){
			inserted.add(list.get(index));
		}
		if (list.size() != 0 && list.get(index).equals(elt)) {
			inserted.add(newElt);
		}
		return (index + 1 >= list.size()) ? inserted : insertAfterAux(list,
				elt, newElt, inserted, index + 1);
	}

	public static <T> List<T> removeNumTimes(List<T> list, T elt, int num) {
		List<T> removed = new ArrayList<T>();
		return removeNumTimesAux(list, elt, num, removed, 0);
	}

	private static <T> List<T> removeNumTimesAux(List<T> list, T elt, int num,
			List<T> removed, int index) {

		if (list.size() > 0){
			if (list.get(index).equals(elt) && num > 0) {
				num--;
			} else { removed.add(list.get(index));}
		}

		return (index + 1 >= list.size()) ? removed : removeNumTimesAux(list,
				elt, num, removed, index + 1);
	}
}
