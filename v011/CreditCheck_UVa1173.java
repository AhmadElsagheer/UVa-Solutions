package v011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CreditCheck_UVa1173 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int i = 0; i < 4; i++)
			{
				int cur = Integer.parseInt(st.nextToken());
				for(int j = 0; j < 4; j++)
				{
					int d = cur%10;
					cur /= 10;
					if(j%2==0)
						sum += d;
					else
					{
						d <<= 1;
						sum += d%10 + d/10;
					}
				}
			}
			if(sum%10==0)
				sb.append("Valid\n");
			else
				sb.append("Invalid\n");
		}
		System.out.print(sb);
	}
}
