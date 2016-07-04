package v115;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Puzzle9_UVa11513 {

	static long target, MSK1 = 15, MSK2 = ((long)1<<12) - 1, MSK3 = 15 | (15<<12) | (15<<24);  
	static TreeMap<Vertex, Vertex> parent;
	static TreeMap<Vertex, Integer> move;
	static TreeMap<Vertex, Integer> dist;
	static String[] mm = new String[]{"H1", "H2", "H3", "V1", "V2", "V3"};
	
	static void ct()
	{
		for(int i = 1, j = 0; i <= 9; ++i, j += 4)
			target |= (long)i<<j;
	}
	static void bfs(Vertex s)
	{
		parent = new TreeMap<Vertex, Vertex>();
		move = new TreeMap<Vertex, Integer>();
		Queue<Vertex> q = new LinkedList<Vertex>();
		dist = new TreeMap<Vertex, Integer>();
		q.add(s);
		dist.put(s, 0);
	
		while(!q.isEmpty())
		{
			Vertex cur = q.remove();

			Integer d = dist.get(cur);
			
			for(int r = 2; r >= 0; --r)
			{
				int shift = r * 4;
				long c = ((MSK1<<shift+24) & cur.msk)>>shift+24, msk2 = cur.msk & ~(MSK3<<shift);
				long a = ((MSK1<<shift) & cur.msk)>>shift, b = ((MSK1<<shift+12) & cur.msk)>>shift+12;
				msk2 |= (c<<shift) | (a<<shift + 12) | (b<<shift + 24) ;
				Vertex nxt = new Vertex(msk2);
				if(dist.get(nxt) == null)
				{
					dist.put(nxt, d + 1);
					q.add(nxt);
					parent.put(nxt, cur);
					move.put(nxt, r + 3);
				}

			}
			for(int r = 2; r >= 0; --r)
			{
				int shift = r * 12;
				long c = ((MSK1<<shift+8) & cur.msk)>>shift+8, msk2 = cur.msk & ~(MSK2<<shift);
				long a = ((MSK1<<shift) & cur.msk)>>shift, b = ((MSK1<<shift+4) & cur.msk)>>shift+4;
				msk2 |= (b<<shift) | (c<<shift + 4) | (a<<shift + 8) ;
				Vertex nxt = new Vertex(msk2);
				if(dist.get(nxt) == null)
				{
					dist.put(nxt, d + 1);
					q.add(nxt);
					parent.put(nxt, cur);
					move.put(nxt, r);
				}
			}
		}

	}
	
	
	public static void main(String[] args) throws IOException {
		ct();
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		bfs(new Vertex(target));
		
		while(true)
		{
			long msk = sc.nextLong();
			if(msk == 0)
				break;
			for(int i = 1; i < 9; ++i)
				msk |= sc.nextLong()<<(i<<2);
			Vertex t = new Vertex(msk);
			Integer min = dist.get(t);
			if(min == null)
				out.println("Not solvable");
			else
			{
				StringBuilder sb = new StringBuilder();
				
				Vertex v = t;
				while(true)
				{
					Vertex u = parent.get(v);
					if(u == null)
						break;
					sb.append(mm[move.get(v)]);
					v = u;
				}
				out.format("%d %s\n", min, sb.toString());
			}
		}
		out.flush();

	}
	
	
	static class Vertex implements Comparable<Vertex>
	{
		long msk;
		
		Vertex(long x) { msk = x; }
		
		public int compareTo(Vertex v)
		{
			if(msk != v.msk)
				return msk > v.msk ? 1 : -1;
			return 0;
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
