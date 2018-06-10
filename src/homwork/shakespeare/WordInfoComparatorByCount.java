package homwork.shakespeare;

import java.util.Comparator;

public class WordInfoComparatorByCount implements Comparator<WordInfo>{

	@Override
	public int compare(WordInfo o1, WordInfo o2) {
		return o1.getCount() - o2.getCount();
	}
}