package cp5_2;
import java.util.*;
import java.io.*;

public class Steps_UVa846 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			int distance = y - x;
			int maxStep = 0;
			while(true)
			{
				int curD = maxStep + (maxStep) * (maxStep-1);
				if(curD>distance)
					break;
				maxStep++;
			}
			maxStep--;
			if(maxStep==0)
				sb.append("0\n");
			else
			{
				int steps = 1 + 2*(maxStep-1);
				int rem = distance - (maxStep + (maxStep) * (maxStep-1));
				while(rem!=0)
				{
					steps += rem/maxStep;
					rem %= maxStep;
					maxStep--;
				}
				sb.append(steps+"\n");
			}
			
		}
		
		System.out.print(sb);
		
	}
}
