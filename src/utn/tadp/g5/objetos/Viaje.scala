package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.tarjetas.Zona

class Viaje { 
  //CORRECCION: recorridos, duraciones y costos deberia ser una sola coleccion con objetos que tengan esos tres datos(por ej: Recorrido)
  var recorridos: ArrayBuffer[Recorrido] = null //Cada indice del Array es una alternativa de viaje
  
  //CORRECCION: Un Array o una lista es mas apropiado que un map en este caso.
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
      total += recorrido(i).getMedio.tiempoPara(recorrido(i).getDireccionInicio, recorrido(i).getDireccionFin)
      if (i>0){
        //combinacion
        total +=recorrido(i).getMedio.tiempoCombinacion(recorrido(i).getDireccionInicio, recorrido(i-1).getMedio, recorrido(i).getDireccionFin)         
      }
      
    }
      
    return total
  }
  
   def getCosto(recorrido:HashMap[Int,Transporte]):Double = {
    var total:Double = 0
    
    for (i <- 0 until recorrido.size){
      total += recorrido(i).getMedio.costoPara(recorrido(i).getDireccionInicio, recorrido(i).getDireccionFin) 
      if (i>0){
        //combinacion
        total +=recorrido(i).getMedio.costoCombinacion(recorrido(i-1).getMedio, recorrido(i).getDireccionInicio, recorrido(i).getDireccionFin)         
      }
    }
    
    return total
  }
}