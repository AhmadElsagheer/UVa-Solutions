package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PoorTradeAdvisor_UVa11749 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			ArrayList<Triple> edgeList = new ArrayList<Triple>(E);
			int max = Integer.MIN_VALUE, cities = 0;
			for(int i = 0; i < E; i++)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				edgeList.add(new Triple(u,v,cost));
				max = Math.max(max, cost);
			}
			UnionFind uf = new UnionFind(N);
			for(int i = 0; i < E; i++)
			{
				Triple cur = edgeList.get(i);
				if(cur.cost == max)
				{
					uf.unionSet(cur.u, cur.v);
					cities = Math.max(cities, uf.sizeOfSet(cur.u));
				}
			}
			sb.append(cities+"\n");
			
		}
		
		System.out.print(sb);
	}
	
}

class Triple
{
	int u,v,cost;
	Triple(int x, int y, int z)
	{
		u = x; v = y; cost = z;
	}
}
class UnionFind {                                              
	ArrayList<Integer> p, rank, setSize;
	int numSets;
	
	public UnionFind(int N) 
	{
		 p = new ArrayList<Integer>(N);
		 rank = new ArrayList<Integer>(N);
		 setSize = new ArrayList<Integer>(N);
		 numSets = N;
		 for (int i = 0; i < N; i++) 
		 {
		   p.add(i);
		   rank.add(0);
		   setSize.add(1);
		 }
	}
	
	public int findSet(int i) 
	{ 
		if (p.get(i) == i) return i;
		else 
		{
			int ret = findSet(p.get(i)); p.set(i, ret);	return ret; 
		} 
	 }
	
	public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
	
	public void unionSet(int i, int j) 
	{ 
		 if (!isSameSet(i, j)) 
		 { 
			 numSets--; 
			 int x = findSet(i), y = findSet(j);
			 // rank is used to keep the tree short
			 if (rank.get(x) > rank.get(y)) 
			 	{ p.set(y, x); setSize.set(x, setSize.get(x) + setSize.get(y)); }
			 else
			 {	 p.set(x, y); setSize.set(y, setSize.get(y) + setSize.get(x));
			 	if (rank.get(x) == rank.get(y)) 
			 		rank.set(y, rank.get(y) + 1); 
			 } 
		 } 
	}
	
	public int numDisjointSets() { return numSets; }
	
	public int sizeOfSet(int i) { return setSize.get(findSet(i)); }
}