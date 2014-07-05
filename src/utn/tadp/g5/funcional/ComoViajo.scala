package utn.tadp.g5.funcional

import utn.tadp.g5.objetos.Direccion
import scala.collection.mutable.ArrayBuffer
import test.ModuloExternoImplementado

trait ModuloExterno{
	def consultarCercanos(direccion :Direccion):ArrayBuffer[(Medio,Direccion)]
	def combinan(medioA :Medio, medioB: Medio):Boolean
	def consultarCombinacion(medioA:Medio, medioB:Medio):Direccion
	def consultarDistanciaPie(direccionLlegada:Direccion, direccionSalida:Direccion):Double
	def consultarDistanciaColectivo(direccionLlegada:Direccion, direccionSalida:Direccion):Double
}

class NoHayViajeDisponibleException extends Exception

class ComoViajo {
  var moduloExterno:ModuloExterno = ModuloExternoImplementado 
  type Criterio = {def apply(tramo1:Tramo, tramo2:Tramo, tarjetas:ArrayBuffer[Tarjeta]):Tramo}
  
  def getViaje(direccionSalida: Direccion, direccionLlegada: Direccion, criterio: Criterio, tarjetas:ArrayBuffer[Tarjeta])={
	this.getTramo(direccionSalida, direccionLlegada, criterio, tarjetas)match{
	  case None => throw new NoHayViajeDisponibleException()
	  case Some(tramo) => new Viaje(direccionSalida, tramo, direccionLlegada, tarjetas)
	}
  }
  
  def getTramo(direccionSalida: Direccion, direccionLlegada: Direccion, criterio:Criterio, tarjetas:ArrayBuffer[Tarjeta])={
    val mediosCercaDeLaLlegada = moduloExterno.consultarCercanos(direccionLlegada)
    val mediosCercaDeLaSalida = moduloExterno.consultarCercanos(direccionSalida)
    val posiblesTramos = new ArrayBuffer[Tramo]()
    var tramo:Option[Tramo] = None
    
    for(tuplaSalida <- mediosCercaDeLaSalida){
      for(tuplaLlegada <- mediosCercaDeLaLlegada){
    	val tramoCandidato = this.getTramoCandidato(tuplaSalida, tuplaLlegada)
    	tramo = tramoCandidato.fold(tramo)(tramoC => tramo.fold(Some(tramoC))
    	    (min => Some(criterio(min, tramoC, tarjetas))))     
      }
    }
    
    tramo
  }
  
  def getTramoCandidato(tuplaSalida:(Medio,Direccion), tuplaLlegada:(Medio,Direccion)):Option[Tramo]={
    
    val (medioLlegada, direccionLlegada) = tuplaLlegada
    val (medioSalida, direccionSalida) = tuplaSalida
    
    if(medioLlegada == medioSalida){
      return Some(new TramoSimple(direccionLlegada, medioLlegada, direccionSalida))
    }
    
    if(moduloExterno.combinan(medioLlegada, medioSalida)){
      val direccionCombinacion = moduloExterno.consultarCombinacion(medioLlegada, medioSalida)
      val tramoSalida= new TramoSimple(direccionSalida, medioSalida, direccionCombinacion)
      val tramoLlegada = new TramoSimple(direccionCombinacion, medioLlegada, direccionSalida)
      return Some(new TramoCombinado(tramoSalida, tramoLlegada))
    }
    
    return None
  } 	
}

class Viaje(direccionSalida:Direccion, tramo:Tramo, direccionLlegada:Direccion, tarjetas:ArrayBuffer[Tarjeta]){
	def costo() = tramo.costoTramo(tarjetas)
	def tiempo() = tramo.tiempoTramo()
	def getTramo() = tramo
}


class CriterioMenorCosto{
  def apply(tramo1:Tramo, tramo2:Tramo, tarjetas:ArrayBuffer[Tarjeta])={
    if(tramo1.costoTramo(tarjetas) <= tramo2.costoTramo(tarjetas)){
      tramo1
    }else{
      tramo2
    }
  }
}

class CriterioMenorTiempo{
  def apply(tramo1:Tramo, tramo2:Tramo, tarjetas:ArrayBuffer[Tarjeta])={
    if(tramo1.tiempoTramo()<= tramo2.tiempoTramo()){
      tramo1
    }else{
      tramo2
    }
  }
}