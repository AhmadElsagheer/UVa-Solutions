package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CaloriesFromFat_UVa10554 {

	static int[] caloriesPerGram = new int[]{9,4,4,4,7};
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			String line  = br.readLine();
			if(line.equals("-"))
				break;
			double totalFat = 0, totalC = 0;
			while(!line.equals("-"))
			{
				StringTokenizer st = new StringTokenizer(line);
				int percent = 0;
				double calories = 0;
				
				for(int i = 0; i < 5; i++)
				{
					String cur = st.nextToken();
					int val = Integer.parseInt(cur.substring(0,cur.length()-1));
					switch(cur.charAt(cur.length()-1))
					{
					case 'g':	val = val * caloriesPerGram[i];
					case 'C':	calories += val;break;
						default:	percent += val;
					}
				}
				calories = 100*calories/(100-percent);
				
			
				String fatRecord = new StringTokenizer(line).nextToken();
				int val = Integer.parseInt(fatRecord.substring(0,fatRecord.length()-1));
				switch(fatRecord.charAt(fatRecord.length()-1))
				{
				case 'g':	val = val * 9;
				case 'C':	totalFat += val;break;
					default:	totalFat += calories * val / 100;
				}
				
				totalC += calories;
				line = br.readLine();
			}
			sb.append(Math.round(totalFat*100/totalC)).append("%\n");
		}
		System.out.print(sb);
	}
}
