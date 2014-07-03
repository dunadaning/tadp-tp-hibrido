package utn.tadp.g5.funcional

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ModuloExterno

trait Tramo {
	def tiempoTramo():Double
	def costoTramo():Double
}

case class TramoSimple(direccionSalida: Direccion, medio:Medio, direccionLlegada:Direccion){
  def costoTramo()= medio.costoPara(direccionSalida, direccionLlegada)
  def tiempoTramo() = medio.tiempoPara(direccionSalida, direccionLlegada)
}

case class TramoCombinado(tramo1: TramoSimple, tramo2: TramoSimple){
  
  def costoTramo()={
    (tramo1, tramo2) match{
      case (TramoSimple(direccionSalida,Subte(linea,estaciones),direccionLlegada),TramoSimple(_,Subte(_,_),_)) => Subte(linea,estaciones).costoPara(direccionSalida, direccionLlegada)
      case (unTramo,otroTramo) => unTramo.costoTramo() + otroTramo.costoTramo()
    }
  }
  
  def tiempoTramo()={
    tramo1.tiempoTramo() + tramo2.tiempoTramo() + this.tiempoCombinacion()
  }
  
  def tiempoCombinacion()={
    (this.tramo1.medio, this.tramo2.medio) match{
      case (Subte(_,_), Subte(_,_)) => 4
      case (Tren(_,_), Tren(_,_)) => 6
      case (Tren(_,_), Subte(_,_)) => 5
      case (Subte(_,_), Tren(_,_)) => 5
      case (_,_) => ModuloExterno.consultarDistanciaPie(tramo1.direccionLlegada , tramo2.direccionSalida) * 25.0
    }
  }
  
}