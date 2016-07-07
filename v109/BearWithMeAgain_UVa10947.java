package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BearWithMeAgain_UVa10947 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int maxDist = sc.nextInt() * sc.nextInt();
			Island s = new Island(new Point(sc.nextInt(), sc.nextInt()), sc.nextInt());
			Island t = new Island(new Point(sc.nextInt(), sc.nextInt()), sc.nextInt());
			int N = sc.nextInt() + 2;
			Island[] islands = new Island[N];
			for(int i = 0; i < N - 2; ++i)
				islands[i] = new Island(new Point(sc.nextInt(), sc.nextInt()), sc.nextInt());
			islands[N-2] = s;
			islands[N-1] = t;
			
			// following part can be replaced with DSU for faster connectivity check
			boolean[][] adjMat = new boolean[N][N];
			for(int i = 0; i < N; ++i)
				for(int j = i + 1; j < N; ++j)
					if(islands[i].canReach(islands[j], maxDist))
						adjMat[i][j] = adjMat[j][i] = true;
			
			for(int k = 0; k < N; ++k)
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						adjMat[i][j] |= adjMat[i][k] && adjMat[k][j];
			
			if(adjMat[N-2][N-1])
				out.println("Larry and Ryan will escape!");
			else
				out.println("Larry and Ryan will be eaten to death.");
		}
		
		out.flush();
		out.close();
	}
	
	static class Point
	{
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
		
		double dist(Point p) { return Math.sqrt(sq(x - p.x) + sq(y - p.y)); }
		
		int sq(int x) { return x * x; }
	}
	
	static class Island
	{
		Point c;
		int r;
		
		Island(Point a, int b) { c = a; r = b; }
		
		boolean canReach(Island s, int maxDist)	{ return c.dist(s.c) - (r + s.r) < maxDist + EPS; }
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