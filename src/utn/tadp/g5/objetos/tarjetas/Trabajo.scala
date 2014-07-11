package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Recorrido

class Trabajo() extends Tarjeta(1.5){
	var laBoca:Zona = null
	var liniers:Zona = null
	var centro:Zona = null
	
	def aplica(recorrido: Recorrido)={
	  (recorrido.saleDesdeLaZona(laBoca)||recorrido.saleDesdeLaZona(liniers))&&(recorrido.llegaALaZona(centro))
	}
	
	override def aplicarDescuentoAlCosto(costo:Double) = costo - this.getDescuento()
}