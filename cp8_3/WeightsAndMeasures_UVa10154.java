package cp8_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class WeightsAndMeasures_UVa10154 {


	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
	
		ArrayList<Turtle> turtles = new ArrayList<Turtle>(5607);

		while(sc.ready())
		{
			int w = sc.nextInt(), s = sc.nextInt();
			if(s >= w)
				turtles.add(new Turtle(w, s));
		}
		Collections.sort(turtles);
		
		
		int[] dp = new int[5608];		//dp[j] => the smallest weight for a stack of j turtles
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		int maxH = 0;
		for(int i = 0; i < turtles.size(); i++)
		{
			Turtle t = turtles.get(i);
			for(int j = maxH; j >= 0; j--)
				if(t.w + dp[j] <= t.s && t.w + dp[j] < dp[j+1])
				{
					dp[j+1] = t.w + dp[j];
					maxH = Math.max(maxH, j + 1);
				}
		}
		System.out.println(maxH);
	}
	
	static class Turtle implements Comparable<Turtle>
	{
		int w, s;
		
		Turtle(int x, int y) { w = x; s = y; }
		
		public int compareTo(Turtle t)
		{
			if(s != t.s) return s - t.s;
			return w - t.w;
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
