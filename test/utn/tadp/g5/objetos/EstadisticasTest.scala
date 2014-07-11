package utn.tadp.g5.objetos

import org.junit.Assert._
import org.junit.Test
import utn.tadp.g5.objetos.estadisticas._

class EstadisticasTest {
  
  @Test
	def testEstadisticasFacturacionTotal = {
    val estadistica = new EstadisticaPorLinea()
	  var origen = new Direccion("Avellaneda", 37)
	  var destino = new Direccion("Alsina", 1400)
	  var parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  var miConsultaDeViaje = new ComoViajo()
	  var miViaje = miConsultaDeViaje.consultar(parametrosViaje)
	  estadistica.viajes += miViaje
	  
	  origen = new Direccion("Pedernera", 37)
	  destino = new Direccion("Rosario", 1400)
	  parametrosViaje = new ParametrosDeViaje(origen, destino)	 
	  miConsultaDeViaje = new ComoViajo()
	  miViaje = miConsultaDeViaje.consultar(parametrosViaje)
	  
	  estadistica.viajes += miViaje
	  val costoTotal = estadistica.calcularCostoTotal(estadistica.viajes)
	  println(costoTotal)
	  assertTrue(costoTotal>10)
	  
	}

}