package v113;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class NestedDolls_UVa11368 {

	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			Doll[] dolls = new Doll[n];
			for(int i = 0; i < n; i++) dolls[i] = new Doll(sc.nextInt(), sc.nextInt());
			Arrays.sort(dolls);
			
			ArrayList<Integer> taken = new ArrayList<Integer>(n);
			for(int i = 0; i < n; i++)
			{
				int idx = bs(taken, dolls[i].w);
				if(idx == taken.size())
					taken.add(dolls[i].w);
				else
					taken.set(idx, dolls[i].w);
			}
			out.println(taken.size());
		}
		out.flush();

	}
	
	static int bs(ArrayList<Integer> taken, int w)
	{
		int ans = taken.size(), lo = 0, hi = taken.size() - 1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(taken.get(mid) > w)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ans;
	}
	
	static class Doll implements Comparable<Doll>
	{
		int w, h;
		
		Doll(int x, int y) { w = x; h = y; }
		
		public int compareTo(Doll d) 
		{
			if(h != d.h) return d.h - h;
			return w - d.w;
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

		int countTokens() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens();
		}
	}
}
