package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FerryLoading_UVa10261 {

	static int[][] memo;
	static int nCars,L, carLength[], cumLength[];
	static final int INF = 100000;
	static final int UNCAL = -1;
	static int[][][] next;
	
	public static int dp(int car, int portRem)
	{
		int starboardRem = L*2 - portRem - cumLength[car-1];
		if(starboardRem<0 || portRem <0)
			return -INF;
		
		if(car==nCars+1)
			return 0;
		
		if(carLength[car]>portRem && carLength[car]>starboardRem)
			return 0;
		
		if(memo[car][portRem]!=UNCAL)
			return memo[car][portRem];
	
		int port = 1 + dp(car+1, portRem-carLength[car]);
		int starboard = 1 + dp(car+1, portRem);
		if(port>=starboard)
		{
			next[car][portRem][0] = 0;		//port
			next[car][portRem][1] = portRem - carLength[car];
		}
		else
		{
			next[car][portRem][0] = 1;		//starboard
			next[car][portRem][1] = portRem;
		}
		return memo[car][portRem] = Math.max(port,starboard);
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(TC-->0)
		{
			br.readLine();
			L = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()) * 100;
			int length;
			carLength = new int[500]; cumLength = new int[500]; nCars = 0;
			while((length=Integer.parseInt(new StringTokenizer(br.readLine()).nextToken()))!=0)
			{
				carLength[++nCars] = length;
				cumLength[nCars] = cumLength[nCars-1] + length;
			}
			memo = new int[nCars+1][L+1];
			for(int i = 0; i < nCars + 1; i++)
				Arrays.fill(memo[i], UNCAL);
			next = new int[nCars+1][L+1][2];
			int cars = dp(1,L);
			sb.append(cars+"\n");int x = L;
			for(int i = 1; i <= cars; i++)
			{
				if(next[i][x][0]==0)
					sb.append("port\n");
				else
					sb.append("starboard\n");
				x = next[i][x][1];
			}
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
		
	}

}
