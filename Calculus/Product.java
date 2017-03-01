import java.util.LinkedList;

/**
 * Product.java
 * 
 * $Id: Product.java,v 1.5 2014/10/08 01:48:33 agm1392 Exp $
 * 
 * $Log: Product.java,v $
 * Revision 1.5  2014/10/08 01:48:33  agm1392
 * Completed commenting
 *
 * Revision 1.4  2014/10/08 00:14:14  agm1392
 * Further commented, completed FunctionTest Class
 *
 * Revision 1.3  2014/10/07 20:05:12  agm1392
 * Added commenting
 *
 * Revision 1.2  2014/10/07 13:03:32  agm1392
 * Added Cos and Sin Classes and Integral function to the Product class
 *
 * Revision 1.1  2014/10/06 22:27:49  agm1392
 * Completed the derivitive method of the Sum Class
 *
 */

/**
 * @author Andrew
 *A class to represent a Multiplication or Product function
 */
public class Product extends Function {
	private LinkedList<Function> terms = new LinkedList<Function>();
	
	public Product(Function...terms)
	{
		double constants = 1;
		for(Function term: terms)
		{
			if (term.isConstant())
			{
				constants = constants * term.evaluate(0);
			}
			else
			{
				this.terms.add(term);
			}
		}
		if(constants== 0)
		{
			this.terms = new LinkedList<Function>();
			this.terms.add(new Constant(0));		
		}
		else if(constants != 1 || (constants ==1 && this.terms.size() == 0))
		{
			this.terms.add(new Constant(constants));
		}
	}
	
	/* (non-Javadoc)
	 * @see Function#evaluate(double)
	 */
	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		double finalVal = 1;
		for(int i = 0; i< this.terms.size();i++)
		{	
				finalVal = finalVal * this.terms.get(i).evaluate(x);
		}
		return finalVal;
	}

	/* (non-Javadoc)
	 * @see Function#derivative()
	 * 
	 * Uses the chain method to return the derivative 
	 */
	@Override
	public Function derivative() {
		// TODO Auto-generated method stub
		LinkedList<Function> terms = (LinkedList<Function>) this.terms.clone();
		//base case
		if(terms.size()==1)
		{
			return terms.get(0).derivative();
		}
		else
		{
			Function fOne = terms.pop();
			
			Function[] function =  new Function[terms.size()];
			Function[] fDeriv =  terms.toArray(function);
			Product check = new Product(fDeriv);
			Function fVal = new Sum(new Product(fOne,check.derivative()),new Product(fOne.derivative(),check));
			return fVal;
		}	
			
	
	}

	/* (non-Javadoc)
	 * @see Function#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String strProduct =" ";
		if(this.terms.size()>1)
		{
			strProduct = "( ";
		}
		if(this.terms.size()>0)
		{
			strProduct = strProduct + this.terms.get(0);
		}
		if(this.terms.size()>1)
		{
			for(int i=1;i<this.terms.size();i++)
			{
				strProduct = strProduct + " * ";
				strProduct = strProduct + this.terms.get(i);
			}
		}
		if(this.terms.size()>1)
		{
			strProduct = strProduct + " )";
		}
		return strProduct;
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
