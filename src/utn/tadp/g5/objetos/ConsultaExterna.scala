package utn.tadp.g5.objetos

object ConsultaExterna {
  
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO
  def consultar(viaje:Viaje) : Array[Recorrido] ={
    
    val tipo: Tipo = new Tipo('c', "colectivo")
    var recorrido = new Recorrido()
    
    viaje.direcciones.origen.calle match {
      case "Rivadavia" => getRespuestaDirecto()
      case "Medrano" => getRespuestaCombina()
      case "Avellaneda" => getRespuestaCriterio()
      case "Jujuy" => getRespuestaNoHayRecorrido()
    } 
  
    /*recorrido.setMedio(new Medio(tipo))
    recorrido.setInicio(new Direccion())
    recorrido.setFin(new Direccion())
    respuesta :+ recorrido*/    
  }
  
  def getRespuestaDirecto() : Array[Recorrido] = {
    var respuesta = new Array[Recorrido](1)  
    respuesta
  }
  
  def getRespuestaCombina() : Array[Recorrido] = {
    var respuesta = new Array[Recorrido](2)    
    respuesta
  }

  def getRespuestaCriterio() : Array[Recorrido] = {
    var respuesta = new Array[Recorrido](1)    
    respuesta
  }
  
  def getRespuestaNoHayRecorrido() : Array[Recorrido] = {
    var respuesta = new Array[Recorrido](0)    
    respuesta
  }

}