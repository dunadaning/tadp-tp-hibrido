package main

class Direccion(val street:String, val number:Int) {
  var calle: String = street
  var numero: Int = number
  
  def this(){
    this(null, 0)
  }
  
}