package cp1_3;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
	public class Zapping{
	
		public static void main(String[] args) throws IOException
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			while(true)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(a==-1)
					break;
				
				int presses = Math.abs(b-a);
				if(presses>50)
					presses = 100 - presses;
				
				sb.append(presses+"\n");
			}
			System.out.print(sb);
			
			
		}
	}
