package test

import utn.tadp.g5.funcional.ModuloExterno
import utn.tadp.g5.objetos.Direccion
import scala.collection.mutable.ArrayBuffer
import utn.tadp.g5.funcional.Medio
import utn.tadp.g5.funcional.Colectivo
import utn.tadp.g5.funcional.Subte
import utn.tadp.g5.funcional.Tren
import org.junit.Test
import utn.tadp.g5.funcional.NoHayViajeDisponibleException
import utn.tadp.g5.funcional.ComoViajo
import utn.tadp.g5.funcional.CriterioMenorTiempo
import utn.tadp.g5.funcional.Tarjeta

class ComoViajoFuncional {
	@Test(expected = classOf[NoHayViajeDisponibleException]) 
	def testSiNoSePuedeCrearViajeSeLanzaExcepcion(){
	  val comoViajo = new ComoViajo()
	  val direccionSalida = new Direccion("Albariño",291)
	  val direccionLlegada = new Direccion("Rivadavia",12800)
	  val criterio = new CriterioMenorTiempo()
	  new ComoViajo().getViaje(direccionSalida, direccionLlegada, criterio, new ArrayBuffer[Tarjeta]())
	}
	
	
}