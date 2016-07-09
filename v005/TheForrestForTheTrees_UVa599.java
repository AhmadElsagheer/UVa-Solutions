package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TheForrestForTheTrees_UVa599 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			boolean[] hasEdge = new boolean[26];
			int E = 0;
			while(true)
			{
				String s = sc.next();
				if(s.charAt(0) == '*')
					break;
				int x = s.charAt(1) - 'A', y = s.charAt(3) - 'A';
				hasEdge[x] = hasEdge[y] = true;
				++E;
			}
			StringTokenizer st = new StringTokenizer(sc.nextLine(), ",");
			int V = st.countTokens();
			
			int c = 0;
			for(boolean b: hasEdge)
				if(b)
					++c;
			
			int a = V - c, t = V - E - a;		//connected components = V - E
			out.printf("There are %d tree(s) and %d acorn(s).\n", t, a);
		}
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