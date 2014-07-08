package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio

class Transporte(tipo:Medio, inicio:Direccion, fin:Direccion) {
	val medio = tipo
	val direccionInicio = inicio
	val direccionFin = fin
	
	def tieneElMismoMedio(otroTransporte : Transporte)={
	  this.medio == otroTransporte.medio 
	}
	
	//CORRECCION: Un transporte con todo null no sirve para nada
	def this(){
	  this(null, null, null)
	}
}
