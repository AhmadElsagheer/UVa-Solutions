package cp4_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class TripRouting_UVa186 {

	static int V = 100, adjMat[][], p[][];
	static final int INF = 100000000;
	static String[] unmap;
	static String[][] unmap_road;
	
	static PrintWriter out = new PrintWriter(System.out);
	
	static void floyd()
	{
		p = new int[V][V];
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				p[i][j] = i;
		
		for(int k = 0; k < V; k++)
			for(int i = 0; i < V; i++)
				for(int j = 0; j < V; j++)
					if(adjMat[i][j] > adjMat[i][k] + adjMat[k][j])
					{
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
						p[i][j] = p[k][j];
					}
	}
	
	static void print(int i, int j)
	{
		if(i == j) return;
		
		print(i, p[i][j]);
		out.printf("%-20s %-20s %-10s %5d\n", unmap[p[i][j]], unmap[j],unmap_road[p[i][j]][j],adjMat[p[i][j]][j]);
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		adjMat = new int[V][V];
		for(int i = 0; i < V; i++)
		{
			Arrays.fill(adjMat[i], INF);
			adjMat[i][i] = 0;
		}
		
		unmap = new String[100];
		unmap_road = new String[100][100];
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		int nxt = 0;
		while(true)
		{
			String line = br.readLine();
			if(line.isEmpty()) break;
			
			StringTokenizer st = new StringTokenizer(line,",");
			String from = st.nextToken(), to = st.nextToken(), road = st.nextToken();
			int d = Integer.parseInt(st.nextToken());
			
			if(!map.containsKey(from))
			{
				unmap[nxt] = from;
				map.put(from, nxt++);
			}
			if(!map.containsKey(to))
			{
				unmap[nxt] = to;
				map.put(to, nxt++);
			}
			
			int u = map.get(from), v = map.get(to);
			
			if(d < adjMat[u][v])
			{
				unmap_road[u][v] = unmap_road[v][u] = road;
				adjMat[u][v] = adjMat[v][u] = d;				
			}
		}
		
		floyd();
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine(), ",");
			int u = map.get(st.nextToken()), v = map.get(st.nextToken());
			
			out.print("\n\nFrom                 To                   Route      Miles\n-------------------- -------------------- ---------- -----\n");
			print(u, v);
			out.print("                                                     -----\n");
			out.printf("                                          Total      %5d\n",adjMat[u][v]);
		}
		out.flush();
		
	}
}
