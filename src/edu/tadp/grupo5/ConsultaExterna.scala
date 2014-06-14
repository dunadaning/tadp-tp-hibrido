package edu.tadp.grupo5

object ConsultaExterna {
  
  //SOLO PARA PRUEBAS, SIMULO MODULO EXTERNO
  def consultar(viaje:Viaje) : Array[Recorrido] ={
    val respuesta: Array[Recorrido]
    val tipo: Tipo = new Tipo('c', "colectivo")
    val recorrido = new Recorrido()
    
    /*viaje.direcciones.origen.calle match {
      case "Medrano" => println(1)  
    }*/ 

    recorrido.medio = new Medio()
    recorrido.medio.tipo = tipo
    
    respuesta = new Array(recorrido)
    
    respuesta
      
  }
}