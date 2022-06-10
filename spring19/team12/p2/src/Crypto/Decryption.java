package Crypto;
import java.util.*;
public class Decryption {
	private long q;
	private long p;
	private long N;
	private long n;
	private long exp;
	private long d;
	private String Encrypt;
	private String Decrypt;
	private ArrayList<String> str;
	
	
	public Decryption()
	{
		q=0;
		p=0;
		N=0;
		n=0;
		exp=0;
		d=0;
		Encrypt = "";
		Decrypt = "";
	}
	
	
	public void Decrypt(long p, long q, long exp, String En)
	{
		this.setp(p);
		this.setq(q);
		this.sete(exp);
		this.setd();
		this.setEn(En);
		this.dechiffre();
		//this.getArray();
		this.ASCII();
		//this.getArray();
		this.str();
	}
	public void decrypt(String En)
	{
		this.setd();
		this.setEn(En);
		this.dechiffre();
		//this.getArray();
		this.ASCII();
		//this.getArray();
		this.str();
		
	}
	
	
	
	
	public void setq(long q)
	{
		this.q = q;
		this.N = this.q * this.p;
		this.n = (this.q-1) * (this.p-1);
	}
	public void setp(long p)
	{
		this.p = p;
		this.N = this.q * this.p;
		this.n = (this.q-1) * (this.p-1);
	}
	public void sete(long e)
	{
		this.exp = e;
	}
	public void setd()
	{
		long m = (this.n/gcd(p-1,q-1));
		while((this.d*exp)%m != 1)
		{
			this.d += 1;
		}
	}
	
	
	
	public void setEn(String s)
	{
		this.Encrypt = s;
	}
	public long getN()
	{
		return this.N;
	}
	public long getexp()
	{
		return this.exp;
	}
	public long getd()
	{
		return this.d;
	}
	
	public void getArray()
	{
		for (int i = 0; i< this.str.size(); i ++)
		{
			System.out.print(this.str.get(i)+" ");
		}
		System.out.print("\n");
	}
	public String getDec()
	{
		return this.Decrypt;
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
	public void dechiffre()
	{
		String s = this.Encrypt;
		int i =0;
		ArrayList<String> str = new ArrayList<String>();
		while (i < s.length())
		{
			String temp = "";
			int j = i;
			while(j<s.length() && s.charAt(j) != ' ')
			{
				temp += s.charAt(j);
				j += 1;
			}
			str.add(temp);
			//System.out.println(temp);
			i = j+ 1;
		}
		int k = 0;
		ArrayList<String> l = new ArrayList<String>();
		while(k < str.size())
		{
			long h = Long.parseLong(str.get(k));
			//System.out.println(h);
			h = exp_mod(h, this.d, N);
			//System.out.println(h);
			String temp = "";
			if(h != 0)
			{
				while(h>0)
				{
					temp = h%10+ temp;
					h = h/10;
				}
			}
			else
				temp = "0";
			if(k != str.size()-1)
			{
				while(temp.length()<4)
					temp= "0" + temp;
			}
			l.add(temp);
			k += 1;
		}
		this.str = l;
	}
	
	public void ASCII()
	{
		ArrayList<String> ASCII = new ArrayList<String>();
		String temp = "";
		for(int i = 0; i<this.str.size(); i++)
		{
			temp += this.str.get(i);
		}
		int i = 0;
		while(i < temp.length())
		{
			String s= "";
			int j = 0;
			while(j+i <temp.length() && j < 3)
			{
				s += temp.charAt(i+j);
				j += 1;
			}
			i += j;
			ASCII.add(s);
		}
		this.str = ASCII;
	}
	public void str()
	{
		int k = 0;
		while(k< this.str.size())
		{
			this.Decrypt+= (char)Integer.parseInt(str.get(k));
			k += 1;
		}
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
}
