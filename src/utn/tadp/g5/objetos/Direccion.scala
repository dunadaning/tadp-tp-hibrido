package utn.tadp.g5.objetos

class Direccion(val street:String, val number:Int) {
  var calle: String = street
  var numero: Int = number
  
  def this(estacion:String){
    this(estacion, 0)
  }
  
}