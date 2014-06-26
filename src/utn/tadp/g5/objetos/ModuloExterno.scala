package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer

object ModuloExterno {
  
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  
  
  def consultarCercanos(direccion:Direccion) : ArrayBuffer[TransporteCercano] = {
    var respuesta = ArrayBuffer[TransporteCercano]()    
    
    if (direccion.calle.equals("Avellaneda")){
      
      var transporteA = new TransporteCercano(new Colectivo(15), new Direccion("Avellaneda",1))    
      respuesta += transporteA
    
      transporteA = new TransporteCercano(new Colectivo(65), new Direccion("Rio de Janeiro",1500))
      respuesta += transporteA

      transporteA = new TransporteCercano(new Colectivo(54), new Direccion("Acevedo",350))
      respuesta += transporteA
    
      transporteA = new TransporteCercano(new Subte('A'), new Direccion("Rivadavia", 3504))
      respuesta += transporteA
      
    }else{
      var transporteA = new TransporteCercano(new Subte('A'), new Direccion("Rivadavia", 3504))    
      respuesta += transporteA
    }
        
    return respuesta
  }
  
  def consultarCombinacion(medio:Medio) : TransporteCercano = {    
    var respuesta = new TransporteCercano()
    
    /*var combina = new TransporteCercano(new Colectivo(103), new Direccion("Rosario", 156))           
    respuesta += combina 
    
    combina = new TransporteCercano(new Subte('H'), new Direccion("Rivadavia", 3000))
    respuesta += combina*/
    
    return respuesta
    
  }
  
  def consultarDistanciaColectivo(colectivo:Colectivo, inicio:Direccion, fin:Direccion) : Int = {        
    return 5000    
  }
    
  def consultarDistanciaPie(inicio:Direccion, fin:Direccion) : Int = {        
    return 50000    
  } 
}