matrizA = [] #   =[[2, 3,5,-1]] --matriz definida
matrizB = [] #   =[[2,-1,2,-1,2,-1]] --matriz definida
matrizC = [] # matriz resultante
raiz = []  # _origen
Multipl=[]
A=[]
B=[]


print ("-----------Convolucion Periodica------\n")

#El usuario ingresa desde teclado el numero de filas
#filasA = int(input("Cantidad de filas de la matriz A:"))
#El usuario ingresa el numero de  columnas
elementosA = int(input("Numero de elementos de la primera Muestra:"))


print ("------------Elementos de la Muestra 1------\n")
for i in range(elementosA):   #creamos  la matriz A
       numero=int(input())
       matrizA.append(numero)  #appen agrega elementos a la matriz A


#for f in range(elementosA):  #recorremos filas requeridas por el usuario
        #matrizA[f]=int(input("Elemento %d:"%(f)))
#El usuario ingresa desde teclado el origen de la Secuencia Finita
#A =int(input("Ingresa el Origen de la Secuencia:"))
#A1 =int(input("Ingresa la posicion de Origen de la Secuencia en la muestra:"))
#raiz.append(A)

print  ('Secuencia de la primera Muestra: \n')
print (matrizA)
#matrizA[i]=int(input("Elemento %d:"%(i)))
#print ("Origen de la Secuencia:")
#print (A)
#A1= input("\n")

#El usuario ingresa desde teclado el numero de filas
#filasB = int(input("Cantidad de filas de la matriz B:"))
#El usuario ingresa el numero de  columnas
elementosB = int(input("Numero de elementos de la segunda Muestra:"))
print ("------------Elementos de la Muestra 2------\n")
for i in range(elementosB):   #creamos  la matriz B
       numero=int(input())
       matrizB.append(numero)  #appen agrega elementos a la matriz A

#for f in range(elementosB):  #recorremos filas requeridas por el usuario
        #matrizA[f]=int(input("Elemento %d:"%(f)))
#El usuario ingresa desde teclado el origen de la Secuencia Finita
#B =int(input("Ingresa el Origen de la Secuencia:"))
#raiz.append(B)
print  ('Secuencia de la Segunda Muestra')
print (matrizB)
#print  ('Origen de la Segunda Muestra: ')
#print(B)

filasC=len(matrizB)
columnasC=len(matrizA)+(filasC-1)


#multiplicacion de matricesJ
# i in range(filasA):
#	for j in range(ColumnasA):
#		for k in range(ColumnasB):
#			matrizC[i][k]=matrizC[i][k]+(matrizA[i][j]*matrizB[j][k])


#multiplicacion de matrices
for i in matrizB:
    for j in matrizA:
        Multipl.append(i*j)
    for x in range(0,len(matrizB)):
    	Multipl.append(0)

#adicion de las Columnas
while (Multipl):
   matrizC.append (Multipl [:columnasC])
   Multipl = Multipl[columnasC:]  #desde elemento columnasC  hasta el ultimo
#print (matrizC)  #matriz resultante ...

aux=[]
for i in range(0,columnasC):
	res=0
	for j in range(0,filasC):
		  res=res+matrizC[j][i]
	aux.append(res);
print ("Secuencia de Muestras Resultantes:")
print (aux)
############-------------------------########
#print ("Origen de la secuencia Final: ")

lF1=aux[0::2]  #empieza desde la posicion0 hasta la posicion final : en uno en 1
lF2=aux[1::2]
lF3=[]

#max(lF)

#print(lF1)
#print ("origen de la secuencia:")
###obtenemos la suma del primera sublista
def sumarLista(lF1):
    sum=0
    for i in range(0,len(lF1)):
        sum=sum+lF1[i]
    return sum

def imprimirLista(lF1,nombre):
    for i in range(0,len(lF1)):
        print nombre + "[" + str(i) + "]=" + str(lF1[i])
A=lF1
#imprimirLista(A,"A")
#print ( "Suma = " + str(sumarLista(A)))

###obtenemos la suma del segunda sublista
def sumarLista1(lF2):
    sum=0
    for i in range(0,len(lF2)):
        sum=sum+lF2[i]
    return sum

def imprimirLista1(lF2,nombre):
    for i in range(0,len(lF2)):
        print nombre + "[" + str(i) + "]=" + str(lF2[i])
A1=lF2   ##suma de la sublista de la subsecuencia
#print ( "Suma = " + str(sumarLista(A1)))

if A<=A1:
    print('El origen de la muestra Final es:'+ str(sumarLista(A1)))
else:
    print('El origen de la muestra Final es:'+ str(sumarLista(A)))