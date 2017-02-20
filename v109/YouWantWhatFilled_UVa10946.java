package v109;


import java.util.*;
import java.io.*;

public class YouWantWhatFilled_UVa10946 {

	static class Pair
	{
		int i, j;
		Pair(int x, int y)
		{
			i = x; j =y;
		}
	}
	static class Pair2 implements Comparable<Pair2>
	{
		char c; int occ;
		Pair2(char x, int y)
		{
			c = x; occ = y;
		}
		@Override
		public int compareTo(Pair2 o) {
			if(o.occ!=this.occ)
				return o.occ - this.occ;
			return this.c - o.c;
		}
		
		
	}
	
	static char[][] graph;
	static ArrayList<Pair2> output;
	static boolean[][] visited;
	static int[] dx = new int[]{-1,0,0,1};
	static int[] dy = new int[]{0,-1,1,0};
	static int R,C;
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j == -1 || i == R || j==C)
			return false;
		return true;
	}
	
	public static void bfs(int i, int j)
	{
		if(graph[i][j]=='.')
			return;
		visited[i][j] = true;
		char c = graph[i][j];
		int area = 0;
		LinkedList<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(i,j));
		while(!q.isEmpty())
		{
			Pair cur = q.remove();
			area++;
			i = cur.i; j = cur.j;
			for(int k = 0; k < 4; k++)
			{
				int x = i + dx[k]; int y = j + dy[k];
				if(valid(x,y) && !visited[x][y] && graph[x][y]==graph[i][j])
				{
					q.add(new Pair(x,y));
					visited[x][y] = true;
				}
			}
		}
		
		
		output.add(new Pair2(c,area));
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(true)
		{
			StringTokenizer st =new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if(R==0 && C==0)
				break;
			graph = new char[R][];
			for(int i = 0; i < R; i++)
				graph[i] = br.readLine().toCharArray();
			visited = new boolean[R][C];
			output = new ArrayList<Pair2>(R*C);
			for(int i = 0;  i < R; i++)
				for(int j = 0; j < C; j++)
					if(!visited[i][j])
						bfs(i,j);
			sb.append("Problem "+k+++":\n");
			Collections.sort(output);
			for(int i = 0, size = output.size(); i < size; i++)
			{
				Pair2 cur = output.get(i);
				sb.append(cur.c+" "+cur.occ+"\n");
			}
		}
		System.out.print(sb);
	}
}
