package v112;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class TransportationSystem_UVa11228 {

	static final double EPS = 1e-9; 
	static ArrayList<Triple> edgeList1;
	static ArrayList<Triple> edgeList2;
	static UnionFind uf;
	
	
	static double mst(ArrayList<Triple> edgeList)
	{
		double mst = 0;
		
		Collections.sort(edgeList);
		for(int i = 0; i < edgeList.size(); i++)
		{
			Triple cur = edgeList.get(i);
			if(!uf.isSameSet(cur.u, cur.v))
			{
				mst += cur.distance;
				uf.unionSet(cur.u, cur.v);
			}
		}
		return mst;
	}

	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; t++)
		{
			int n = sc.nextInt(), r = sc.nextInt();
			int[][] pos = new int[n][2];
			for(int i = 0; i < n; i++)
			{
				pos[i][0] = sc.nextInt();
				pos[i][1] = sc.nextInt();
			}
			edgeList1 = new ArrayList<Triple>();
			edgeList2 = new ArrayList<Triple>();
			for(int i = 0; i < n; i++)
				for(int j = i + 1; j < n; j++)
				{
					int x = pos[i][0] - pos[j][0], y = pos[i][1] - pos[j][1]; 
					double d = Math.sqrt(x * x + y * y);
					if(d <= r + EPS)
						edgeList1.add(new Triple(i,j,d));
					else
						edgeList2.add(new Triple(i,j,d));
				}
			uf = new UnionFind(n);
	
			int y = (int)Math.round(mst(edgeList1)), x = uf.numDisjointSets(), z = (int)Math.round(mst(edgeList2));
			out.printf("Case #%d: %d %d %d\n",t , x, y, z);
		}
		out.flush();
		
	}
	
	
	
	
	
	
	static class Triple implements Comparable<Triple>
	{
		int u, v;
		double distance;
		
		Triple(int x, int y, double z) { u = x; v = y; distance = z; }

		public int compareTo(Triple o) { return distance > o.distance? 1 : -1; }
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
