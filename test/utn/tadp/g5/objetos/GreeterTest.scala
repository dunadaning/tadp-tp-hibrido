package utn.tadp.g5.objetos

import org.junit.Test;
import edu.tadp.grupo5.Greeter

class GreeterTest {

	  @Test
	  def testSaludar {
	    new Greeter().saludar()
	    new Greeter().saludarA("Juan")
	  }
}
