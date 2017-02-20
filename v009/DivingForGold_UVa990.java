package v009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class DivingForGold_UVa990 {

	static final int UNCAL = -1;
	static final int INF = 100000000;
	
	static int N;
	static int[] time, value, depth;
	
	static int[][] memo;
	static int[][] next;
	
	//Top-down Approach
	public static int dp(int tr, int remTime)
	{
		if(remTime < 0)
			return -INF;
		if(tr==N ||remTime == 0)
			return 0;
		if(memo[tr][remTime]!=UNCAL)
			return memo[tr][remTime];
		int take = value[tr]+dp(tr+1,remTime-time[tr]);
		int leave = dp(tr+1,remTime);
		
		int max;
		if(take>=leave)
		{
			max = take; next[tr][remTime] = remTime - time[tr];
		}
		else
		{
			max = leave;next[tr][remTime] = remTime;
		}
		return memo[tr][remTime] = max;
		
	}
	
	//Top-Down Approach
	static int[] memoBottom;
	static boolean[][] taken;
	public static int dp(int t)
	{
		memoBottom = new int[t+1];
		taken = new boolean[t+1][N];
		for(int i = 1; i <= t; i++)
		{
			int max = 0;int chosen = -1;
			for(int j = 0; j < N; j++)
			{
				if(i-time[j]<0 || taken[i-time[j]][j])
					continue;
				int cur = value[j] + memoBottom[i-time[j]];
				if(cur>max)
				{
					max = cur;
					chosen = j;
				}
			}
			memoBottom[i] = max;
			if(chosen!=-1)
			{
				taken[i][chosen] = true;
				copy(i-time[chosen],i);
			}
		}
		
		return memoBottom[t];
	}
	
	public static void copy(int x, int y)
	{
		for(int i  = 0; i < N; i++)
			if(taken[x][i])
				taken[y][i] = true;
	}
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int k = 0;
		
		while(br.ready())
		{
			if(k==0)
				k = 1;
			else
			{
				sb.append("\n");
				br.readLine();
			}
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t  = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()) * 3;
			N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			depth = new int[N];
			time = new int[N];
			value = new int[N]; 
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				depth[i] = Integer.parseInt(st.nextToken());
				value[i] = Integer.parseInt(st.nextToken());
				time[i] = depth[i]*w;
			}
			memo = new int[N][t+1];
			next = new int[N][t+1];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			int maxTreasure = dp(0,t);
			LinkedList<Integer> Taken = new LinkedList<Integer>();
			int rem = t;
			for(int i = 0; i < N; i++)
			{
				if(next[i][rem]!=rem)
					Taken.add(i);
				rem = next[i][rem];
			}
			sb.append(maxTreasure+"\n").append(Taken.size()+"\n");
			while(!Taken.isEmpty())
			{
				int tr = Taken.remove();
				sb.append(depth[tr]+" "+value[tr]+"\n");
			}
			
//			sb.append("Trial\n");
//			sb.append(dp(t)+"\n");
//			String items = "";int count = 0;
//			for(int i = 0; i < N; i++)
//				if(taken[t][i])
//				{
//					count++;
//					items = depth[i]+" "+value[i]+"\n";
//				}
//			sb.append(count+"\n"+items);
		}
		
		System.out.print(sb);
	}
}
