package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import org.junit.Assert._
import org.junit.Test
import utn.tadp.g5.objetos.criterios.CriterioTiempo

class ComoViajoTest {
  	
  @Test
	def testConsultarViajeDirecto = {
	  val origen = new Direccion("Avellaneda", 37)
	  val destino = new Direccion("Alsina", 1400)
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje)
	  
	  imprimirTest("TEST Viaje Directo:")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.toArray.size,2)
	}
  
  @Test
	def testConsultarViajeCombina = {
    val origen = new Direccion("Pedernera", 750)
	  val destino = new Direccion("Brandsen", 350)
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje)
	  
	  imprimirTest("TEST Viaje Combinado:")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,2)
	}
 /*
  @Test
	def testConsultarViajeCriterio {
    
    val miCriterio =  new CriterioTiempo()
    val origen = new Direccion("Jujuy", 750)
	  val destino = new Direccion("Campichuelo", 350)	  
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miCriterio.consultar(miConsultaDeViaje.consultar(parametrosViaje))
	  	  
	  imprimirTest("TEST Viaje Criterio:")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,2)
	  
	}
  
  
  @Test
	def testConsultarViajeNoHayRecorrido {
    val origen = new Direccion("Jujuy", 750)
	  val destino = new Direccion("Pedernera", 1350)
	  val direcciones = new ParametrosDeViaje(origen, destino)
	  val viaje = new Viaje(direcciones) 
	  assertEquals("d",ConsultaExterna.consultar(viaje).size,0)
	}
	*/
  
  def mostrarResultadoPorConsola(viaje: Viaje){   
    var orden:Int = 0
    var descripcion:String = null
    var linea:Any = null 
    var direccionInicio:String = null
    var direccionFin:String = null
    val recorridos = viaje.recorridos.toArray
    var combina:String = null
    var resultadoCompleto:String = null
    
    for (i <- 0 until recorridos.size){                  
      if (recorridos(i).mapa.size>1){
        resultadoCompleto = "Alternativa combinada " + i.toString() + " : "
        
        for (e <- 0 until recorridos(i).mapa.size){          
          descripcion =  recorridos(i).mapa(e).medio.getDescripcion().toString()
          linea = recorridos(i).mapa(e).medio.getLinea()
          direccionInicio = recorridos(i).mapa(e).direccionInicio.calle + " " + recorridos(i).mapa(e).direccionInicio.numero
          direccionFin = recorridos(i).mapa(e).direccionFin.calle + " " + recorridos(i).mapa(e).direccionFin.numero
          resultadoCompleto += descripcion + "-" + linea + " / " + "Direccion (desde/hasta): " + direccionInicio + " a " + direccionFin
          if (e<(recorridos(i).mapa.size-1)){
            resultadoCompleto += " --> "
          }                             
        } 
        println(resultadoCompleto)
      }else if (recorridos(i).mapa.size>0){
          orden = (i+1) 
          descripcion =  recorridos(i).mapa(i).medio.getDescripcion().toString()
          linea = recorridos(i).mapa(i).medio.getLinea()
          direccionInicio = recorridos(i).mapa(i).direccionInicio.calle
          
          if (recorridos(i).mapa(i).direccionInicio.numero>0){
            direccionInicio += " " + recorridos(i).mapa(i).direccionInicio.numero
          }
          
          direccionFin = recorridos(i).mapa(i).direccionFin.calle
          
          if (recorridos(i).mapa(i).direccionFin.numero>0){
            direccionFin += " " + recorridos(i).mapa(i).direccionFin.numero
          }
          
          resultadoCompleto = "Alternativa directa " + orden + ": " + descripcion + "-" + linea + " / " + "Direccion (desde/hasta): " + direccionInicio + " a " + direccionFin
          
          println(resultadoCompleto)   
      }           
    } 
    println("")
  }
  
  def imprimirTest(nombre:String){    
	  println(nombre)	  
  }
}