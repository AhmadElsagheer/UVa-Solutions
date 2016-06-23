package cp3_2;


	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Division_UVa725 {
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int k = 0;
		while(true)
		{
			String x = br.readLine();
			if(x.equals("0"))
				break;
			
			if(k!=0)
				sb.append("\n");
			else
				k++;
			
			int n = Integer.parseInt(x);
			
			int start = 01234;
			int end = 98765/n;
			
			boolean found = false;
			
			for(int i = start; i <= end; i++)
			{
				int num = i*n;
				if(unique(i,num))
				{
					String output1 = String.format("%05d", num);
					String output2 = String.format("%05d", i);
					sb.append(output1+" / "+output2+" = "+n+"\n");
					found = true;
				}
			}
			
			if(!found)
				sb.append("There are no solutions for "+n+".\n");
				
			
		}
		
		System.out.print(sb);

		
		
	}
	
	public static boolean unique(int n, int m)
	{
		int[] numbers = new int[10];
		for(int i = 0; i < 5; i++)
		{
			int x = n%10;
			if(numbers[x]==1)
				return false;
			numbers[x]++;
			n = n/10;
		}
		for(int i = 0; i < 5; i++)
		{
			int x = m%10;
			if(numbers[x]==1)
				return false;
			numbers[x]++;
			m = m/10;
		}
		return true;
	}
	

	
}	
