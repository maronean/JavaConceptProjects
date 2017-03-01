/**
 * Sum.java
 * 
 *   $Id: Sum.java,v 1.5 2014/10/08 01:48:33 agm1392 Exp $
 * 
 *   $Log: Sum.java,v $
 *   Revision 1.5  2014/10/08 01:48:33  agm1392
 *   Completed commenting
 *
 *   Revision 1.4  2014/10/07 20:05:12  agm1392
 *   Added commenting
 *
 *   Revision 1.3  2014/10/06 22:27:49  agm1392
 *   Completed the derivitive method of the Sum Class
 *
 *   Revision 1.2  2014/10/06 19:14:56  agm1392
 *   Added integral function to existing classes
 *
 *   Revision 1.1  2014/10/06 17:05:16  agm1392
 *   Part one- abstract class and initial function subclasses
 *
 *
 */
/**
 * Andrew Marone
 * A class to represent an addition function
 */
import java.util.*;
public class Sum extends Function {
	private LinkedList<Function> terms = new LinkedList<Function>();
	public Sum(Function...terms)
	{
		double constant = 0;
		for(Function term: terms)
		{
			if(term.isConstant())
			{
				constant += term.evaluate(0);
			}
			else
			{
				this.terms.add(term);
			}
		}
		if(constant != 0 || (constant ==0 && this.terms.size() == 0))
		{
			this.terms.add(new Constant(constant));
		}
	}
	
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		double sum = 0;
		for(int i=0;i < this.terms.size();i++)
		{
			sum+= this.terms.get(i).evaluate(x);
		}
		return sum;
	}

	public Function derivative() {
		// TODO Auto-generated method stub
		
		Function[] function =  new Function[this.terms.size()];
		Function[] terms =  this.terms.toArray(function);
		for(int i =0;i<terms.length;i++)
		{
			terms[i] = terms[i].derivative();
			//System.out.println(terms[i]);
		}
		//System.out.println(terms[0]);
		return new Sum(terms);
		
	}
	
	/**
	 * Returns a string representing the function
	 */
	@Override
	public String toString() 
	{
		// TODO Auto-generated method stub
		String strSum ="";
		if(this.terms.size()>1)
		{
			strSum = "( ";
		}
		if(this.terms.size()>0)
		{
			strSum = strSum + this.terms.get(0);
		}
		if(this.terms.size()>1)
		{
			for(int i=1;i<this.terms.size();i++)
			{
				strSum = strSum + " + ";
				strSum = strSum + this.terms.get(i);
			}
		}
		if(this.terms.size()>1)
		{
			strSum =strSum + " )";
		}
		return strSum;
	}
	/**
	 * Returns a double representing the integral of this function
	 * adds the integral of all contained functions
	 */
	@Override
	public double integral(double lower, double upper, int accuracy) {
		// TODO Auto-generated method stub
		double finalVal = 0;
		for(int i =0;i<this.terms.size();i++)
		{
			finalVal += this.terms.get(i).integral(lower, upper, accuracy);
		}
		return finalVal;
	}
	
	/**
	 * returns true if all values contained are constant
	 * is false if any contained functions are not constant
	 */
	public boolean isConstant()
	{
		for(int i = 0; i<this.terms.size();i++)
		{
			if(!this.terms.get(i).isConstant())
			{
				return false;
			}
		}
		return true;
	}

}
