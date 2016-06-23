package cp2_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SplittingNumbers_UVa11933 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			int a = 0, b = 0;
			int count = 0;
			while(n!=0)
			{
				int low = n & (-n);
				if(count++%2==0)
					a |= low;
				else
					b |= low;
				n ^= low;
			}
			out.printf("%d %d\n",a,b);
		}
		
		out.flush();
		out.close();
	}
}
