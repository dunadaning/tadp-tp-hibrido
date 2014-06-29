package utn.tadp.g5.objetos.criterios

import utn.tadp.g5.objetos.criterios.Criterio
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Tramo

class CriterioTiempo extends Criterio {
  
  def cumpleCriterioSobre(unTramo:Tramo, otroTramo:Tramo)={
    unTramo.tiempoTramo() <= otroTramo.tiempoTramo()
  }
  
  def consultar(viaje:Viaje):Viaje = {
    def viajeRespuesta = new Viaje()
    def min:Int = 0
    def ult:Int = 0
    
    for (i <- 0 until viaje.duraciones.size){
      //TODO buscar minina duracion, y devolver un Viaje con la alternativa que correspode        
    }
    
    viajeRespuesta
  }
}