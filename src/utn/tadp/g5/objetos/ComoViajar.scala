package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer

class ComoViajar {

  def consultar(viaje:Viaje, criterio:Criterio) : ArrayBuffer[Recorrido] = {
    
   
    var transporteCercanosInicio = ArrayBuffer[Transporte]()     
    var transporteCercanosFin = ArrayBuffer[Transporte]()
    
    transporteCercanosInicio = obtenerTransportesCercanosEn(viaje.direcciones.origen)
    transporteCercanosFin = obtenerTransportesCercanosEn(viaje.direcciones.destino)

    return  calcularRecorrido(transporteCercanosInicio, transporteCercanosFin)
        
  }
  
  def calcularRecorrido(tInicio:ArrayBuffer[Transporte], tFin:ArrayBuffer[Transporte]): ArrayBuffer[Recorrido] = {
    
    calcularDirecto(tInicio, tFin)// || calcularCombinado(tInicio, tFin)
    
  }

  def calcularDirecto(tInicio:ArrayBuffer[Transporte], tFin:ArrayBuffer[Transporte]): ArrayBuffer[Recorrido] = {
    val recorridos = ArrayBuffer[Recorrido]()   
    val recorrido = new Recorrido() 
        
    for(e <- tInicio.toArray) {
      
     if (pasaPorDestino(e.medio, tFin.toArray)){
        recorrido.medio = e.medio        
        recorrido.direccion = e.direccion
        recorridos += recorrido 
      }      
    }
    
    return recorridos
  }
  
  def calcularCombinado(tInicio:ArrayBuffer[Transporte], tFin:ArrayBuffer[Transporte]): ArrayBuffer[Recorrido] = {
    val recorridos = ArrayBuffer[Recorrido]()   
    val recorrido = new Recorrido() 
        
   //TODO
    
    return recorridos
  }
  
  def obtenerTransportesCercanosEn(direccion:Direccion): ArrayBuffer[Transporte] = {    
    ModuloExterno.consultarCercanos(direccion)
  }
  
  def pasaPorDestino(myTransportIda:Medio, transporteCercanosFin:Array[Transporte]) : Boolean = {
		  
	  for(e <- transporteCercanosFin) {
	  
	    if (e.medio.getLinea().equals(myTransportIda.getLinea())){	     
	      return true
	    }	  
	  }	 
	  
    return false
  }
  
  def consultar(viaje:Viaje) : ArrayBuffer[Recorrido] = {
    this.consultar(viaje, null)
  }
  
}