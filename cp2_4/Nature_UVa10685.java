package cp2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Nature_UVa10685 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
			if(C==0)
				break;
			int R = Integer.parseInt(st.nextToken());
			TreeMap<String,Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < C; i++)
			{
				String name = br.readLine();
				map.put(name, i);
			}
			UnionFind x = new UnionFind(C);
			while(R-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = map.get(st.nextToken());
				int v = map.get(st.nextToken());
				x.unionSet(u, v);
			}
			int max = 0;
			for(int i = 0; i < C; i++)
				max = Math.max(max, x.setSize[i]);
			sb.append(max+"\n");
			br.readLine();
		}
		System.out.print(sb);
		
	}
}



//Union-Find Disjoint Sets Library written in OOP manner, using both path compression and union by rank heuristics
class UnionFind {                                              
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