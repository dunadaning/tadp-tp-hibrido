package utn.tadp.g5.objetos

class Greeter {

  def saludar() = saludarA("Mundo")

  def saludarA(quien: String) = print("Hola " + quien + "!")
  
}