package utn.tadp.g5.objetos.criterios

import utn.tadp.g5.objetos.Recorrido

trait Criterio{
  //T-tiempo
  //C-costo
  //var codigo: Char = cod
  
  def seleccionar(unRecorrido:Recorrido, otroRecorrido: Recorrido): Recorrido= {
    if(unRecorrido == null){
	    return otroRecorrido
	  }
	 
	  if(this.cumpleCriterioSobre(unRecorrido, otroRecorrido)){
	    return unRecorrido
	  }else{
	    return otroRecorrido
	  }
  }
  
  def cumpleCriterioSobre(unRecorrido:Recorrido, otroReccorido: Recorrido):Boolean
}