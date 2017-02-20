package v005;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class BoxOfBricks_UVa591{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();int k = 1;
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] blocks = new int[n];
			int sum = 0;
			for(int i = 0; i < n; i++)
			{
				blocks[i] = Integer.parseInt(st.nextToken());
				sum += blocks[i];
			}
			int count = 0;
			for(int i = 0; i < n; i++)
			{
				if(blocks[i]<sum/n)
					count += sum/n - blocks[i];
			}
			
			sb.append("Set #"+k+++"\nThe minimum number of moves is "+count+".\n\n");
		}
		System.out.print(sb);
		
	}
	
}	