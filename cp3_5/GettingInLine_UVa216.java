package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GettingInLine_UVa216 {

	static PrintWriter out = new PrintWriter(System.out);
	static int N, counter;
	static boolean[] visited;
	static double min;
	static int[] sol, trial;
	static int[][] pos;
	static double[][] adjMatrix;
	
	public static void dfs(int k, int i, double sum)
	{
		trial[k] = i;
		if(k==N-1)
		{
			if(sum<min)
			{
				for(int j = 0; j < N; j++)
					sol[j] = trial[j];
				min = sum;
			}
			return;
		}
		visited[i] = true;
		for(int j = 0; j < N; j++)
			if(!visited[j])
				dfs(k+1,j,sum + adjMatrix[i][j]) ;
		visited[i] = false;
	}

	
	public static void print()
	{
		out.print("**********************************************************\n");
		out.printf("Network #%d\n",++counter);
		for(int i = 0; i < N - 1; i++)
			out.printf("Cable requirement to connect (%d,%d) to (%d,%d) is %s feet.\n",pos[sol[i]][0],
					pos[sol[i]][1],pos[sol[i+1]][0],pos[sol[i+1]][1],roundDouble(adjMatrix[sol[i]][sol[i+1]]));
		out.printf("Number of feet of cable required is %s.\n",roundDouble(min));
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			pos = new int[N][2];
			StringTokenizer st;
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				pos[i][0] = Integer.parseInt(st.nextToken());
				pos[i][1] = Integer.parseInt(st.nextToken());
			}
			adjMatrix = new double[N][N];
			for(int i = 0; i < N; i++)
				for(int j = i + 1; j < N; j++)
					adjMatrix[i][j] = adjMatrix[j][i] = Math.hypot(pos[i][0]-pos[j][0], pos[i][1]-pos[j][1]) + 16;
			min = 3000000;
			trial = new int[N];
			sol = new int[N];
			visited = new boolean[N];
			for(int i = 0; i < N; i++)
				dfs(0,i,0);
			print();
		}
		out.flush();
	}
	
	static String roundDouble(double x)
	{
		return new DecimalFormat("0.00").format(Math.round(x*100)/100.0);
	}
}
