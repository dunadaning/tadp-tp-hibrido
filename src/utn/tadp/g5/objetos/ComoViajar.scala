package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer

class ComoViajar {

  def consultar(viaje:Viaje, criterio:Criterio) : ArrayBuffer[Recorrido] = {
    
    val recorridos = ArrayBuffer[Recorrido]()   
    val recorridoIda = new RecorridoIda()
    val recorridoVuelta = new RecorridoVuelta()    
    var transporteCercanosIda = ArrayBuffer[TransporteCercano]()     
    var transporteCercanosFin = ArrayBuffer[TransporteCercano]()
    //var myTransport = new TransporteCercano()
    
    transporteCercanosIda = ModuloExterno.consultarCercanos(viaje.direcciones.origen)
    transporteCercanosFin = ModuloExterno.consultarCercanos(viaje.direcciones.destino)

    for(myTransport <- transporteCercanosIda.toArray) {
      //TODO: consultar combinaciones de cercanos. Por cada uno calcular distancia entre
      //direccion destino y direccion de combinacion
      //ModuloExterno.consultarCombinacion(myTransport.medio)
      
     if (pasaPorDestino(myTransport.medio, transporteCercanosFin.toArray)){
        
      }
      
    }
    
    return  recorridos
        
  }
  
  def pasaPorDestino(myTransportIda:Medio, transporteCercanosFin:Array[TransporteCercano]) : Boolean = {
		  
	for(myTransportFin <- transporteCercanosFin) {
	  if (myTransportFin.medio.linea == myTransportIda.linea){
	    
	  }
	}
	    	  
    
    return false
  }
  
  def consultar(viaje:Viaje) : ArrayBuffer[Recorrido] = {
    this.consultar(viaje, null)
  }
  
}