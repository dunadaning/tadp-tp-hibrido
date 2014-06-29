package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Viaje

abstract class Tarjeta(descuento:Double) {
	def aplica(viaje:Viaje):Boolean
  	def aplicarDescuento(viaje:Viaje):Double = viaje.costoViaje() * descuento
	def getDescuento() = descuento
  	
	def aplicarDescuetoViaje(viaje:Viaje):Double={
	  if(this.aplica(viaje)){
	    return this.aplicarDescuento(viaje)
	  }
	  return 0.0
	}
}