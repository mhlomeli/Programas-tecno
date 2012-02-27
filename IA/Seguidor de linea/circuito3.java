/*    Copyright 2011 Miguel Hernández Lomelí
*    This file is part of SeguidorLinea.
*
*    SeguidorLinea is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    SeguidorLinea is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with SeguidorLinea.  If not, see <http://www.gnu.org/licenses/>
*/
//Importamos las clases necesarias para el manejo del robot
import josx.platform.rcx.*;

//Implementamos la interfaz SensorConstants
public class circuito3 implements SensorConstants {

//Inicio del programa, el programa puede lanzar excepciones InterruptedException
public static void main(String[] args) throws InterruptedException {
        // Configurar sensor de luz
        Sensor.S1.setTypeAndMode(SENSOR_TYPE_LIGHT, SENSOR_MODE_PCT);
        Sensor.S3.setTypeAndMode(SENSOR_TYPE_LIGHT, SENSOR_MODE_PCT);

	//Activamos los sensores 1 y 3        
	Sensor.S1.activate();
        Sensor.S3.activate();
	
	//Indicamos la potencia de los motores A y C
        Motor.A.setPower(5);
        Motor.C.setPower(5);

	//Variables donde guardaremos los valores obtenidos de los sensores
	int valor1,valor2;

	//Inicia el ciclo infinito
        while (true){

		//Leemos los sensores	
                valor1 = Sensor.S1.readValue();
                valor2 = Sensor.S3.readValue();
		
		//Si los sensores nos dan un resultado igual, con una toleracnia de 5, entonces
		if (Math.abs(valor1 - valor2) < 5)
		{
			//Avanzamos
                       	Motor.A.forward();
                       	Motor.C.forward();
		} 
	
		//Si el sensor 1, nos da un valor menor que el del sensor 2, entonces
                else if (valor1 < valor2) {
			//Detenemos los motores
                        Motor.A.stop();
                        Motor.C.stop();
			//Giramos a la izquierda
	      	  	Motor.A.backward();
	      	  	Motor.C.forward();

                //Si el sensor 2, nos da un valor menor que el del sensor 1, entonces
		}else if (valor2 < valor1) {
			//Detenemos los motores
                        Motor.A.stop();
                        Motor.C.stop();
			//Giramos a la derecha
        		Motor.C.backward();
        		Motor.A.forward();

                }
        }
}
}
