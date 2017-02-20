package v110;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boxes_UVa11003 {

	static final int UNCAL = -1, INF = 6000;
	static int[][] memo;
	static int N;
	static int[] weight, boxMaxLoad;
	
	
	public static int dp(int box, int maxLoad)
	{
		if(maxLoad < 0)
			return -INF;
		if(box==N || maxLoad == 0)
			return 0;
		if(memo[box][maxLoad]!=UNCAL)
			return memo[box][maxLoad];
		int put = 1 + dp(box+1,Math.min(maxLoad - weight[box], boxMaxLoad[box]));
		int ignore = dp(box+1,maxLoad);
		return memo[box][maxLoad] = Math.max(put, ignore);
	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			weight = new int[N];
			boxMaxLoad = new int[N];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				weight[i] = Integer.parseInt(st.nextToken());
				boxMaxLoad[i] = Integer.parseInt(st.nextToken());
			}
			
			//memo
			memo = new int[N][6001];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			//call
			int max = dp(0,INF);
			//output
			sb.append(max+"\n");
		}
		System.out.print(sb);
	}
}
