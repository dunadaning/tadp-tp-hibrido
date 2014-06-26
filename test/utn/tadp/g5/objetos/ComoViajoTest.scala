package utn.tadp.g5.objetos

import org.junit.Assert.assertEquals
import org.junit.Test
import utn.tadp.g5.objetos.ModuloExterno
import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ParametrosDeViaje
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Criterio
import scala.collection.mutable.ArrayBuffer

class ComoViajoTest {
  	
  @Test
	def testConsultarViajeDirecto {
	  val origen = new Direccion("Avellaneda", 37)
	  val destino = new Direccion("Alsina", 1400)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones)	  
	  val miViaje = new ComoViajar()
	  val miRecorrido = miViaje.consultar(viaje)
	  
    mostrarResultadoPorConsola(miRecorrido)
    
	  assertEquals(miRecorrido.size,1)
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
  
  def mostrarResultadoPorConsola(recorrido: ArrayBuffer[Recorrido]){   
    var orden:Int = 0
    var descripcion:String = null
    var linea:Any = null 
    var direccion:String = null
    
    for (i <- 0 until recorrido.size){
      orden = (i+1)
      descripcion =  recorrido(i).medio.getDescripcion().toString()
      linea = recorrido(i).medio.getLinea()
      direccion = recorrido(i).direccion.calle + " " + recorrido(i).direccion.numero
      
      println("Transporte " + orden + ": " + descripcion + "-" + linea + " / " + "Direccion: " + direccion)  
    
    }      
  }
  
}