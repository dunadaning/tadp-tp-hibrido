package utn.tadp.g5.objetos.criterios

//import utn.tadp.g5.objetos.criterios.Criterio
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Recorrido
import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.HashMap
import scala.collection.mutable.MutableList

class CriterioTiempo extends Criterio {
  
  def cumpleCriterioSobre(unTramo:Recorrido, otroTramo:Recorrido): Boolean = {
    //unTramo.tiempoTramo() <= otroTramo.tiempoTramo()
    true
  }
  
  def consultar(viaje:Viaje):Viaje = {
    var viajeRespuesta = new Viaje()
    
    viajeRespuesta.recorridos = List[Recorrido](viaje.getRecorridoDuracionMinima())
    viajeRespuesta
  }
}