package v121;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TariffPlan_UVa12157{

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for(int i = 1; i <= t; i++)
		{
			int costMile = 0; int costJuice = 0;
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <=n; j++)
			{
				int callDuration = Integer.parseInt(st.nextToken());
				costMile += 10*(callDuration/30+1);
				costJuice += 15*(callDuration/60+1);
			}
			sb.append("Case "+i+": ");
			if(costMile<=costJuice)
				sb.append("Mile ");
			if(costMile>=costJuice)
				sb.append("Juice ");
			sb.append(Math.min(costMile, costJuice)+"\n");
		}
		System.out.print(sb);


	}
}
