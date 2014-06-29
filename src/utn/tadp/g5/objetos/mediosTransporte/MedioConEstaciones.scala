package utn.tadp.g5.objetos.mediosTransporte

import utn.tadp.g5.objetos.Direccion

trait MedioConEstaciones extends Medio {
	def getEstaciones():List[Direccion] 
	def tiempoPorEstacion: Double
	
	def estacionesEntre(direccionSalida:Direccion, direccionLlegada:Direccion)={
	  val estaciones = this.getEstaciones()
	  estaciones.indexOf(direccionLlegada) - estaciones.indexOf(direccionSalida)
	}
	
	def tiempoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double={
	  this.estacionesEntre(direccionSalida, direccionLlegada) * tiempoPorEstacion 
	}
}