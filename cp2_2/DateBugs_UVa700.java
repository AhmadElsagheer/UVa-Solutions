package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DateBugs_UVa700 {

	static int[] years;
	static int min, N;
	
	public static void compute(int display, int a, int b)
	{
		int real = a;
		int fake = a;
		while(real<10000)
		{
			if(fake==b)	
				fake = a;
			if(fake==display)
				years[real]++;
			fake++;
			real++;
		}
	}
	
	public static int getCommon()
	{
		for(int i = min; i < 10000; i++)
			if(years[i]==N)
				return i;
		return -1;
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int k =1;
		while(true)
		{
			N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			years = new int[10000];
			min = 0;
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int d = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				compute(d,a,b);
				min = Math.max(a, min);
			}
			int res = getCommon();
			out.printf("Case #%d:\n",k++);
			if(res==-1)
				out.print("Unknown bugs detected.\n\n");
			else
				out.printf("The actual year is %d.\n\n",res);
		}
		out.flush();
		
		
	}
}
