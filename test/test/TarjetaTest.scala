package test

import utn.tadp.g5.objetos.Direccion
import scala.collection.mutable.ArrayBuffer
import org.junit.Test
import org.junit.Assert._
import utn.tadp.g5.funcional.ComoViajo
import utn.tadp.g5.funcional.CriterioMenorTiempo
import utn.tadp.g5.funcional.Tarjeta
import utn.tadp.g5.funcional.Discapacitados
import utn.tadp.g5.funcional.Trabajo



class TarjetaTest {
  
  @Test
  def testTarjetaDiscapacitadosAplicaDescuentoATramo(){
	var buscador = new ComoViajo();
	
	var salida = new Direccion("Albarinio",291);
	var llegada = new Direccion("Medrano",900);
	var criterio = new CriterioMenorTiempo();
	var tarjetas = new ArrayBuffer[Tarjeta];
	tarjetas += new Discapacitados();
	
	val viaje = buscador.getViaje(salida,llegada, criterio, tarjetas);
	
    var costo:Double = viaje.costo();
    var gratis:Double = 0.0;
    assertTrue(gratis.equals(costo));
  }
/*
 @Test
  def testTarjetaTrabajoAplicaDescuentoATramo(){
	var buscador = new ComoViajo();
	
	var salida = new Direccion("Albarinio",291);
	var llegada = new Direccion("Medrano",900);
	var criterio = new CriterioMenorTiempo();
	var tarjetas = new ArrayBuffer[Tarjeta];
	tarjetas += new Trabajo();
	
	val viaje = buscador.getViaje(salida,llegada, criterio, tarjetas);
	
    var costo = viaje.costo();
    
    assertEquals(costo,0.0);
    
    
  }*/

}