package v117;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class ExpensiveSubway_UVa11710 {

	
	static int mst(Triple[] edgeList, int n)
	{
		int mst = 0;
		Arrays.sort(edgeList);
		UnionFind uf = new UnionFind(n);
		for(int i = 0; i < edgeList.length; i++)
		{
			Triple cur = edgeList[i];
			if(!uf.isSameSet(cur.u, cur.v))
			{
				mst += cur.cost;
				uf.unionSet(cur.u, cur.v);
			}
		}
		if(uf.numDisjointSets() != 1)
			return -1;
		return mst;
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int n = sc.nextInt(), m = sc.nextInt(), nxt = 0;
			if(n == 0)
				break;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < n; i++) map.put(sc.next(), nxt++);
			
			Triple[] edgeList = new Triple[m];
			for(int i = 0; i < m; i++)
			{
				int u = map.get(sc.next()), v = map.get(sc.next()), cost = sc.nextInt();
				edgeList[i] = new Triple(u, v, cost);
			}
			sc.next();
			
			int ans = mst(edgeList, n);
			if(ans == -1)
				sb.append("Impossible\n");
			else
				sb.append(ans+"\n");
		}
		System.out.print(sb);
	}
	
	
	static class Triple implements Comparable<Triple>
	{
		int u, v, cost;
		
		Triple(int x, int y, int z)
		{
			u = x; v = y; cost = z;
		}

		public int compareTo(Triple o) { return cost - o.cost; }
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
