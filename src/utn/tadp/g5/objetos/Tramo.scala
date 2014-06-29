package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio
import utn.tadp.g5.objetos.tarjetas.Zona

trait Tramo{
  def costoTramo():Double
  def tiempoTramo(): Double
  def perteneceALaZona(zona:Zona): Boolean
  def saleDesdeLaZona(zona:Zona):Boolean
  def llegaALaZona(zona:Zona):Boolean
}

class TramoSimple(direccionSalida: Direccion, medio:Medio, direccionLlegada:Direccion){
	def getMedio() = medio
	def getDireccionSalida() = direccionSalida
	def getDireccionLlegada() = direccionLlegada
	
	def perteneceALaZona(zona:Zona) = zona.incluye(direccionSalida) && zona.incluye(direccionLlegada)
	def saleDesdeLaZona(zona:Zona) = zona.incluye(direccionSalida)
    def llegaALaZona(zona:Zona) = zona.incluye(direccionLlegada)
	
	def costoTramo()= medio.costoPara(direccionSalida, direccionLlegada)
	def tiempoTramo() = medio.tiempoPara(direccionSalida, direccionLlegada)
	
	def costoCombinacion(tramoCombinado: TramoSimple)={
	  tramoCombinado.getMedio().costoCombinacion(medio, direccionSalida, direccionLlegada)
	}
	
	def tiempoCombinacion(tramoCombinado: TramoSimple)={
	  tramoCombinado.getMedio().tiempoCombinacion(tramoCombinado.getDireccionLlegada(),medio, direccionSalida, direccionLlegada)
	}
}

class TramoCombinado(tramo1: TramoSimple, tramo2:TramoSimple) extends Tramo{
  def costoTramo() = tramo1.costoTramo() + tramo2.costoCombinacion(tramo1)
  def tiempoTramo() = tramo1.tiempoTramo() + tramo2.tiempoCombinacion(tramo1)
  
  def perteneceALaZona(zona:Zona)={
	 tramo1.perteneceALaZona(zona) || tramo2.perteneceALaZona(zona) ||
	 	(zona.incluye(tramo1.getDireccionSalida) && zona.incluye(tramo2.getDireccionLlegada))
  }
  
  def saleDesdeLaZona(zona:Zona)= zona.incluye(tramo1.getDireccionSalida())
  def llegaALaZona(zona:Zona)= zona.incluye(tramo2.getDireccionLlegada())
}