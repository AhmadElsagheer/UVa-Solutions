package cp4_7;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WenterimBackpackingTrip_UVa907 {

	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int n= sc.nextInt(), k = sc.nextInt(), maxDistance = 0, sum = 0;
			int[] dist = new int[n + 1];
			for(int i = 0; i < n + 1; ++i)
			{
				maxDistance = Math.max(maxDistance, dist[i] = sc.nextInt());
				sum += dist[i];
			}
			int ans = -1, lo = maxDistance, hi = sum;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;
				if(possible(dist, mid, k))
				{
					ans = mid;
					hi = mid - 1;
				}
				else
					lo = mid + 1;
			}
			out.println(ans);
			
		}
		out.flush();
		
	}
	
	static boolean possible(int[] dist, int max, int k)
	{
		for(int i = 0, d = 0; i < dist.length; ++i)
			if(d + dist[i] > max)
				if(k-- == 0)
					return false;
				else
					d = dist[i];
			else
				d += dist[i];
		return true;
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

		public boolean nxtEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}
		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
