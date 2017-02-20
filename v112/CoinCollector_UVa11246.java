package v112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CoinCollector_UVa11246 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] value = new int[N];
			for(int i = 0; i < N; i++)
				value[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(value);
			
			long sum = 0;
			int count = 1;
			for(int i = 0; i < N - 1; i++)
			{
				if(value[i] + sum < value[i+1])
				{
					count++;
					sum += value[i];
				}
			}
			sb.append(count+"\n");
		}
		System.out.print(sb);
	}
}
