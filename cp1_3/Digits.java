package cp1_3;
	
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
	
public class Digits{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		String s = br.readLine();
		while(!s.equals("END"))
		{
			sb.append(iCalculator(s, 1)+"\n");
			s = br.readLine();
		}
		System.out.print(sb);
	}
	
	public static int iCalculator(String x, int i)
	{
		String y = ""+x.length();
		if(y.equals(x))
			return i;
		return iCalculator(y,i+1);
	}
}	