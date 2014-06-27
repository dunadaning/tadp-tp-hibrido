package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer

import org.junit.Assert.assertEquals
import org.junit.Test

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ParametrosDeViaje

class ComoViajoTest {
  	
  @Test
	def testConsultarViajeDirecto {
	  val origen = new Direccion("Avellaneda", 37)
	  val destino = new Direccion("Alsina", 1400)
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajar()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje)
	  
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,1)
	}
  
  /*
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
	* */
  
  def mostrarResultadoPorConsola(viaje: Viaje){   
    var orden:Int = 0
    var descripcion:String = null
    var linea:Any = null 
    var direccion:String = null
    val recorridos = viaje.recorridos.toArray
    
    for (i <- 0 until recorridos.size){
      orden = (i+1)      
      descripcion =  recorridos(i).mapa(i).medio.getDescripcion().toString()
      linea = recorridos(i).mapa(i).medio.getLinea()
      direccion = recorridos(i).mapa(i).direccion.calle + " " + recorridos(i).mapa(i).direccion.numero
      
      println("Transporte " + orden + ": " + descripcion + "-" + linea + " / " + "Direccion: " + direccion)  
    
    }      
  }
  
}