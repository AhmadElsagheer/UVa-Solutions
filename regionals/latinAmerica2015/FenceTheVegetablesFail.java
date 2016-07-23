package regionals.latinAmerica2015;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class FenceTheVegetablesFail {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int P = sc.nextInt(), V = sc.nextInt() + 1;
			int[] Xp = new int[P], Yp = new int[P], Xv = new int[V], Yv = new int[V];
			for(int i = 0; i < P; ++i)
			{
				Xp[i] = sc.nextInt();
				Yp[i] = sc.nextInt();
			}
			
			for(int i = 0; i < V - 1; ++i)
			{
				Xv[i] = sc.nextInt();
				Yv[i] = sc.nextInt();
			}
			
			Xv[V - 1] = Xv[0];
			Yv[V - 1] = Yv[0];
			
			boolean[] ver = inside(P, V, Xp, Yp, Xv, Yv);
			boolean[] hor = inside(P, V, Yp, Xp, Yv, Xv);
			long ans = 0;
			for(int i = 0; i < P; ++i)
				if(!ver[i] || !hor[i])
					ans += i + 1;
			out.println(ans);
		}
		
		out.flush();
		out.close();
	}
	
	static boolean[] inside(int P, int V, int[] Xp, int[] Yp, int[] Xv, int[] Yv)
	{
		TreeSet<Integer> yCoords = new TreeSet<Integer>();
		ArrayList<Segment> segs = new ArrayList<Segment>(V + P);
		for(int i = 0; i < P; ++i)
		{	
			segs.add(new Segment(Xp[i], Yp[i], Yp[i], i));
			yCoords.add(Yp[i]);
		}
		for(int i = 0; i < V - 1; ++i)
			if(Xv[i] == Xv[i + 1])
			{
				segs.add(new Segment(Xv[i], Yv[i], Yv[i + 1], -1));
				yCoords.add(Yv[i]);
				yCoords.add(Yv[i + 1]);
			}
		Collections.sort(segs);
		TreeMap<Integer, Integer> mapYCoords = new TreeMap<Integer, Integer>();
		int n = 0;
		for(int y: yCoords)
			mapYCoords.put(y, ++n);
		FenwickTree ft = new FenwickTree(n);
		boolean[] ret = new boolean[P];
		for(Segment s: segs)
			if(s.y1 == s.y2)
			{
				int seenSegs = ft.query(mapYCoords.get(s.y1));
				ret[s.idx] = (seenSegs & 1) == 1;
			}
			else
			{
				int l = mapYCoords.get(s.y1), r = mapYCoords.get(s.y2);
				ft.increment(l, r);
			}
		return ret;
	}
	
	static class Segment implements Comparable<Segment>
	{
		int idx, x, y1, y2;
		
		Segment(int a, int b, int c, int d) 
		{ 
			x = a; idx = d;
			if(b < c) { y1 = b; y2 = c; }	
			else { y1 = c; y2 = b; } 
			
		}
		
		public int compareTo(Segment s) { return x - s.x; }
	}
	
	static class FenwickTree
	{
		int[] ft;
		
		FenwickTree(int n) { ft = new int[n + 1]; }
		
		void increment(int l, int r) { update(l + 1, 1); update(r + 1, -1); }
		
		void update(int idx, int val) { while(idx < ft.length) { ft[idx] += val; idx += idx & -idx; } }
		
		int query(int idx)
		{ 
			int ret = 0; 
			while(idx > 0) { ret += ft[idx]; idx ^= idx & -idx; } 
			return ret;
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