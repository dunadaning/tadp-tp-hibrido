package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.tarjetas.Zona

class Viaje { 
  var recorridos: ArrayBuffer[Recorrido] = null //Cada indice del Array es una alternativa de viaje
  var duraciones: HashMap[Int,Int] = null //Cada clave es la alternativa
  var costos: HashMap[Int,Double] = null //Cada clave es la alternativa
  var tramo:Tramo = null
  
  def getLineas() = recorridos.map(recorrido => recorrido.getLineas()).flatten
  
  def getTipos() = recorridos.map(recorrido => recorrido.getTipos()).flatten
  
  def getCompanias() = recorridos.map(recorrido => recorrido.getCompanias()).flatten
  
  def perteneceALaZona(zona:Zona)={
    this.tramo.perteneceALaZona(zona)
  }
  
  def costoViaje()={
    this.tramo.costoTramo()
  }
  
  def tiempoViaje()={
    this.tramo.tiempoTramo()
  }
  
  def saleDesdeLaZona(zona:Zona)={
    this.tramo.saleDesdeLaZona(zona)
  }
  
  def llegaALaZona(zona:Zona)={
    this.tramo.llegaALaZona(zona)
  }
  
  def calcularDuraciones(){
    //TODO a partir de los recorridos, calcular cada duracion por alternativa
  }

  def calcularCostos(){
    //TODO a partir de los recorridos, calcular cada costo por alternativa
  }
}