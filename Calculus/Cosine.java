/**
 * Cosine.java
 * 
 * $Is$
 * 
 * $Log: Cosine.java,v $
 * Revision 1.4  2014/10/08 01:48:32  agm1392
 * Completed commenting
 *
 * Revision 1.3  2014/10/08 00:14:14  agm1392
 * Further commented, completed FunctionTest Class
 *
 * Revision 1.2  2014/10/07 20:05:11  agm1392
 * Added commenting
 *
 * Revision 1.1  2014/10/07 13:03:31  agm1392
 * Added Cos and Sin Classes and Integral function to the Product class
 *
 */

/**
 * @author Andrew
 *A class to represent a Cosine function
 */
public class Cosine extends Function {
	public Function contents;
	
	/**
	 * @param x
	 * A function object 
	 */
	public Cosine(Function x)
	{
		this.contents = x;
	}
	
	/* (non-Javadoc)
	 * @see Function#evaluate(double)
	 */
	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return Math.cos(this.contents.evaluate(x));
	}

	/**
	 * @see Function#derivative()
	 * Returns a function value 
	 */
	@Override
	public Function derivative() {
		// TODO Auto-generated method stub
		return new Product(new Constant(-1),this.contents.derivative(),new Sine(this.contents));
	}

	/* (non-Javadoc)
	 * @see Function#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Cos( " + this.contents.toString()+ " )";
	}


}
