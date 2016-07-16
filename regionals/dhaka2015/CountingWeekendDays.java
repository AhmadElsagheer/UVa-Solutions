package regionals.dhaka2015;

import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class CountingWeekendDays {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		TreeSet<String> is31 = new TreeSet<String>();
		is31.add("JAN");is31.add("MAR");is31.add("MAY");is31.add("JUL");
		is31.add("AUG");is31.add("OCT");is31.add("DEC");
		
		TreeMap<String, Integer> getDay = new TreeMap<String, Integer>();
		getDay.put("SAT", 0); getDay.put("SUN", 1); getDay.put("MON", 2);
		getDay.put("TUE", 3); getDay.put("WED", 4); getDay.put("THU", 5);
		getDay.put("FRI", 6); 
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String month = sc.next(), day = sc.next();
			int end = 30;
			if(month.equals("FEB"))
				end = 28;
			else if(is31.contains(month))
				end = 31;
			int d = getDay.get(day), ans = 0;
			for(int i = 0; i < end; ++i)
			{
				if(d == 0 || d == 6)
					++ans;
				d = (d + 1)%7;
			}
			out.println(ans);
		}
		out.flush();
		out.close();
	}
}
