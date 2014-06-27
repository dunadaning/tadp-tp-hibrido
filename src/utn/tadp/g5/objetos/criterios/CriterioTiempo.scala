package utn.tadp.g5.objetos.criterios

import utn.tadp.g5.objetos.criterios.Criterio
import utn.tadp.g5.objetos.Viaje

class CriterioTiempo extends Criterio {
  
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