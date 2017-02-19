package v012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Racing_UVa1234 {

	public static void main(String[] args) throws IOException {
	
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int sum = 0;
			N = sc.nextInt();
			edgeList = new Triple[sc.nextInt()];
			for(int i = 0; i < edgeList.length; ++i)
			{
				edgeList[i] = new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt());
				sum += edgeList[i].cost;
			}
			out.println(sum - mstKruskal());
		}
		out.flush();
		out.close();
	}
	
	static Triple[] edgeList;	//use ArrayList in case of unknown number of edges
	static int N;
	
	public static int mstKruskal()
	{
		Arrays.sort(edgeList);
		UnionFind uf = new UnionFind(N);
		int mst = 0;
		
		for(int i = 0, size = edgeList.length; i < size; i++)
		{
			Triple cur = edgeList[i];
			if(!uf.isSameSet(cur.to, cur.from))
			{
				mst += cur.cost;
				uf.unionSet(cur.to, cur.from);
			}
		}
		
		return mst;
	}
	
	

	static class Triple implements Comparable<Triple>
	{
		int from, to, cost;
		Triple(int x, int y, int z) {from = x; to = y; cost = z;}
		public int compareTo(Triple p) {return p.cost - cost;}
	}

	//Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
	static class UnionFind {                                              
		int[] p, rank, setSize;
		int numSets;

		public UnionFind(int N) 
		{
			p = new int[numSets = N];
			rank = new int[N];
			setSize = new int[N];
			for (int i = 0; i < N; i++) {  p[i] = i; setSize[i] = 1; }
		}

		public int findSet(int i) { return p[i] == i ? i : (p[i] = findSet(p[i])); }

		public boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

		public void unionSet(int i, int j) 
		{ 
			if (isSameSet(i, j)) 
				return;
			numSets--; 
			int x = findSet(i), y = findSet(j);
			if(rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
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

		public boolean ready() throws IOException {return br.ready(); }
	}
}