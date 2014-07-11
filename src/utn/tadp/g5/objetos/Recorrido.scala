package utn.tadp.g5.objetos

import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import utn.tadp.g5.objetos.tarjetas.Tarjeta
import utn.tadp.g5.objetos.tarjetas.Zona

class Recorrido {
  var ruta = List[Transporte]()
  var duracion:Double = 0
  var costo:Double = 0
  var descuento:Double = 0
  
  def getLineas() = ruta.map(transporte => transporte.getMedio().getLinea())
  
  def getTipos() = ruta.map(transporte => transporte.getMedio().getDescripcion())
  
  def getCompanias() = ruta.map(transporte => transporte.getMedio().getCompania())
  
  def getDuracion() = duracion
  
  def getCosto() = costo
   
  def calcularDuracion() {
    ruta.map(transporte => duracion+=transporte.getMedio().tiempoPara(transporte.getDireccionInicio(), transporte.getDireccionFin()))    
  }

  def calcularCosto(tarjeta:Tarjeta) {
    ruta.map(transporte => costo+=transporte.getMedio().costoPara(transporte.getDireccionInicio(), transporte.getDireccionFin()))
    if (tarjeta!=null) aplicarDescuento(tarjeta)
  }
  
  def aplicarDescuento(tarjeta:Tarjeta){
    descuento = tarjeta.aplicarDescuentoAlRecorrido(this)
    costo -= (descuento)
  }
  
  def perteneceALaZona(zona:Zona)={
    ruta.exists(transporte => transporte.perteneceALaZona(zona))
  }
    
  def saleDesdeLaZona(zona:Zona)={
    ruta.exists(transporte => transporte.saleDesdeLaZona(zona))
  }
  
  def llegaALaZona(zona:Zona)={
    ruta.exists(transporte => transporte.llegaALaZona(zona))
  }
  
  def getCostoTotal() = costo + descuento
  
  def getPorcentajeDescuento() = (descuento * 100) / getCostoTotal()
  
}