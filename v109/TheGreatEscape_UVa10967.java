package v109;
import java.util.*;
import java.io.*;

public class TheGreatEscape_UVa10967 {

	static char[][] castle;
	static int[][] doorTime;
	static int R,C;
	static int INF = 500000000;
	static int[] dx = new int[]{0,-1,0,1};
	static int[] dy = new int[]{-1,0,1,0};
	
	public static int dijkstra()
	{
		int[][] totalTime = new int[R][C];
		for(int i = 0; i < R; i++)
			Arrays.fill(totalTime[i], INF);
		totalTime[R-1][0] = 0;
		PriorityQueue<Triple> q = new PriorityQueue<Triple>();
		q.add(new Triple(R-1,0,0));
		while(!q.isEmpty())
		{
			Triple cur = q.remove();
			if(cur.time > totalTime[cur.i][cur.j])
				continue;
			for(int k = 0; k < 4; k++)
			{
				int x = cur.i + dx[k];
				int y = cur.j + dy[k];
				if(valid(x,y) && (castle[x][y]=='.' || map(castle[x][y])==k))
				{
					int curTime = cur.time + 1 + calTime(cur.i,cur.j,k);
					if(curTime < totalTime[x][y])
					{
						totalTime[x][y] = curTime;
						q.add(new Triple(x,y,curTime));
					}
				}
			}
		}
		return totalTime[0][C-1];
	}
	
	public static int calTime(int i, int j, int k)
	{
		if(castle[i][j]=='.')
			return 0;
		int x = (map(castle[i][j])+2)%4;
		int f = (x-k+4)%4;
		if(f==3)
			f = 1;
		return doorTime[i][j] * f;
		
	}
	
	public static int map(char c)
	{
		switch(c)
		{
		case 'E':return 0;
		case 'S':return 1;
		case 'W':return 2;
		case 'N':return 3;
		default: return -1;
		}
	}
	
	public static boolean valid(int i, int j)
	{
		if(i == -1 || j == -1 || i == R || j == C)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while(TC-->0)
		{
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			castle = new char[R][];
			doorTime = new int[R][C];
			for(int i = 0; i < R; i++)
				castle[i] = br.readLine().toCharArray();
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(castle[i][j]!='#' && castle[i][j]!='.')
					{
						if(!st.hasMoreTokens())
							st = new StringTokenizer(br.readLine());
						doorTime[i][j] = Integer.parseInt(st.nextToken());
					}
			int min = dijkstra();
			if(min==INF)
				out.println("Poor Kianoosh");
			else
				out.printf("%d\n",min);
		}
		out.flush();
		out.close();
	}
	
	static class Triple implements Comparable<Triple>
	{
		int i, j, time;
		Triple(int x, int y, int z){i = x; j = y; time = z;}
		public int compareTo(Triple o) {return this.time - o.time;}
	}
}