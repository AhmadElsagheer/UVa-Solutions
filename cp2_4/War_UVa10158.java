package cp2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class War_UVa10158 {

	//1 setFriends, 2 setEnem, 3 areFriends, 4 areEnem
	
	public static void main(String[] args) throws IOException {
		
		
		InputReader in = new InputReader(System.in);
		StringBuilder sb = new StringBuilder();
		
		int n = in.nextInt();
		
		UnionFind friends = new UnionFind(n);
		
		while(true)
		{
			int c = in.nextInt(), x = friends.findSet(in.nextInt()), y = friends.findSet(in.nextInt());
			
			if(c == 0) break;
			
			switch(c)
			{
			case 1:
				if(friends.E[x] == y || friends.E[y] == x)
					sb.append(-1+"\n");
				else
				{
					friends.unionSet(x, y);
					if(friends.E[x] != - 1 && friends.E[y] != -1) 
					{
						friends.unionSet(friends.E[x], friends.E[y]);
						friends.E[friends.findSet(friends.E[x])] = friends.findSet(x);
					}
					else
						if(friends.E[x] != - 1)
						{
							friends.E[x] = friends.E[y] = friends.findSet(friends.E[x]);
							friends.E[friends.E[x]] = friends.findSet(x);
						}
						else
							if(friends.E[y] != - 1)
							{
								friends.E[x] = friends.E[y] = friends.findSet(friends.E[y]);
								friends.E[friends.E[x]] = friends.findSet(x);
							}
							
				}
				break;
			case 2:
				if(friends.isSameSet(x, y))				
					sb.append(-1+"\n");
				else
				{
					if(friends.E[x] != -1)
						friends.unionSet(friends.E[x], y);
					if(friends.E[y] != -1)
						friends.unionSet(x, friends.E[y]);						
					
					x = friends.findSet(x);
					y = friends.findSet(y);
					friends.E[x] = y;
					friends.E[y] = x;
				}
				break;
			case 3:
				if(x == y)
					sb.append("1\n");
				else
					sb.append("0\n");
				break;
				default:
					if(friends.E[x] == y)
						sb.append("1\n");
					else
						sb.append("0\n");
			}
		}
		System.out.print(sb);
	}
	
	
	
	
	
	
	
	static class UnionFind {                                              
		int[] p, E, rank, setSize;
		int numSets;
		
		public UnionFind(int N) 
		{
			 p = new int[N];
			 rank = new int[N];
			 setSize = new int[N];
			 E = new int[N];
			 Arrays.fill(E, -1);
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
		
		public String toString()
		{
			ArrayList<Integer>[] array = new ArrayList[p.length];
			for(int i = 0; i < p.length; i++)
				array[i] = new ArrayList<Integer>();
			for(int i = 0; i < p.length; i++)
				array[findSet(i)].add(i);
			String out = "";
			for(int i = 0; i < p.length; i++)
				if(!array[i].isEmpty())
				{
					out += "[";
					for(int j = 0; j < array[i].size(); j++)
						out += " " + array[i].get(j);
					out += " ] Enemy =" + E[i] +"\n";
				}
			return out;
		}
	}
	
	static class InputReader {
		StringTokenizer st;
		BufferedReader br;

		public InputReader(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}


	}

}
