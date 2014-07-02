package utn.tadp.g5.objetos.mediosTransporte

import utn.tadp.g5.objetos.mediosTransporte.Contains
import utn.tadp.g5.objetos.mediosTransporte.Medio
import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.ModuloExterno
import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.Tramo

class Colectivo(li:Int) extends Medio {  
	val linea = li
	val descripcion = "Colectivo"	
	val C1 = new Contains(0 to 3)
	val C2 = new Contains(4 to 6)
	val velocidad = 15.0
	
	def costoPara(direccionSalida:Direccion, direccionLlegada:Direccion):Double={
	  val distancia = this.calcularDistancia(direccionSalida, direccionLlegada)
	  var costo = 0.0
	  
	  if(distancia >= 0 && distancia <= 3){
	    costo = 2.5
	  }
	  
	  if(distancia > 3 && distancia <= 6){
	    costo = 2.75
	  }
	  
	  if(distancia > 6){
	    costo = 2.85
	  }
	  
	  return costo
	}
	
	def costoCombinacion(medio:Medio, direccionSalida:Direccion, direccionLlegada:Direccion)={
	  medio.costoCombinacionColectivo(direccionSalida, direccionLlegada)
	}
	
	def tiempoCombinacion(direccionCombinacion:Direccion, medio:Medio, direccionSalida:Direccion)={
	  medio.tiempoCombinacionColectivo(direccionCombinacion, direccionSalida)
	}
	
	def calcularDistancia(direccionSalida:Direccion, direccionLlegada:Direccion)={
	  ModuloExterno.consultarDistanciaColectivo(this, direccionSalida, direccionLlegada)
	}
	
	def tiempoPara(direccionSalida:Direccion, direccionLlegada:Direccion)={
	  val distancia = this.calcularDistancia(direccionSalida, direccionLlegada)
	  
	  (distancia / velocidad) * 60
	}
	
	def calcularPrecio(distancia:Int):Double ={
	  
	  distancia match {
	    case C1() => 2.5
	    case C2() => 2.75
	    case _ => 2.85
	  }	 
	}
	
	override def getLinea(): Int = {
	  return linea
  }
	
	override def getDescripcion(): String = {
	  return descripcion
  }
	
}