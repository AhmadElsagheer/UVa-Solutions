package cp4_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ACMContestAndBlackout_UVa10600 {
	
	static int V;
	static Edge[] edgeList;

	static int mst(boolean first)
	{
		int mst = 0;	
		UnionFind uf = new UnionFind(V);
		for(Edge e: edgeList)
			if(!e.forb && !uf.isSameSet(e.from, e.to))
			{
				mst += e.cost;
				if(first)
					e.inMST = true;
				uf.unionSet(e.from, e.to);
			}
		if(uf.numDisjointSets() > 1)
			mst = Integer.MAX_VALUE;
		return mst;
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			V = sc.nextInt();
			int E = sc.nextInt();
			edgeList = new Edge[E];
			for(int i = 0; i < E; ++i)
				edgeList[i] = new Edge(sc.nextInt() - 1 , sc.nextInt() - 1, sc.nextInt());
			Arrays.sort(edgeList);
			
			int mst1 = mst(true), mst2 = Integer.MAX_VALUE;
			for(Edge e: edgeList)
				if(e.inMST)
				{
					e.forb = true;
					mst2 = Math.min(mst2, mst(false));
					e.forb = false;
				}
			
			out.printf("%d %d\n", mst1, mst2);
		}
		out.flush();
		out.close();
	}
	
	static class Edge implements Comparable<Edge>
	{
		int from, to, cost;
		boolean inMST, forb;
		Edge(int x, int y, int z)
		{
			from = x;
			to = y;
			cost = z;
		}
		
		public int compareTo(Edge e)
		{
			return cost - e.cost;
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
		
		public boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
		
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
