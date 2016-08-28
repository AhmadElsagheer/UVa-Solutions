package v105;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HowManyCalls_UVa10518 {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt(), w = sc.nextInt(), v = sc.nextInt(), u = sc.nextInt();
		Point[] bus = new Point[n];
		for(int i = 0; i < n; ++i)
			bus[i] = new Point(sc.nextInt(), sc.nextInt());
		int count = 0;
		for(int i = 0; i < n; ++i)
		{
			double tBus = 1.0 * bus[i].x / v;
			double tPer = 1.0 * bus[i].y / u;
			if(tPer < tBus + EPS)
				++count;
		}
		if(count == n || count == 0)
			out.println(w * 1.0 / u);
		else
		{
			int idx = 0;
			for(int i = 1; i < n; ++i)
				if(bus[i].y < bus[idx].y || bus[i].y == bus[idx].y && bus[i].x > bus[idx].x)
					idx = i;
			double t = 0;
			int y = 0;
			for(int i = 0; i < n; ++i)
			{
				Point p = bus[(idx + i)%n];
				if(p.y < y)
					break;
				t += Math.max(1.0 * p.x / v - t, 1.0 * (p.y - y) / u);
				y = p.y;
			}
			out.println(t + (w - y) * 1.0 / u);
		}
		out.flush();
		out.close();
	}
	
	static class Point
	{	
		int x, y;
		
		Point(int a, int b) { x = a; y = b; }
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

		public boolean hasNext() throws IOException
		{
			while(br.ready() && (st == null || !st.hasMoreTokens()))
				st = new StringTokenizer(br.readLine());
			return st.hasMoreTokens();
		}


	}
}