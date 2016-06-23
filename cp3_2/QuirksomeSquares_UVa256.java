package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class QuirksomeSquares_UVa256 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		
		int[][] array = new int[4][];
		array[0] = new int[]{0,1,81};
		array[1] = new int[]{0,1,2025,3025,9801};
		array[2] = new int[]{0,1,88209,494209,998001};
		array[3] = new int[]{0,1,4941729,7441984,24502500,25502500,52881984,60481729,99980001};
		String[] format = new String[]{"00","0000","000000","00000000"};
		int[] map = new int[]{0,0,0,0,1,0,2,0,3};
		
		while(br.ready())
		{
			int n = Integer.parseInt(br.readLine());
			for(int i = 0; i < array[map[n]].length; i++)
				out.println(new DecimalFormat(format[map[n]]).format(array[map[n]][i]));
		}
		out.flush();
		out.close();
	}
}
