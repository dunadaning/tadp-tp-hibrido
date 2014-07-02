package utn.tadp.g5.objetos.estadisticas

import utn.tadp.g5.objetos.Viaje

trait Criterio {
	def cumple(viaje:Viaje):Boolean
}