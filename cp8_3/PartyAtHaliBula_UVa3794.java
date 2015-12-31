package cp8_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class PartyAtHaliBula_UVa3794 {

	static ArrayList<Integer>[] adjList;
	static ArrayList<Integer>[] children;
	static boolean[] visited;
	static int N;
	
	static void buildTree()
	{
		children = new ArrayList[N];
		for(int i = 0; i < N; i++)
			children[i] = new ArrayList<Integer>();
		visited = new boolean[N];
		buildTree(0);
	}
	static void buildTree(int u)
	{
		visited[u] = true;
		for(int i = 0; i < adjList[u].size(); i++)
		{
			int v = adjList[u].get(i);
			if(!visited[v])
			{
				children[u].add(v);
				buildTree(v);
			}
		}
	}
	
	static Pair[][] memo;
	
	static Pair dp(int u, int canCome)
	{
		if(children[u].isEmpty()) return new Pair(canCome);
		if(memo[u][canCome] != null) return memo[u][canCome];
		
		Pair dont = new Pair(0);
		//don't invite
		for(int i = 0; i < children[u].size(); i++)
		{
			int v = children[u].get(i);
			Pair nxt = dp(v, 1);
			dont.count += nxt.count;
			dont.unique &= nxt.unique;
		}
		//invite
		if(canCome == 1)
		{
			Pair inv = new Pair(1);
			for(int i = 0; i < children[u].size(); i++)
			{
				int v = children[u].get(i);
				Pair nxt = dp(v, 0);
				inv.count += nxt.count;
				inv.unique &= nxt.unique;
			}
			if(dont.count == inv.count)
				dont.unique = false;
			else
				if(inv.count > dont.count)
					dont = inv;
		}
		return memo[u][canCome] = dont;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			adjList = new ArrayList[N];
			for(int i = 0; i < N; i++)
				adjList[i] = new ArrayList<Integer>();
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			map.put(br.readLine(), 0);
			int c = 1;
			for(int i = 0; i < N - 1; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				String x = st.nextToken(), y = st.nextToken();
				if(!map.containsKey(x))
					map.put(x, c++);
				if(!map.containsKey(y))
					map.put(y, c++);
				int v = map.get(x), u = map.get(y);
				adjList[u].add(v); adjList[v].add(u);
			}
			buildTree();
			memo = new Pair[N][2];
			Pair ans = dp(0, 1);
			sb.append(ans.count+" "+(ans.unique?"Yes\n":"No\n"));
 		}
		
		System.out.print(sb);
	}
	
	static class Pair
	{
		int count;
		boolean unique;
		Pair(int count) {this.count = count; unique = true;}
	}
}


