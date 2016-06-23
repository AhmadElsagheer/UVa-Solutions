package cp5_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class kProblem_UVa10025 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			
			long k = Math.abs(Integer.parseInt(br.readLine()));
			int N = (int) Math.ceil((-1+Math.sqrt(1+8*k))/2);
			int diff = (int) (N*(N+1)/2 - k);
			
			if(diff%2==1)
			{
				if((N+1-diff)%2==0 && (N+1-diff)/2 <= N)
					N+=1;
				else
					N += 2;
			}
			if(k==0)
				sb.append("3\n");
			else
				sb.append(N+"\n");		
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
