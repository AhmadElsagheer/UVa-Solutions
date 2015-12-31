package cp3_5;

import java.util.*;
import java.io.*;

public class GameShowMath_UVa10400 {

	static PrintWriter out = new PrintWriter(System.out);
	static final int UNCAL = -1;
	static final int OFFSET = 32000;
	static int[][] memo;
	static int[] value;
	static int N, target;
	
	public static int dp(int i, int val)
	{
		if(i==N)
			if(val==target)
				return 1;
			else
				return 0;
		if(memo[i][val+OFFSET]!=UNCAL)
			return memo[i][val+OFFSET];
		int newValue = val + value[i];
		int plus =  newValue<=32000 && newValue >= -32000 && dp(i+1,newValue)==1?1:0;
		newValue = val - value[i];
		int minus = newValue<=32000 && newValue >= -32000 && dp(i+1,newValue)==1?1:0;
		newValue = val * value[i];
		int mult = newValue<=32000 && newValue >= -32000 && dp(i+1,newValue)==1?1:0;
		int divide = value[i] != 0 && val%value[i]==0 && dp(i+1,val / value[i])==1?1:0;
		
		return memo[i][val+OFFSET] = plus | minus | mult | divide;
	}
	
	public static void print(int i, int val)
	{
		if(i==N)
			out.printf("=%d\n",target);
		else
		{
			int newValue = val + value[i];
			int plus =  newValue<=32000 && newValue >= -32000 && dp(i+1,newValue)==1?1:0;
			if(plus==1){out.printf("+%d",value[i]);print(i+1,newValue);return;}
			newValue = val - value[i];
			int minus = newValue<=32000 && newValue >= -32000 && dp(i+1,newValue)==1?1:0;
			if(minus==1){out.printf("-%d",value[i]);print(i+1,newValue);return;}
			newValue = val * value[i];
			int mult = newValue<=32000 && newValue >= -32000 && dp(i+1,newValue)==1?1:0;
			if(mult==1){out.printf("*%d",value[i]);print(i+1,newValue);return;}
			int divide = value[i] != 0 && val%value[i]==0 && dp(i+1,val / value[i])==1?1:0;
			if(divide==1){out.printf("/%d",value[i]);print(i+1,val / value[i]);return;}
			
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			value = new int[N];
			for(int i = 0; i < N; i++)
				value[i] = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			memo = new int[N][65000];
			for(int i = 0; i < N; i++)
				Arrays.fill(memo[i], UNCAL);
			int res = dp(1,value[0]);
			if(res==0)
				out.println("NO EXPRESSION");
			else
			{
				out.print(value[0]);
				print(1,value[0]);
			}
		}
		out.flush();
		out.close();
	}
}
