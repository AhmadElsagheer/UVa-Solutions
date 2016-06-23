package cp4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SpreadingTheNews_UVa924 {

	static LinkedList<Integer>[] adjList;
	static StringBuilder sb = new StringBuilder();
	static int E;
	
	public static void bfs(int S)
	{
		int[] day = new int[E];
		int []visited =  new int[E];
		LinkedList<Integer> q = new LinkedList<Integer>();
		
		Arrays.fill(visited, -1);
		visited[S] = 0;
		q.add(S);
		while(!q.isEmpty())
		{
			int cur = q.remove();
			for(int i = 0, size = adjList[cur].size(); i < size; i++)
			{
				int v = adjList[cur].get(i);
				if(visited[v]==-1)
				{			
					visited[v] = visited[cur] + 1;
					day[visited[cur]+1]++;
					q.add(v);
				}
			}
		}
		int size = 0; int index = -1;
		for(int i = 1; i < E; i++)
			if(day[i]>size)
			{
				size = day[i];
				index  = i;
			}
		if(size==0)
			sb.append("0\n");
		else
			sb.append(size+" "+index+"\n");
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		E = Integer.parseInt(br.readLine());
		adjList = new LinkedList[E];
		for(int i = 0; i < E; i++)
		{
			adjList[i] = new LinkedList<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			while(N-->0)
				adjList[i].add(Integer.parseInt(st.nextToken()));
		}

		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
			bfs(Integer.parseInt(br.readLine()));
		System.out.print(sb);
	}
}
