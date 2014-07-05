package utn.tadp.g5.objetos.criterios

//import utn.tadp.g5.objetos.criterios.Criterio
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Recorrido
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap

class CriterioTiempo extends Criterio {
  
  def cumpleCriterioSobre(unTramo:Recorrido, otroTramo:Recorrido): Boolean = {
    //unTramo.tiempoTramo() <= otroTramo.tiempoTramo()
    true
  }
  
  def consultar(viaje:Viaje):Viaje = {
    var viajeRespuesta = new Viaje()
    var min:Double = 0
    var idx:Int = 0
    
    if (viaje.duraciones.size>0){
      min = viaje.duraciones(0)
      idx = 0
    }
    for (i <- 1 until viaje.duraciones.size){
      //TODO buscar minina duracion, y devolver un Viaje con la alternativa que correspode 
      if (viaje.duraciones(i)<min || min == 0){
        min = viaje.duraciones(i)
        idx = i
      }
    }
    
    viajeRespuesta.recorridos = ArrayBuffer[Recorrido]()
    viajeRespuesta.recorridos += viaje.recorridos(idx)
    viajeRespuesta.duraciones = new HashMap[Int,Double]
    viajeRespuesta.duraciones += (0 -> viaje.duraciones(idx))
    viajeRespuesta.costos = new HashMap[Int,Double]
    viajeRespuesta.costos += (0 -> viaje.costos(idx))
    viajeRespuesta
  }
}