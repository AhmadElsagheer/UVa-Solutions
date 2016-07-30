package v012;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;


public class TaxiCabScheme_UVa1201 {
	
	static ArrayList<Integer>[] adjList;
	static int N, match[];
	static BitSet vis;
	
	static int maxMatches()
	{
		int matches = 0;
		match = new int[N];
		Arrays.fill(match, -1);
		for(int i = 0; i < N; ++i)
		{
			vis = new BitSet(N);
			matches += aug(i);
		}
		return matches;
	}
	
	static int aug(int u)
	{
		vis.set(u);
		for(int v: adjList[u])
			if(match[v] == -1 || !vis.get(match[v]) && aug(match[v]) == 1)
			{
				match[v] = u;
				return 1;
			}
		return 0;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			Booking[] bookings = new Booking[N];
			for(int i = 0; i < N; ++i)
				bookings[i] = new Booking(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					if(i != j && bookings[i].canReach(bookings[j]))
						adjList[i].add(j);
			
			out.println(N - maxMatches());
		}
		out.flush();
		out.close();
	}
	
	static class Booking
	{
		int t1, t2, x1, x2, y1, y2;
		
		Booking(String s, int a, int b, int c, int d)
		{
			x1 = a; y1 = b; x2 = c; y2 = d;
			t1 = Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3));
			t2 = Math.abs(x1 - x2) + Math.abs(y1 - y2) + t1;
			
		}
		
		boolean canReach(Booking b)
		{
			return t2 + Math.abs(x2 - b.x1) + Math.abs(y2 - b.y1) < b.t1;
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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