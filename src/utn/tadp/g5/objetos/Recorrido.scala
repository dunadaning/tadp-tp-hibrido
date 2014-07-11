package utn.tadp.g5.objetos

import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer
import utn.tadp.g5.objetos.tarjetas.Tarjeta
import utn.tadp.g5.objetos.tarjetas.Zona

class Recorrido {
  var ruta = List[Transporte]()
  var costo:Double = 0
  var descuento:Double = 0
  var duracion:Double = 0
  
  def getLineas() = ruta.map(transporte => transporte.getMedio().getLinea())
  
  def getTipos() = ruta.map(transporte => transporte.getMedio().getDescripcion())
  
  def getCompanias() = ruta.map(transporte => transporte.getMedio().getCompania())
  
  def getDuracion() = calcularDuracion()
  
  def getCosto(tarjeta:Tarjeta) = calcularCosto(tarjeta)//costo
  
  //CORRECCION: no esta bueno hacerlo con map. Para hacer una sumatoria usen fold. 
  // Ademas se va a aumentar la duración cada vez que se llame a calcularDuracion.
  // Deberian calcular la duracion en el metodo getDuracion sin efecto colateral(sin cambiar la variable duracion).
  def calcularDuracion() = {
    ruta.foldLeft(0.0)((acum , transporte) => acum + this.tiempoPara(transporte)) 
  }
  
  def tiempoPara(transporte:Transporte)={
    transporte.getMedio().tiempoPara(transporte.getDireccionInicio(), transporte.getDireccionFin())
  }

  //CORRECCION: idem calcularDuracion
  def calcularCosto(tarjeta:Tarjeta) = {
    ruta.foldLeft(0.0)((acum,transporte) => acum + this.costoPara(transporte))- this.aplicarDescuento(tarjeta)
  }
  
  def costoPara(transporte:Transporte)={
    transporte.getMedio().costoPara(transporte.getDireccionInicio(), transporte.getDireccionFin())
  }
  
  //CORRECCION: de nuevo el efecto colateral. Deberian aplicar el descuento cuando se pide el costo total.
  def aplicarDescuento(tarjeta:Tarjeta)={
    if (tarjeta!=null){
      tarjeta.aplicarDescuentoAlRecorrido(this)
    }else{
      0
    }
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