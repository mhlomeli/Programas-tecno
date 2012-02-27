#! /usr/bin/env python
# -*- coding: utf-8 -*-

#    Copyright 2011 Miguel Hernández Lomelí
#    This file is part of NReinas.
#
#    8Puzzle is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.
#
#    8Puzzle is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.
#
#    You should have received a copy of the GNU General Public License
#    along with 8Puzzle.  If not, see <http://www.gnu.org/licenses/>.

class Utilidades:
	""" Clase para los datos de los archivos """
	__n = None
	__EstadoInicial = None
	__EstadoFinal = None

	def __leerArchivo(self, rutaArchivo):
		archivo = None
		try:
			archivo = file(rutaArchivo)
		except:
			raise IOError('Error al leer el archivo: '+rutaArchivo)
		else:
			self.__n = archivo.readline()
			self.__EstadoInicial = archivo.readline()
			self.__EstadoFinal = archivo.readline()
			archivo.close()
	
	def __init__(self,ruta):
		self.__leerArchivo(ruta)

	def getN(self):
		"""Método que regresa un entero con el valor de N """
		try:
			return int(self.__n)
		except:
			#TODO: Mejorar esto, poner excepciones personalizadas
			raise Exception('Error obtener N')
	
	def getInicio(self):
		"""Método que regresa una lista con los valores del estado inicial"""
		try:
			arr = self.__EstadoInicial.split(',')
			return [int(i) for i in arr]
		except:
			#TODO: Mejorar esto, poner excepciones personalizadas
			raise Exception('Error obtener el estado inicial')
	
	def getFinal (self):
		"""Método que regresa una lista con los valores del estado final """
		try:
			arr = self.__EstadoFinal.split(',')
			return [int(i) for i in arr]
		except:
			#TODO: Mejorar esto, poner excepciones personalizadas
			raise Exception('Error al obtener el estado final')

class Matriz:

	edoIzq = None
	edoDer = None
	edoArr = None
	edoAba = None

	""" Clase para la manipulación de matrices """	
	def __init__(self,n):
		self.__n = n
		self.__obtenerLimites()

	def __convertir(self, arr, n):
		""" Método para convertir un array a una matriz de dimensiones NxN """
		matriz = []
		for i in xrange(n):
			 matriz.append([])
			 for j in xrange(n):
				 matriz[i].append(arr[j+(i*n)])
		
		return matriz

	#TODO:DRY
	def __avanzar(self,arreglo, i,pos):
		#Creamos una copia del arreglo
		arr = arreglo[:]
	        #for i in range(inicio,pos+inicio):
	        tmp = arr[i]
	        arr[i] = arr[i+pos]
	        arr[i+pos] = tmp
		return arr

	def __retroceder(self,arreglo, i,pos):
		arr = arreglo[:]
	        #for i in xrange(inicio,inicio-pos,-1):
	        tmp = arr[i]
	        arr[i] = arr[i-pos]
	        arr[i-pos] = tmp
	        return arr
	
	def moverAbajo(self,arr):
		#Posición del cero
		inicio = arr.index(0)

		#Verificamos si podemos self.__avanzar
		if inicio in self.edoAba:
			#Si no podemos self.__avanzar
			return None
		else:
			#Avanzamos 3 lugares a la derecha
			return self.__avanzar(arr,inicio,self.__n)
	
	def moverIzquierda(self,arr):
		#Posición del cero
		inicio = arr.index(0)

		#Verificamos si podemos self.__avanzar

		if inicio in self.edoIzq:
			#Si no podemos self.__avanzar
			return None
		else:
			#Avanzamos 1 lugar a la izquierda
			return self.__retroceder(arr,inicio,1)

	def moverDerecha(self,arr):
		#Posición del cero
		inicio = arr.index(0)

		#Verificamos si podemos self.__avanzar
		if inicio in self.edoDer:
			#Si no podemos self.__avanzar
			return None
		else:
			#Avanzamos 1 lugar a la derecha
			return self.__avanzar(arr,inicio,1)
	
	def moverArriba(self,arr):
		#Posición del cero
		inicio = arr.index(0)
		
		#Verificamos si podemos self.__avanzar
		if inicio in self.edoArr:
			#Si no podemos self.__avanzar
			return None
		else:
			#Avanzamos 3 lugares a la izquierda
			return self.__retroceder(arr,inicio,self.__n)

        def __obtenerLimites(self):
                
	        self.edoIzq = [i*self.__n for i in xrange(self.__n)]
	
		self.edoDer = [(i*self.__n)+(self.__n-1) for i in xrange(self.__n)]

		self.edoArr =  [i for i in xrange(self.__n)]

	        self.edoAba =  [i for i in xrange(self.__n*(self.__n-1),self.__n**2)]

	def obtenerEstados(self,arreglo):
		nodos = []

		nodos.append((self.moverDerecha(arreglo),'D'))
		nodos.append((self.moverArriba(arreglo),'A'))
		nodos.append((self.moverAbajo(arreglo),'AB'))
		nodos.append((self.moverIzquierda(arreglo),'I'))

		return nodos

