package utn.tadp.g5.funcional

import utn.tadp.g5.objetos.Direccion

abstract class Tarjeta(descuento:Double) {
	def aplica(tramo:Tramo):Boolean = true
  	def aplicarDescuento(tramo:Tramo):Double = tramo.costoTramo() * descuento
	def getDescuento() = descuento
  	
	def aplicarDescuetoViaje(tramo:Tramo):Double={
	  if(this.aplica(tramo)){
	    return this.aplicarDescuento(tramo)
	  }
	  return 0.0
	}
}

class Turismo(zona: Zona) extends Tarjeta(0.10){
   
  override def aplica(tramo: Tramo)={
    tramo.perteneceALaZona(zona)
  }
}

class Discapacitados extends Tarjeta(1)

class Trabajo() extends Tarjeta(1.5){
	var laBoca:Zona = null
	var liniers:Zona = null
	var centro:Zona = null
	
	override def aplica(tramo:Tramo)={
	  (tramo.saleDesdeLaZona(laBoca)||tramo.saleDesdeLaZona(liniers))&&(tramo.llegaALaZona(centro))
	}
	
	override def aplicarDescuento(tramo:Tramo) = this.getDescuento()
}

trait Zona {
	def incluye(direccionInicio:Direccion):Boolean
}