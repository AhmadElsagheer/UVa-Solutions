package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TheDieisCast_UVa657 {

	static char[][] picture;
	static int R,C;
	static int[] dx = new int[]{0,0,-1,1};
	static int[] dy = new int[]{-1,1,0,0};
	static boolean[][] cal;
	static int counter;
	
	public static void mark(int i, int j)
	{
		picture[i][j] = '*';
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y) && picture[x][y]=='X')
				mark(x,y);
		}
		
	}
	
	public static void dfs(int i, int j)
	{
		if(picture[i][j]=='X')
		{
			counter++;
			mark(i,j);
		}
		picture[i][j] = '.';
		for(int k = 0; k < 4; k++)
		{
			int x = i + dx[k];
			int y = j + dy[k];
			if(valid(x,y))
				dfs(x,y);
		}
	}
	public static boolean valid(int i, int j)
	{
		if(i==-1 || j == -1 || i == R || j== C || picture[i][j]=='.')
			return false;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);int k = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if(R==0)
				break;
			picture = new char[R][];
			for(int i  = 0; i < R; i++)
				picture[i] = br.readLine().toCharArray();
			ArrayList<Integer> dots = new ArrayList<Integer>(3000);
			cal = new boolean[R][C];
			for(int i = 0; i < R; i++)
				for(int j = 0; j < C; j++)
					if(picture[i][j]!='.')
					{
						counter = 0;
						dfs(i,j);
						dots.add(counter);
					}
			Collections.sort(dots);
			out.printf("Throw %d\n",k++);
			boolean first = true;
			for(int i = 0; i < dots.size(); i++)
			{
				int x = dots.get(i);
				if(x==0)
					continue;
				if(first)
				{
					first = false;
					out.printf("%d",x);
				}
				else
					out.printf(" %d",x);
			}
			out.println("\n");
		}
		
		out.flush();
		out.close();
	}
}
