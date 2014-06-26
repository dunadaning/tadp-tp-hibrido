package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer

class ComoViajar {

  def consultar(viaje:Viaje, criterio:Criterio) : ArrayBuffer[Recorrido] = {
    
    val recorridos = ArrayBuffer[Recorrido]()   
    val recorrido = new Recorrido()    
    var transporteCercanosIda = ArrayBuffer[TransporteCercano]()     
    var transporteCercanosFin = ArrayBuffer[TransporteCercano]()
    
    transporteCercanosIda = ModuloExterno.consultarCercanos(viaje.direcciones.origen)
    transporteCercanosFin = ModuloExterno.consultarCercanos(viaje.direcciones.destino)

    for(e <- transporteCercanosIda.toArray) {
      
     if (pasaPorDestino(e.medio, transporteCercanosFin.toArray)){
        recorrido.medio = e.medio        
        recorrido.direccion = e.direccion
        recorridos += recorrido 
      }
      
    }
    
    return  recorridos
        
  }
  
  def pasaPorDestino(myTransportIda:Medio, transporteCercanosFin:Array[TransporteCercano]) : Boolean = {
		  
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