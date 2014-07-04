package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio

class Transporte(tipo:Medio, inicio:Direccion, fin:Direccion) {
	val medio = tipo
	val direccionInicio = inicio
	val direccionFin = fin
	
	def tieneElMismoMedio(otroTransporte : Transporte)={
	  this.medio == otroTransporte.medio 
	}
	
	def this(){
	  this(null, null, null)
	}
}
