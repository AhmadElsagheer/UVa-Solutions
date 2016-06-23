package cp4_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class GalacticImport_UVa388 {

	static boolean[][] adjMatrix;
	static int[] value;
	static int[] lines;
	static final int INF = 100;
	
	public static void bfs(int S)
	{
		int[] dist = new int[27];
		boolean[] visited = new boolean[27];
		Arrays.fill(dist, INF);
		dist[S] = 0;
		visited[S] = true;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);
		while(!q.isEmpty())
		{
			int cur = q.remove();
			for(int i = 0; i < 27; i++)
				if(adjMatrix[cur][i] && !visited[i])
				{
					visited[i]  = true;
					q.add(i);
				}
		}
		lines[S] = dist[26];
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		while(br.ready())
		{
			adjMatrix = new boolean[27][27];
			value = new int[26];
			lines = new int[26];
			int N = Integer.parseInt(br.readLine());
			while(N-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int cur = st.nextToken().charAt(0) - 'A';
				String v = st.nextToken();
				String to = st.nextToken();
				for(int i = 0; i < to.length(); i++)
				{
					char next = to.charAt(i);
					if(next=='*')
						adjMatrix[cur][26] = true;
					else
						adjMatrix[cur][next-'A'] = true;
				}
				st = new StringTokenizer(v,".");
				value[cur] = Integer.parseInt(st.nextToken())*100 + Integer.parseInt(st.nextToken());
			}
			for(int i = 0; i < 26; i++)
				if(value[i]!=0)
					bfs(i);
			
			double max = 0;
			int importFrom = -1;
			for(int i = 0; i < 26; i++)
			{
				int stops = lines[i] - 1;
				double v = value[i];
				while(stops-->0)
				{
					v -= v*0.05;
				}
				if(v>max)
				{
					max = v;
					importFrom = i;
				}
			}
			out.printf("Import from %c\n",(char)(importFrom+'A'));
		}
		out.flush();
		out.close();
	}
}
