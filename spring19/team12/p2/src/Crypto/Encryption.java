package Crypto;
import java.util.*;

public class Encryption {

	private long N;
	
	private long exp;
	
	private String text;
	private ArrayList<String> ASCII;
	private String encrypt;
	
	public Encryption()
	{

		N=0;
		exp=0;
		text = "";
		encrypt = "";
	}
	
	public void Encrypt(long N, long exp, String txt)
	{
		this.setN(N);
		this.sete(exp);
		this.settext(txt);
		this.ASCII();
		this.chiffrer();
		this.encrypt();
	}
	
	
	public void settext(String a)
	{
		this.text = a;
	}
	public void setN(long q)
	{
		this.N = q;
	}
	
	public void sete(long e)
	{
		this.exp = e;
	}
	
	
	
	public String getEn()
	{
		return this.encrypt;
	}
	public ArrayList<String> getASCII()
	{
		return this.ASCII;
	}
	
	
	
	
	
	public void ASCII()                             // transforms en ASCII
	{
		String t = this.text;
		ArrayList<String> l = new ArrayList<String>();
		for(int i = 0; i < t.length(); i++)
		{
			l.add("" + (int)t.charAt(i));
			if(l.get(i).length()< 3)
			{
				String a =  l.get(i);
				l.set(i, "0" + a);                     // rajoute un 0 si la longuer < 3
			}
		}
		this.ASCII = l;
	}

	public void  chiffrer()
	{
		ArrayList<String> l = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i<this.ASCII.size(); i++)
		{
			temp += this.ASCII.get(i);
		}
		//System.out.print(temp + "\n");
		int i  = 0;
		while(i<temp.length())
		{
			String p = "";
			int j = 0;
			while(i+j<temp.length() && j<4)
			{
				p += temp.charAt(i+j);
				j += 1;
			}
			while(p.length()<4)
			{
				p = "0" + p;
			}
			l.add(p);
			i += j;
		}
		this.ASCII = l;
		/*String temp2 = "";
		for(int j = 0; j<this.ASCII.size(); j++)
		{
			temp2 += this.ASCII.get(j) + "  ";
		}
		System.out.print(temp2+"\n");*/
	}
	public static long exp_mod(long m , long exp, long mod)
	{
		long b = m;
		while(exp>1)
		{
			m = (m*b)%mod;
			exp -=1;
		}
		return m;
	}
	public void encrypt()
	{
		String e = "";
		for(int i = 0; i<this.ASCII.size(); i++)
		{
			e += exp_mod(Integer.parseInt(this.ASCII.get(i)), this.exp , this.N  )+" ";
			//System.out.print(e+"\n");
		}
		this.encrypt = e.substring(0, e.length()-1);
	}
	
	
	
	public static long power(long a, int e)
	{
		long b = a;
		while(e>1)
		{
			a = a*b;
			e -= 1;
		}
		return a;
	}
	public static long gcd(long a, long b)
	{
		long r;
		 while (b != 0)
		 {
			 r = a%b;
		 	a = b;
		 	b = r;
		 }
		return a;
	}
	
	public static boolean prime(long a)
	{
		boolean test = true;
		long b = 2;
		while(b*b < a && test)
		{
			test = test && a%b!=0;
			b += 1;
		}
		return test;
	}
	public static long[] copy(long[] tab, int count)
	{
		long[] res = new long[count];
		for(int i = 0; i<count; i++)
		{
			res[i] = tab[i];
		}
		return res;
	}
	public static long[] list_prime(long a)   // return all primes inferior to a
	{
		long b = 2;
		int count = 0;
		long[] list = new long[(int)a];
		while(b<a)
		{
			if(prime(b))
			{
				list[count] = b;
				count+= 1;
			}
			b+=1;
		}
		list = copy(list,count);
		return list;
	}
	public static long[] prime_fac(long a)     // return prime factorisation of a
	{
		long b = 2;
		long[] tab = new long[(int)a];
		int count = 0;
		while(b<a)
		{
			if(gcd(a,b) == 0 && prime(b))
			{
				tab[count] = b;
				count+=1;
			}
			b += 1;
		}
		long[] res = copy(tab, count);
		return res;
	}
	/*public static long[] generator(long F)
	{
		long[] tab = new long[(int)F];
		for(int i = 0; i<F; i++)
		{
			
		}
	}
	public static boolean is_root(long a, long F)
	{
		long h = F-1;
		long[] tab = list_prime(h);
		long[] tab2 = new long[tab.length];
		for(int i = 0;i<tab.length; i++)
		{
			tab2[i] = h/tab[i];
		}
		int p = 
	}*/
}
