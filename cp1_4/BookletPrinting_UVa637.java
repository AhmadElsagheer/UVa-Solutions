package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookletPrinting_UVa637 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			int N = (n+3)/4 * 4;
			
			int[] arr = new int[]{N,1,2,N-1};
			sb.append("Printing order for "+n+" pages:\n");
			for(int i = 1; i <= N >> 2; i++)
			{
				if(arr[0] <= n || arr[1] <= n)
					sb.append("Sheet "+i+", front: "+(arr[0]>n?"Blank":arr[0])+", "+(arr[1]>n?"Blank":arr[1])+"\n");
				
				if(arr[2] <= n || arr[3] <= n)
					sb.append("Sheet "+i+", back : "+(arr[2]>n?"Blank":arr[2])+", "+(arr[3]>n?"Blank":arr[3])+"\n");
				
				arr[0]-=2;arr[1]+=2;arr[2]+=2;arr[3]-=2;
			}
		}
		System.out.print(sb);
	}
}
