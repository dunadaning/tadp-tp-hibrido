package utn.tadp.g5.objetos.criterios

//import utn.tadp.g5.objetos.criterios.Criterio
import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Recorrido

class CriterioCosto extends Criterio {
	 
  //def cumpleCriterioSobre(unTramo:Tramo, otroTramo:Tramo)={
  def cumpleCriterioSobre(unTramo:Recorrido, otroTramo:Recorrido):Boolean ={  
		 //unTramo.costoTramo <= otroTramo.costoTramo()
    true
	 }
}