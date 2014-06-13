package test

import main.Greeter;
import org.junit.Test;

class GreeterTest {

	  @Test
	  def testSaludar {
	    new Greeter().saludar()
	    new Greeter().saludarA("Juan")
	  }
}
