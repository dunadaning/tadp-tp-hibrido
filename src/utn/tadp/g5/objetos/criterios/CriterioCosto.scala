package utn.tadp.g5.objetos.criterios

import utn.tadp.g5.objetos.criterios.Criterio
import utn.tadp.g5.objetos.Tramo

class CriterioCosto extends Criterio {
	 def cumpleCriterioSobre(unTramo:Tramo, otroTramo:Tramo)={
		 unTramo.costoTramo <= otroTramo.costoTramo()
	 }
}