package cp5_5;
import java.io.*;
import java.util.*;
import java.math.BigInteger;



public class SimpleDivision_UVa10407 {

	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(st.countTokens()>1)
		{
			BigInteger x = new BigInteger(st.nextToken());
			BigInteger y = new BigInteger(st.nextToken());
			BigInteger d = x.subtract(y).abs();
			while(st.countTokens()>1)
			{
				BigInteger z = new BigInteger(st.nextToken());
				d = d.gcd(z.subtract(x).abs());
				x = z;
			}
			sb.append(d.toString()+"\n");

			st = new StringTokenizer(br.readLine());
		}
		

		System.out.print(sb);
	}
}
