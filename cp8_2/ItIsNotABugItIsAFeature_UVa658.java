package cp8_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ItIsNotABugItIsAFeature_UVa658 {
	
	static int[][] patch_cond, fix_bug;
	static int N, M, time[];
	
	static int dijkstra()
	{
		Vertex s = new Vertex((1<<N) - 1);
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
		TreeMap<Vertex, Integer> dist = new TreeMap<Vertex, Integer>();
		pq.add(new Edge(s, 0));
		dist.put(s, 0);
		while(!pq.isEmpty())
		{
			Edge cur = pq.remove();
			if(cur.to.msk == 0)
				return cur.cost;
			for(int i = 0; i < M; ++i)
				if((patch_cond[i][1] & cur.to.msk) == patch_cond[i][1] && (patch_cond[i][0] & cur.to.msk) == 0)
				{
					Vertex nxt = new Vertex(cur.to.msk & ~(fix_bug[i][0]) | fix_bug[i][1]);
					Integer d = dist.get(nxt);
					if(d == null || d > time[i] + cur.cost)
					{
						d = cur.cost + time[i];
						dist.put(nxt, d);
						pq.add(new Edge(nxt, d));
					}
				}
			
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = 1;
		while(true)
		{
			N = sc.nextInt(); M = sc.nextInt();
			if(N == 0)
				break;
			patch_cond = new int[M][2];
			fix_bug = new int[M][2];
			time = new int[M];
			for(int i = 0; i< M; ++i)
			{
				time[i] = sc.nextInt();
				String x = sc.next();
				for(int j = 0; j < N; ++j)
					if(x.charAt(j) == '+')
						patch_cond[i][1] |= 1<<j;
					else
						if(x.charAt(j) == '-')
							patch_cond[i][0] |= 1<<j;
				x = sc.next();
				for(int j = 0; j < N; ++j)
					if(x.charAt(j) == '+')
						fix_bug[i][1] |= 1<<j;
					else
						if(x.charAt(j) == '-')
							fix_bug[i][0] |= 1<<j;
			}
			int ans = dijkstra();
			if(ans != -1)
				out.format("Product %d\nFastest sequence takes %d seconds.\n\n", tc++, ans);
			else
				out.format("Product %d\nBugs cannot be fixed.\n\n", tc++, ans);
			
			
		}
		out.flush();

	}
	
	
	static class Vertex implements Comparable<Vertex>
	{
		int msk;
		
		Vertex(int x) { msk = x; }
		
		public int compareTo(Vertex v) { return msk - v.msk; }
	}
	
	static class Edge implements Comparable<Edge>
	{
		Vertex to;
		int cost;
		
		Edge(Vertex x, int y) { to = x; cost = y; }
		
		public int compareTo(Edge e) { return cost - e.cost; }
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
