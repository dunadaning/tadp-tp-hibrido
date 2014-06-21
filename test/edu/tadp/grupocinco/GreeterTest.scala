package edu.tadp.grupocinco

import org.junit.Test;
import edu.tadp.grupo5.Greeter

class GreeterTest {

	  @Test
	  def testSaludar {
	    new Greeter().saludar()
	    new Greeter().saludarA("Juan")
	  }
}
