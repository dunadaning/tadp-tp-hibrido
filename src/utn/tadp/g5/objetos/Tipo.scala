package utn.tadp.g5.objetos

class Tipo(val cod: Char, val des: String) {
  var codigo: Char = cod
  var descripcion: String = des
  
  def this(){
    this(' ', "")
  }
}