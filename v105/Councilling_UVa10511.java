package v105;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Councilling_UVa10511 {
	
	static ArrayList<Integer>[] adjList;
	static int V, p[], res[][];
	
	static void addEdge(int x, int y, int w)
	{
		adjList[x].add(y);
		adjList[y].add(x);
		res[x][y] = w;
	}
	
	static int maxFlow()
	{
		int mf = 0;
		while(true)
		{
			Queue<Integer> q = new LinkedList<Integer>();
			p = new int[V];
			Arrays.fill(p, -1);
			p[0] = 0;
			q.add(0);
			while(!q.isEmpty())
			{
				int u = q.remove();
				for(int i = 0; i < adjList[u].size(); ++i)
				{
					int v = adjList[u].get(i);
					if(p[v] == -1 && res[u][v] > 0)
					{
						p[v] = u;
						q.add(v);
					}
				}
			}
			if(p[1] == -1)
				break;
			++mf;
			augment(1);
		}
		return mf;
	}
	
	static void augment(int v)
	{
		if(v == 0)
			return;
		--res[p[v]][v];
		++res[v][p[v]];
		augment(p[v]);
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int tc = sc.nextInt();
		sc.nextLine();
		while(tc-->0)
		{
			int n = 0, nxtP = 0, nxtC = 0;
			String[][] in = new String[1000][];
			TreeMap<String, Integer> mapParties = new TreeMap<String, Integer>();
			TreeMap<String, Integer> mapClubs = new TreeMap<String, Integer>();
			ArrayList<String> unmapParties = new ArrayList<String>();
			ArrayList<String> unmapClubs = new ArrayList<String>();
			String line;
			while((line = sc.nextLine()) != null && !line.isEmpty())
			{
				in[n] = line.split(" ");
				if(!mapParties.containsKey(in[n][1]))
				{
					mapParties.put(in[n][1], nxtP++);
					unmapParties.add(in[n][1]);
				}
				for(int i = 2; i < in[n].length; ++i)
					if(!mapClubs.containsKey(in[n][i]))
					{
						mapClubs.put(in[n][i], nxtC++);
						unmapClubs.add(in[n][i]);
					}
				n++;
			}
			V = (n<<1) + nxtP + nxtC + 2;
			res = new int[V][V];
			adjList = new ArrayList[V];
			for(int i = 0; i < V; ++i)
				adjList[i] = new ArrayList<Integer>();
			for(int i = 0; i < n; ++i)
			{
				int member = i, party = mapParties.get(in[i][1]) + (n<<1) + 2;
				addEdge((member<<1) + 2, (member<<1) + 3, 1);
				addEdge((member<<1) + 3, party, 1);
				for(int j = 2; j < in[i].length; ++j)
				{
					int club = mapClubs.get(in[i][j]) + (n<<1) + nxtP + 2;
					addEdge(club, (member<<1) + 2, 1);
				}
			}
			for(int i = 0; i < nxtC; ++i)
				addEdge(0, i + (n<<1) + nxtP + 2, 1);
			for(int i = 0; i < nxtP; ++i)
				addEdge(i + (n<<1) + 2, 1, (nxtC+1>>1) -1);
			
			int mf = maxFlow();
			if(mf != nxtC)
				out.println("Impossible.");
			else
			{
				for(int i = 0; i < nxtC; ++i)
				{
					int club = i + (n<<1) + nxtP + 2;
					for(int j = 0; j < adjList[club].size(); ++j)
					{
						int cand = adjList[club].get(j);
						if(cand != 0 && res[club][cand] == 0)
						{
							out.format("%s %s\n", in[cand - 2>>1][0], unmapClubs.get(i));
							break;
						}
					}
				}
			}
			if(tc != 0)
				out.println();
		}
		out.flush();
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
