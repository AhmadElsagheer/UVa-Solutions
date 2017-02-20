package v003;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Run_UVa347 {

	static int X;
	static boolean[] used;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int k = 1;
		while(true)
		{
			String line = br.readLine();
			X = Integer.parseInt(line);
			if(X==0)
				break;
			for(int i = line.length(); i < 10; i++)
			{
				used = new boolean[10];
				String res = findRunAround("",i);
				if(!res.equals("X"))
				{
					sb.append("Case "+k+++": ").append(res+"\n");
					break;
				}
			}
			
		}
		System.out.print(sb);
	}
	
	static boolean isRunAround(String number)
	{
		boolean[] used = new boolean[10];
		int length = number.length();
		used = new boolean[10];
		int digit = number.charAt(0) - '0';
		int index = 0;
		used[digit] = true;
		for(int i = 0; i < length - 1; i++)
		{
			index = (index+digit)%length;
			digit = number.charAt(index) -'0';
			if(used[digit])
				return false;
			used[digit] = true;
		}
		if((index+digit)%length==0)
			return true;
		return false;
	}
	
	static String findRunAround(String n, int k)
	{
		if(k==0)
			if(Integer.parseInt(n) >= X && isRunAround(n)) return n; else return "X";
		for(int i = 1; i < 10; i++)
			if(!used[i])
			{
				used[i] = true;
				String trial = findRunAround(n+((char)(i+'0')),k-1);
				if(!trial.equals("X"))
					return trial;
				used[i] = false;
			}
		return "X";
				
	}
	
	
}
