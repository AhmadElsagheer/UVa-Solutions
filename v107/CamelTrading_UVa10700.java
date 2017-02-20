package v107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;

public class CamelTrading_UVa10700 {

	static long operation(long op1, long op2, char op)
	{
		switch(op)
		{
		case '*': return op1*op2;
		default: return op1+op2;
		}
	}
	
	static long compute(String exp, boolean min)
	{
		Stack<Character> ops = new Stack<Character>();
		Stack<Long> nums = new Stack<Long>();
		String last = "";
		for(int i = 0; i < exp.length(); i++)
		{
			char c = exp.charAt(i);
			if(c >= '0' && c <= '9')
				last += c;
			else
			{
				nums.push(Long.parseLong(last));
				last = "";
				if(c=='+')
				{
					if(!min)
						while(!ops.isEmpty() && ops.peek()=='+')
							nums.push(operation(nums.pop(),nums.pop(),ops.pop()));
					
					else
						while(!ops.isEmpty())
							nums.push(operation(nums.pop(),nums.pop(),ops.pop()));
					ops.push(c);
				}
				else
				{
					if(min)
						while(!ops.isEmpty() && ops.peek()=='*')
							nums.push(operation(nums.pop(),nums.pop(),ops.pop()));
					else
						while(!ops.isEmpty())
							nums.push(operation(nums.pop(),nums.pop(),ops.pop()));
					ops.push(c);
				}
			}
				
		}
		nums.push(Long.parseLong(last));
		while(!ops.isEmpty())
			nums.push(operation(nums.pop(),nums.pop(),ops.pop()));
		return nums.pop();
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			String exp = br.readLine();
			long min = compute(exp,true);
			long max = compute(exp,false);
			out.printf("The maximum and minimum are %d and %d.\n",max,min);
		}
		out.flush();
	}
}
