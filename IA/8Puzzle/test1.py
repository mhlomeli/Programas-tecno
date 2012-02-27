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
n = 3
arr = [0,1,2,3,4,5,6,7,8]
matriz = []
for i in range(n):
	matriz.append([])
	for j in range(n):
		matriz[i].append(arr[j+(i*n)])


for i in range(n):
	for j in range(n):
		print matriz[i][j]
	print '\n'
