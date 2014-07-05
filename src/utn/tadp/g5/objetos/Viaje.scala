package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.tarjetas.Zona

class Viaje { 
  var recorridos: ArrayBuffer[Recorrido] = null //Cada indice del Array es una alternativa de viaje
  var duraciones = new HashMap[Int,Double]//Cada clave es la alternativa
  var costos = new HashMap[Int,Double]//Cada clave es la alternativa
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
    val alternativas = recorridos.toArray
    for(i <- 0 until alternativas.size){
      duraciones += (i -> getDuracion(alternativas(i).mapa))  
    }
  }

  def calcularCostos(){
    //TODO a partir de los recorridos, calcular cada costo por alternativa
    val alternativas = recorridos.toArray
    for(i <- 0 until alternativas.size){
      costos += (i -> getCosto(alternativas(i).mapa))  
    }
  }
  
  def getDuracion(recorrido:HashMap[Int,Transporte]):Double = {
    var total:Double = 0
    
    for (i <- 0 until recorrido.size){
      total += recorrido(i).medio.tiempoPara(recorrido(i).direccionInicio, recorrido(i).direccionFin)
      if (i>0){
        //combinacion
        total +=recorrido(i).medio.tiempoCombinacion(recorrido(i).direccionInicio, recorrido(i-1).medio, recorrido(i).direccionFin)         
      }
      
    }
      
    return total
  }
  
   def getCosto(recorrido:HashMap[Int,Transporte]):Double = {
    var total:Double = 0
    
    for (i <- 0 until recorrido.size){
      total += recorrido(i).medio.costoPara(recorrido(i).direccionInicio, recorrido(i).direccionFin) 
      if (i>0){
        //combinacion
        total +=recorrido(i).medio.costoCombinacion(recorrido(i-1).medio, recorrido(i).direccionInicio, recorrido(i).direccionFin)         
      }
    }
    
    return total
  }
}