package utn.tadp.g5.objetos.estadisticas

import utn.tadp.g5.objetos.Viaje

trait Filtro {
	def cumple(viaje:Viaje):Boolean
}