package utn.tadp.g5.objetos.tarjetas

import utn.tadp.g5.objetos.Direccion
import utn.tadp.g5.objetos.Tramo
import utn.tadp.g5.objetos.Viaje

class Turismo(zona: Zona) extends Tarjeta(0.10){
   
  def aplica(viaje: Viaje)={
    viaje.perteneceALaZona(zona)
  }
    
}