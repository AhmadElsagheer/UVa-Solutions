package cp3_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WhatIsTheMarble_UVa10474 {

	static int bs(int[] A, int t)
	{
		int lo = 0, hi = A.length - 1, ans = -1;
		while(lo <= hi)
		{
			int mid = lo + (hi - lo)/2;
			if(A[mid] == t)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				if(A[mid] > t)
					hi = mid - 1;
				else
					lo = mid + 1;
		}
		return ans;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			if(n == 0 && q == 0)
				break;
			sb.append("CASE# "+k+++":\n");
			int[] A = new int[n];
			for(int i = 0; i < n; i++)
				A[i] = Integer.parseInt(br.readLine());
			Arrays.sort(A);
			while(q-->0)
			{
				int t = Integer.parseInt(br.readLine());
				int ans = bs(A,t) + 1;
				if(ans != 0)
					sb.append(t+" found at "+ans+"\n");
				else
					sb.append(t+" not found\n");
			}
		}
		System.out.print(sb);
	}
}
