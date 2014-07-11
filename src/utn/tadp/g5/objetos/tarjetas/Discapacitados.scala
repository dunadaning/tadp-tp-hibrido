package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.tarjetas.Tarjeta
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Recorrido

class Discapacitados extends Tarjeta(1){
	var descuento = 1
  def aplica(recorrido:Recorrido) = true
}