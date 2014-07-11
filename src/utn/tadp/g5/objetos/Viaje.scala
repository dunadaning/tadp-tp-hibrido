package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.tarjetas.Zona
import scala.collection.mutable.MutableList
import utn.tadp.g5.objetos.tarjetas.Tarjeta

class Viaje { 
  //CORRECCION: recorridos, duraciones y costos deberia ser una sola coleccion con objetos que tengan esos tres datos(por ej: Recorrido)
  //SE UTILIZA UNA LISTA
  var recorridos: List[Recorrido] = null //Cada indice del Array es una alternativa de viaje
  
  //CORRECCION: Un Array o una lista es mas apropiado que un map en este caso.
  //var duraciones = new MutableList[Double]//Cada clave es la alternativa
  //var costos = new HashMap[Int,Double]//Cada clave es la alternativa
  //SE PASO A RECORRIDO
  
  def getLineas() = recorridos.map(recorrido => recorrido.getLineas()).flatten
  
  def getTipos() = recorridos.map(recorrido => recorrido.getTipos()).flatten
  
  def getCompanias() = recorridos.map(recorrido => recorrido.getCompanias()).flatten
    
  def calcularDuraciones(){
    recorridos.map(recorrido => recorrido.calcularDuracion())
  }

  def calcularCostos(tarjeta:Tarjeta){
    recorridos.map(recorrido => recorrido.calcularCosto(tarjeta))
  }

  
  def getRecorridoDuracionMinima():Recorrido = {
    recorridos.min(Ordering.by((r:Recorrido) => r.duracion))
  }
  
  def getRecorridoCostoMinimo():Recorrido = {
    recorridos.min(Ordering.by((r:Recorrido) => r.costo))
  }

}