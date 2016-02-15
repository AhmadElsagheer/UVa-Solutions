<<<<<<< HEAD
package cp1_3;
	
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
	
public class ZeroesAndOnes{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(br.ready())
		{
			String s = br.readLine();
			if(s.equals(""))
				break;
			sb.append("Case "+k+++":\n");
			
			int t = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < t; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(x>y)
				{
					int tmp = x;
					x = y;
					y = tmp;
				}
				int j;
				for(j = x; j < y; j++)
					if(s.charAt(j)!=s.charAt(j+1))
						break;
				if(j!=y)
					sb.append("No\n");
				else
					sb.append("Yes\n");
			}
		}
		System.out.print(sb);
				

	}
		
}
=======
package cp1_3;
	
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;
	
public class ZeroesAndOnes{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(br.ready())
		{
			String s = br.readLine();
			if(s.equals(""))
				break;
			sb.append("Case "+k+++":\n");
			
			int t = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < t; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if(x>y)
				{
					int tmp = x;
					x = y;
					y = tmp;
				}
				int j;
				for(j = x; j < y; j++)
					if(s.charAt(j)!=s.charAt(j+1))
						break;
				if(j!=y)
					sb.append("No\n");
				else
					sb.append("Yes\n");
			}
		}
		System.out.print(sb);
				

	}
		
}
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
