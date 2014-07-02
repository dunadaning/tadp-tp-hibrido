package utn.tadp.g5.objetos.mediosTransporte

import utn.tadp.g5.objetos.Direccion

class Subte(li:Char, estaciones:List[Direccion]) extends MedioConEstaciones {
	 val linea = li
	 val precio = 4.5
	 val descripcion = "Subte"
	 val tiempoPorEstacion:Double = 2
	   
	 
	 def getEstaciones() = estaciones
	 //def costoPara():Double ={
	 
	 def costoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double = {
	   precio
	 }
	 
	 override def costoCombinacionSubte(direccionSalida:Direccion, direccionLlegada:Direccion) = 0
	 
	 override def tiempoCombinacionSubte(direccionCombinacion : Direccion, direccionSalida:Direccion)={
	   4.0 
	 }
	 
	 override def tiempoCombinacionTren(direccionCombinacion : Direccion, direccionSalida:Direccion)={
	   5.0 
	 }
	 
	 def costoCombinacion(medio:Medio, direccionSalida:Direccion, direccionLlegada:Direccion)={
	  medio.costoCombinacionSubte(direccionSalida, direccionLlegada)
	}
	
	def tiempoCombinacion(direccionCombinacion:Direccion, medio:Medio, direccionSalida:Direccion)={
	  medio.tiempoCombinacionSubte(direccionCombinacion, direccionSalida)
	}
	 
	 override def getLinea(): Char = {
        return linea
     }

	 override def getDescripcion(): String = {
		return descripcion
	 }   
	
}