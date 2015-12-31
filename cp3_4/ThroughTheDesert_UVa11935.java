package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ThroughTheDesert_UVa11935 {
	
	static ArrayList<Event> events;
	
//	static boolean possible(double V)
//	{
//		int pos = 0, leaks = 0, cons = 0;
//		double fuel = V;
//		for(int i = 0; i < events.size(); i++)
//		{
//			Event cur = events.get(i);
//			fuel -= (cur.pos - pos) * (leaks + cons/100.0);
//			
//			if(Math.abs(fuel) > 1e-9 && fuel < 0)
//				return false;
//			switch(cur.event)
//			{
//			case 'F':	cons = cur.fuel;break;
//			case 'G':	fuel = V;break;
//			case 'M':	leaks = 0;break;
//				default:	leaks++;
//			}
//			pos = cur.pos;
//		}
//		return true;
//	}
	
	static double find()
	{
		int pos = 0, leaks = 0, cons = 0;
		double fuel = 0, min = 0;
		for(int i = 0; i < events.size(); i++)
		{
			Event cur = events.get(i);
			fuel += (cur.pos - pos) * (leaks + cons/100.0);
			min = Math.max(fuel, min);
			
			switch(cur.event)
			{
			case 'F':	cons = cur.fuel;break;
			case 'G':	fuel = 0;break;
			case 'M':	leaks = 0;break;
				default:	leaks++;
			}
			pos = cur.pos;
		}
		return min;
	}
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		while(true)
		{
			String line = br.readLine();
			if(line.equals("0 Fuel consumption 0"))
				break;
			events = new ArrayList<Event>();
			while(true)
			{
				StringTokenizer st = new StringTokenizer(line);
				int pos = Integer.parseInt(st.nextToken());
				String e = st.nextToken();
				if(e.equals("Fuel"))
				{	
					st.nextToken();
					events.add(new Event(pos,e.charAt(0),Integer.parseInt(st.nextToken())));
				}
				else
				{
					events.add(new Event(pos,e.charAt(0)));
					if(e.equals("Goal"))
						break;
				}
				line = br.readLine();
			}
			
//			double low = 0, high = 10000;
//			double sol = -1;
//			for(int i = 0; i < 100; i++)
//			{
//				double mid = (low+high)/2;
//				
//				if(possible(mid))
//				{
//					sol = mid;
//					high = mid - 0.001;
//				}
//				else
//					low = mid + 0.001;
//			}
//			out.println(new DecimalFormat("0.000").format(sol));
			out.println(new DecimalFormat("0.000").format(find()));
		}
		out.flush();
			
	}

}

class Event
{
	int pos, fuel;
	char event;
	
	Event(int x, char e){pos = x; event = e;}
	Event(int x, char e, int val){pos = x; event = e; fuel = val;}
	
}