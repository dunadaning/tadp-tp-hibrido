package utn.tadp.g5.objetos

class Subte(li:Char) extends Medio {
	 val linea = li
	 val precio = 4.5
	 val descripcion = "Subte"
	 
	 def calcularPrecio():Double ={
	   precio
	 }
	 
   override def getLinea(): Char = {
        return linea
    }

	override def getDescripcion(): String = {
	  return descripcion
  }   
}