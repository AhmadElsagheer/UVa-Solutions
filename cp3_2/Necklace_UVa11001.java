package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Necklace_UVa11001 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		double VT, Vo;
		StringTokenizer st;
		while(true)
		{
			st = new StringTokenizer(br.readLine());
			VT = Integer.parseInt(st.nextToken());
			Vo = Integer.parseInt(st.nextToken());
			if(VT==0)
				break;
			double V,cur, maxLength = -1; int maxCount = 0; int k = 1;int count = 1;
			while(true)
			{
				V = VT / k;
				if(V<=Vo)
					break;
				cur = 0.3 * Math.sqrt(V-Vo)*k;
				if(Math.abs(cur-maxLength) <= 10e-7)
				{
					count++;
				}
				else
					if(cur>maxLength)
					{
						maxLength = cur;
						maxCount  = k;
						count  = 1;
					}
				
				
					
				k++;
			}
			out.println(count==1?maxCount:0);
		}
		out.flush();
		out.close();
	}
}
