package v105;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TheOuroborosProblem_UVa10506 {

	
	public static void main(String[] args) throws IOException 
	{
//		Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileReader("TheTravelingJudgesProblem_UVa1040-large.in"));
		PrintWriter out = new PrintWriter(new FileWriter("problem_B.out"));
//		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			char[] s = sc.next().toCharArray();
			int ans = 0;
			while(!happy(s))
			{
				if(s[0] == '+')
					flip(s, getFirst(s));
				else
					flip(s, getLast(s));
				++ans;
			}
							
			out.printf("Case #%d: %d\n", t, ans);
		}
		out.flush();
		out.close();

	}
	
	static char revert(char x)
	{
		return x == '+' ? '-' : '+';
	}
	
	static void flip(char[] s, int idx)
	{
		for(int i = 0, j = idx; i <= j; ++i, --j)
		{
			char tmp = revert(s[i]);
			s[i] = revert(s[j]);
			s[j] = tmp;
		}
	}
	
	static int getLast(char[] s)
	{
		int i = s.length - 1;
		while(s[i] == '+')
			--i;
		return i;
	}
	
	static int getFirst(char[] s)
	{
		int i = 0;
		while(s[i] == '+')
			++i;
		return i-1;
	}
	
	static boolean happy(char[] s)
	{
		for(char x: s)
			if(x != '+')
				return false;
		return true;
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