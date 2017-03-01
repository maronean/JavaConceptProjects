/**
 * Variable.java
 * 
 *   $Id: Variable.java,v 1.5 2014/10/08 01:48:32 agm1392 Exp $
 * 
 *   $Log: Variable.java,v $
 *   Revision 1.5  2014/10/08 01:48:32  agm1392
 *   Completed commenting
 *
 *   Revision 1.4  2014/10/08 00:14:14  agm1392
 *   Further commented, completed FunctionTest Class
 *
 *   Revision 1.3  2014/10/06 22:27:48  agm1392
 *   Completed the derivitive method of the Sum Class
 *
 *   Revision 1.2  2014/10/06 19:14:56  agm1392
 *   Added integral function to existing classes
 *
 *   Revision 1.1  2014/10/06 17:05:14  agm1392
 *   Part one- abstract class and initial function subclasses
 *
 *
 */


/**
 * @author Andrew Marone
 * Represents a single variable
 * cannot be directly instantiated
 *
 */
public class Variable extends Function {
	
	private Variable() {}
	public final static Variable X = new Variable();
	
	
	/* (non-Javadoc)
	 * @see Function#evaluate(double)
	 */
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return x;
	}

	/* (non-Javadoc)
	 * @see Function#derivative()
	 */
	public Function derivative() {
		// TODO Auto-generated method stub
		return new Constant(1);
	}

	/* (non-Javadoc)
	 * @see Function#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x";
	}

	@Override
	public double integral(double lower, double upper, int accuracy) {
		// TODO Auto-generated method stub
		return (upper * upper)/2 - (lower * lower)/2;
	}
	
	@Override
	public boolean isConstant()
	{
		return false;
	}
}
