package utn.tadp.g5.objetos.criterios

//import utn.tadp.g5.objetos.criterios.Criterio
import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Recorrido
import utn.tadp.g5.objetos.Viaje
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.MutableList

class CriterioCosto extends Criterio {
	 
  //def cumpleCriterioSobre(unTramo:Tramo, otroTramo:Tramo)={
  def cumpleCriterioSobre(unTramo:Recorrido, otroTramo:Recorrido):Boolean ={  
		 //unTramo.costoTramo <= otroTramo.costoTramo()
    true
	 }
 
  def consultar(viaje:Viaje):Viaje = {
    var viajeRespuesta = new Viaje()
    var min:Double = 0
    var idx:Int = 0
    
    if (viaje.costos.size>0){
      min = viaje.costos(0)
      idx = 0
    }
    for (i <- 1 until viaje.duraciones.size){
      //TODO buscar minina duracion, y devolver un Viaje con la alternativa que correspode 
      if (viaje.costos(i)<min || min == 0){
        min = viaje.costos(i)
        idx = i
      }
    }
    
    viajeRespuesta.recorridos = ArrayBuffer[Recorrido]()
    viajeRespuesta.recorridos += viaje.recorridos(idx)
    viajeRespuesta.duraciones = new MutableList[Double]
    viajeRespuesta.duraciones += viaje.duraciones(idx)
    viajeRespuesta.costos = new HashMap[Int,Double]
    viajeRespuesta.costos += (0 -> viaje.costos(idx))
    viajeRespuesta
  }  
}