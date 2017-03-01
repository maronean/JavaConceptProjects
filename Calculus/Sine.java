/**
 * 
 */
import java.math.*;
/**
 * @author Andrew Marone
 *A class to represent a Sine function
 */
public class Sine extends Function {
	private Function contents;
	
	public Sine(Function x)
	{
		contents = x;
	}
	
	/* (non-Javadoc)
	 * @see Function#evaluate(double)
	 */
	@Override
	public double evaluate(double x) {
		// TODO Auto-generated method stub
		return Math.sin(this.contents.evaluate(x));
	}

	/* (non-Javadoc)
	 * @see Function#derivative()
	 */
	@Override
	public Function derivative() {
		// TODO Auto-generated method stub
		return new Product(this.contents.derivative(),new Cosine(this.contents));
	}

	/* (non-Javadoc)
	 * @see Function#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Sin( " + this.contents.toString()+ " )";
	}


}
