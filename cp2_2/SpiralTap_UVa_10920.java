package cp2_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SpiralTap_UVa_10920 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		while(true){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long p = Long.parseLong(st.nextToken());
			if(N==0)
				break;
			long lowerBound = 1; long upperBound = 1; int ring = 0;
			while(!(p>=lowerBound && p <= upperBound))
			{
				ring++;
				
				lowerBound = upperBound + 1;
				upperBound = upperBound + 4 * (2*ring+1) - 4;
			}
			int x = (N+1)/2 + ring, y = (N+1)/2 + ring;	// x and y represents coordinates of upperBound 
			boolean vertical = true; int inc = -1;
			long current = upperBound; 
			
			int lowerLimit = (N+1)/2 - ring; int upperLimit = (N+1)/2 + ring;
			while(current!=p)
			{
				
				
				if(vertical)
					x += inc;
				else
					y += inc;
				if(vertical)
				{
					if(inc==-1 && x == lowerLimit)
						vertical = false;
					else
						if(inc==1 && x==upperLimit)
						{
							vertical = false;upperLimit--;
						}
				}
				else
				{
					if(inc==-1 && y==lowerLimit)
					{
						vertical = true;
						inc = 1;lowerLimit++;
					}
					else
						if(inc==1 && y==upperLimit)
						{
							vertical = true;
							inc = -1;
						}
						
				}
				current--;
			}
			
			sb.append("Line = "+x+", column = "+y+".\n");
		}
		
		System.out.print(sb);
	}
}
