package v105;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HelpingFillBates_UVa10567 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		String line = sc.next();
		int n = line.length();
		ArrayList<Integer>[] f = new ArrayList[60];
		for(int i = 0; i < 60; ++i)
			f[i] = new ArrayList<Integer>();
		for(int i = 0; i < n; ++i)
			f[line.charAt(i) - 'A'].add(i);
		int q = sc.nextInt();
		while(q-->0)
		{
			line = sc.next();
			boolean found = true;
			int x = -1, y = -1;
			for(int i = 0; i < line.length() && found; ++i)
			{
				int idx = bs(f[line.charAt(i) - 'A'], y);
				if(idx == -1)
					found = false;
				else
				{
					if(x == -1)
						x = idx;
					y = idx;
				}
			}
			if(found)
				out.format("Matched %d %d\n", x, y);
			else
				out.format("Not matched\n");
		}
		out.flush();
	}
	
	static int bs(ArrayList<Integer> x, int lowerbound)
	{
		int ans = -1, lo = 0, hi = x.size() - 1;
		while(lo <= hi)
		{
			int mid = (hi - lo) / 2 + lo;
			if(x.get(mid) > lowerbound)
			{
				ans = x.get(mid);
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
