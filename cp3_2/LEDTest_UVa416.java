package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LEDTest_UVa416 {

	static String[] map = new String[]{"YYYYYYN","NYYNNNN","YYNYYNY","YYYYNNY","NYYNNYY","YNYYNYY","YNYYYYY","YYYNNNN","YYYYYYY","YYYYNYY"};
	static String[] sequence;
	static boolean[] failed;
	static int n;
	static boolean equals(String x, int i)
	{
		String y = map[i];
		for(int k = 0; k < 7; k++)
			if(x.charAt(k)=='Y')
			{
				 if(failed[k] || y.charAt(k)=='N')
					return false;
			}
			else
			{
				if(y.charAt(k)=='Y')
					failed[k] = true;
			}
		return true;
	}
	
	static boolean backtrack(int cur, int i)
	{
		if(cur==n)
			return true;
		if(i<0)
			return false;
		if(equals(sequence[cur],i))
			return backtrack(cur+1,i-1);
		return false;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if(n==0)
				break;
			sequence = new String[n];
			for(int i = 0; i < n; i++)
				sequence[i] = br.readLine();
			boolean match = false;
			for(int i = 9; i >= 0 && !match; i--)
			{
				failed = new boolean[7];
				if(backtrack(0,i))
					match = true;
			}
			sb.append(match?"MATCH\n":"MISMATCH\n");		
			
		}
		System.out.print(sb);
	}
	
	
	
	
}
