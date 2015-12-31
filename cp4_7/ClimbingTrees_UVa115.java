package cp4_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ClimbingTrees_UVa115 {
	
	static int[] Parent = new int[500];
	static int N;
	static ArrayList<Integer>[] path;
	
	static void generate(int u, int i) { if(u == -1) return; path[i].add(u); generate(Parent[u], i); }
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		Arrays.fill(Parent, -1);
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			if(child.equals("no.child"))
				break;
			String parent = st.nextToken();
			if(!map.containsKey(child))
				map.put(child, N++);
			
			if(!map.containsKey(parent))
				map.put(parent, N++);
				
			int p = map.get(child);
			int q = map.get(parent);
			Parent[p] = q;
		}
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			Integer x = map.get(st.nextToken());
			Integer y = map.get(st.nextToken());
			if(x == null || y == null)
			{
				sb.append("no relation\n");
				continue;
			}
			
			path = new ArrayList[2];
			path[0] = new ArrayList<Integer>();
			path[1] = new ArrayList<Integer>();
			generate(x,0);generate(y,1);
			int idx = path[0].indexOf(y);
			if(idx != -1)
			{
				StringBuilder out = new StringBuilder();
				while(idx > 2)
				{
					out.append("great ");
					idx--;
				}
				if(idx == 2)
				{
					out.append("grand ");
					idx--;
				}
				sb.append(out).append("child\n");			
				continue;
			}
			idx = path[1].indexOf(x);
			if(idx != -1)
			{
				StringBuilder out = new StringBuilder();
				while(idx > 2)
				{
					out.append("great ");
					idx--;
				}
				if(idx == 2)
				{
					out.append("grand ");
					idx--;
				}
				sb.append(out).append("parent\n");			
				continue;
			}
			if(Parent[x] == Parent[y])
			{
				sb.append("sibling\n");
				continue;
			}
			int n = -1, m = -1;
			for(int a = 0; a < path[0].size() && n == -1; a++)
				for(int b = 0; b < path[1].size() && n == -1; b++)
					if(path[0].get(a) == path[1].get(b)){n = a - 1;	m = b - 1;}
			if(n == -1)
				sb.append("no relation\n");
			else
			{
				int k = Math.min(n, m);
				int j = Math.abs(n - m);
				sb.append(k+" cousin").append(j==0?"":(" removed "+j)).append("\n");
			}
		}
		
		System.out.print(sb);
	}
}
