package v009;
import java.util.*;
import java.io.*;

public class ReconnectingComputerSites_UVa908 {

	static LinkedList<Triple> edgeList;
	static int N;
	public static long mst()
	{
		long MST = 0;
		Collections.sort(edgeList);
		for(int i = 0; i < edgeList.size() - 1; i++)
		{
			if(edgeList.get(i).equals(edgeList.get(i+1)))
			{
				edgeList.remove(i+1);
				i--;
			}
		}
		UnionFind x = new UnionFind(N);
		for(int i = 0, size = edgeList.size(); i < size; i++)
		{
			Triple cur = edgeList.get(i);
			if(!x.isSameSet(cur.to,cur.from))
			{
				MST += cur.cost;
				x.unionSet(cur.to, cur.from);
			}
		}
		return MST;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		boolean first = true;

		while(br.ready())
		{
			if(first)
				first = false;
			else
			{
				sb.append("\n");
				br.readLine();
			}
			edgeList = new LinkedList<Triple>();

			N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N - 1; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				edgeList.add(new Triple(u,v,cost));
			}

			long oldCost = mst();
			int K = Integer.parseInt(br.readLine());
			for(int i = 0; i < K; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				edgeList.add(new Triple(u,v,cost));
			}
			int M = Integer.parseInt(br.readLine());
			for(int i = 0; i < M; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				int cost = Integer.parseInt(st.nextToken());
				edgeList.add(new Triple(u,v,cost));
			}

			long newCost = mst();
			if(newCost>oldCost)
				newCost = oldCost;

			sb.append(oldCost+"\n"+newCost+"\n");


		}
		System.out.print(sb);
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
	
	
	static class Triple implements Comparable<Triple>
	{
		int from,to,cost;
		Triple(int x, int y, int z){from = x; to = y; cost = z;}

		public int compareTo(Triple x) 
		{ 
			if(this.cost != x.cost)
				return this.cost - x.cost;
			if(this.from != x.from)
				return this.from - x.from;
			return this.to - x.to;
		}

	}
}