package utn.tadp.g5.objetos

class Direccion(val street:String, val number:Int) {
  var calle: String = street
  var numero: Int = number
  
  override def equals(otraDireccion: Any)={
    val direccion = otraDireccion.asInstanceOf[Direccion]
    calle == direccion.calle && numero == direccion.numero 
  }
  
  def this(estacion:String){
    this(estacion, 0)
  }
  
}