package v004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class WordTransformation_UVa429 {

	static ArrayList<Integer>[] adjMatrix;
	static TreeMap<String,Integer> map;
	static String[] unmap;
	static int N;
	
	public static int bfs(int S, int T)
	{
		int[] dist = new int[N];
		boolean[] visited = new boolean[N];
		visited[S] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);
		while(!q.isEmpty())
		{
			int u = q.remove();
			for(int i = 0; i < adjMatrix[u].size(); i++)
			{
				int v = adjMatrix[u].get(i);
				if(!visited[v])
				{
					visited[v] = true;
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
					
		}
		
		return dist[T];
	}
	
	public static boolean isNeighbor(String x, String y)
	{
		if(x.length()!=y.length())
			return false;
		boolean foundOne = false;
		for(int i = 0; i < x.length(); i++)
			if(x.charAt(i)!=y.charAt(i))
				if(foundOne)
					return false;
				else
					foundOne = true;
		return foundOne;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		StringTokenizer st;String start,end;
		int TC = Integer.parseInt(br.readLine());
		br.readLine();
		while(TC-->0)
		{
			N = 0;
			unmap = new String[200];
			map = new TreeMap<String,Integer>();
			String word;
			while(!(word=br.readLine()).equals("*"))
			{
				unmap[N] = word;
				map.put(word, N++);
			}
			adjMatrix = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjMatrix[i]  =new ArrayList<Integer>(N);
			
			for(int i = 0; i < N; i++)
				for(int j = i + 1; j < N; j++)
					if(isNeighbor(unmap[i], unmap[j]))
					{
						adjMatrix[i].add(j);
						adjMatrix[j].add(i);
					}
			String line;
			while(br.ready() && !(line = br.readLine()).equals(""))
			{
				st = new StringTokenizer(line);
				start = st.nextToken();
				end = st.nextToken();
				int ans = bfs(map.get(start),map.get(end));
				out.printf("%s %s %d\n",start,end,ans);
			}
			
			if(TC!=0)
				out.println();
		}
		out.flush();
		out.close();
		
	}
}
