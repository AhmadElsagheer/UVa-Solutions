package v104;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
 
 
public class ListofConquests_UVa10420 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int n = sc.nextInt();
		TreeMap<String, TreeSet<String>> cnts = new TreeMap<>();
		while(n-->0)
		{
			String s = sc.nextLine();
			int idx = 0;
			while(s.charAt(idx) != ' ')
				++idx;
			String country = s.substring(0, idx), woman = s.substring(idx + 1);
			TreeSet<String> woms = cnts.get(country);
			if(woms == null)
				cnts.put(country, woms = new TreeSet<>());
			woms.add(woman);
		}
		for(String s: cnts.keySet())
			out.printf("%s %d\n", s, cnts.get(s).size());
		out.flush();
		out.close();
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
 
		public double nextDouble() throws IOException { return Double.parseDouble(next()); }
 
		public boolean ready() throws IOException {return br.ready();} 
	}
} 