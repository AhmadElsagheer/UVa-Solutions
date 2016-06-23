package cp4_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DarkRoads_UVa11631 {
	
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			int total = 0;
			Triple[] edgeList = new Triple[M];
			for(int i = 0; i < M; i++)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				total += z;
				edgeList[i] = new Triple(x,y,z);
				
			}
			Arrays.sort(edgeList);
			UnionFind x = new UnionFind(N);
			for(int i = 0; i < M; i++)
			{
				Triple cur = edgeList[i];
				if(!x.isSameSet(cur.to,cur.from))
				{
					total -= cur.cost;
					x.unionSet(cur.to, cur.from);
				}
			}
			sb.append(total).append("\n");
			
		}
	
		System.out.print(sb);
	}
	
	static class Triple implements Comparable<Triple>
	{
		int to,from,cost;
		Triple(int x, int y, int z) {to = x; from = y; cost = z;}
		@Override
		public int compareTo(Triple x) {
			
			return this.cost - x.cost;
		}
	}


	static class UnionFind {                                              // OOP style
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
}



