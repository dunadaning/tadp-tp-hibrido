package edu.tadp.grupo5

class Viaje (val direcciones: ParametrosDeViaje, val criterio:Criterio) {
  val dir: ParametrosDeViaje = direcciones
  
  def this(direcciones: ParametrosDeViaje){
    this(direcciones, null)
  }
  
}