package cp2_2;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Newspaper{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();StringTokenizer st;
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++)
		{
			int k = Integer.parseInt(br.readLine());
			HashMap<Character,Integer> h = new HashMap<Character,Integer>();
			for(int j = 0; j < k; j++)
			{
				st = new StringTokenizer(br.readLine());
				h.put(st.nextToken().charAt(0),Integer.parseInt(st.nextToken()));
			}
			int m = Integer.parseInt(br.readLine());
			int cost = 0;
			for(int j = 0; j < m; j++)
			{
				String s = br.readLine();
				for(int x = 0; x < s.length(); x++)
				{
					Integer z = h.get(s.charAt(x));
					if(z!=null)
						cost += z;
				}
					
			}
		
			sb.append(new DecimalFormat("0.00").format(cost/100.0)+"$\n");
		}
		System.out.print(sb);
	}
	
	
	
}		