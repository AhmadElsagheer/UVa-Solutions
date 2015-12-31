package cp4_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Frogger_UVa534 {

	static ArrayList<Triple>[] adjList;
	static Triple[] edgeList;
	static boolean[] visited;
	static int N;
	
	@SuppressWarnings("unchecked")
	static void mst()
	{
		UnionFind uf = new UnionFind(N);
		adjList = new ArrayList[N];
		for(int i = 0; i < N; i++) adjList[i] = new ArrayList<Triple>();
		Arrays.sort(edgeList);
		
		for(int i = 0; i < edgeList.length; i++)
		{
			Triple cur = edgeList[i];
			
			if(!uf.isSameSet(cur.u, cur.v))
			{
				uf.unionSet(cur.v, cur.u);
				
				adjList[cur.v].add(new Triple(cur.v, cur.u, cur.distance));
				adjList[cur.u].add(new Triple(cur.u, cur.v, cur.distance));
			}
		}
		
	}
	
	static double dfs(int u)
	{
		if(u == 1)
			return 0;
		visited[u] = true;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			Triple nxt = adjList[u].get(i);
			if(!visited[nxt.v])
			{
				double d = dfs(nxt.v);
				if(d > -5)
					return Math.max(d, nxt.distance);
				
			}
		}
		return -10;
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			N = sc.nextInt();
			if(N == 0)
				break;
			int[][] pos = new int[N][2];
			for(int i = 0; i < N; i++)
			{
				pos[i][0] = sc.nextInt();
				pos[i][1] = sc.nextInt();
			}
			
			edgeList = new Triple[N * (N - 1) / 2];
			
			for(int i = 0, nxt = 0; i < N; i++)
				for(int j = i + 1; j < N; j++)
				{
					int x = pos[i][0] - pos[j][0], y = pos[i][1] - pos[j][1];
					edgeList[nxt++] = new Triple(i, j, Math.sqrt(x * x + y * y));
				}
			
			mst();
			visited = new boolean[N];
			
			out.printf("Scenario #%d\nFrog Distance = %.3f\n\n",k++,dfs(0));
		}
		out.flush();
	}
	
	static class Triple implements Comparable<Triple>
	{
		int u, v;
		double distance;
		
		Triple(int x, int y, double z) { u = x; v = y; distance = z; }
		
		public int compareTo(Triple o) { return distance < o.distance ? -1 : 1; }
		
	}
	
	
	static class UnionFind {                                              
		int[] p, rank, setSize;
		int numSets;
		
		public UnionFind(int N) 
		{
			 p = new int[N];
			 rank = new int[N];
			 setSize = new int[N];
			 numSets = N;
			 for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
		}
		
		public int findSet(int i) 
		{ 
			if (p[i] == i) return i;
			else return p[i] = findSet(p[i]);
		 }
		
		public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
		
		public void unionSet(int i, int j) 
		{ 
			 if (isSameSet(i, j)) 
				 return;
			 numSets--; 
			 int x = findSet(i), y = findSet(j);
			 // rank is used to keep the tree short
			 if (rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
			 else
			 {	p[x] = y; setSize[y] += setSize[x];
			 	if(rank[x] == rank[y]) rank[y]++; 
			 } 
		}
		
		public int numDisjointSets() { return numSets; }
		
		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
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
