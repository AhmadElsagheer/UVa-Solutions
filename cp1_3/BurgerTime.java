package cp1_3;
	
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class BurgerTime{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		int l = Integer.parseInt(br.readLine());
		while(l!=0)
		{
			String s = br.readLine();
			int minD = l;
			int curD = 0;
			char lastP = '.';
			for(int i = 0; i < l; i++)
			{
				char curP = s.charAt(i);
				if(curP=='Z')
				{
					minD = 0;break;
				}
				if(curP=='R')
				{
					if(lastP=='D')
						if(curD<minD)
							minD = curD;
						curD=1;				
					lastP='R';
					continue;
				}
				if(curP=='D')
				{
					if(lastP=='R')
						if(curD<minD)
							minD = curD;
						curD=1;				
					lastP='D';
					continue;
				}
				curD++;
				
			}
			
			sb.append(minD+"\n");
			l = Integer.parseInt(br.readLine());
		}
		System.out.print(sb);
		

	}
}	