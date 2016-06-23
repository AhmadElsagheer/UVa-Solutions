package v006;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class DNASorting_UVa612 {
	
	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt(), m = sc.nextInt();
			Pair[] seq = new Pair[m];
			for(int i = 0; i < m; ++i)
			{
				String s = sc.next();
				seq[i] = new Pair(s, compute(n, s.toCharArray()), i);
			}
			Arrays.sort(seq);
			for(int i = 0; i < m; ++i)
				out.println(seq[i].s);
			if(tc != 0)
				out.println();
		}		
		
		out.flush();
		out.close();
	}
	
	static int compute(int n, char[] s)
	{
		int count = 0;;
		for(int i = 0; i < n; ++i)
			for(int j = i + 1; j < n; ++j)
				if(s[i] > s[j])
					++count;
		return count;
	}
	
	static class Pair implements Comparable<Pair>
	{
		String s;
		int inv, idx;
		
		Pair(String a, int b, int c)
		{
			s = a;
			inv = b;
			idx = c;
		}
		
		public int compareTo(Pair p)
		{
			if(inv != p.inv)
				return inv - p.inv;
			return idx - p.idx;
		}
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