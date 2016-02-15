<<<<<<< HEAD
package cp3_2;


import java.io.*; 
import java.text.DecimalFormat;
public class GroceryStore_UVa11236{
	
	public static void main(String[] args) throws IOException
	{
	
		StringBuilder sb = new StringBuilder();
		
		for(int a = 1; a * 4 <= 2000; a ++)
			for(int b = a ; a + b * 3<= 2000; b ++)
				for(int c = b ; a + b + c * 2 <= 2000; c ++)
				{
					if(a*b*c <=1000000)
						continue;
					int d = (a+b+c)*1000000/(a*b*c-1000000);
					if(d>=c && a+b+c+d <= 2000 && (a+b+c+d)*1000000 == a*b*c*d)
					{
						sb.append(new DecimalFormat("0.00").format(a/100.0) + " "+ new DecimalFormat("0.00").format(b/100.0)+ " " + new DecimalFormat("0.00").format(c/100.0)+ " " + new DecimalFormat("0.00").format(d/100.0)+"\n");
					}
						
				}
		
		System.out.print(sb);

		
		
	}
=======
package cp3_2;


import java.io.*; 
import java.text.DecimalFormat;
public class GroceryStore_UVa11236{
	
	public static void main(String[] args) throws IOException
	{
	
		StringBuilder sb = new StringBuilder();
		
		for(int a = 1; a * 4 <= 2000; a ++)
			for(int b = a ; a + b * 3<= 2000; b ++)
				for(int c = b ; a + b + c * 2 <= 2000; c ++)
				{
					if(a*b*c <=1000000)
						continue;
					int d = (a+b+c)*1000000/(a*b*c-1000000);
					if(d>=c && a+b+c+d <= 2000 && (a+b+c+d)*1000000 == a*b*c*d)
					{
						sb.append(new DecimalFormat("0.00").format(a/100.0) + " "+ new DecimalFormat("0.00").format(b/100.0)+ " " + new DecimalFormat("0.00").format(c/100.0)+ " " + new DecimalFormat("0.00").format(d/100.0)+"\n");
					}
						
				}
		
		System.out.print(sb);

		
		
	}
>>>>>>> 39f869b7a0b14772c795150d9bb52127d820fece
}	