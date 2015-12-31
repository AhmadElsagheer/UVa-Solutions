package cp4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ErdosNumbers_UVa10044 {

	static final int INF = 10000000;
	static ArrayList<Integer>[] adjList;
	static TreeMap<String,Integer> map;
	static int N;
	static int[] dist;
	
	static void bfs()
	{
		dist = new int[N];
		Arrays.fill(dist, INF);
		dist[0] = 0;
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(0);
		visited[0] = true;
		while(!q.isEmpty())
		{
			int u = q.remove();
			for(int i = 0, size = adjList[u].size(); i < size; i++)
			{
				int v = adjList[u].get(i);
				if(!visited[v])
				{
					visited[v] = true;
					dist[v] = dist[u] + 1;
					q.add(v);
				}
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			String line;
			while((line=br.readLine()).equals(""));
			sb.append("Scenario "+k+"\n");
			StringTokenizer st = new StringTokenizer(line);
			int P = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[P*50];
			map = new TreeMap<String,Integer>();
			adjList[0] = new ArrayList<Integer>(P*10);
			map.put("Erdos, P.", 0);
			N = 1;
			while(P-->0)
			{
				st = new StringTokenizer(new StringTokenizer(br.readLine(),":").nextToken(),",");
				int names = st.countTokens()/2;
				String[] paper = new String[names];
				for(int i = 0; i < names; i++)
					paper[i] = st.nextToken().substring(i==0?0:1) + "," + st.nextToken();
			
			
				
				for(int i = 0; i < names; i++)
					for(int j = i + 1; j < names; j++)
					{
						
							if(!map.containsKey(paper[i]))
							{
								adjList[N] = new ArrayList<Integer>(P*10);
								map.put(paper[i], N++);
							}
							if(!map.containsKey(paper[j]))
							{
								adjList[N] = new ArrayList<Integer>(P*10);
								map.put(paper[j], N++);
							}
							int u = map.get(paper[i]);
							int v = map.get(paper[j]);
							adjList[u].add(v);
							adjList[v].add(u);
					}
				
			}
			bfs();
			while(M-->0)
			{
				String name = br.readLine();
				if(!map.containsKey(name))
				{
					sb.append(name+" infinity\n");
					continue;
				}
				int d = dist[map.get(name)];
				if(d==INF)
					sb.append(name+" infinity\n");
				else
					sb.append(name+" "+d +"\n");
			}
		}
		System.out.print(sb);
	}
	
}


