package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.tarjetas.Tarjeta
import utn.tadp.g5.objetos.Viaje

class Discapacitados extends Tarjeta(1){
	def aplica(viaje:Viaje) = true
}