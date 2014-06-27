package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class Viaje { 
  var recorridos: ArrayBuffer[Recorrido] = null
  var duraciones: HashMap[Int,Int] = null
  var costos: HashMap[Int,Double] = null
  
  /*def this(direcciones: ParametrosDeViaje){
    this(direcciones, null)
  }*/
  
  def calcularDuracion() : Int = {
    //TODO
    1
  }
  
}