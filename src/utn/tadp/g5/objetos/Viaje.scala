package utn.tadp.g5.objetos

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import utn.tadp.g5.objetos.tarjetas.Zona
import scala.collection.mutable.MutableList

class Viaje { 
  //CORRECCION: recorridos, duraciones y costos deberia ser una sola coleccion con objetos que tengan esos tres datos(por ej: Recorrido)
  //SE UTILIZA UNA LISTA
  var recorridos: List[Recorrido] = null //Cada indice del Array es una alternativa de viaje
  
  //CORRECCION: Un Array o una lista es mas apropiado que un map en este caso.
  //var duraciones = new MutableList[Double]//Cada clave es la alternativa
  //var costos = new HashMap[Int,Double]//Cada clave es la alternativa
  //SE PASO A RECORRIDO
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
    recorridos.map(recorrido => recorrido.calcularDuracion())
  }
  

  def calcularCostos(){
    recorridos.map(recorrido => recorrido.calcularCosto())
  }
  
/*  
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
  }*/
}