package cp2_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map.Entry;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class EntropyTextAnalyzer_UVa860 {
	
	static final String reg = ("\\,|\\.|\\:|\\;|\\!|\\?|\\\"|\\(|\\)");
	static final double ln10 = Math.log(10);
	
	static TreeMap<String, Integer> f;
	static int count;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		String word;
		
		while(!(word = sc.next()).equals("****END_OF_INPUT****"))
		{
			f = new TreeMap<String, Integer>();
			count = 0;
			String[] pp = word.split(reg);
			
			addWord(pp);
			while(!(word = sc.next()).equals("****END_OF_TEXT****"))
			{
				pp = word.split(reg);
				addWord(pp);
			}
			double Et = 0, logCount = Math.log(count) / ln10;
			for(Entry<String, Integer> e : f.entrySet())
			{
				int ff = e.getValue();
				Et += ff * (logCount - Math.log(ff) / ln10);
			}
			Et /= count;
			out.format("%d %.1f %d\n", count, Et, Math.round(Et / logCount * 100.0));
		}
		out.flush();

	}
	
	static void addWord(String[] x)
	{
		for(int i = 0; i < x.length; ++i)
			addWord(x[i]);
	}
	static void addWord(String x)
	{
		if(x.isEmpty())
			return;
		count++;
		x = x.toLowerCase();
		Integer d = f.get(x);
		if(d == null)
			d = 0;
		f.put(x, d + 1);
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
