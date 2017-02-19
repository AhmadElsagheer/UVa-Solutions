package v101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Doublets_UVa10150 {

	static final int INF = 50000;
	static TreeMap<String,Integer> map = new TreeMap<String,Integer>();
	static String[] unmap = new String[25200];
	static int[] parent, dist;
	static int N;
	
	static int bfs(String S, String T)
	{
		dist = new int[N];
		parent = new int[N];
		Arrays.fill(parent, -1);
		Arrays.fill(dist, INF);
		dist[map.get(S)] = 0;
		Queue<String> q = new LinkedList<String>();
		q.add(S);
		while(!q.isEmpty())
		{
			String cur = q.remove();
			int x = map.get(cur);
			if(cur.equals(T))
				return x;
			
			for(int i = 0; i < cur.length(); i++)
			{
				StringBuilder sb = new StringBuilder(cur);
				for(int j = 0; j < 26; j++)
					if(cur.charAt(i)!=j+'a')
					{
						String next = sb.replace(i, i+1, ""+(char)(j+'a')).toString();
						Integer y = map.get(next);
						if(y!=null && dist[y]==INF)
						{
							dist[y] = dist[x] + 1;
							parent[y] = x;
							q.add(next);
						}	
					}
					
			}
		}
		return -1;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line;
		while(!(line=br.readLine()).equals(""))	
		{
				unmap[N] = line;
				map.put(line, N++);
		}
		boolean first = true;
		
		while(br.ready())
		{
			if(first)
				first = false;
			else
				sb.append("\n");
			StringTokenizer st = new StringTokenizer(br.readLine());
			String S = st.nextToken();
			String T = st.nextToken();
			if(!map.containsKey(S) || !map.containsKey(T))
			{
				sb.append("No solution.\n");
				continue;
			}
			int idx = bfs(S,T);
			if(idx==-1)
				sb.append("No solution.\n");
			else
			{
				Stack<String> out = new Stack<String>();
				while(idx!=-1)
				{
					out.push(unmap[idx]);
					idx = parent[idx];
				}
				while(!out.isEmpty())
					sb.append(out.pop()).append("\n");
			}
		}
		
		System.out.print(sb);
	}
}
