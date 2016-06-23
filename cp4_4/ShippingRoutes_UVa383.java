package cp4_4;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class ShippingRoutes_UVa383 {
	
	static int N;
	static boolean[][] adjMatrix;
	
	public static int bfs(int S, int T)
	{
		int[] dist = new int[N];
		dist[S] = 1;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(S);
		while(!q.isEmpty())
		{
			int cur = q.remove();
			for(int i = 0; i < N; i++)
				if(adjMatrix[cur][i] && dist[i]==0)
				{
					dist[i] = dist[cur] + 1;
					q.add(i);
				}
		}
		return dist[T] - 1;
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = sc.nextInt();
		out.println("SHIPPING ROUTES OUTPUT\n");
		for(int k = 1; k <= TC; k++)
		{
			N = sc.nextInt();
			adjMatrix = new boolean[N][N];
			TreeMap<String,Integer> map = new TreeMap<String,Integer>();
			int E = sc.nextInt();
			int P = sc.nextInt();
			for(int i = 0; i < N; i++)
				map.put(sc.next(), i);
			while(E-->0)
			{
				int u = map.get(sc.next());
				int v = map.get(sc.next());
				adjMatrix[u][v] = adjMatrix[v][u] = true;
			}
			
			out.printf("DATA SET  %d\n\n", k);
			while(P-->0)
			{
				int size = sc.nextInt();
				int S = map.get(sc.next());
				int T = map.get(sc.next());
				int legs = bfs(S,T);
				if(legs==-1)
					out.println("NO SHIPMENT POSSIBLE");
				else
					out.printf("$%d\n", legs*100*size);
			}
			out.println();
		}
		out.println("END OF OUTPUT");
		out.flush();
		
	}
}
