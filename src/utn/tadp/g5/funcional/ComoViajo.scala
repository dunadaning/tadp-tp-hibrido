package utn.tadp.g5.funcional

import utn.tadp.g5.objetos.Direccion
import scala.collection.mutable.ArrayBuffer

trait ModuloExterno{
	def consultarCercanos(direccion :Direccion):ArrayBuffer[(Medio,Direccion)]
	def combinan(medioA :Medio, medioB: Medio):Boolean
	def consultarCombinacion(medioA:Medio, medioB:Medio):Direccion
}

class ComoViajo {
  val moduloExterno:ModuloExterno = null 
  
  def getViaje(direccionSalida: Direccion, direccionLlegada: Direccion)={
    
  }
  
  def getPosiblesTramos(direccionSalida: Direccion, direccionLlegada: Direccion)={
    val mediosCercaDeLaLlegada = moduloExterno.consultarCercanos(direccionLlegada)
    val mediosCercaDeLaSalida = moduloExterno.consultarCercanos(direccionSalida)
    val posiblesTramos = new ArrayBuffer[Tramo]()
    
    for(medioSalida <- mediosCercaDeLaSalida){
      for(medioLlegada <- mediosCercaDeLaLlegada){
    	  
      }
    }
    
  }
  
  def getTramo(tuplaSalida:(Medio,Direccion), tuplaLlegada:(Medio,Direccion)):Option[Tramo]={
    
    val (medioLlegada, direccionLlegada) = tuplaLlegada
    val (medioSalida, direccionSalida) = tuplaSalida
    
    if(medioLlegada == medioSalida){
      return Some(new TramoSimple(direccionLlegada, medioLlegada, direccionSalida))
    }
    
    if(moduloExterno.combinan(medioLlegada, medioSalida)){
      val direccionCombinacion = moduloExterno.consultarCombinacion(medioLlegada, medioSalida)
      val tramoSalida= new TramoSimple(direccionSalida, medioSalida, direccionCombinacion)
      val tramoLlegada = new TramoSimple(direccionCombinacion, medioSalida, direccionSalida)
      return Some(new TramoCombinado(tramoSalida, tramoLlegada))
    }
    
    return None
  }
  	
}