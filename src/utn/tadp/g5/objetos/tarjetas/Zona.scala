package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Direccion

trait Zona {
	def incluye(direccionInicio:Direccion):Boolean
}