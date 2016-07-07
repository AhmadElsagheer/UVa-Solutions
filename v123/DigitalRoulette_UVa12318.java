package v123;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DigitalRoulette_UVa12318 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(true)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			if(n == 0 && m == 0)
				break;
			int k = sc.nextInt(), c[] = new int[k + 1];
			for(int i = 0; i <= k; ++i)
				c[i] = sc.nextInt();
			TreeSet<Integer> vis = new TreeSet<Integer>();
			while(m >= 0)
				vis.add(evaluate(c, k, m--, n + 1));
			out.println(vis.size());
		}
		out.flush();
		out.close();
	}
	
	static int evaluate(int[] c, int k, int x, int n)
	{
		int res = 0;
		while(k >= 0)
			res = (int) ((c[k--] + 1l * res * x) % n);
		return res;
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