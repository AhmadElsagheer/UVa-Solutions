import java.math.*;
import java.util.*;

public class Main
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);

        int i,a,n;
        BigInteger sum = new BigInteger("0");
        BigInteger A = new BigInteger("0");

        while(in.hasNext())
        {
            n = in.nextInt();
            a = in.nextInt();

            sum = new BigInteger("0");
            A = BigInteger.valueOf(a);

            for(i=1; i<=n; i++)
            {
                sum = sum.add(BigInteger.valueOf(i).multiply(A.pow(i)));
            }
            System.out.println(sum);
        }
    }


}