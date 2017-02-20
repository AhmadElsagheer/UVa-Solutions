package v002;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class TestingCatcher_UVa231{
 
	
	static int LIS(int[] A, int n)
	{
		ArrayList<Integer> L = new ArrayList<Integer>();

		int lis = 0;
		for (int i = 0; i < n; ++i) 
		{
			int pos = -1, lo = 0, hi = L.size() - 1;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo)/2;
				if(L.get(mid) <= A[i])
				{
					pos = mid;
					lo = mid + 1;
				}
				else
					hi = mid - 1;
			}
			pos++;
			if (pos >= L.size()) L.add(A[i]);
			else                 L.set(pos, A[i]);

			if (pos + 1 > lis)
				lis = pos + 1;
		}
		return lis;
	}

	

	public static void main(String[] args) throws NumberFormatException, IOException 
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] A = new int[1000000];
		int k = 1;
		while(true)
		{
			int x = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if(x==-1)
				break;
			if(k!=1)
				sb.append("\n");
			int n = 0;
			while(x!=-1)
			{
				A[n++] = -x;
				x = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			}
			sb.append("Test #"+k+++":\n  maximum possible interceptions: "+LIS(A,n)+"\n");
		}
		System.out.print(sb);
	}
	  
}

