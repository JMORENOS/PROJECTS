from pylab import *
2
3 def dy_forward(y, h):
4 N = len(y)
5 dy = zeros(N)
6 for k in range(N - 1):
7 dy[k] = (y[k+1] - y[k])/h
8 return dy
9
10 def dy_central(y, h):
11 N = len(y)
12 dy = zeros(N)
13 for k in range(1, N - 1):
14 dy[k] = (y[k+1] - y[k-1])/(2*h)
15 return dy
16
17 def dy_richardson(y, h):
18 N = len(y)
19 dy = zeros(N)
20 for k in range(2, N - 2):
21 dy[k] = 4.0/3*((y[k+1] - y[k-1])/(2*h)) - \
22 1.0/3*((y[k+2] - y[k-2])/(4*h))
23 return dy
24
25 ########################################
26 a,b,h = 0, 2*pi, 0.1
27 x = arange(a,b,h)
28 y = sin(x)
29 dy1 = dy_forward(y, h)
30 dy2 = dy_central(y, h)
31 dy3 = dy_richardson(y, h)
32
33 P = [(dy1, ’Dif. Forward’), (dy2, ’Dif. Central’), (dy3, ’Richardson’)]
34
35 for k,Dy in enumerate(P):
36 dy, tit = Dy
4
37 subplot(3,1,k+1)
38 scatter(x,y)
39 scatter(x,dy, color=’r’)
40 axis(’equal’)
41 grid()
42 title(tit)
43
44 show()