package v104;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
 
//Solvable with BigInteger
public class IfWeWereAChildAgain_UVa10494 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			String s = sc.next(), o = sc.next();
			divideAndMod(s, sc.nextInt());
			if(o.equals("/"))
				out.println(d);
			else
				out.println(r);
		}
		out.flush();
		out.close();
	}
	
	static int r;
	static StringBuilder d;
	
	static void divideAndMod(String x, int y)
	{
		d = new StringBuilder();
		long curX = 0;
		for(int i = 0; i < x.length(); ++i)
		{
			curX = curX * 10 + x.charAt(i) - '0';
			int dd = (int) (curX / y);
			curX -= 1l * dd * y;
			if(dd != 0 || d.length() != 0)
				d.append(dd);
		}
		r = (int)curX;
		if(d.length() == 0)
			d.append(0);
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;
 
		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}
		
		public Scanner(FileReader s){	br = new BufferedReader(s);}
 
		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
 
		public int nextInt() throws IOException {return Integer.parseInt(next());}
 
		public long nextLong() throws IOException {return Long.parseLong(next());}
 
		public String nextLine() throws IOException {return br.readLine();}
 
		public double nextDouble() throws IOException
		{
			return Double.parseDouble(next());
		}
 
		public boolean ready() throws IOException {return br.ready();}
 
 
	}
} 