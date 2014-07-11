package utn.tadp.g5.objetos

import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

class Recorrido {
  var ruta = List[Transporte]()
  var duracion:Double = 0
  var costo:Double = 0
  
  def getLineas() = ruta.map(transporte => transporte.getMedio().getLinea())
  
  def getTipos() = ruta.map(transporte => transporte.getMedio().getDescripcion())
  
  def getCompanias() = ruta.map(transporte => transporte.getMedio().getCompania())
  
  def getDuracion() = duracion
  
  def getCosto() = costo
  
  //def tiempoDeViaje() = 1
  
  def calcularDuracion() {
    ruta.map(transporte => duracion+=transporte.getMedio().tiempoPara(transporte.getDireccionInicio(), transporte.getDireccionFin()))    
  }

  def calcularCosto() {
    ruta.map(transporte => costo+=transporte.getMedio().costoPara(transporte.getDireccionInicio(), transporte.getDireccionFin()))    
  }
}