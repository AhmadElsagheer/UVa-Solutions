package v115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class VirtualFriends_UVa11503 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			int m = Integer.parseInt(br.readLine());
			UnionFind x = new UnionFind(m<<1);
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			int N = 0;
			while(m-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				String first = st.nextToken();
				String second = st.nextToken();
				if(!map.containsKey(first))
					map.put(first, N++);
				if(!map.containsKey(second))
					map.put(second, N++);
				int a = map.get(first);
				int b = map.get(second);
				if(x.isSameSet(a, b))
					sb.append(x.sizeOfSet(a)).append("\n");
				else
				{
					sb.append(x.sizeOfSet(a)+x.sizeOfSet(b)).append("\n");
					x.unionSet(a, b);
				}
			}
		}
		
		System.out.print(sb);
	}
	
	static class UnionFind {                                              
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