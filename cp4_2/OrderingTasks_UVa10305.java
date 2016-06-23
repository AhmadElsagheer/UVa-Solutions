package cp4_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class OrderingTasks_UVa10305 {

	static LinkedList<Integer>[] adjList;
	static boolean[] visited;
	static LinkedList<Integer> array;
	 
	public static void toposort(int u)
	{
		visited[u] = true;
		for(int i = 0, size = adjList[u].size(); i < size; i++)
		{
			int v = adjList[u].get(i);
			if(!visited[v])
				toposort(v);
		}
		array.addFirst(u);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0)
				break;
			adjList = new LinkedList[N+1];
			for(int  i = 1; i <= N; i++)
				adjList[i] = new LinkedList<Integer>();
			
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u  = Integer.parseInt(st.nextToken());
				int v  = Integer.parseInt(st.nextToken());
				adjList[u].add(v);
			}
			array = new LinkedList<Integer>();
			visited = new boolean[N+1];
			for(int i  =1; i <= N; i++)
				if(!visited[i])
					toposort(i);
			sb.append(array.get(0));
			for(int i = 1; i < N; i++)
				sb.append(" "+array.get(i));
			
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
}
