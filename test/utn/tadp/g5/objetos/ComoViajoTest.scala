package utn.tadp.g5.objetos

import org.junit.Assert._
import org.junit.Test
import utn.tadp.g5.objetos.criterios.CriterioCosto
import utn.tadp.g5.objetos.criterios.CriterioTiempo
import utn.tadp.g5.objetos.tarjetas.Discapacitados
import utn.tadp.g5.objetos.tarjetas.Turismo
import utn.tadp.g5.objetos.tarjetas.Trabajo
import utn.tadp.g5.objetos.tarjetas.Zona
import utn.tadp.g5.objetos.tarjetas.Tarjeta

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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(2.75, recorrido.getCosto, 0.1)
	  assertEquals("105", recorrido.ruta(0).getMedio().getLinea())
	  
	  //Test de que existe un recorrido en Subte de 12 minutos
	  assertEquals(getRecorridoConDuracion(miViaje,12).size,1)
	  
	  //Test de que existe un recorrido en Subte de costo es $ 4.5
	  assertEquals(getRecorridoConCosto(miViaje,4.5).size,1)
	  
	  //Test que por cada alternativa de recorrido el tramo es directo
	  miViaje.recorridos.foreach(recorrido => assertEquals(recorrido.ruta.size,1))
	  
	  mostrarResultadoPorConsola(miViaje)
    	  
	}
  
  def getRecorridoConDuracion(viaje:Viaje, duracion:Double):Option[Recorrido] = {
    viaje.recorridos.find(recorrido => recorrido.getDuracion()==duracion)
  }

  def getRecorridoConCosto(viaje:Viaje, costo:Double):Option[Recorrido] = {
    viaje.recorridos.find(recorrido => recorrido.getCosto()==costo)
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
	  
	  //assertTrue: Test que prueba que me tengo que tomar el 103 para ambos tramos
	  miViaje.recorridos.foreach(recorrido => assertTrue(recorrido.ruta.exists(t => t.getMedio().getLinea()=="103")))
    
	  //assertTrue: Test que por cada alternativa de recorrido el tramo es combinado
	  miViaje.recorridos.foreach(recorrido => assertTrue(recorrido.ruta.size > 1))
	  
	  mostrarResultadoPorConsola(miViaje)
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
	  
	  val recorrido = miViaje.recorridos(0)
	  
	  //Test que prueba que el viaje mas corto lo hago con el colectivo 103
	  assertEquals(recorrido.ruta(0).getMedio().getLinea(),"103")
	  
	}

  @Test
	def testConsultarViajeDirectoB {
    
    val miCriterio =  new CriterioCosto()
    val origen = new Direccion("Pedernera", 750)
	  val destino = new Direccion("Rosario", 350)	  
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miCriterio.consultar(miConsultaDeViaje.consultar(parametrosViaje))
	  	  
	  imprimirTest("TEST Viaje Directo:")
	  imprimirDescripcion("Se consulta un solo tramo de un viaje combinado para testear el costo")
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
	def testConsultarViajeDescuentoDiscapacitado {
    
    val miDescuento =  new Discapacitados()
    val origen = new Direccion("Avellaneda", 750)
	  val destino = new Direccion("Alsina", 350)	  
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje, miDescuento)
	  	  
	  imprimirTest("TEST Viaje con Descuento para Discapacitados:")
	  imprimirDescripcion("Dado una consulta con descuento, se le aplica el mismo al costo")
	  mostrarResultadoPorConsola(miViaje, miDescuento)
    
	  miViaje.recorridos.foreach(recorrido => assertTrue(recorrido.getCosto(miDescuento) == 0))
	  
	}    

  @Test
	def testConsultarViajeDescuentoTrabajo {
    
    val miDescuento =  new Trabajo()
    val origen = new Direccion("Rivadavia", 750)
	  val destino = new Direccion("Alsina", 350)	  
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje, miDescuento)
	  	  
	  imprimirTest("TEST Viaje con Descuento para Trabajador:")
	  imprimirDescripcion("Dado una consulta con descuento, se le aplica el mismo al costo")
	  mostrarResultadoPorConsola(miViaje, miDescuento)
    
	  assertEquals(miViaje.recorridos.size,1)
	  
	}

  @Test
	def testConsultarViajeDescuentoTurismoEnZona {
    var centro:Zona = null
    val miDescuento =  new Turismo(centro)
    val origen = new Direccion("Avellaneda", 750)
	  val destino = new Direccion("Alsina", 350)	  
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje, miDescuento)
	  	  
	  imprimirTest("TEST Viaje con Descuento para Turismo:")
	  imprimirDescripcion("Dado una consulta con descuento, se le aplica el mismo al costo")
	  mostrarResultadoPorConsola(miViaje, miDescuento)
    
	  miViaje.recorridos.foreach(recorrido => recorrido.getPorcentajeDescuento(miDescuento)==10)
    
	}

  @Test
	def testConsultarViajeNoHayRecorrido {
	  val origen = new Direccion("Jujuy", 37)
	  val destino = new Direccion("Pedernera", 1400)
	  val parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  val miConsultaDeViaje = new ComoViajo()
	  val miViaje = miConsultaDeViaje.consultar(parametrosViaje)
	  
	  imprimirTest("TEST Viaje SIN Recorrido:")
	  imprimirDescripcion("Consulta de un origen/destino, y no exiten resultados")
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,0)
	}

  def mostrarResultadoPorConsola(viaje: Viaje, tarjeta:Tarjeta){   
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
        imprimirCostos(viaje,i, tarjeta)
        
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
          imprimirCostos(viaje,i, tarjeta)
      }           
    } 
    println("")
  }
  def mostrarResultadoPorConsola(viaje: Viaje){
    this.mostrarResultadoPorConsola(viaje, null)
  }
  
  def imprimirDuracion(viaje:Viaje, i:Int){
    println("Duracion: " + viaje.recorridos(i).getDuracion() + " minutos")
  }
  
  def imprimirCostos(viaje:Viaje, i:Int, tarjeta:Tarjeta){
    println("Costo: $ " + viaje.recorridos(i).getCosto(tarjeta))
    //if (viaje.recorridos(i).descuento>0) println("Descuento: $ " + viaje.recorridos(i).descuento)
  }
  
  def imprimirTest(nombre:String){    
	  println(nombre)	  
  }
  
  def imprimirDescripcion(descripcion:String){
    println(descripcion)
  }
}