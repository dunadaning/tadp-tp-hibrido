package test

import utn.tadp.g5.funcional.ModuloExterno
import utn.tadp.g5.objetos.Direccion
import scala.collection.mutable.ArrayBuffer
import utn.tadp.g5.funcional.Medio
import utn.tadp.g5.funcional.Colectivo
import utn.tadp.g5.funcional.Subte
import utn.tadp.g5.funcional.Tren
import org.junit.Test
import org.junit.Assert._
import utn.tadp.g5.funcional.NoHayViajeDisponibleException
import utn.tadp.g5.funcional.ComoViajo
import utn.tadp.g5.funcional.CriterioMenorTiempo
import utn.tadp.g5.funcional.Tarjeta
import utn.tadp.g5.funcional.CriterioMenorCosto
import utn.tadp.g5.funcional.TramoCombinado
import utn.tadp.g5.funcional.CriterioMenorTiempo

class ComoViajoFuncional {
	@Test(expected = classOf[NoHayViajeDisponibleException]) 
	def testSiNoSePuedeCrearViajeSeLanzaExcepcion(){
	  new ComoViajo().getViaje(new Direccion("Albariño",291), new Direccion("Rivadavia",12800),new CriterioMenorTiempo(), new ArrayBuffer[Tarjeta]())
	}
	
	@Test def testSePuedeCrearUnViajeDeTramoSimple(){
	  val viaje = new ComoViajo().getViaje(new Direccion("Albariño",291),new Direccion("Albariño", 600),new CriterioMenorCosto(), new ArrayBuffer[Tarjeta]())
	  assertEquals(2.5, viaje.costo, 0.0)
	}
	
	@Test def testSePuedeCrearUnViajeDeTramoCompuesto(){
	  val viaje = new ComoViajo().getViaje(new Direccion("Rivadavia",600),new Direccion("Rivadavia", 3100),new CriterioMenorCosto(), new ArrayBuffer[Tarjeta]())
	  assertEquals(9.0, viaje.costo(), 0.0)
	}
	
	@Test def testElCriterioPorCostoEligeElDeMenor(){
	  val viaje = new ComoViajo().getViaje(new Direccion("Carabobo",2000),new Direccion("Carabobo", 4600),new CriterioMenorCosto(), new ArrayBuffer[Tarjeta]())
	  assertEquals(2.5, viaje.costo(), 0.0)
	}
	
	@Test def testElCriterioPorTiempoEligeElDeMenor(){
	  val viaje = new ComoViajo().getViaje(new Direccion("Carabobo",2000),new Direccion("Carabobo", 4600),new CriterioMenorTiempo(), new ArrayBuffer[Tarjeta]())
	  assertEquals(2.0, viaje.tiempo(), 0.0)
	}
}