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
  
  def calcularDuracion() {
    ruta.map(transporte => duracion+=transporte.getMedio().tiempoPara(transporte.getDireccionInicio(), transporte.getDireccionFin()))    
  }

  def calcularCosto() {
    ruta.map(transporte => duracion+=transporte.getMedio().costoPara(transporte.getDireccionInicio(), transporte.getDireccionFin()))    
  }
}