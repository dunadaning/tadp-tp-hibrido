package utn.tadp.g5.funcional

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ModuloExterno
import scala.collection.immutable.TreeMap
import utn.tadp.g5.funcional.ModuloExternoImplementado

trait Medio {
	def getLinea(): String
	def costoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double
	def tiempoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double
}

class Contains(inicio:Double, fin:Double) { 
  def unapply(i: Double): Boolean = inicio <= i && i <= fin
}

trait MedioConEstaciones extends Medio {
	def getEstaciones():List[Direccion] 
	val tiempoPorEstacion: Double
	
	def estacionesEntre(direccionSalida:Direccion, direccionLlegada:Direccion)={
	  val estaciones = this.getEstaciones()
	  (estaciones.indexOf(direccionLlegada) - estaciones.indexOf(direccionSalida)).abs
	}
	
	def tiempoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double={
	  this.estacionesEntre(direccionSalida, direccionLlegada) * tiempoPorEstacion 
	}
}

object Colectivo{
  var moduloExterno:ModuloExterno = ModuloExternoImplementado
}

case class Colectivo(linea: String) extends Medio{
	def getLinea()= linea
	val velocidad = 15
	
	val entre0y3 = new Contains(0.0,  3.0)
	val entre4y6 = new Contains(4.0 , 6.0)
	
	def costoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double ={
	  val distancia = this.calcularDistancia(direccionSalida, direccionLlegada)
	  
	  distancia match {
	    case entre0y3() => 2.5
	    case entre4y6() => 2.75
	    case _ => 2.85
	  }	 
	}
	
	def tiempoPara(direccionSalida:Direccion, direccionLlegada:Direccion)={
	  val distancia = this.calcularDistancia(direccionSalida, direccionLlegada)
	  
	  (distancia / velocidad) * 60
	}
	
	def calcularDistancia(direccionSalida: Direccion, direccionLlegada:Direccion)={
	 Colectivo.moduloExterno.consultarDistanciaColectivo(direccionSalida, direccionLlegada)
	}
}

case class Subte(linea:String, estaciones: List[Direccion]) extends MedioConEstaciones{
  def getLinea() = linea
  def getEstaciones() = estaciones
  val precio = 4.5
  val tiempoPorEstacion= 2.0 
  
  def costoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double = {
	   precio
  }
  
}

case class Tren(linea:String, estaciones: List[Direccion]) extends MedioConEstaciones{
  def getLinea() = linea
  def getEstaciones() = estaciones
  val tiempoPorEstacion= 3.0 
  var tablaPrecios = new TreeMap[Int,Double]
  
  def addPrecio(rangoEstaciones:Int, precio:Double){
	  tablaPrecios += (rangoEstaciones -> precio)
  }
  
  def costoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double = {
	   this.costoPara(this.estacionesEntre(direccionSalida, direccionLlegada))
  }
  
  def costoPara(estacionesRecorridas :Int):Double={
	  for(cantidadEstaciones <- tablaPrecios.keySet){
	    if(estacionesRecorridas <= cantidadEstaciones){
	      return tablaPrecios(cantidadEstaciones) 
	    }
	  }
	  return 0.0
  }
  
}
