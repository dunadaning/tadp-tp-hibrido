package utn.tadp.g5.objetos.mediosTransporte

import utn.tadp.g5.objetos.mediosTransporte.Medio

class Contains(r: Range) { 
  def unapply(i: Int): Boolean = r contains i 
}

class Tren(li:String) extends Medio{
	val linea = li
	val descripcion = "Tren"	
	val C1 = new Contains(1 to 5)
  val C2 = new Contains(6 to 8)	
	
	def calcularPrecio(estaciones:Int):Double ={
	  
	  estaciones match {
	    case C1() => 2
	    case C2() => 3.5
	    case _ => 4.75
	  }	  	 
	}
	
	override def getLinea(): String = {
	  return linea
  }
	
	override def getDescripcion(): String = {
	  return descripcion
  }
	
}