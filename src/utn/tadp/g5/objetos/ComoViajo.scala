package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.mediosTransporte.Medio
import utn.tadp.g5.objetos.criterios.Criterio

class ComoViajo {

  def consultar(parametrosDeViaje:ParametrosDeViaje, criterio:Criterio) : Viaje = {
    var viaje = new Viaje()   
    var transporteCercanosInicio = ArrayBuffer[Cercano]()     
    var transporteCercanosFin = ArrayBuffer[Cercano]()
    
    transporteCercanosInicio = obtenerTransportesCercanosEn(parametrosDeViaje.origen)
    transporteCercanosFin = obtenerTransportesCercanosEn(parametrosDeViaje.destino)

    viaje.recorridos = calcularRecorridos(transporteCercanosInicio, transporteCercanosFin, criterio)
    viaje.calcularDuraciones()
    viaje.calcularCostos()
    
    return  viaje
        
  }
  
  def calcularRecorridos(tInicio:ArrayBuffer[Cercano], tFin:ArrayBuffer[Cercano], criterio:Criterio): ArrayBuffer[Recorrido] = {
    var recorridos = ArrayBuffer[Recorrido]()
    
    recorridos += calcularDirecto(tInicio, tFin)
    recorridos = fusionarRecorridos(recorridos, calcularCombinado(tInicio, tFin))
    
    recorridos
    
  }

  def fusionarRecorridos(recorridosA: ArrayBuffer[Recorrido], recorridosB:ArrayBuffer[Recorrido]):ArrayBuffer[Recorrido]={
    var recorridosFusionados = ArrayBuffer[Recorrido]()
    val recB = recorridosB.toArray
    recorridosFusionados = recorridosA
    
    for (e <- recB){
      recorridosFusionados+=e
    }
    
    return recorridosFusionados
  }
  
  def calcularDirecto(tInicio:ArrayBuffer[Cercano], tFin:ArrayBuffer[Cercano]): Recorrido = {    
    val recorridos = new Recorrido() 
    var transporte = new Transporte()   
    val ti = tInicio.toArray
    val tf = tFin.toArray
    var indxFin:Int = -1
    
    for (i <- 0 until ti.size){
     indxFin = pasaPorDestino(ti(i).medio, tf)
     if (indxFin > -1){       
       
       transporte = new Transporte(ti(i).medio, ti(i).direccion, tf(indxFin).direccion)       
       recorridos.mapa += (getKeyMap(recorridos.mapa) -> transporte)      
      }      
    }
    
    return recorridos
  }
  
  def getKeyMap(mapa:HashMap[Int,Transporte]): Int = {
    return mapa.size    
  }
  
  def calcularCombinado(tInicio:ArrayBuffer[Cercano], tFin:ArrayBuffer[Cercano]): ArrayBuffer[Recorrido] = {    
    //var recorridos = new Recorrido() 
    var combinados = new ArrayBuffer[Recorrido]
    var transporteA = new Transporte()
    var transporteB = new Transporte()
    var direccion:Direccion = null
    val ti = tInicio.toArray
    val tf = tFin.toArray
    
    for (i <- 0 until ti.size){
     for (p <- 0 until tf.size){
       direccion = ModuloExterno.consultarCombinacion(ti(i).medio, tf(p).medio)
       if (direccion!=null){
         var recorridos = new Recorrido()
         recorridos.mapa = new HashMap[Int,Transporte]
         transporteA = new Transporte(ti(i).medio, ti(i).direccion, direccion) 
         transporteB = new Transporte(tf(p).medio, direccion, tf(p).direccion) 
         recorridos.mapa += (getKeyMap(recorridos.mapa) -> transporteA)
         recorridos.mapa += (getKeyMap(recorridos.mapa) -> transporteB)  
         combinados += recorridos
       }
     }              
    }
    
    return combinados
  }
  
  def obtenerTransportesCercanosEn(direccion:Direccion): ArrayBuffer[Cercano] = {    
    ModuloExterno.consultarCercanos(direccion)
  }
  
  def pasaPorDestino(myTransportIda:Medio, transporteCercanosFin:Array[Cercano]) : Int = {
		  
	  for(i <- 0 until transporteCercanosFin.size) {
	  
	    if (transporteCercanosFin(i).medio.getLinea().equals(myTransportIda.getLinea())){	     
	      return i
	    }	  
	  }	 
	  
    return -1
  }
  
  def consultar(viaje:ParametrosDeViaje) : Viaje = {
    this.consultar(viaje, null)
  }
  
}