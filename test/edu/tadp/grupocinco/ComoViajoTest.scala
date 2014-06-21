package edu.tadp.grupocinco

import org.junit.Test
import org.junit.Assert.assertEquals

import edu.tadp.grupo5.Criterio
import edu.tadp.grupo5.ParametrosDeViaje
import edu.tadp.grupo5.Viaje
import edu.tadp.grupo5.Direccion
import edu.tadp.grupo5.ConsultaExterna

class ComoViajoTest {
  	
  @Test
	def testConsultarViajeDirecto {
    val origen = new Direccion("Rivadavia", 2487)
	  val destino = new Direccion("Independencia", 350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones)
	  assertEquals("d",ConsultaExterna.consultar(viaje).size,1)
	}
  
  @Test
	def testConsultarViajeCombina {
    val origen = new Direccion("Medrano", 750)
	  val destino = new Direccion("Independencia", 350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones)
	  assertEquals("d",ConsultaExterna.consultar(viaje).size,2)
	}
  
  @Test
	def testConsultarViajeCriterio {
    val origen = new Direccion("Avellaneda", 750)
	  val destino = new Direccion("Corrientes", 350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
    val criterio =  new Criterio('T')
	  val viaje = new Viaje(direcciones, criterio) 
	  assertEquals("d",ConsultaExterna.consultar(viaje).size,1)
	}
  
     @Test
	def testConsultarViajeNoHayRecorrido {
    val origen = new Direccion("Jujuy", 750)
	  val destino = new Direccion("Pedernera", 1350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones) 
	  assertEquals("d",ConsultaExterna.consultar(viaje).size,0)
	} 
}