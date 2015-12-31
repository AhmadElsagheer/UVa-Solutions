package cp4_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Audiophoboa_UVa10048 {

	static Triple[] edgeList;
	static ArrayList<Pair>[] adjList;
	static UnionFind uf;
	static int V,E, max;
	static boolean[] visited;
	
	
	static void mst()
	{
		adjList = new ArrayList[V];
		for(int i = 0; i < V;i++)
			adjList[i] = new ArrayList<Pair>();
		
		uf = new UnionFind(V);
		Arrays.sort(edgeList);
		
		for(int i = 0; i < E; i++)
		{
			Triple cur = edgeList[i];
			if(!uf.isSameSet(cur.u, cur.v))
			{
				uf.unionSet(cur.v, cur.u);
				adjList[cur.u].add(new Pair(cur.v,cur.level));
				adjList[cur.v].add(new Pair(cur.u,cur.level));
			}
		}
		
	}
	
	static boolean dfs(int u, int v)
	{
		if(u == v)
			return true;
		visited[u] = true;
		for(int i = 0, size = adjList[u].size(); i < size; i++)
		{
			Pair cur = adjList[u].get(i);
			if(!visited[cur.to] && dfs(cur.to,v))
			{
				max = Math.max(max, cur.level);
				return true;
			}
			
		}
		return false;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int k = 0;
		while(true)
		{
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			if(V==0 && E==0 && Q==0)
				break;
			if(++k!=1)
				sb.append("\n");
			edgeList = new Triple[E];
			for(int i = 0; i < E; i++)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				edgeList[i] = new Triple(u,v,d);
			}
			mst();
			
			sb.append("Case #"+k+"\n");
			while(Q-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				if(!uf.isSameSet(u, v))
					sb.append("no path\n");
				else
				{
					visited = new boolean[V];	
					max = 0;
					dfs(u,v);
					sb.append(max+"\n");
				}
			}
			
			
		}
		System.out.print(sb);
	}
	
	static class Pair
	{
		int to, level;
		Pair(int x, int y)
		{
			to = x;
			level = y;
		}
	}
	
	static class Triple implements Comparable<Triple>
	{
		int u,v,level;
		
		Triple(int x, int y, int d)
		{
			u = x; v = y; level = d;
		}
		@Override
		public int compareTo(Triple o) {
			// TODO Auto-generated method stub
			return level - o.level;
		}
		
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
			 if (rank[x] == rank[y]) rank[y]++; 
			 } 
		}
		
		public int numDisjointSets() { return numSets; }
		
		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
	}
}

