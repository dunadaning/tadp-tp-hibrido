package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio

class Cercano(tipo:Medio, dir:Direccion) {
	val medio = tipo
	var direccion = dir
	
	def this(){
	  this(null, null)
	}
	
	def this(tipo:Medio){
	  this(tipo, null)
	}
	
	def elMedioCoinideCon(otroMedio: Medio): Boolean = {
	  
	  medio.getLinea().equals(otroMedio.getLinea())
	}
	
}