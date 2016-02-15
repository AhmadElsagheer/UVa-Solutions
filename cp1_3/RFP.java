
package cp1_3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;
	
public class RFP{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			if(r==0)
				break;
			int p = Integer.parseInt(st.nextToken());
			for(int i = 0; i < r; i++)
				br.readLine();
			int maxMetR = -1; String bestP = ""; double bestPrice = 0; 
			for(int i = 0; i < p; i++)
			{
				String curP = br.readLine();
				st = new StringTokenizer(br.readLine());
				double curPrice = Double.parseDouble(st.nextToken());
				int curMetR = Integer.parseInt(st.nextToken());
				for(int j = 0; j < curMetR; j++)
					br.readLine();
				if(curMetR>maxMetR)
				{
					maxMetR = curMetR;bestP = curP;bestPrice = curPrice;
				}
				else
					if(curMetR==maxMetR&&curPrice<bestPrice)
					{
						bestP = curP;bestPrice = curPrice;
					}
			}
			if(k!=1)
				sb.append("\n");
			sb.append("RFP #"+k+"\n"+bestP+"\n");
			
			k++;
		}
		System.out.print(sb);
	}
		
}
