import java.util.ArrayList;
import java.util.List;

public class Pair {

	private Tokenize a,b;
	
	private List<String> tokens;
	
	double distance = 0.0D;
	
	public Pair(Tokenize a, Tokenize b) {
		this.a = a;
		this.b = b;
		tokens = new ArrayList<String>();
		for(String s : a.getTokens()) {
			if(!tokens.contains(s))
				tokens.add(s);
		}
		for(String s : b.getTokens()) {
			if(!tokens.contains(s))
				tokens.add(s);
		}
		int va[] = new int[tokens.size()];
		int vb[] = new int[tokens.size()];
		
		for(int i = 0; i < tokens.size(); i++) {
			va[i] = a.getTokenCount(tokens.get(i));
			vb[i] = b.getTokenCount(tokens.get(i));
		}
			
		/*for(int i = 0; i < va.length; i++) 
			if(va[i] > 0 && vb[i] > 0)
				System.out.println(va[i] + " " + tokens.get(i));*/
		
		distance = CosineSimilarity.similarity(va, vb);
	}
	
	public Pair(String a, String b) {
		this(new Tokenize(a), new Tokenize(b));
	}
	
	public Pair(String a, Tokenize b) {
		this(new Tokenize(a), b);
	}
	
	public double getSimilarity() {
		return distance;
	}
	
}
