package edu.tadp.grupocinco

import org.junit.Test
import main.Direccion
import main.ParametrosDeViaje
import main.Direccion
import main.Viaje
import main.Criterio
import main.ConsultaExterna

class ComoViajoTest {
  	
  @Test
	def testConsultarViajeDirecto {
    val origen = new Direccion("Rivadavia", 2487)
	  val destino = new Direccion("Independencia", 350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones)
	  //assertEquals(ConsultaExterna.consultar(viaje).size,1)
	}
  
  @Test
	def testConsultarViajeCombina {
    val origen = new Direccion("Medrano", 750)
	  val destino = new Direccion("Independencia", 350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones)
	  //ConsultaExterna.consultar(viaje)
	}
  
  @Test
	def testConsultarViajeCriterio {
    val origen = new Direccion("Avellaneda", 750)
	  val destino = new Direccion("Corrientes", 350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
    val criterio =  new Criterio('T')
	  val viaje = new Viaje(direcciones, criterio) 
	  //ConsultaExterna.consultar(viaje)
	}
  
     @Test
	def testConsultarViajeNoHayRecorrido {
    val origen = new Direccion("Medrano", 750)
	  val destino = new Direccion("Pedernera", 1350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones) 
	  //ConsultaExterna.consultar(viaje)
	} 
}