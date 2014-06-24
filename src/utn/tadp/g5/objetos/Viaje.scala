package utn.tadp.g5.objetos

class Viaje (val direcciones: ParametrosDeViaje, val criterio:Criterio) {
  val dir: ParametrosDeViaje = direcciones
  
  def this(direcciones: ParametrosDeViaje){
    this(direcciones, null)
  }
  
}