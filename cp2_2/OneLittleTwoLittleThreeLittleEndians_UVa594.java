package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class OneLittleTwoLittleThreeLittleEndians_UVa594 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);

		
		while(br.ready())
		{
			int x = Integer.parseInt(br.readLine());
			out.printf("%d converts to %d\n",x,convert(x));
		}
		
		out.flush();
		out.close();
	}
	
	public static int convert(int x)
	{
		int lowlow = x & 0x000000FF;
		int highhigh = x & 0xFF000000;
		int highlow = x & 0x00FF0000;
		int lowhigh = x & 0x0000FF00;
		return (lowhigh << 8 | highlow >>> 8) | (lowlow << 24 | highhigh >>> 24);
	}
	
	
}
