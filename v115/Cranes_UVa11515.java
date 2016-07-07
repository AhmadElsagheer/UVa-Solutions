package v115;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Cranes_UVa11515 {

	static Crane[] cranes;
	static int N;
	
	static int maxArea(int idx, int used)
	{
		if(idx == N)
			return 0;
		
		int take = 0, leave = maxArea(idx + 1, used);
		Crane c = cranes[idx];
		boolean can = true;
		for(int i = 0; can && i < N; ++i)
			if((used & 1<<i) != 0 && cranes[i].intersect(c))
				can = false;
		if(can)
			take = c.r * c.r + maxArea(idx + 1, used | 1<<idx);
		return Math.max(take, leave);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			cranes = new Crane[N];
			for(int i = 0; i < N; ++i)
				cranes[i] = new Crane(sc.nextInt(), sc.nextInt(), sc.nextInt());
			out.println(maxArea(0, 0));
		}
		out.flush();
		out.close();
	}
	
	static class Crane
	{
		int x, y, r;
		
		Crane(int a, int b, int c) { x = a; y = b; r = c; }
		
		boolean intersect(Crane c)
		{
			return dist2(c) <= sq(r + c.r);
		}
		
		int dist2(Crane c) { return sq(x - c.x) + sq(y - c.y); }
		
		int sq(int x) { return x * x; }
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