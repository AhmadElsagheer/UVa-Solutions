package cp3_5;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class TermStrategy_UVa11341 {

	static final int UNCAL = -1;
	static final int INF = 1000000;
	static int M, N;
	static int[][] grades;
	static int[][] memo;
	
	public static int dp(int exam, int hours)
	{
		if(hours<0)
			return -INF;
		if(exam==N)
			return 0;
		if(memo[exam][hours]!=UNCAL)
			return memo[exam][hours];
		int max = 0;
		for(int j = 1; j <= M; j++)
		{
			if(grades[exam][j-1]<5)
				continue;
			max = Math.max(max, grades[exam][j-1]+dp(exam+1,hours-j));
		}
		if(max==0)
			return memo[exam][hours] = -INF;
		return memo[exam][hours] = max;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb =new StringBuilder();
		int TC  = Integer.parseInt(br.readLine());
		
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			grades = new int[N][M];
			memo = new int[N][M+1];
			for(int i = 0; i < N; i++)
			{
				Arrays.fill(memo[i], UNCAL);
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++)
					grades[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int max = dp(0,M);
			if(max==-INF)
				sb.append("Peter, you shouldn't have played billiard that much.\n");
			else
				sb.append("Maximal possible average mark - "+new DecimalFormat("0.00").format(Math.round(100.0*max/N)/100.0)+".\n");
				



		}
		
		System.out.print(sb);
	}
}
