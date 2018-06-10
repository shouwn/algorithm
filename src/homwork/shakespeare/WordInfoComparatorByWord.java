package homwork.shakespeare;

import java.util.Comparator;

public class WordInfoComparatorByWord implements Comparator<WordInfo>{

	@Override
	public int compare(WordInfo o1, WordInfo o2) {
		return o1.getWord().compareTo(o2.getWord());
	}
}