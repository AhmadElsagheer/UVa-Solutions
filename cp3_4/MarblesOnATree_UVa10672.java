package cp3_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class MarblesOnATree_UVa10672 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			Queue<Integer> q = new LinkedList<Integer>();
			int[] marbles = new int[N+1];
			int[] parent = new int[N+1];
			int[] children = new int[N+1];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int node = Integer.parseInt(st.nextToken());
				marbles[node] = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				children[node] = d;
				if(d==0)	//leaf
					q.add(node);
				else
					while(d-->0)
						parent[Integer.parseInt(st.nextToken())] = node;
			}
			boolean[] inserted = new boolean[N+1];
			inserted[0] = true;
			int count = 0;
			while(!q.isEmpty())
			{
				int node = q.remove();
				marbles[parent[node]] += marbles[node] - 1;
				count += Math.abs(marbles[node] - 1);
				if(!inserted[parent[node]])
					if(children[parent[node]]==1)
					{
						inserted[parent[node]] = true;
						q.add(parent[node]);
					}
					else
						children[parent[node]]--;
			}
			sb.append(count).append("\n");
			
		}
		System.out.print(sb);
		
	}
	
}