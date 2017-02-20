package v120;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
	
	public class GoogleIsFeelingLucky_UVa12015{
	
		public static void main(String[] args) throws IOException
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			int t = Integer.parseInt(br.readLine());
			for(int i = 1; i <= t; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				StringBuilder tmp = new StringBuilder(st.nextToken()+"\n");
				int maxRelevance = Integer.parseInt(st.nextToken());
				for(int j = 1; j < 10; j++)
				{
					st = new StringTokenizer(br.readLine());
					String s = st.nextToken();
					int curRelevance = Integer.parseInt(st.nextToken());
					if(curRelevance>maxRelevance)
					{
						maxRelevance = curRelevance;
						tmp = new StringBuilder(s+"\n");
					}
					else
						if(curRelevance==maxRelevance)
							tmp.append(s+"\n");
				}
				sb.append("Case #"+i+":\n"+tmp);
			}
			System.out.print(sb);
			
			
		}
	}
