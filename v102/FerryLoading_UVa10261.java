package v102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FerryLoading_UVa10261 {

	static StringBuilder sb;
	static int nCars, carLength[], memo[][];
	
	static int dp(int car, int portRem, int starboardRem)
	{
		if(car == nCars)
			return 0;
		
		if(memo[car][portRem] != -1)
			return memo[car][portRem];
		
		int port = 0, starboard = 0, curLen = carLength[car];
		
		if(curLen <= portRem)		//put in the port side
			port = 1 + dp(car + 1, portRem - curLen, starboardRem);
		
		if(curLen <= starboardRem)	//put in the startboard side
			starboard = 1 + dp(car + 1, portRem, starboardRem - curLen);
		
		return memo[car][portRem] = Math.max(port,starboard);
	}
	
	static void print(int car, int portRem, int starboardRem)
	{
		if(car == nCars)
			return;
		
		int optimal = dp(car, portRem, starboardRem), curLen = carLength[car];
		
		if(curLen <= portRem)
		{
			int port = 1 + dp(car + 1, portRem - curLen, starboardRem);
			if(optimal == port)
			{
				sb.append("port\n");
				print(car + 1, portRem - curLen, starboardRem);
				return;
			}
		}
		
		if(curLen <= starboardRem)
		{
			int starboard = 1 + dp(car + 1, portRem, starboardRem - curLen);
			if(optimal == starboard)
			{
				sb.append("starboard\n");
				print(car + 1, portRem, starboardRem - curLen);
				return;
			}
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{	
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		
		int tc = sc.nextInt();
		while(tc-- > 0)
		{
			int L = sc.nextInt() * 100;
			carLength = new int[500]; 
			nCars = 0;
			
			while(true)
			{
				int curLen = sc.nextInt();
				if(curLen == 0)
					break;
				carLength[nCars++] = curLen;
			}
			
			memo = new int[nCars][L+1];
			for(int i = 0; i < nCars; i++)
				Arrays.fill(memo[i], -1);
	
			sb.append(dp(0, L, L)+"\n");
			print(0, L, L);
			
			if(tc != 0)
				sb.append("\n");
		}
		System.out.print(sb);
		
	}

	
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
	}
}
