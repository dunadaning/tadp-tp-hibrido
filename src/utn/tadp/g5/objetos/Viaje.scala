package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.tarjetas.Zona
import scala.collection.mutable.MutableList
import utn.tadp.g5.objetos.tarjetas.Tarjeta

class Viaje { 
  //SE UTILIZA UNA LISTA
  var recorridos: List[Recorrido] = null //Cada indice del Array es una alternativa de viaje
  
  //var duraciones = new MutableList[Double]//Cada clave es la alternativa
  //var costos = new HashMap[Int,Double]//Cada clave es la alternativa
  //SE PASO A RECORRIDO
  
  //DETALLE: se puede hacer con un flatmap
  def getLineas() = recorridos.flatMap(recorrido => recorrido.getLineas())
  
  //DETALLE: se puede hacer con un flatmap
  def getTipos() = recorridos.flatMap(recorrido => recorrido.getTipos())
  
  //DETALLE: se puede hacer con un flatmap
  def getCompanias() = recorridos.flatMap(recorrido => recorrido.getCompanias())
    
  def calcularDuraciones(){
    recorridos.map(recorrido => recorrido.getDuracion())
  }

  def calcularCostos(tarjeta:Tarjeta){
    recorridos.map(recorrido => recorrido.getCosto(tarjeta))
  }

  
  def getRecorridoDuracionMinima():Recorrido = {
    recorridos.min(Ordering.by((r:Recorrido) => r.duracion))
  }
  
  def getRecorridoCostoMinimo():Recorrido = {
    recorridos.min(Ordering.by((r:Recorrido) => r.costo))
  }
  
  def costoViaje():Double = {
   var costoViaje:Double = 0
   
   recorridos.foreach(recorrido => costoViaje += recorrido.costo)
   
   costoViaje
   
  }

  def tiempoViaje():Double = {
   var tiempoViaje:Double = 0
   
   recorridos.foreach(recorrido => tiempoViaje += recorrido.duracion)
   
   costoViaje
   
  }  
  
  def perteneceALaZona(zona:Zona):Boolean = {
    recorridos.forall(recorrido => recorrido.perteneceALaZona(zona))
  }
}