package v109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FormingQuizTeams_UVa10911 {

	
	static double[] memo;
	static final double UNCAL = -1, INF = 10000;
	static int[][] location;
	static int N;
	public static double dp(int pairs)
	{
		if((pairs ^ ((1<<N) - 1))==0)
			return 0;
			
		if(memo[pairs]!=UNCAL)
			return memo[pairs];
		
		double min = INF;
		int x = ~pairs;
		int first = x & (-x);
		first = (int) (Math.log(first)/Math.log(2));
		for(int i = first + 1; i < N; i++)
		{
			if((pairs & (1<<i)) != 0)
				continue;
			int newPair = pairs | (1 << i);
			newPair |= 1 << first;
			min = Math.min(min,Math.sqrt((location[first][0]-location[i][0])*(location[first][0]-location[i][0])+(location[first][1]-location[i][1])*(location[first][1]-location[i][1]))+ dp(newPair));
		}
		return memo[pairs] = min;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int k = 1;
		while(true)
		{
			N = Integer.parseInt(br.readLine()) * 2;
			if(N==0)
				break;
			
			location = new int[N][2];
			for(int i  = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				
				location[i][0] = Integer.parseInt(st.nextToken());
				location[i][1] = Integer.parseInt(st.nextToken());
			}
			
			memo = new double[2000000];
			Arrays.fill(memo, UNCAL);
			double min  = Math.round(100*dp(0))/100.0;
			sb.append("Case "+k+++": ").append(new DecimalFormat("0.00").format(min)+"\n");
		}
		System.out.print(sb);
		
	}
}
