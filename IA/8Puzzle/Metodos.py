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

from Arbol import Arbol, Nodo
from Util import Matriz
from sys import stdout
from time import sleep
from collections import deque
from random import randrange

class BusquedaAncha:

	__n = 0
	__edoIni = None
	__edoFnl = None

	def __init__(self,n, edoIni, edoFnl):
		self.__n = n
		self.__edoFnl = edoFnl
		self.__edoIni = edoIni


	def buscar(self):
		arbol = Arbol(self.__n)
		arbol.estadoFinal = self.__edoFnl
		arbol.raiz = Nodo(self.__edoIni, None, None)

		arbol.estadosPendientes.add(arbol.raiz)

		encontrado = False
		nodoFinal = None
		while not encontrado:
			
			nodosTmp = []

			while len(arbol.estadosPendientes) != 0:
				nodo = arbol.estadosPendientes.pop()
				if nodo.estado == arbol.estadoFinal:
					encontrado = True
					nodoFinal = nodo
					break
				else:
					nodosTmp.append(nodo)
			for nodoT in nodosTmp:
				arbol.estadosPendientes.update(arbol.expandirNodos(nodoT))
		msg = []
		msg.append('Estado final encontrado a una profundidad de: ')
		msg.append(str(arbol.obtenerProfundidad(nodoFinal)))
		msg.append('\n\nSe utilizaron %i nodos' % len(arbol.estadosRevisados))
		msg.append('\n\nSe utilizaron las siguientes acciones:\n')
		msg.append(str(arbol.obtenerAcciones()))
		return ''.join(msg)


class BusquedaProfunda:

	__n = 0
	__edoIni = None
	__edoFnl = None
	__matriz = None
	nodoFinal = None
	__arbol = None
	def __init__(self,n, edoIni, edoFnl):
		self.__n = n
		self.__edoFnl = edoFnl
		self.__edoIni = edoIni
		self.__matriz = Matriz(n)
		self.__arbol = Arbol(self.__n)

	def buscar(self):
		self.__arbol.estadoFinal = self.__edoFnl
		self.__arbol.raiz = Nodo(self.__edoIni, None, None)

		if self.busquedaProfunda(self.__arbol.raiz,0):
			msg = []
			msg.append('Estado final encontrado a una profundidad de: ')
			msg.append(str(self.__arbol.obtenerProfundidad(self.nodoFinal)))
			msg.append('\n\nSe utilizaron %i nodos' % len(self.__arbol.estadosRevisados))
			msg.append('\n\n%s'%str(self.nodoFinal.estado))
			msg.append('\n\nSe utilizaron las siguientes acciones: ')
			msg.append(str(self.__arbol.obtenerAcciones()))
			return ''.join(msg)
		else:
			return 'Lo siento no he encontrado nada a esa profundidad'
	def busquedaProfunda(self,nodo, deep):

		if deep ==350 or nodo.estado is None:
			return False
		if nodo.estado == self.__edoFnl:
			self.nodoFinal = nodo
			return True
		if tuple(nodo.estado) in self.__arbol.estadosRevisados:
			return False

		else:
			self.__arbol.estadosRevisados.add(tuple(nodo.estado))
			if self.busquedaProfunda(Nodo(self.__matriz.moverIzquierda(nodo.estado),nodo,'I'),deep+1):
				return True
			elif self.busquedaProfunda(Nodo(self.__matriz.moverDerecha(nodo.estado),nodo,'D'),deep+1):
				return True
			elif self.busquedaProfunda(Nodo(self.__matriz.moverArriba(nodo.estado),nodo,'A'),deep+1):
				return True
			elif self.busquedaProfunda(Nodo(self.__matriz.moverAbajo(nodo.estado),nodo,'AB'),deep+1):
				return True
			else:
				return False
class AEstrella:

	__n = 0
	__edoIni = None
	__edoFnl = None
	__matriz = None
	__arbol = None
	__dis = 0
	f={}
	
	def __init__(self,n, edoIni, edoFnl):
		self.__n = n
		self.__edoFnl = edoFnl
		self.__edoIni = edoIni
		self.__matriz = Matriz(n)
		self.__arbol = Arbol(self.__n)
		self.initFun()

	def manhattan(self, edo):
		meta = self.__edoFnl
		n = self.__n
		dist = 0
		for e in edo:
			i = edo.index(e) #Posicion del numero en el estado actual
			j = meta.index(e) #Posicion del numero en el estado meta
			edoTmp = edo[:]
			while (i-j) > n or (j-i) > n:
				if i < j: #Esta arriba, lo movemos abajo
					edoTmp[i],edoTmp[i+n] = edoTmp[i+n],edoTmp[i]
				elif i > j: #Esta abajo
					edoTmp[i],edoTmp[i-n] = edoTmp[i-n],edoTmp[i]
				dist += 1
				i = edoTmp.index(e) 
			while i != j:
				if i > j: #Esta a la derecha
					edoTmp[i],edoTmp[i-1] = edoTmp[i-1],edoTmp[i]
				elif i < j: #Esta a la izquierda
					edoTmp[i],edoTmp[i+1] = edoTmp[i+1],edoTmp[i]
				dist += 1
				i = edoTmp.index(e) 
		return dist
	
	def initFun(self):
		self.f['A'] = self.__matriz.moverArriba
		self.f['AB'] = self.__matriz.moverAbajo
		self.f['I'] = self.__matriz.moverIzquierda
		self.f['D'] = self.__matriz.moverDerecha
	
	def getValues(self, nodos):
		lista=[]
		for nodo in nodos:
			lista.append([self.manhattan(nodo.estado),nodo])
		return lista

	def buscar(self):
		nodosAbiertos = []
		nodo = Nodo(self.__edoIni,None,None)
		
		while nodo.estado != self.__edoFnl or len(self.__arbol.estadosPendientes) != 0:
			nodosAbiertos.extend(self.getValues(self.__arbol.expandirNodos(nodo)))
			nodosAbiertos.sort()
			nodo = nodosAbiertos.pop(0)[1]
			#self.__dis += 1
	
		
		profundidad = (str(self.__arbol.obtenerProfundidad(nodo)))
		
		msg = []
		msg.append('Maximo local encontrado a una profundidad de: ')
		msg.append(profundidad)
		msg.append('\n\nCon un costo de %s ' % profundidad)
		msg.append('\n\n%s'%str(nodo.estado))
		msg.append('\n\nCon las siguientes acciones: ')
		msg.append(str(self.__arbol.obtenerAcciones()))

		return ''.join(msg)
