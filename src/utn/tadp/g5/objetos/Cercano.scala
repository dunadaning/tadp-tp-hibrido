package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio

class Cercano(tipo:Medio, dir:Direccion) {
	val medio = tipo
	var direccion = dir
	
	//CORRECCION: No sirve un objeto con todos sus campos null
	def this(){
	  this(null, null)
	}
	
	//CORRECCION: No sirve un objeto con todos sus campos null
	def this(tipo:Medio){
	  this(tipo, null)
	}
	
}