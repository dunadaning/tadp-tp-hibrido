package utn.tadp.g5.objetos

class Transporte(tipo:Medio, dir:Direccion) {
	val medio = tipo
	val direccion = dir
	
	def this(){
	  this(null, null)
	}
}