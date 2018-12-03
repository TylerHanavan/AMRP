import java.util.ArrayList;
import java.util.List;

public class Tokenize {

	private String sentence;
	private List<String> tokens = null;
	private int[] counts = null;
	
	public Tokenize(String sentence) {
		sentence = filter(sentence.toLowerCase());
		this.sentence = sentence;
		this.tokens = new ArrayList<String>();
		for(String s : sentence.split(" ")) {
			if(!this.tokens.contains(s))
				this.tokens.add(s);
		}
		counts = new int[this.tokens.size()];
		for(String s : sentence.split(" "))
			counts[tokens.indexOf(s)]++;
	}
	
	public int getTokenCount(String s) {
		if(tokens.indexOf(s) == -1)
			return 0;
		return counts[tokens.indexOf(s)];
	}
	
	public List<String> getTokens() {
		return tokens;
	}
	
	public String getSentence() {
		return sentence;
	}
	
	private static String[] rem = { " A ", " The ", " In ", " This ", " That ", " There ", " An ", " Does ", " Do " };
	
	private static String filter(String toFilter) {
		String ret = toFilter;
		for(String s : rem) {
			ret = ret.replaceAll(s, " ");
			ret = ret.replaceAll(s.toLowerCase(), " ");
		}
		return ret;
	}
	
}
