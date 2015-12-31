package cp4_3;

import java.util.*;
import java.io.*;

public class HeavyCycleEdges_UVa11747 {

	static LinkedList<Integer> sol;
	static Triple[] edgeList;
	static int N, M;
	
	public static void mst()
	{
		Arrays.sort(edgeList);
		UnionFind x = new UnionFind(N);
		sol  = new LinkedList<Integer>();
		for(int i = 0; i < M; i++)
		{
			Triple cur = edgeList[i];
			if(!x.isSameSet(cur.u, cur.v))
				x.unionSet(cur.v, cur.u);
			else
				sol.add(cur.cost);
			
		}
		
		Collections.sort(sol);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0)
				break;
			edgeList = new Triple[M];
			for(int i = 0; i < M; i++)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edgeList[i] = new Triple(u,v,cost);
			}
			
			mst();
			int size = sol.size();
			if(size==0)
				sb.append("forest\n");
			else
			{
				for(int  i = 0; i < size - 1; i++)
					sb.append(sol.get(i)+" ");
				sb.append(sol.get(size-1)+"\n");
			}
		}
		
		System.out.print(sb);
	}
	
}

class Triple implements Comparable<Triple>
{
	int u,v,cost;

	Triple(int x, int y, int z) { u = x; v= y; cost =z;}
	@Override
	public int compareTo(Triple x) {
		return this.cost - x.cost;
		
	}
	
}

class UnionFind {                                              // OOP style
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