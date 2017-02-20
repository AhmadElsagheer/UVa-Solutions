package v006;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
	
public class FalseCoin_UVa665{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int coins = Integer.parseInt(st.nextToken());
			int weightings = Integer.parseInt(st.nextToken());
			boolean[] x = new boolean[coins];
			
			for(int j = 0; j < weightings; j++)
			{
				st = new StringTokenizer(br.readLine());
				String s = br.readLine();
				if(s.equals("="))
				{
					int p = Integer.parseInt(st.nextToken())*2;
					for(int k = 0; k < p; k++)
						x[Integer.parseInt(st.nextToken())-1] = true;
				}
				else
				{
					int p = Integer.parseInt(st.nextToken())*2;
					int[] z = new int[p];
					for(int k = 0; k < p; k++)
						z[k] = Integer.parseInt(st.nextToken());
					Arrays.sort(z);
					for(int k = 1; k <= coins; k++)
						if(Arrays.binarySearch(z,k)<0)
							x[k-1] = true;
				}	
			}
			int index = -1;
			for(int j = 0; j < coins; j++)
				if(!x[j])
					if(index==-1)
						index = j;
					else
					{
						index = -1;break;
					}
			sb.append(index+1+"\n");
			if(t-1!=i)
				sb.append("\n");
		}
		System.out.print(sb);
	}
	
}	