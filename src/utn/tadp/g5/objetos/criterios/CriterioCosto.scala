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
    
    viajeRespuesta.recorridos = List[Recorrido](viaje.getRecorridoCostoMinimo())
    viajeRespuesta
  }  
}