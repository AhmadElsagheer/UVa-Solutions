package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class StorageKeepers_UVa10163 {

	static final int INF = 100000000;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 0)
				continue;
			int N = Integer.parseInt(st.nextToken());
			if(N == 0)
				break;
			
			int M = Integer.parseInt(st.nextToken());
			int[] P = new int[M+1];
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= M; i++)
				P[i] = Integer.parseInt(st.nextToken());
			
			int[][] L = new int[M+1][N+1];		//L[i][j] : max safety for j storages using the first i keepers
			for(int i = 0; i <= M; i++)
				L[i][0] = INF;
			for(int i = 1; i <= M; i++)
				for(int j = 1; j <= N; j++)
				{
					L[i][j] = L[i-1][j];
					for(int k = 1; k <= j; k++)
						L[i][j] = Math.max(L[i][j], Math.min(L[i-1][j-k], P[i] / k));
				}
			
			int[][] Y = new int[M+1][N+1];		//Y[i][j] : min cost for j storages using first i keepers under the required condition
			for(int i = 0; i <= M; i++)
			{
				Arrays.fill(Y[i], INF);
				Y[i][0] = 0;
			}
			int best_L = L[M][N];
			for(int i = 1;i <= M; i++)
				for(int j = 1; j <= N; j++)
				{
					Y[i][j] = Y[i-1][j];
					for(int k = 1; k <= j; k++)
						if(P[i] / k >= best_L)
							Y[i][j] = Math.min(Y[i][j], Y[i-1][j-k] + P[i]);
				}
						
			sb.append(L[M][N]+" "+(L[M][N]==0?0:Y[M][N])+"\n");
		}
		System.out.print(sb);
	}
}
