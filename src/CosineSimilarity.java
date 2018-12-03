
public class CosineSimilarity {
	
	public static int cross(int[] a, int[] b) {
		if(a.length != b.length)
			return -1;
		int j = 0;
		for(int i = 0; i < a.length; i++) {
			j += a[i] * b[i];
		}
		return j;
	}
	
	public static double length(int[] a) {
		int j = 0;
		for(int i = 0; i < a.length; i++) {
			j += a[i] * a[i];
		}
		return Math.sqrt(j);
	}
	
	public static double similarity(int[] a, int[] b) {
		return Math.acos(cross(a, b) / (length(a) * length(b)));
	}
	
}
