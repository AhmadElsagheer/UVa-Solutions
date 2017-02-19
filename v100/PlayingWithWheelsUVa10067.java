package v100;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class PlayingWithWheelsUVa10067 {

	static final int FORB = -1;
	static int[] dist;
	static int[] d = new int[]{1,-1,10,-10,100,-100,1000,-1000};
	
	public static int bfs(int x, int y)
	{
		dist[x] = 0;
		boolean[] visited = new boolean[10000];
		visited[x] = true;
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(x);
		while(!q.isEmpty())
		{
			int cur = q.remove();
			for(int i = 0; i < 8; i++)
			{
				int f = Math.abs(d[i])*10;
				int next = cur/f*f + (cur%f + d[i] + f)%f;
				if(next > - 1 && next < 10000 && !visited[next] && dist[next]!=FORB)
				{
					visited[next] = true;
					dist[next] = dist[cur] + 1;
					q.add(next);
				}
			}
		}
		return visited[y]?dist[y]:-1;
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int TC = sc.nextInt();
		dist = new int[10000];
		while(TC-->0)
		{
			Arrays.fill(dist, 0);
			int factor = 1000;
			int start = 0;
			for(int i = 0; i < 4; i++)
			{
				start += factor*sc.nextInt();
				factor /= 10;
			}
			int target = 0;
			factor = 1000;
			for(int i = 0; i < 4; i++)
			{
				target += factor*sc.nextInt();
				factor /= 10;
			}
			int N = sc.nextInt();
			while(N-->0)
			{
				int forb = 0;
				factor = 1000;
				for(int i = 0; i < 4; i++)
				{
					forb += factor*sc.nextInt();
					factor /= 10;
				}
				dist[forb] = -1;
			}
			out.println(bfs(start,target));
		}
		out.flush();
		
	}
}
