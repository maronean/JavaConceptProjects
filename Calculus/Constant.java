/**
 * Constant.java
 * 
 *   $Id: Constant.java,v 1.4 2014/10/08 01:48:32 agm1392 Exp $
 * 
 *   $Log: Constant.java,v $
 *   Revision 1.4  2014/10/08 01:48:32  agm1392
 *   Completed commenting
 *
 *   Revision 1.3  2014/10/06 22:27:48  agm1392
 *   Completed the derivitive method of the Sum Class
 *
 *   Revision 1.2  2014/10/06 19:14:55  agm1392
 *   Added integral function to existing classes
 *
 *   Revision 1.1  2014/10/06 17:05:10  agm1392
 *   Part one- abstract class and initial function subclasses
 *
 *
 */

/**
 * @author Andrew Marone
 * represents a constant value in the form of a function
 */
public class Constant extends Function {
	private double value;
	
	public Constant(double value)
	{
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see Function#evaluate(double)
	 */
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return this.value;
	}

	/* (non-Javadoc)
	 * @see Function#derivative()
	 */
	public Function derivative() {
		// TODO Auto-generated method stub
		return new Constant(0);
	}
	

	/* (non-Javadoc)
	 * @see Function#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Double.toString(this.value);
	}

	@Override
	public double integral(double lower, double upper, int accuracy) {
		// TODO Auto-generated method stub
		return (upper *  this.value) - (lower * this.value);
	}
	
	/**
	 * always returns true
	 */
	@Override
	public boolean isConstant()
	{
		return true;
	}

}
