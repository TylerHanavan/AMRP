
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSV {

    public static void main(String[] args) {

        String csvFile = "small.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        

		String in = "";
		for(String s : args) {
			in += s + " ";
		}
		Film top = null;
		Film two = null;
		Film three = null;

        try {
        	
        	long l = new Date().getTime();

            br = new BufferedReader(new FileReader("/var/plot/" + csvFile));
            int i = 0;
            
            String s = "";
            String name = "";
            String genre = "";
            Tokenize p = null;
            Pair pair1 = null;
            
            while ((line = br.readLine()) != null) {
            	
            	if(line.length() == 0) continue;
            	
            	if(newPlot(line)) { // Process plot & begin new plot
            		
        			p = new Tokenize(s);
        			pair1 = new Pair(in, p);
        			
        			double distance = pair1.getSimilarity();
        			
        			boolean b1 = false;
        			
        			if(top == null || distance < top.getDistance()) {
        				b1 = true;
        				three = two;
        				two = top;
        				top = new Film(name, genre, distance);
        			}
        			
        			if(!b1 && (two == null || distance < two.getDistance())) {
        				b1 = true;
        				three = two;
        				two = new Film(name, genre, distance);
        			}
        			
        			if(!b1 && (three == null || distance < three.getDistance())) {
        				b1 = true;
        				three = new Film(name, genre, distance);
        			}
                    
                    ++i;
            		
            		s = "";

                    // use comma as separator
                    String[] data = line.split(cvsSplitBy);
                    
                    name = data[1];
                    genre = data[2];
                    
                    for(int j = 2; j < data.length; j++)
                    	s += data[j];
                    
                    if(s.replaceAll(" ", "").equals("")) continue;
            		
            	} else { // Plot in progress
            		
            		s += line;
            	}
                
                if(i > 18064) break;

            }
            
            System.out.println("It took: " + (new Date().getTime() - l) + "ms to process that text");
            
    		System.out.println("Title          |       Genre        | Distance");
    		System.out.println(top.getTitle() + " | " + top.getGenre() + " | " + top.getDistance());
    		System.out.println(two.getTitle() + " | " + two.getGenre() + " | " + two.getDistance());
    		System.out.println(three.getTitle() + " | " + three.getGenre() + " | " + three.getDistance());
    		

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    
    public enum Genre {
    	HORROR("horror scary"),
    	THRILLER("thiller"),
    	COMEDY("comedy funny"),
    	FAMILY("family"),
    	ACTION("action adventure"),
    	ROMANCE("romance romantic love"),
    	DOCUMENTARY("documentary biography");
    	
    	String s;

    	Genre(String s) {
    		this.s = s;
    	}
    	
    	public String getNames() {
    		return s;
    	}
    	
    	static Genre[] getGenres(String in) {
    		Genre[] g = new Genre[3];
    		int i = 0;
    		for(String s : in.split(" ")) {
    			for(Genre genre : Genre.values()) {
    				if(genre.getNames().toLowerCase().contains(s.toLowerCase())) {
    					g[i++] = genre;
    				}
    			}
    		}
    		return g;
    	}
    	
    	static int getGenresSize(String in) {
    		return getGenres(in).length;
    	}
    	
    }
    
    public static boolean newPlot(String s) {
    	if(s.length() == 0) return false;
    	char c = s.charAt(0);
    	return (c == '1' || c == '2') && s.charAt(4) == ',';
    }

}