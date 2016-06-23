package cp3_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BestCoalitions_UVa11658 {

	static int N;
	static int[] holders;
	static final int INF = 1000000;
	static final int UNCAL = -1;
	static int[][] memo;
	public static int dp(int holder, int percentage)
	{
		if(percentage > 5000)
			return 0;
		if(holder==N)
			return INF;
		if(memo[holder][percentage]!=UNCAL)
			return memo[holder][percentage];
		int take = holders[holder] + dp(holder+1,percentage+holders[holder]);
		int ignore = dp(holder+1,percentage);
		
		return memo[holder][percentage] = Math.min(take, ignore);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()) - 1;
			if(N==-1)
				break;
			int x = Integer.parseInt(st.nextToken());
			int myHolder = -1;
			holders = new int[N];int k = 0;
			for(int i = 1; i <= N + 1; i++)
			{
				st = new StringTokenizer(br.readLine(),".");
				
				int value = Integer.parseInt(st.nextToken())*100+Integer.parseInt(st.nextToken());
				if(i==x)
					myHolder = value;
				
				else
					holders[k++] = value;
			}
			if(myHolder > 5000)
				sb.append("100.00\n");
			else
			{
				memo = new int[N][5001];
				for(int i = 0; i < N; i++)
					Arrays.fill(memo[i], UNCAL);
				int min = dp(0,myHolder) + myHolder;

				double percentage = myHolder*100.0/min;
				percentage = Math.round(percentage*100)/100.0;
				
				sb.append(new DecimalFormat("0.00").format(percentage)+"\n");
			}
		}
		System.out.print(sb);
	}
}
