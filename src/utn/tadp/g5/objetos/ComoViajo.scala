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
    recorridosFusionados = recorridosA
    
    recorridosFusionados.appendAll(recorridosB)
    
    recorridosFusionados
  }
  
  def calcularDirecto(tInicio:ArrayBuffer[Cercano], tFin:ArrayBuffer[Cercano]): Recorrido = {    
    val recorridos = new Recorrido()    
    
    //CORRECCION: map en lugar de foreach
    tInicio.foreach(cercano => this.obtenerRecorrido(cercano, tFin, recorridos))
    recorridos
  }
  
  def obtenerRecorrido(inicio: Cercano, transportesFin: ArrayBuffer[Cercano], recorridos: Recorrido) = {
        
    val cercano = pasaPorDestino(inicio.medio, transportesFin)
    var transporte = new Transporte()
    
    if (!cercano.equals(None)){       
       
      transporte = new Transporte(inicio.medio, inicio.direccion, cercano.get.direccion)       
      recorridos.mapa += (getKeyMap(recorridos.mapa) -> transporte)       
      } 
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

  //CORRECCION: pasaPorDestino no expresa lo que hace.
  def pasaPorDestino(myTransportIda:Medio, transporteCercanosFin:ArrayBuffer[Cercano]) : Option[Cercano] = {
	  transporteCercanosFin.find(cercano => cercano.elMedioCoinideCon(myTransportIda))
  }
  
  def consultar(viaje:ParametrosDeViaje) : Viaje = {
    this.consultar(viaje, null)
  }
  
}