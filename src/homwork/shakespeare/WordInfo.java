package homwork.shakespeare;


public class WordInfo{
	String word;
	int count;

	public WordInfo(String word) {
		this.word = word;
		this.count = 1;
	}
	
	public WordInfo(String word, int count) {
		this.word = word;
		this.count = count;
	}
	
	public void setWord(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public WordInfo getCopy() {
		WordInfo info = new WordInfo(word);
		info.setCount(this.count);
		return info;
	}

	public void countUp() {
		count++;
	}

	public int getCount() {
		return count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordInfo other = (WordInfo) obj;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + word + ", " + count + ")";
	}
}