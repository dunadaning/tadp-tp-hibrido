package utn.tadp.g5.funcional

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ModuloExterno
import scala.collection.mutable.ArrayBuffer

trait Tramo {
	var moduloExterno:ModuloExterno=null
	def tiempoTramo():Double
	def costoTramo():Double
	def costoTramo(tarjetas: ArrayBuffer[Tarjeta]):Double
	def perteneceALaZona(zona:Zona): Boolean
	def saleDesdeLaZona(zona:Zona):Boolean
	def llegaALaZona(zona:Zona):Boolean
}

case class TramoSimple(direccionSalida: Direccion, medio:Medio, direccionLlegada:Direccion)extends Tramo{
  def costoTramo()= medio.costoPara(direccionSalida, direccionLlegada)
  def tiempoTramo() = medio.tiempoPara(direccionSalida, direccionLlegada)
  
  def costoTramo(tarjetas: ArrayBuffer[Tarjeta])={
    this.costoTramo() - tarjetas.map(_.aplicarDescuento(this)).sum
  }
  
  def perteneceALaZona(zona:Zona) = zona.incluye(direccionSalida) && zona.incluye(direccionLlegada)
  def saleDesdeLaZona(zona:Zona) = zona.incluye(direccionSalida)
  def llegaALaZona(zona:Zona) = zona.incluye(direccionLlegada)
}

case class TramoCombinado(tramoSalida: TramoSimple, tramoLlegada: TramoSimple) extends Tramo{
  
  def costoTramo()={
    (tramoSalida, tramoLlegada) match{
      case (TramoSimple(direccionSalida,Subte(linea,estaciones),direccionLlegada),TramoSimple(_,Subte(_,_),_)) => Subte(linea,estaciones).costoPara(direccionSalida, direccionLlegada)
      case (unTramo,otroTramo) => unTramo.costoTramo() + otroTramo.costoTramo()
    }
  }
  
  def costoTramo(tarjetas: ArrayBuffer[Tarjeta])={
    this.costoTramo() - tarjetas.map(_.aplicarDescuento(this)).sum
  }
  
  def tiempoTramo()={
    tramoSalida.tiempoTramo() + tramoLlegada.tiempoTramo() + this.tiempoCombinacion()
  }
  
  def tiempoCombinacion()={
    (this.tramoSalida.medio, this.tramoLlegada.medio) match{
      case (Subte(_,_), Subte(_,_)) => 4
      case (Tren(_,_), Tren(_,_)) => 6
      case (Tren(_,_), Subte(_,_)) => 5
      case (Subte(_,_), Tren(_,_)) => 5
      case (_,_) => moduloExterno.consultarDistanciaPie(tramoSalida.direccionLlegada , tramoLlegada.direccionSalida) * 25.0
    }
  }
  
  def perteneceALaZona(zona:Zona)={
	 tramoSalida.perteneceALaZona(zona) || tramoLlegada.perteneceALaZona(zona) ||
	 	(zona.incluye(tramoSalida.direccionSalida) && zona.incluye(tramoLlegada.direccionLlegada))
  }
  
  def saleDesdeLaZona(zona:Zona)= zona.incluye(tramoSalida.direccionSalida)
  def llegaALaZona(zona:Zona)= zona.incluye(tramoLlegada.direccionLlegada)
  
}