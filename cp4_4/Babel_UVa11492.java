package cp4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Babel_UVa11492 {

	static final int INF = 50000000;
	static ArrayList<Triple>[] adjList;
	static int N;
	
	static int dijkstra(int S, int T)
	{
		int[][] dist = new int[N][26];
		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
		
		for(int i = 0; i < N; i++)
			Arrays.fill(dist[i], INF);
		for(int i = 0; i < 26; i++)
		{
			dist[S][i] = 0;
			q.add(new Triple(S,0,i));
		}
		
		while(!q.isEmpty())
		{
			Triple cur = q.remove();
			if(cur.length > dist[cur.lang][cur.c])
				continue;
			for(int i = 0; i < adjList[cur.lang].size(); i++)
			{
				Triple next = adjList[cur.lang].get(i);
				
				if(next.c != cur.c && cur.length + next.length < dist[next.lang][next.c])
				{
					dist[next.lang][next.c] = cur.length + next.length;
					q.add(new Triple(next.lang,dist[next.lang][next.c],next.c));
				}
			}
		}
		int min = INF;
		for(int i = 0; i < 26; i++)
			min = Math.min(min, dist[T][i]);
		return min;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int m = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if(m==0)
				break;
			adjList = new ArrayList[5000];
			adjList[0] = new ArrayList<Triple>();
			adjList[1] = new ArrayList<Triple>();
			
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			N = 2;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			map.put(st.nextToken(), 0);
			map.put(st.nextToken(), 1);
			
			while(m-->0)
			{
				st = new StringTokenizer(br.readLine());
				String one = st.nextToken(), two = st.nextToken(), word = st.nextToken();
				if(!map.containsKey(one))
				{
					adjList[N] = new ArrayList<Triple>();
					map.put(one,N++);
				}
				
				if(!map.containsKey(two))
				{
					adjList[N] = new ArrayList<Triple>();
					map.put(two,N++);
				}
				int u = map.get(one), v = map.get(two);
				
				adjList[u].add(new Triple(v,word.length(),word.charAt(0) - 'a'));
				adjList[v].add(new Triple(u,word.length(),word.charAt(0) - 'a'));
			}
		
			int sol = dijkstra(0,1);
			if(sol==INF)
				sb.append("impossivel\n");
			else
				sb.append(sol+"\n");
			
			
		}
		System.out.print(sb);
	}
	
}

class Triple implements Comparable<Triple>
{
	int lang, length, c;
	
	Triple(int x, int y, int z) {lang = x; length = y; c = z;}
	
	public int compareTo(Triple o) {return length - o.length;}
}