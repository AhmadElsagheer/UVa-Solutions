package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class VitoFamily_UVa10041 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int[] locations = new int[R];
			for(int i = 0; i < R; i++)
				locations[i] = Integer.parseInt(st.nextToken());
				
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < R; i++)
			{
				int cur = locations[i];
				int d = 0;
				for(int j = 0; j < R; j++)
					d += Math.abs(cur-locations[j]);
				min = Math.min(min, d);
			}
			sb.append(min).append("\n");
			
		}
		System.out.print(sb);
	}
}
