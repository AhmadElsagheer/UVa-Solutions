package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Multitasking_UVa11926 {

	static int[] start;
	static int[] end;
	
	static boolean scan()
	{
		int tasks = 0;
		for(int i = 0; i <= 1000000; i++)
		{
			tasks = tasks + start[i] - end[i];
			if(tasks>1)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if(n==0 && m==0)
				break;
			start = new int[1000001];
			end = new int[1000001];
			while(n-->0)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				start[x]++;end[y]++;
			}
			while(m-->0)
			{
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				for(int i = x; i <= 1000000; i+=z)
					start[i]++;
				for(int i = y; i <= 1000000; i+=z)
					end[i]++;
			}
			out.print(scan()?"NO CONFLICT\n":"CONFLICT\n");
		}
		out.flush();
	}
}
