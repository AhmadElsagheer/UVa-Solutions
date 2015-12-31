package cp1_3;
	
import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
public class TrainTracks{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int tokens = st.countTokens();
			if(tokens==1)
			{
				sb.append("NO LOOP\n");continue;
			}
			int m = 0;
			int f = 0;
			for(int j = 0; j < tokens; j++)
			{
				String s = st.nextToken();
				if(s.charAt(0)=='M')
					m++;
				else
					f++;
				if(s.charAt(1)=='M')
					m++;
				else
					f++;
			}
			if(m==f&&m==tokens)
				sb.append("LOOP\n");
			else
				sb.append("NO LOOP\n");
		}
		System.out.print(sb);
	
	
	
	}
}	