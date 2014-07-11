package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Recorrido

abstract class Tarjeta(descuento:Double) {
	def aplica(recorrido:Recorrido):Boolean
  def aplicarDescuentoAlCosto(costo:Double):Double = costo * descuento
	
  def getDescuento() = descuento
  
  def aplicarDescuentoAlRecorrido(recorrido:Recorrido):Double={
	  if (aplica(recorrido)) 
	    return aplicarDescuentoAlCosto(recorrido.getCosto())
	  
	  return 0
	}
}