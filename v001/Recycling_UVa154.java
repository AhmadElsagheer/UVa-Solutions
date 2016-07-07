package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Recycling_UVa154 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			String s = sc.nextLine();
			if(s.equals("#"))
				break;
			ArrayList<char[]> als = new ArrayList<char[]>();
			while(s.charAt(0) != 'e')
			{
				StringTokenizer st = new StringTokenizer(s, ",");
				char[] a = new char[26];
				for(int i = 0; i < 5; ++i)
				{
					String t = st.nextToken();
					a[t.charAt(0)-'a'] = t.charAt(2);
				}
				als.add(a);
				s = sc.nextLine();
			}
			
			int[] b = new int[als.size()];
			for(int i = 0; i < b.length; ++i)
				for(int j = i + 1; j < b.length; ++j)
				{
					int x = mismatches(als.get(i), als.get(j));
					b[i] += x;
					b[j] += x;
				}
			int ans = 0;
			for(int i = 1; i < b.length; ++i)
				if(b[i] < b[ans])
					ans = i;
			out.println(ans + 1);
			
		}
		out.flush();
		out.close();
	}
	
	static int mismatches(char[] x, char[] y)
	{
		int ans = 0;
		for(int i = 0; i < 26; ++i)
			if(x[i] != y[i])
				++ans;
		return ans;
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