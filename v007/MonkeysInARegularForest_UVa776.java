package v007;
import java.util.*;
import java.io.*;

public class MonkeysInARegularForest_UVa776 {

	static class Pair
	{
		int i, j;
		public Pair(int i, int j)
		{
			this.i = i; this.j = j;
		}
	}
	static char[][] forest = new char[2000][2000];
	static int[][] monkeys;
	static boolean[][] visited;
	static int R,C;
	static int[] dx = new int[]{-1,-1,-1,0,0,1,1,1};
	static int[] dy = new int[]{-1,0,1,-1,1,-1,0,1};
	static int counter;
	
	public static void bfs(int i, int j)
	{
		monkeys[i][j] = counter;
		visited[i][j] = true;
		LinkedList<Pair> queue = new LinkedList<Pair>();
		queue.add(new Pair(i,j));
		while(!queue.isEmpty())
		{
			Pair current = queue.remove();
			i = current.i;
			j = current.j;
			for(int k = 0; k < 8; k++)
			{
				if(valid(i+dx[k],j+dy[k]) && !visited[i+dx[k]][j+dy[k]] && forest[i+dx[k]][j+dy[k]]==forest[i][j])
				{
					visited[i+dx[k]][j+dy[k]] = true;
					monkeys[i+dx[k]][j+dy[k]] = counter;
					queue.add(new Pair(i+dx[k],j+dy[k]));
					
				}
			}
			
		}
		counter++;
		
	}
	
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j==-1 || i==R || j==C)
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(br.ready())
		{
			
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = st.countTokens();
			for(int i = 0; i < C; i++)
				forest[0][i] = st.nextToken().charAt(0);
			R = 1;
			while(br.ready())
			{
				
				st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				if(c=='%')
					break;
				forest[R][0] = c;
				for(int i = 1; i < C; i++)
					forest[R][i] = st.nextToken().charAt(0);
				R++;
			}
			
			visited = new boolean[R][C];
			monkeys = new int[R][C];
			counter = 1;
			for(int i = 0; i < R; i++)
				for(int j = 0 ; j < C; j++)
					if(!visited[i][j])
						bfs(i,j);
			

			int[] maxW = new int[C];
			for(int j = 0; j < C; j++)
			{
				int max = monkeys[0][j];
				for(int i = 1; i < R; i++)
					max = Math.max(max, monkeys[i][j]);
				maxW[j] = findW(max);
			}
			for(int i = 0; i < R; i++)
			{	
				for(int j = 0; j < C ; j++)
				{
					int cur = monkeys[i][j];
					int spaces = maxW[j] - findW(cur);
					while(spaces-->0)
						sb.append(" ");
					sb.append(cur);
					if(j==C-1)
						sb.append("\n");
					else
						sb.append(" ");
				}
			}	
			sb.append("%\n");
				

		}
		System.out.print(sb);
	}
	
	public static int findW(int x)
	{
		int w = 0;
		while(x>0)
		{
			w++;
			x /= 10;
		}
		return w;
	}
}
