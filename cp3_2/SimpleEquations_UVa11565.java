package cp3_2;


	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class SimpleEquations_UVa11565{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
				
		int t = Integer.parseInt(br.readLine());
		for(int k = 0; k < t; k++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			boolean found = false;
			for(int a = -57; a * a * 3 <= 10000 && !found; a++)
				for(int b = a + 1; a * a + b * b * 2 <= 10000 && !found; b++)
					for(int c = b + 1; a * a+ b * b + c * c <= 10000 && !found; c++)
						if(a+b+c==A && a*b*c==B && a*a+b*b+c*c==C)
						{
							found = true;
							sb.append(a+" "+b+" "+c+"\n");
						}
									
			if(!found)
				sb.append("No solution.\n");
		}
		
		
		System.out.print(sb);

		
		
	}
}	