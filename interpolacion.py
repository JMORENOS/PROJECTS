#!/usr/bin/env python
#-* coding: utf-8
 Superficie de interpolación TIN 3D con los módulos numpy, matplotlib y visvis de Python 
#Librerías
import numpy as np
from matplotlib.mlab import griddata
from mpl_toolkits.mplot3d.axes3d import *
from matplotlib import cm
import matplotlib.pyplot as plt
  
#Arreglos de puntos: ahora son 16!
x=[0,1,2,3,4,5,0,0,0,0,0,1,2,3,4,5]
y=[0,0,0,0,0,0,1,2,3,4,5,1,2,3,4,5]
z=[1,5,1,3,2,1,1,7,1,4,2,1,2,2,2,1]
  
# construcción de la grilla 2D
xi = np.linspace(min(x), max(x))
yi = np.linspace(min(y), max(y))
X, Y = np.meshgrid(xi, yi)
  
# interpolación
Z = griddata(x, y, z, xi, yi)
  
#Visualización con Matplotlib
fig = plt.figure()
ax = Axes3D(fig)
ax.plot_surface(X, Y, Z, rstride=1, cstride=1, cmap=cm.jet,linewidth=1, antialiased=True)
plt.plot
  
plt.show() #Para que la ventana de visualización permanezca estática
