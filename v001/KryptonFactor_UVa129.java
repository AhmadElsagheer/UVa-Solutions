package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class KryptonFactor_UVa129 {

	
	static int L, N;
	static int count;
	static String res;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			if(N==0)
				break;
			count = 0;
			backtrack("");
			int length = res.length();
			int groups = 0;
			for(int k = 0; k < length; )
			{
				for(int i = 0; i < 4 && k < length; i++, k++)
					sb.append(res.charAt(k));
				if(k==length)
					groups = 16;
				else
					groups++;
				if(groups!=16)
					sb.append(" ");
				else
				{
					sb.append("\n");
					groups = 0;
				}
			}
			sb.append(length).append("\n");
				
		}
		
		System.out.print(sb);
		
	}
	static boolean backtrack(String x)
	{
		for(int i = 0; i < L; i++)
		{
			String y = x + (char)(i+'A');
			if(possible(y))
			{
				count++;
				if(count==N)
				{
					res = y;
					return true;
				}
				if(backtrack(y))
					return true;
			}
		}
		return false;
			
	}
	
	static boolean possible(String x)
	{
		int j = x.length() - 1;
		while(true)
		{
			int i = (j << 1) - x.length();
			if(i < 0)
				break;
			if(x.substring(i, j).equals(x.substring(j)))
				return false;
			j--;
		}
		return true;
	}
	
	
}
