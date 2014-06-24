package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer

class ComoViajar {

  def consultar(viaje:Viaje, criterio:Criterio) : ArrayBuffer[Recorrido] = {
    
    val recorridos = ArrayBuffer[Recorrido]()   
    val recorridoIda = new RecorridoIda()
    val recorridoVuelta = new RecorridoVuelta()    
    var transporteCercanosIda = ArrayBuffer[TransporteCercano]()     
    var transporteCercanosVuelta = ArrayBuffer[TransporteCercano]()
    var myTransport = new TransporteCercano()
    
    transporteCercanosIda = ModuloExterno.consultarCercanos(viaje.direcciones.origen)
    transporteCercanosVuelta = ModuloExterno.consultarCercanos(viaje.direcciones.destino)

    for(myTransportIda <- transporteCercanosIda.toArray) {
      //TODO: consultar combinaciones de cercanos. Por cada uno calcular distancia entre
      //direccion destino y direccion de combinacion
      //ModuloExterno.consultarCombinacion(myTransport.medio)
      
    }
    
    return  recorridos
        
  }
  
  def consultar(viaje:Viaje) : ArrayBuffer[Recorrido] = {
    this.consultar(viaje, null)
  }
  
}