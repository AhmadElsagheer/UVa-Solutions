<<<<<<< HEAD
package cp1_3;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class BlowingFuses{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();int k = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n==0)
				break;
			int m = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int maxP = 0;int curP = 0; boolean willBlow = false;
			int[] state = new int[n];
			int[] consumption = new int[n];
			for(int i = 0; i < n; i++)
				consumption[i] = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++)
			{
				int device = Integer.parseInt(br.readLine())-1;
				if(state[device]==0)
				{
					curP += consumption[device];
					if(curP>c)
						willBlow = true;
					if(curP>maxP)
						maxP = curP;
				}
				else
					curP -= consumption[device];
				state[device] = (state[device]+1)%2;
			}
			sb.append("Sequence "+k+++"\n");
			if(willBlow)
				sb.append("Fuse was blown.\n");
			else
				sb.append("Fuse was not blown.\nMaximal power consumption was "+maxP+" amperes.\n");
			sb.append("\n");
		}
		System.out.print(sb);

	}
		
}
=======
package cp1_3;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class BlowingFuses{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();int k = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n==0)
				break;
			int m = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int maxP = 0;int curP = 0; boolean willBlow = false;
			int[] state = new int[n];
			int[] consumption = new int[n];
			for(int i = 0; i < n; i++)
				consumption[i] = Integer.parseInt(br.readLine());
			for(int i = 0; i < m; i++)
			{
				int device = Integer.parseInt(br.readLine())-1;
				if(state[device]==0)
				{
					curP += consumption[device];
					if(curP>c)
						willBlow = true;
					if(curP>maxP)
						maxP = curP;
				}
				else
					curP -= consumption[device];
				state[device] = (state[device]+1)%2;
			}
			sb.append("Sequence "+k+++"\n");
			if(willBlow)
				sb.append("Fuse was blown.\n");
			else
				sb.append("Fuse was not blown.\nMaximal power consumption was "+maxP+" amperes.\n");
			sb.append("\n");
		}
		System.out.print(sb);

	}
		
}
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
