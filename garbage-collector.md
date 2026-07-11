
## ¿QUÉ GC USA JAVA 21 POR DEFECTO?
G1 (Garbage-First), desde Java 9 en adelante, cuando hay al menos **2 CPUs** y **2GB** de heap. 

## LAS REGIONES
G1 es un recolector regionalizado y generacional, el heap de objetos Java se divide en un número de regiones de igual tamaño, y al arrancar la JVM decide ese tamaño de región. El tamaño de región puede variar entre 1 MB y 32 MB dependiendo del tamaño del heap, con el objetivo de no tener más de 2048 regiones.

## ¿QUÉ RESUELVE ESTE DISEÑO POR REGIONES?
Antes de G1, los recolectores dividían el heap en **3 espacios** contiguos grandes (*Eden*, *Survivor*, *Old*) de tamaño fijo. El problema: con heaps muy grandes, **compactar/limpiar** un espacio contiguo enorme toma mucho tiempo y genera **pausas largas**. G1 resuelve esto partiendo el heap en muchas regiones pequeñas — puede seleccionar un pequeño grupo de regiones para recolectar y terminar rápido, en vez de tener que procesar todo un espacio gigante de una vez. Por eso el nombre **"Garbage-First"**: prioriza recolectar primero las regiones que tienen más basura y menos objetos vivos, maximizando el espacio liberado con el menor esfuerzo.

## LAS GENERACIONES YOUNG Y OLD
- **Eden**: donde nace TODO objeto nuevo. Un objeto siempre se asigna primero a la generación joven, es decir, a regiones eden, con la excepción de objetos gigantes ("humongous") que van directo a la generación vieja. 
- **Survivor**: cuando un objeto sobrevive una recolección de la generación joven, se mueve aquí (no se borra, se "evacúa" — se copia a otra región). 
- **Old (vieja/tenured)**: objetos que ya sobrevivieron varias rondas se "promueven" aquí, porque estadísticamente van a seguir vivos por mucho tiempo.

## Tipos de recolección, de más barata a más cara

| Tipo             | Qué recolecta                               | Costo                                                           |
|:-----------------|:--------------------------------------------|:----------------------------------------------------------------|
| Young GC (Minor) | Solo regiones jóvenes (eden + survivor)     | Bajo/rápido                                                     |
| Mixed GC         | Jóvenes + un subconjunto de regiones viejas | Medio                                                           |
| Full GC          | TODO el heap, con compactación total        | Alto/lento — es el respaldo cuando el modo normal ya no alcanza |