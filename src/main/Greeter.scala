package main

class Greeter {

  def saludar() = saludarA("Mundo")

  def saludarA(quien: String) = print("Hola " + quien + "!")
  
}