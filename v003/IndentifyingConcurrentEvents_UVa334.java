package v003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class IndentifyingConcurrentEvents_UVa334 {

	/*
	 * Another Solution (for Small Graphs only)
	 * concurrent events = unconnected nodes in DAG
	 * Use Floyd-Warshall
	 */
	static ArrayList<Integer>[] adjList;
	static int N;
	
	static int[] topsort(int[] inDeg, int sorter)
	{
		int idx = 0, ret[] = new int[N], p[] = Arrays.copyOf(inDeg, N);
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i = 0; i < N; ++i)
			if(p[i] == 0)
				pq.add(i * sorter);
		while(!pq.isEmpty())
		{
			int u = pq.remove() * sorter;
			ret[idx++] = u;
			for(int v: adjList[u])
				if(--p[v] == 0)
					pq.add(v * sorter);
		}
		return ret;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int C = sc.nextInt();
			if(C == 0)
				break;
			String[][] events = new String[C][];
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			N = 0;
			for(int i = 0; i < C; ++i)
			{
				int k = sc.nextInt();
				events[i] = new String[k];
				for(int j = 0; j < k; ++j)
					map.put(events[i][j] = sc.next(), N++);
			}
			int[] p = new int[N];
			String[] allEvents = new String[N];
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0, u = 0; i < C; ++i)
			{
				int k = events[i].length - 1;
				for(int j = 0; j < k; ++j, ++u)
				{
					adjList[u].add(u + 1);
					p[u+1] = 1;
					allEvents[u] = events[i][j];
				}
				allEvents[u++] = events[i][k];
			}
			int m = sc.nextInt();
			while(m-->0)
			{
				int u = map.get(sc.next()), v = map.get(sc.next());
				adjList[u].add(v);
				++p[v];
			}
			int[] a = topsort(p, 1), b = topsort(p, -1), fIdx = new int[N];
			for(int i = 0; i < N; ++i)
				fIdx[a[i]] = i + 1;
			FenwickTree ft = new FenwickTree(N);
			boolean[] on = new boolean[N];
			int ans = 0, x1 = -1, y1 = -1, x2 = -1, y2 = -1;
			for(int i = 0; i < N; ++i)
			{
				int idx = fIdx[b[i]];
				on[idx-1] = true;
				ft.update(idx);
				int pairs = idx - ft.query(idx);
				ans += pairs;
				if((x1 == -1 || x2 == -1) && pairs > 0)
				{
					int k = 0;
					while(on[k])
						++k;
					if(x1 == -1) { x1 = a[k]; y1 = b[i]; }
					else { x2 = a[k]; y2 = b[i]; }
					++k;
					if(x2 == -1 && pairs > 1)
					{
						while(on[k])
							++k;
						x2 = a[k]; y2 = b[i]; 
						
					}
				}
				
			}
			out.printf("Case %d, ", tc++);
			if(ans == 0)
				out.println("no concurrent events.");
			else
			{
				out.printf("%d concurrent events:\n", ans);
				out.printf("(%s,%s) ", allEvents[x1], allEvents[y1]);
				if(x2 != -1)
					out.printf("(%s,%s) ", allEvents[x2], allEvents[y2]);
				out.println();
			}
		}
		out.flush();
		out.close();
	}
	
	static class FenwickTree
	{
		int ft[];
		
		FenwickTree(int n) { ft = new int[n + 1]; }
		
		void update(int k)
		{
			while(k < ft.length)
			{
				++ft[k];
				k += k & -k;
			}
		}
		
		int query(int k)
		{
			int sum = 0;
			while(k > 0)
			{
				sum += ft[k];
				k ^= k & -k;
			}
			return sum;
		}
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

	}
}