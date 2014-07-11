package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Viaje
import utn.tadp.g5.objetos.Recorrido

class Turismo(zona: Zona) extends Tarjeta(0.10){
   
  def aplica(recorrido: Recorrido)={
    recorrido.perteneceALaZona(zona)
  }
}