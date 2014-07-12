package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio

class Cercano(val medio:Medio, var direccion:Direccion) {
	
	def elMedioCoinideCon(otroMedio: Medio): Boolean = {
	  
	  medio.getLinea().equals(otroMedio.getLinea())
	}
	
}