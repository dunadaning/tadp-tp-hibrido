package utn.tadp.g5.objetos

import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class Recorrido {
  //var medio:Medio = null// = new Medio()
  //var direccion:Direccion = null// = new Direccion()
  var mapa:HashMap[Int,Transporte] = new HashMap
  //val tramos = new ArrayBuffer[Tramo]
 
  def getLineas() = mapa.mapValues(transporte => transporte.medio.getLinea())
  
  def getTipos() = mapa.mapValues(transporte => transporte.medio.getDescripcion())
  
  def getCompanias() = mapa.mapValues(transporte => transporte.medio.getCompania())
  
  def calcular():Int = {
    return 1
  }
  
  /*def this(transporteSalida:Transporte, transporteLlegada :Transporte)={
   this()
   this.transportes += transporteSalida
   this.transportes += transporteLlegada
  }
  
  def this(transporteSalida:Transporte, transporteLlegada :Transporte, direccion:Direccion)={
   this()
   this.transportes += transporteSalida
   this.transportes += new Transporte(transporteSalida.medio , direccion)
   this.transportes += new Transporte(transporteLlegada.medio, direccion)
   this.transportes += transporteLlegada
  }*/
  
  /*def costo() = {
    for()
  }*/
  def tiempoDeViaje() = 1
 
}