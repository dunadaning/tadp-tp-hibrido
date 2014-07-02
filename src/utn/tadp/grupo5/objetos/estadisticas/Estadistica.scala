package utn.tadp.g5.objetos.estadisticas

import scala.collection.mutable.ArrayBuffer
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Viaje
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.tarjetas.Zona
import utn.tadp.g5.objetos.tarjetas.Zona
import java.lang.invoke.LambdaForm
import utn.tadp.g5.objetos.estadisticas.Criterio

trait HistoricoViajes{
  type Linea = String
  def getViajes():ArrayBuffer[Viaje]
  def getLineas():ArrayBuffer[Linea]
  def getZonas():ArrayBuffer[Zona]
}


class Estadistica[T] {
	type Viajes = ArrayBuffer[Viaje]
  
	val viajes = this.getViajes()
	val historicoViajes:HistoricoViajes = null
	val criterios = new ArrayBuffer[Criterio]()
	
	def agregarCriterio(criterio:Criterio){
	  criterios += criterio
	}
	
	def cumple(viaje:Viaje)={
	  criterios.forall(criterio => criterio.cumple(viaje))
	}
	
	def getViajes()={
	  historicoViajes.getViajes().filter( v => this.cumple(v))
	}
	
	def getElementosComparacion():ArrayBuffer[T]
	
	def formaParteDelElemento(viaje: Viaje):Boolean
	
	def getEstadistica(bloque: (Viajes => Double))={
		val estadistica = new HashMap[T, Double]()
				
		for(elementoComparacion <- this.getElementosComparacion()){
		  val viajesElemento = viajes.filter(viaje => this.formaParteDelElemento(viaje) && this.cumple(viaje))
		  estadistica(elementoComparacion)= bloque.apply(viajesElemento)
		}
		
		estadistica
	}
	
	def calcularCostoTotal(viajes: Viajes)={
	  viajes.foldRight(0.0)((viaje, acum) => acum + viaje.costoViaje())
	}
	
	def calcularTiempoTotal(viajes:Viajes)={
	  viajes.foldRight(0.0)((viaje, acum) => acum + viaje.tiempoViaje())
	}
	
	def getEstadisticaCostoPromedio()={
		val bloque:(Viajes => Double) = 
		  viajes => ( this.calcularCostoTotal(viajes) / viajes.length)
		this.getEstadistica(bloque)
	}
	
	def getEstadisticaTiempoPromedio()={
		val bloque:(Viajes => Double) = 
		  viajes => ( this.calcularTiempoTotal(viajes) / viajes.length)
		this.getEstadistica(bloque)
	}
	
	def getEstadisticaFacturacionTotal()={
		val bloque:(Viajes => Double) = 
		  viajes => this.calcularCostoTotal(viajes)
		this.getEstadistica(bloque)
	}

	def getEstadisticaPorcentajeUtilizacion()={
		val cantidadUsos = this.getElementosComparacion().length
	  
		val bloque:(Viajes => Double) = 
		  viajes => viajes.length / cantidadUsos
		
		this.getEstadistica(bloque)
	}

}

class EstadisticaPorZona extends Estadistica{
	
	
}

class EstadisticaPorLineas extends Estadistica{
	
}

class EstadisticaPorTipo extends Estadistica{
	
}

class EstadisticaPorCompania extends Estadistica{
	
}