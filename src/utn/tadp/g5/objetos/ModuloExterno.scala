package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer

object ModuloExterno {
  
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO!!!!
  
  
  def consultarCercanos(direccion:Direccion) : ArrayBuffer[Transporte] = {
    var respuesta = ArrayBuffer[Transporte]()    
    
    if (direccion.calle.equals("Avellaneda")){
      
      var transporteA = new Transporte(new Colectivo(15), new Direccion("Avellaneda",1))    
      respuesta += transporteA
    
      transporteA = new Transporte(new Colectivo(65), new Direccion("Rio de Janeiro",1500))
      respuesta += transporteA

      transporteA = new Transporte(new Colectivo(54), new Direccion("Acevedo",350))
      respuesta += transporteA
    
      transporteA = new Transporte(new Subte('A'), new Direccion("Rivadavia", 3504))
      respuesta += transporteA
      
    }else if (direccion.calle.equals("Alsina")){
      var transporteA = new Transporte(new Subte('A'), new Direccion("Rivadavia", 3504))    
      respuesta += transporteA
      
    }else if (direccion.calle.equals("Pedernera")){
      var transporteA = new Transporte(new Colectivo(103), new Direccion("Primera Junta", 35))
      respuesta += transporteA
      transporteA = new Transporte(new Colectivo(92), new Direccion("Cabildo", 355))
      respuesta += transporteA
      
    }else if (direccion.calle.equals("Brandsen")){
      var transporteA = new Transporte(new Colectivo(86), new Direccion("Almirante Brown", 378))    
      respuesta += transporteA
    }
        
    return respuesta
  }
  
  def consultarCombinacion(medioA:Medio, medioB:Medio) : Direccion = {    
    var respuesta: Direccion = null//new Direccion()
    
    if (medioA.getLinea().equals(103) && medioB.getLinea().equals(86)){  
        respuesta = new Direccion("Rosario", 156) 
    }
    
    return respuesta
    
  }
  
  def consultarDistanciaColectivo(colectivo:Colectivo, inicio:Direccion, fin:Direccion) : Int = {        
    return 5000    
  }
    
  def consultarDistanciaPie(inicio:Direccion, fin:Direccion) : Int = {        
    return 50000    
  } 
}