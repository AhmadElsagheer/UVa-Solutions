package cp3_4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MoliuNumberGenerator_UVa11567 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.hasNext())
		{
			long s = sc.nextLong(), ans = 0;
			boolean up = false;

			while(s > 1)
			{

				boolean oldUp = up;
				if((s & 1) == 1)
				{
					if(up)
					{
						up = false;
						--s;
					}
					else
						if(s == 3)
						{
							--s;
							++ans;
						}
						else
						{
							up = true;
							++ans;
							++s;
						}
				}
				if(up == oldUp)
					up = false;
				++ans;
				s >>= 1;

			}

			out.println(ans + s);

		}

		out.flush();
		out.close();
	}

}
