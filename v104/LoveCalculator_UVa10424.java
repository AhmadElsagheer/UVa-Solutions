package v104;
	
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
	
public class LoveCalculator_UVa10424{
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			String x = br.readLine();
			String y = br.readLine();
			double num1 = 0;
			double num2 = 0;
			//calculate first number
			x = x.toLowerCase();
			for(int i = 0; i < x.length(); i++)
			{
				int cur = x.charAt(i) - 'a' + 1;
				if(cur>=1 && cur <= 26)
					num1 += cur;
			}
			//calculate second number
			y = y.toLowerCase();
			for(int i = 0; i < y.length(); i++)
			{
				int cur = y.charAt(i) - 'a' + 1;
				if(cur>=1 && cur <= 26)
					num2 += cur;
			}
			num1 = reduce((int)num1);
			num2 = reduce((int)num2);
						
			double ratio;
			if(num1<num2)
				ratio = 100*num1/num2;
			else
				ratio = 100*num2/num1;
			ratio = Math.round(ratio*100)/100.0;
			
			String result = (new DecimalFormat("#.00")).format(ratio);
			
			sb.append(result+" %\n");
		}
		System.out.print(sb);

	}
	
	public static int reduce(int x)
	{
		if(x%10==x)
			return x;
		int n = 0;
		while(x>0)
		{
			n += x%10;
			x /= 10;
		}
		return reduce(n);
	}
		
}
