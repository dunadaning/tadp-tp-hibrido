package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class ComoViajar {

  def consultar(parametrosDeViaje:ParametrosDeViaje, criterio:Criterio) : Viaje = {
    var viaje = new Viaje()   
    var transporteCercanosInicio = ArrayBuffer[Transporte]()     
    var transporteCercanosFin = ArrayBuffer[Transporte]()
    
    transporteCercanosInicio = obtenerTransportesCercanosEn(parametrosDeViaje.origen)
    transporteCercanosFin = obtenerTransportesCercanosEn(parametrosDeViaje.destino)

    viaje.recorridos = calcularRecorridos(transporteCercanosInicio, transporteCercanosFin, criterio)
    return  viaje
        
  }
  
  def calcularRecorridos(tInicio:ArrayBuffer[Transporte], tFin:ArrayBuffer[Transporte], criterio:Criterio): ArrayBuffer[Recorrido] = {
    var recorridos = ArrayBuffer[Recorrido]()
    
    recorridos += calcularDirecto(tInicio, tFin)
    recorridos += calcularCombinado(tInicio, tFin)
    
    recorridos
    
  }

  def calcularDirecto(tInicio:ArrayBuffer[Transporte], tFin:ArrayBuffer[Transporte]): Recorrido = {    
    val recorridos = new Recorrido() 
    var transporte = new Transporte()   
    val ti = tInicio.toArray
    val tf = tFin.toArray
    
    for (i <- 0 until ti.size){
      
     if (pasaPorDestino(ti(i).medio, tf)){       
       
       transporte = new Transporte(ti(i).medio, ti(i).direccion)       
       recorridos.mapa += (getKeyMap(recorridos.mapa) -> transporte)      
      }      
    }
    
    return recorridos
  }
  
  def getKeyMap(mapa:HashMap[Int,Transporte]): Int = {
    return mapa.size    
  }
  
  def calcularCombinado(tInicio:ArrayBuffer[Transporte], tFin:ArrayBuffer[Transporte]): Recorrido = {    
    val recorridos = new Recorrido() 
    var transporteA = new Transporte()
    var transporteB = new Transporte()
    var direccion:Direccion = null
    val ti = tInicio.toArray
    val tf = tFin.toArray
    
    for (i <- 0 until ti.size){
     for (p <- 0 until tf.size){
       direccion = ModuloExterno.consultarCombinacion(ti(i).medio, tf(p).medio)
       if (direccion!=null){              
         transporteA = new Transporte(ti(i).medio, direccion) 
         transporteB = new Transporte(tf(i).medio, tf(i).direccion) 
         recorridos.mapa += (getKeyMap(recorridos.mapa) -> transporteA)
         recorridos.mapa += (getKeyMap(recorridos.mapa) -> transporteB) 
       }
     } 
    }
    
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
  
  def consultar(viaje:ParametrosDeViaje) : Viaje = {
    this.consultar(viaje, null)
  }
  
}