#
## ¿QUÉ ES BINARIO?
Es un sistema para representar cualquier número usando solo dos símbolos: `0` y `1`.


### ¿PORQUÉ SE USA?
Una computadora, a nivel físico es un monton de circuitos eléctricos. Un circuito eléctrico tiene una forma **muy confiable y barata** de estar en dos estados:
- Hay corriente (encendido) → `1`
- No hay corriente (apagado) → `0`

> El binario existe porque el hardware eléctrico solo puede distinguir de forma confiable "hay corriente" y "no hay corriente" es decir 2 estados y el **sistema binario** es la forma matemática de construir cualquier **número o dato** usando solamente esos 2 estados

## ¿QUÉ ES EL SISTEMA BINARIO?
Es un sistema de numeración **posicional** de **base 2** donde cualquier cantidad se representa combinando únicamente los dígitos `1` y `0.

> "Posicional" significa que el valor de cada dígito depende de la
> POSICIÓN donde está úbicado, no del simbolo en sí.

### ¿POR QUÉ BASE 2?
Porque solo existen 2 símbolos disponibles (`0` y `1`). Cuando una columna ya no tiene símbolo para seguir contando, se abre una columna nueva a la izquierda.

### UNIDADES BÁSICAS

- **Bit** (binary digit): un solo dígito, `0` o `1`. La unidad más
  pequeña de información que existe en una computadora.
- **Byte**: un grupo de 8 bits. Con 8 bits se pueden representar
  2⁸ = 256 combinaciones distintas (de 00000000 a 11111111).

| 2⁷=128 | 2⁶=64 | 2⁵=32 | 2⁴=16 | 2³=8 | 2²=4 | 2¹=2 | 2⁰=1 |
|--------|-------|-------|-------|------|------|------|------|

Para saber el valor de un binario: sumas SOLO las columnas
donde hay un `1`. Las columnas con `0` simplemente no aportan nada a la suma. Veamos un ejemplo:

Número: 00010110

| 2⁷=128 | 2⁶=64 | 2⁵=32 | 2⁴=16 | 2³=8 | 2²=4 | 2¹=2 | 2⁰=1 |
|:------:|:-----:|:-----:|:-----:|:----:|:----:|:----:|:----:|
|   0    |   0   |   0   |   1   |  0   |  1   |  1   |  0   | 

Esto quiere decir que existe 16, existe 4 y existe 2. Ahora toca sumar para hallar el resultado.

= 16 + 4 + 2
= 22

## BIT DE SIGNO
Si tenemos un byte podemos graficarlo con 8 columnas y podemos representar desde el 0 al 255, todos números positivos. Pero en programación necesitamos números negativos (saldos en contra, temperaturas bajo cero, restas, etc.). Entonces necesitamos una forma de decirle a la computadora: "este número es negativo".

### LA SOLUCIÓN: SACRIFICAR UNA COLUMNA
De las 8 columnas que grafican un byte, JAVA toma la columna de la izquierda del todo y **deja de usarla como cantidad**. En su lugar, la convierte en un **indicador** que solo dice una cosa:

| SIGNO | 2⁶=64 | 2⁵=32 | 2⁴=16 | 2³=8 | 2²=4 | 2¹=2 | 2⁰=1 |
|:-----:|:-----:|:-----:|:-----:|:----:|:----:|:----:|:----:|

- Si esa casilla tiene `0` → el número es **positivo**.
- Si esa casilla tiene `1` → el número es **negativo**.

> Ahora solo quedan **7 columnas** utiles. Por eso el positivo máximo de un `byte` es **127** veamos un ejemplo:

Número: 01010110

| SIGNO | 2⁶=64 | 2⁵=32 | 2⁴=16 | 2³=8 | 2²=4 | 2¹=2 | 2⁰=1 |
|:-----:|:-----:|:-----:|:-----:|:----:|:----:|:----:|:----:|
|   0   |   1   |   0   |   1   |  0   |  1   |  1   |  0   | 

Esto quiere decir que existe el 64, existe el 16, existe el 4 y existe 2. Ahora toca sumar para hallar el resultado.

= 64 + 16 + 4 + 2
= +86

Para el proceso de número negativos se necesita usar **Two's Complements** este es el método rea que usa Java para representar números negativos.

### CÓMO SE SUMA EN BINARIO

Se suma columna por columna, de derecha a izquierda, igual que sumas en
decimal (empiezas por las unidades). La diferencia es que en binario
solo existen 4 combinaciones posibles por columna:

| Operación | Resultado | Qué pasa                                    |
|:---------:|:---------:|:--------------------------------------------|
|   0 + 0   |     0     | sin acarreo                                 |
|   0 + 1   |     1     | sin acarreo                                 |
|   1 + 0   |     1     | sin acarreo                                 |
|   1 + 1   |    10     | vale 2 en binario → se escribe 0, y se      |
|           |           | "lleva 1" (acarreo) a la columna siguiente  |
|   1+1+1   |    11     | (cuando hay acarreo de la columna anterior) |
|           |           | vale 3 → se escribe 1, se lleva 1           |

> Esto es EXACTAMENTE lo mismo que haces en decimal cuando sumas
> 7 + 5 = 12: escribes el 2 y "llevas 1" a la columna de las decenas.
> En binario pasa lo mismo, pero el límite por columna no es 10, es 2.

#### Ejemplo simple: 3 + 2 en binario (0011 + 0010)

```
0011 -      (3)
0010        (2) 
----
0101        (5)  
```

### TWO'S COMPLEMENTS
Para sacar el negativo de un número, hay 2 pasos:

1. PASO 1 - INVERTIR TODOS LOS BITS:
Cada `0` se vuelve `1` cada `1`se vuelve `0`. Esto se llama **one's complement**
2. PASO 2 - SUMAR 1: Al resultado obtenido del paso 1


#### EJEMPLO: SACAR EL -86 A PARTIR DEL +86
Partimos de +86: 01010110

| Pasos                 |  Binario  |        Detalle         |
|:----------------------|:---------:|:----------------------:|
| Partimos de +86:      | 01010110  |                        |
| Paso 1: Invertir bits | 10101001  | cada 0 → 1, cada 1 → 0 |
| Paso 2: Sumar 1       | +00000001 |                        |
| Resultado:            | 10101010  |                        | 
 
> La suma de los binarios se manejan tal que si es 1 + 1 el resultado es 2 por ello la última columna de la derecha se hace 0 y la penultima columna de la derecha se hace 1

**Verificación**: si sumamos +86 y -86 en teoría debería dar 0. Vamos a comprobarlo sumando en binario

```
  01010110 +      (+86)
  10101010        (-86)
  ---------
(1)00000000   ←  da 0, y el "1" extra a la izquierda se descarta
                  (no hay más columnas donde ponerlo, un byte solo tiene 8)
```