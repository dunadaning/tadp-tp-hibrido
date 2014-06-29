package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Viaje

class Trabajo() extends Tarjeta(1.5){
	var laBoca:Zona = null
	var liniers:Zona = null
	var centro:Zona = null
	
	def aplica(viaje: Viaje)={
	  (viaje.saleDesdeLaZona(laBoca)||viaje.saleDesdeLaZona(liniers))&&(viaje.llegaALaZona(centro))
	}
	
	override def aplicarDescuento(viaje:Viaje) = this.getDescuento()
}