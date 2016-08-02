package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class MultiplyingByRotation_UVa550 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int base = Integer.parseInt(st.nextToken());
			int least = Integer.parseInt(st.nextToken());
			int factor = Integer.parseInt(st.nextToken());
			LinkedList<Integer> digits = new LinkedList<Integer>();
			
			digits.push(least);
			int product;int rem = 0;
			while((product=rem+factor*digits.peek())!=least)
			{
				digits.push(product%base);
				rem = product/base;
			}
			sb.append(digits.size()+"\n");
			
		}
		System.out.print(sb);
		
	}
}
