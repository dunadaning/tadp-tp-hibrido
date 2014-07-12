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
	  assertEquals(21.6, recorrido.getDuracion, 0.1)
	  assertEquals("105", recorrido.ruta(0).getMedio().getLinea())
	  
	  val recorrido2 = miViaje.recorridos(1)
	  assertEquals(4.50, recorrido2.getCosto, 0.1)
	  assertEquals(12.0, recorrido2.getDuracion, 0.1)
	  assertEquals('A', recorrido2.ruta(0).getMedio().getLinea())
	  
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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(5.35, recorrido.getCosto, 0.1)
	  assertEquals(52.0, recorrido.getDuracion, 0.1)
	  assertEquals("103", recorrido.ruta(0).getMedio().getLinea())
	  assertEquals("86", recorrido.ruta(1).getMedio().getLinea())

	  val recorrido2 = miViaje.recorridos(1)
	  assertEquals(5.35, recorrido2.getCosto, 0.1)
	  assertEquals(78.0, recorrido2.getDuracion, 0.1)
	  assertEquals("103", recorrido2.ruta(0).getMedio().getLinea())
	  assertEquals("96", recorrido2.ruta(1).getMedio().getLinea())
	  
	  //assertTrue: Test que prueba que me tengo que tomar el 103 para ambos tramos
	  miViaje.recorridos.foreach(recorrido => assertTrue(recorrido.ruta.exists(t => t.getMedio().getLinea()=="103")))
	  
	  val recorridoTramoRosario = miViaje.recorridos(0)
	  val recorridoTramoEva = miViaje.recorridos(1)
	  //Test que prueba que la combinacion colectivo 103 y 86 dura menos que combinar colectivo 103 y colectivo 96
	  assertTrue(recorridoTramoRosario.getDuracion()<recorridoTramoEva.getDuracion())
    
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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(5.35, recorrido.getCosto, 0.1)
	  assertEquals(52.0, recorrido.getDuracion, 0.1)
	  assertEquals("103", recorrido.ruta(0).getMedio().getLinea())
	  assertEquals("86", recorrido.ruta(1).getMedio().getLinea())
	  
	  mostrarResultadoPorConsola(miViaje)
    
	  assertEquals(miViaje.recorridos.size,1)
	  
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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(2.85, recorrido.getCosto, 0.1)
	  assertEquals(52.0, recorrido.getDuracion, 0.1)
	  assertEquals("103", recorrido.ruta(0).getMedio().getLinea())
	  
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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(5.35, recorrido.getCosto, 0.1)
	  assertEquals(52.0, recorrido.getDuracion, 0.1)
	  assertEquals("103", recorrido.ruta(0).getMedio().getLinea())
	  
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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(0.0, recorrido.getCosto(miDescuento), 0.1)
	  assertEquals(21.6, recorrido.getDuracion, 0.1)
	  assertEquals("105", recorrido.ruta(0).getMedio().getLinea())

	  val recorrido2 = miViaje.recorridos(1)
	  assertEquals(0.0, recorrido2.getCosto(miDescuento), 0.1)
	  assertEquals(12.0, recorrido2.getDuracion, 0.1)
	  assertEquals('A', recorrido2.ruta(0).getMedio().getLinea())
	  
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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(1.5, recorrido.getCosto(miDescuento), 0.1)
	  assertEquals(66.8, recorrido.getDuracion, 0.1)
	  assertEquals("8", recorrido.ruta(0).getMedio().getLinea())
	  
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
	  
	  val recorrido = miViaje.recorridos(0)
	  assertEquals(2.75, recorrido.getCosto(miDescuento), 0.1)
	  assertEquals(21.6, recorrido.getDuracion, 0.1)
	  assertEquals("105", recorrido.ruta(0).getMedio().getLinea())

	  val recorrido2 = miViaje.recorridos(1)
	  assertEquals(4.05, recorrido2.getCosto(miDescuento), 0.1)
	  assertEquals(12.0, recorrido2.getDuracion, 0.1)
	  assertEquals('A', recorrido2.ruta(0).getMedio().getLinea())
	  
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