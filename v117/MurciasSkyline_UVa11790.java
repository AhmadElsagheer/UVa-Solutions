package v117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MurciasSkyline_UVa11790 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			sb.append("Case "+k+". ");
			int N = Integer.parseInt(br.readLine());
			int[] H = new int[N];
			int[] W = new int[N];
			int max1, max2;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				H[i] = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
				W[i] = Integer.parseInt(st.nextToken());
			
			int[] LIS = new int[N];
			
			max1 = LIS[0] = W[0];
			for(int i = 1; i < N; i++)
			{
				LIS[i] = W[i];
				for(int j = 0; j < i; j++)
					if(H[j] < H[i] && LIS[j] + W[i] > LIS[i])
						LIS[i] = LIS[j] + W[i];
				max1 = Math.max(max1, LIS[i]);
			}
			
			
			LIS = new int[N];
			
			max2 = LIS[0] = W[0];
			for(int i = 1; i < N; i++)
			{
				LIS[i] = W[i];
				for(int j = 0; j < i; j++)
					if(H[j] > H[i] && LIS[j] + W[i] > LIS[i])
						LIS[i] = LIS[j] + W[i];
				max2 = Math.max(max2, LIS[i]);
			}
			if(max1>=max2)
				sb.append("Increasing ("+max1+"). ").append("Decreasing ("+max2+").\n");
			else
				sb.append("Decreasing ("+max2+"). ").append("Increasing ("+max1+").\n");
			
		}
		System.out.print(sb);
	}
}
