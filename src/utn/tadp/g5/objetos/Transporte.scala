package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio

class Transporte(medio: Medio, direccionInicio: Direccion, direccionFin: Direccion) {
	
	def tieneElMismoMedio(otroTransporte : Transporte): Boolean = {
	  this.getMedio == otroTransporte.getMedio 
	}
	
	def getMedio(): Medio = {
	  medio
	}
	
	def getDireccionInicio(): Direccion = {
	  direccionInicio
	}
	
	def getDireccionFin(): Direccion = {
	  direccionFin
	}
	
	//CORRECCION: Un transporte con todo null no sirve para nada
	def this(){
	  this(null, null, null)
	}
}
