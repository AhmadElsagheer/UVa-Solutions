package v006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class HowManyKnights_UVa696 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N==0 && M==0)
				break;
			int total = N * M;
			int max;
			if(N==1 || M==1)
				max = total;
			else
				if(N==2 || M==2)
					max = total/8*4 + (total%8==0?0:total%8/4*2+2);
				else
					max = (total+1)/2;
			out.printf("%d knights may be placed on a %d row %d column board.\n",max,N,M);
		}
		out.flush();
	}
}
