package utn.tadp.g5.objetos.mediosTransporte

import utn.tadp.g5.objetos.mediosTransporte.Medio
import utn.tadp.g5.objetos.Direccion

class Subte(li:Char, estaciones:List[Direccion]) extends MedioConEstaciones {
	 val linea = li
	 val precio = 4.5
	 val descripcion = "Subte"
	 val tiempoPorEstacion = 2
	   
	 def getEstaciones() = estaciones
	 def costoPara():Double ={
	   precio
	 }
	 
	 def costoCombinacionSubte(direccionSalida:Direccion, direccionLlegada:Direccion) = 0
	 
	 def tiempoCombinacionSubte(direccionCombinacion : Direccion, direccionSalida:Direccion, direccionLlegada:Direccion)={
	   4.0 + this.tiempoPara(direccionSalida, direccionLlegada)
	 }
	 
	 def tiempoCombinacionTren(direccionCombinacion : Direccion, direccionSalida:Direccion, direccionLlegada:Direccion)={
	   5.0 + this.tiempoPara(direccionSalida, direccionLlegada)
	 }
	 
	 def costoCombinacion(medio:Medio, direccionSalida:Direccion, direccionLlegada:Direccion)={
	  medio.costoCombinacionSubte(direccionSalida, direccionLlegada)
	}
	
	def tiempoCombinacion(direccionCombinacion:Direccion, medio:Medio, direccionSalida:Direccion, direccionLlegada:Direccion)={
	  medio.tiempoCombinacionSubte(direccionCombinacion, direccionSalida, direccionLlegada)
	}
	 
	 override def getLinea(): Char = {
        return linea
     }

	 override def getDescripcion(): String = {
		return descripcion
	 }   
}