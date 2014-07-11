package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ModuloExterno

trait Zona {
	def incluye(direccionInicio:Direccion):Boolean = true
}