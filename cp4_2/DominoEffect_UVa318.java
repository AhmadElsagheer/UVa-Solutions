package cp4_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DominoEffect_UVa318 {

	static int[][] adjMatrix;
	static int N;
	static double maxtime;
	static int one,two;
	
	public static void run()
	{
		int[] time = new int[N];
		Arrays.fill(time, 1000000000);
		time[0] = 0; 
	
		PriorityQueue<Pair> q = new PriorityQueue<Pair>();
		q.add(new Pair(0,0));
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			if(cur.time > time[cur.next])
				continue;
			for(int i = 0; i < N; i++)
				if(adjMatrix[cur.next][i]!=-1 && cur.time + adjMatrix[cur.next][i] < time[i])
				{
					time[i] = cur.time + adjMatrix[cur.next][i];
					q.add(new Pair(i,time[i]));
				}
					
		}
		int minimax = -1;
		for(int i = 0; i < N; i++)
			if(minimax<time[i])
			{one = i; minimax = time[i];}
		
		
		int max = 0;two = -1;maxtime = time[one];
		for(int i =0; i < N; i++)
			if(adjMatrix[one][i]!=-1)
			{
				int cur = (time[i] + adjMatrix[one][i]) - time[one];
				if(cur>max)
				{
					max = cur;
					two = i;
					maxtime = time[one] + cur / 2.0 ;
				}
			}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			adjMatrix = new int[N][N];
			for(int i = 0; i < N; i++)
				Arrays.fill(adjMatrix[i], -1);
			while(M-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				adjMatrix[u][v] = adjMatrix[v][u] = Integer.parseInt(st.nextToken());
			}
			run();
			one++;
			two++;
			if(two<one)
			{
				int tmp = one;
				one  = two;
				two = tmp;
			}
			out.printf("System #%d\n",tc++);
			String t = new DecimalFormat("0.0").format(maxtime);
			if(one==0)
				out.printf("The last domino falls after %s seconds, at key domino %d.\n",t,two);
			else
				out.printf("The last domino falls after %s seconds, between key dominoes %d and %d.\n",t,one,two);
			out.println();
		}
		
		out.flush();
		out.close();
	}
	static class Pair implements Comparable<Pair>
	{
		int next, time;
		Pair(int x, int y) { next = x; time =  y;}
		public int compareTo(Pair p) {
			
			return this.time - p.time;
		}
		
		
	}
}
