package v100;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Tellmethefrequencies_UVa10062 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		boolean first = true;
		
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			Letter[] ch = new Letter[256];
			for(int i = 0; i < 256; ++i)
				ch[i] = new Letter(i);
			char[] s = sc.nextLine().toCharArray();
			for(char c: s)
				ch[c].freq++;
			Arrays.sort(ch);
			for(int i = 0; i < 256; ++i)
				if(ch[i].freq != 0)
					out.println(ch[i].idx + " " + ch[i].freq);
		}
		
		out.flush();
		out.close();
	}
	
	static class Letter implements Comparable<Letter>
	{
		int idx, freq;
		
		Letter(int a) { idx = a; }
		
		public int compareTo(Letter l)
		{
			if(freq != l.freq)
				return freq - l.freq;
			return l.idx - idx;
		}
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