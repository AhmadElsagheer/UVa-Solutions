package v115;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class SayGoodbyeToTicTacToe_UVa11534 {

	static int[][][] g;
	
	static int mex(int n, int l, int r)
	{
		if(n == 0)
			return 0;
		if(g[l][r][n] != -1)
			return g[l][r][n];
		TreeSet<Integer> k = new TreeSet<Integer>();
		for(int i = 0; i < n; ++i)
		{
			if((i != 0 || l != 0) && (i != n - 1 || r != 0))
			{
				int g1 = mex(i, l, 0), g2 = mex(n - i - 1, 0, r);
				k.add(g1 ^ g2);
			}
			
			if((i != 0 || l != 1) && (i != n - 1 || r != 1))
			{
				int g1 = mex(i, l, 1), g2 = mex(n - i - 1, 1, r);
				k.add(g1 ^ g2);
			}
		}
		int ret = 0;
		while(k.contains(ret))
			++ret;
		return g[l][r][n] = ret;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		g = new int[3][3][101];
		for(int i = 0; i < 3; ++i)
			for(int j = 0; j < 3; ++j)
				Arrays.fill(g[i][j], -1);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[] s = sc.next().toCharArray();
			int l = 2, ans = 0, len = 0, alice = 1;
			for(int i = 0; i < s.length; ++i)
				if(s[i] == '.')
					++len;
				else
				{
					int r = s[i] == 'X' ? 1 : 0;
					ans ^= mex(len, l, r);
					l = r;
					len = 0;
					alice ^= 1;
				}
			ans ^= mex(len, l, 2);
			out.println(alice == 1 && ans != 0 || alice == 0 && ans == 0 ? "Possible." : "Impossible.");
		}
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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

		public boolean hasNext() throws IOException
		{
			while(br.ready() && (st == null || !st.hasMoreTokens()))
				st = new StringTokenizer(br.readLine());
			return st.hasMoreTokens();
		}


	}
}