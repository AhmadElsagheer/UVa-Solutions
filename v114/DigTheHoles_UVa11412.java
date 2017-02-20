package v114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DigTheHoles_UVa11412 {

	static char[][] s = new char[2][];
	static int[][] n = new int[2][2];
	static boolean[] used;
	static char[] chars = new char[]{'R', 'G', 'B', 'Y', 'O', 'V'};
	static boolean generate(int idx, char[] test)
	{
		if(idx == 4)
			return possible(test);
		for(char c: chars)
			if(!used[c - 'A'])
			{
				used[c - 'A'] = true;
				test[idx] = c;
				if(generate(idx + 1, test))
					return true;
				used[c - 'A'] = false;
			}
		return false;
	}
	
	static boolean possible(char[] test)
	{
		for(int i = 0; i < 2; ++i)
		{	
			int c1 = 0, c2 = 0;
			for(int j = 0; j < 4; ++j)
				if(test[j] == s[i][j])
					++c1;
				else
					if(used[s[i][j] - 'A'])
						++c2;
			if(c1 != n[i][0] || c2 != n[i][1])
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			for(int i = 0; i < 2; ++i)
			{
				s[i] = sc.next().toCharArray();
				n[i][0] = sc.nextInt();
				n[i][1] = sc.nextInt();
			}
			
			used = new boolean[26];
			if(generate(0, new char[4]))
				out.println("Possible");
			else
				out.println("Cheat");
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
		
		public double nextDouble() throws IOException
		{
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
