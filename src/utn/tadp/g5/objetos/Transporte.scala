package utn.tadp.g5.objetos

import utn.tadp.g5.objetos.mediosTransporte.Medio
import utn.tadp.g5.objetos.tarjetas.Zona

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
	
	def perteneceALaZona(zona:Zona) = ModuloExterno.direccionIncluidaEnZona(zona, direccionInicio) && ModuloExterno.direccionIncluidaEnZona(zona, direccionFin)//zona.incluye(direccionInicio) && zona.incluye(direccionFin)
	
	def saleDesdeLaZona(zona:Zona) = ModuloExterno.direccionIncluidaEnZona(zona, direccionInicio)//zona.incluye(direccionInicio)
  def llegaALaZona(zona:Zona) = ModuloExterno.direccionIncluidaEnZona(zona, direccionFin)//zona.incluye(direccionFin)
  
	//CORRECCION: Un transporte con todo null no sirve para nada
	//Se utiliza para instanciar en calcularCombinado de ComoViajo
	def this(){
	  this(null, null, null)
	}
}
