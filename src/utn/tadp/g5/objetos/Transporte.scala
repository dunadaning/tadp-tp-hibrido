package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.mediosTransporte.Medio

class Transporte(tipo:Medio, dir:Direccion) {
	val medio = tipo
	val direccion = dir
	
	def this(){
	  this(null, null)
	}
}