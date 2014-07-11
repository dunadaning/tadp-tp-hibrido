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
/* 
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
    val recorridos = viaje.recorridos
    var combina:String = null
    var resultadoCompleto:String = null
    var ordenCom = 0
    var ordenDir = 0
    
    for (i <- 0 until recorridos.size){                  
      if (recorridos(i).ruta.size>1){
        ordenCom += 1
        resultadoCompleto = "Alternativa combinada " + (ordenCom).toString() + " : "
        
        for (e <- 0 until recorridos(i).ruta.size){          
          descripcion =  recorridos(i).ruta(e).getMedio().getDescripcion().toString()
          linea = recorridos(i).ruta(e).getMedio().getLinea()
          direccionInicio = recorridos(i).ruta(e).getDireccionInicio().calle + " " + recorridos(i).ruta(e).getDireccionInicio().numero
          direccionFin = recorridos(i).ruta(e).getDireccionFin().calle + " " + recorridos(i).ruta(e).getDireccionFin().numero
          resultadoCompleto += descripcion + "-" + linea + " / " + "Direccion (desde/hasta): " + direccionInicio + " a " + direccionFin
          if (e<(recorridos(i).ruta.size-1)){
            resultadoCompleto += " --> "
          }                             
        } 
        println(resultadoCompleto)
        imprimirDuracion(viaje,i)
        imprimirCostos(viaje,i)
        
      }else if (recorridos(i).ruta.size>0){
          //orden = (i+1)
          ordenDir += 1
          descripcion =  recorridos(i).ruta.head.getMedio().getDescripcion().toString()
          linea = recorridos(i).ruta.head.getMedio().getLinea()
          direccionInicio = recorridos(i).ruta.head.getDireccionInicio().calle
          
          if (recorridos(i).ruta.head.getDireccionInicio().numero>0){
            direccionInicio += " " + recorridos(i).ruta.head.getDireccionInicio().numero
          }
          
          direccionFin = recorridos(i).ruta.head.getDireccionFin().calle
          
          if (recorridos(i).ruta.head.getDireccionFin().numero>0){
            direccionFin += " " + recorridos(i).ruta.head.getDireccionFin().numero
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
    println("Duracion: " + viaje.recorridos(i).duracion + " minutos")
  }
  
  def imprimirCostos(viaje:Viaje, i:Int){
    println("Costo: $ " + viaje.recorridos(i).costo)
  }
  
  def imprimirTest(nombre:String){    
	  println(nombre)	  
  }
  
  def imprimirDescripcion(descripcion:String){
    println(descripcion)
  }
}