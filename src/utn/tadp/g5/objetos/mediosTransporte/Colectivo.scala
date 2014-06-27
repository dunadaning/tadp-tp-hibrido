package utn.tadp.g5.objetos.mediosTransporte

import utn.tadp.g5.objetos.mediosTransporte.Contains
import utn.tadp.g5.objetos.mediosTransporte.Medio

class Colectivo(li:Int) extends Medio {  
	val linea = li
	val descripcion = "Colectivo"	
	val C1 = new Contains(0 to 3)
  val C2 = new Contains(4 to 6)
	
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