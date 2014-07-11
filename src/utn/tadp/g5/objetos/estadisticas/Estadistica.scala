package utn.tadp.g5.objetos.estadisticas

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

import utn.tadp.g5.objetos.Recorrido
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.estadisticas.Filtro
import utn.tadp.g5.objetos.tarjetas.Zona
import utn.tadp.g5.objetos.tarjetas.Zona
import utn.tadp.g5.objetos.tarjetas.Zona


trait HistoricoViajes{
  type Linea = String
  def getViajes():ArrayBuffer[Viaje]
  def getLineas():ArrayBuffer[Linea]
  def getZonas():ArrayBuffer[Zona]
}


abstract class Estadistica[T] {
	type Viajes = ArrayBuffer[Viaje]
  
	val viajes:Viajes = new ArrayBuffer[Viaje] // = this.getViajes()
	val historicoViajes:HistoricoViajes = null
	val criterios = new ArrayBuffer[Filtro]()
	val elementosComparacion = new ArrayBuffer[T]()
	
	def agregarCriterio(criterio:Filtro){
	  criterios += criterio
	}
	
	def agregarElementoComparacion(elemento :T){
	  elementosComparacion += elemento
	}
	
	def cumple(viaje:Viaje)={
	  criterios.forall(criterio => criterio.cumple(viaje))
	}
	
	def getViajes()={
	  historicoViajes.getViajes().filter( v => this.cumple(v))
	}
	
	
	def formaParteDelElemento(viaje: Viaje):Boolean = 
	  elementosComparacion.exists(elem => this.perteneceElemento(viaje, elem))
	  
	def perteneceElemento(viaje: Viaje, elemento: T): Boolean
	
	def getEstadistica(bloque: (Viajes => Double))={
		val estadistica = new HashMap[T, Double]()
				
		for(elementoComparacion <- this.elementosComparacion){
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
		val cantidadUsos = this.elementosComparacion.length
	  
		val bloque:(Viajes => Double) = 
		  viajes => viajes.length / cantidadUsos
		
		this.getEstadistica(bloque)
	}

}

class EstadisticaPorZona extends Estadistica[Zona]{

	def perteneceElemento(viaje: Viaje, elem: Zona): Boolean = viaje.perteneceALaZona(elem)

}

class EstadisticaPorLinea extends Estadistica[String]{
	
	def perteneceElemento(viaje: Viaje, elem: String): Boolean = viaje.getLineas().contains(elem)
}

class EstadisticaPorTipo extends Estadistica[String]{

	def perteneceElemento(viaje: Viaje, elem: String): Boolean = viaje.getTipos().contains(elem)
}

class EstadisticaPorCompania extends Estadistica[String]{

	def perteneceElemento(viaje: Viaje, elem: String): Boolean = viaje.getCompanias().contains(elem)
}