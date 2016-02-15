package cp3_2;

import java.util.*;
import java.io.*;

public class OpenCreditSystem_UVa11078 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			int N = Integer.parseInt(br.readLine());
			int max = -1; int senior = -200000;
			while(N-->0)
			{
				int cur = Integer.parseInt(br.readLine());
				if(senior>=cur)
					max = Math.max(max, senior-cur);
				else
					senior = cur;
			}
			out.println(max);
		}
		out.flush();
		out.close();
	}
}
