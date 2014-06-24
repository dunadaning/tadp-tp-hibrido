package utn.tadp.g5.objetos

class TransporteCercano(tipo:Medio, dir:Direccion) {
	val medio = tipo
	val direccion = dir
	
	def this(){
	  this(null, null)
	}
}