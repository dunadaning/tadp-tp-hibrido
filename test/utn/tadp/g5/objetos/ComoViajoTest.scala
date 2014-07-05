package utn.tadp.g5.objetos

import org.junit.Assert.assertEquals
import org.junit.Test

import utn.tadp.g5.objetos.criterios.CriterioCosto
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
	  imprimirDescripcion("Consulta de un origen/destino, resultado una sola posibilidad de viaje")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.toArray.size,1)
	}

  @Test
	def testConsultarViajeCombina = {
    val origen = new Direccion("Pedernera", 750)
	  val destino = new Direccion("Brandsen", 350)
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje)
	  
	  imprimirTest("TEST Viaje Combinado:")
	  imprimirDescripcion("Dado un origen y un destino se tiene como resultado 2 alternativas de viaje que a su vez son combinadas")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,3)
	}

  @Test
	def testConsultarViajeCriterioTiempo {
    
    val miCriterio =  new CriterioTiempo()
    val origen = new Direccion("Pedernera", 750)
	  val destino = new Direccion("Brandsen", 350)	  
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miCriterio.consultar(miConsultaDeViaje.consultar(parametrosViaje))
	  	  
	  imprimirTest("TEST Viaje Criterio Tiempo:")
	  imprimirDescripcion("Dado una consulta con distintas alternativas se selecciona segun criterio")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,1)
	  
	}

    @Test
	def testConsultarViajeCriterio {
    
    val miCriterio =  new CriterioCosto()
    val origen = new Direccion("Pedernera", 750)
	  val destino = new Direccion("Brandsen", 350)	  
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miCriterio.consultar(miConsultaDeViaje.consultar(parametrosViaje))
	  	  
	  imprimirTest("TEST Viaje Criterio Costo:")
	  imprimirDescripcion("Dado una consulta con distintas alternativas se selecciona segun criterio")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,1)
	  
	}
    
/* 
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
    var ordenCom = 0
    var ordenDir = 0
    
    for (i <- 0 until recorridos.size){                  
      if (recorridos(i).mapa.size>1){
        ordenCom += 1
        resultadoCompleto = "Alternativa combinada " + (ordenCom).toString() + " : "
        
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
        imprimirDuracion(viaje,i)
        imprimirCostos(viaje,i)
        
      }else if (recorridos(i).mapa.size>0){
          //orden = (i+1)
          ordenDir += 1
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
          
          resultadoCompleto = "Alternativa directa " + ordenDir + ": " + descripcion + "-" + linea + " / " + "Direccion (desde/hasta): " + direccionInicio + " a " + direccionFin
          
          println(resultadoCompleto)   
          imprimirDuracion(viaje,i)
          imprimirCostos(viaje,i)
      }           
    } 
    println("")
  }
  
  def imprimirDuracion(viaje:Viaje, i:Int){
    println("Duracion: " + viaje.duraciones(i) + " minutos")
  }
  
  def imprimirCostos(viaje:Viaje, i:Int){
    println("Costo: $ " + viaje.costos(i))
  }
  
  def imprimirTest(nombre:String){    
	  println(nombre)	  
  }
  
  def imprimirDescripcion(descripcion:String){
    println(descripcion)
  }
}