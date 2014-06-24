package utn.tadp.g5.objetos

import org.junit.Test;
import utn.tadp.g5.objetos.Greeter

class GreeterTest {

	  @Test
	  def testSaludar {
	    new Greeter().saludar()
	    new Greeter().saludarA("Juan")
	  }
}
