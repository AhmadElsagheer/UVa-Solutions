package cp3_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;


public class StationBalance_UVa410 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int set = 1;
	
		
		while(br.ready())
		{
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int[] masses = new int[c*2];
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int i = 0; i < s; i++)
			{
				masses[i] = Integer.parseInt(st.nextToken());
				sum += masses[i];
			}
			int[] massesX = masses.clone();
			Arrays.sort(massesX);
			double AM = sum*1.0/c;
			double imbalance = 0;
			sb.append("Set #"+set+++"\n");
			for(int i = 0; i < c; i++)
			{
				sb.append(" "+i+":");
				int s1 = massesX[i];
				int s2 = massesX[2*c-1-i];
				int index1 = indexOf(masses, s1);
				int index2 = indexOf(masses, s2);
				if(s1==0 || s2==0)
				{
					if(s1!=0)
						sb.append(" "+s1);
					if(s2!=0)
						sb.append(" "+s2);
				}
				else
				{
					if(index1>index2)
						sb.append(" "+s2+" "+s1);
					else
						sb.append(" "+s1+" "+s2);
				}
				masses[index1] = -1;
				masses[index2] = -1;
				
				imbalance += Math.abs((s1+s2)-AM);
				sb.append("\n");
			}
			sb.append("IMBALANCE = "+new DecimalFormat("0.00000").format(imbalance)+"\n\n");	
		}
		System.out.print(sb);

		


		
	}
	
	public static int indexOf(int[] array, int element)
	{
		for(int i = 0; i < array.length; i++)
			if(array[i] == element)
				return i;
		return -1;
	}
}
