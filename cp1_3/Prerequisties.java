package cp1_3;
	
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
	
public class Prerequisties{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			if(k==0)
				break;
			int m = Integer.parseInt(st.nextToken());
			HashSet<String> h = new HashSet<String>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < k; i++)
				h.add(st.nextToken());
			boolean possible = true;
			for(int i = 0; i < m; i++)
			{
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				while(r!=0&&c>0)
				{
					String course = st.nextToken();
					if(h.contains(course))
						r--;
					c--;
				}
				if(r!=0)
					possible = false;
			}
			if(possible)
				sb.append("yes\n");
			else
				sb.append("no\n");
			
		}
		System.out.print(sb);
	
	
	}
}	