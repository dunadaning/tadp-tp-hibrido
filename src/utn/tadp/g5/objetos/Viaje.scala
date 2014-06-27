package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class Viaje { 
  var recorridos: ArrayBuffer[Recorrido] = null //Cada indice del Array es una alternativa de viaje
  var duraciones: HashMap[Int,Int] = null //Cada clave es la alternativa
  var costos: HashMap[Int,Double] = null //Cada clave es la alternativa
  
  def calcularDuraciones(){
    //TODO a partir de los recorridos, calcular cada duracion por alternativa
  }

  def calcularCostos(){
    //TODO a partir de los recorridos, calcular cada costo por alternativa
  }
}