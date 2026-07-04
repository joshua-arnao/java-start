# VARIABLES Y CONSTANTES

## 1. VARIABLES

Espacio de memoria donde se almacena un valor que puede cambiar.

```java
int number = 42;
```

| [Tipo de datos] | [Name] | [Fin sentencía] |
|:---------------:|:------:|:---------------:|
|       int       |  age   |        ;        |

> [!NOTE]:
> variable sin inicializar: valor por defecto = 0

### REGLAS PARA DEFINIR NOMBRES DE VARIABLES

1. Usar camelCase
2. Usar nombres significativos
3. Java es sensible a mayúscula
4. Evita nombres muy largo

## 2. CONSTANTES

Espacio en memoria donde se almacena un valor que **no** puede cambiar durante la ejecución.

```java
final int MAX_USERS = 42;
```

| [Modificador] | [Tipo de datos] | [Name] | [fin sentencía] |
|:-------------:|:---------------:|:------:|:---------------:|
|     final     |       int       |  MAX   |        ;        |

> [!NOTE]:
> **NO** tiene valor por defecto

### REGLAS PARA DEFINIR NOMBRES DE VARIABLES

1. Usar UPPER_CASE
2. Usar nombres significativos
3. Java es sensible a mayúscula
4. Evita nombres muy largo

---

## TIPO DE VARIABLES EN JAVA

Existen dos categorías de datos principales **Primitivas** y **Referencia**

### 1. TIPO DE DATOS PRIMITIVOS

Son tipos de datos que contienen un solo valor.

| Tipos de dato | Tamaño  | Valor por defecto | Uso común                               |
|:--------------|:--------|:------------------|:----------------------------------------|
| **byte**      | 1 byte  | 0                 | Números pequeños (ahorro de memoria).   |
| **short**     | 2 bytes | 0                 | Números pequeños (más grande que byte). |
| **int**       | 4 bytes | 0                 | Números enteros generales.              |
| **long**      | 8 bytes | 0L                | Números enteros muy grandes.            |
| **float**     | 4 bytes | 0.0f              | Números decimales con precisión simple. |
| **double**    | 8 bytes | 0.0d              | Números decimales con precisión doble.  |
| **char**      | 2 bytes | \u0000            | Almacenar caracteres individuales.      |
| **boolean**   | 1 bit   | false             | Valores lógicos (verdadero/falso).      |

#### TIPO DE DATO: **boolean**

- Un solo bit.
- Expresa un valor VERDADERO o FALSO

    ```java
    boolean isLoading = false;
    boolean isActive = true;
    ```

#### TIPO DE DATO: **char**

- Usa el código UNICODE y ocupa cada carácter 16 bits

    ```java
    char aCharacter = 'a';
    char oneCharacter = '1';
    char unicode = '\u0021';
    ```

#### TIPO DE DATOS: **byte**, **short**, **int**, **long** - PRIMITIVOS ENTEROS

- Números enteros `z = {..., -2, -1, 0, 1, 2, ...}`
- Difieren en las precisiones y pueden ser positivos o negativos
- Todos siguen la MISMA lógica: N bits, 1 bit para signo, N-1 bits para magnitud.

| Tipo    | Bits | Fórmula rango | Rango                            | Uso típico                                                                |
|:--------|:----:|:-------------:|:---------------------------------|---------------------------------------------------------------------------|
| `byte`  |  8   |  -2⁷ a 2⁷-1   | -128 a 127                       | Arrays masivos, streams de datos, ahorro de memoria a escala              |
| `short` |  16  | -2¹⁵ a 2¹⁵-1  | -32,768 a 32,767                 | Poco usado hoy; legado de sistemas con memoria muy limitada               |
| `int`   |  32  | -2³¹ a 2³¹-1  | -2,147,483,648 a 2,147,483,647   | Default para enteros: IDs, contadores, loops                              |
| `long`  |  64  | -2⁶³ a 2⁶³-1  | ±9,223,372,036,854,775,807 aprox | Timestamps (epoch millis), IDs grandes, cálculos que pueden desbordar int |


```java
// Declaración de variables en sus rangos máximos positivos
byte enteroByte = 127;
short enteroShort = 32767;
int enteroInt = 2147483647;
long enteroLong =9223372036854775807L;
```

> Regla práctica: en Java, si escribes un número entero sin sufijo,
> el compilador lo trata como `int` por defecto. Para forzar `long`,
> se agrega el sufijo `L`: `long id = 5000000000L;`

##### ¿QUÉ RESUELVE CADA UNO? (el "por qué" de su existencia)

- **byte**: resuelve el problema de memoria a GRAN escala. Si tienes un array de millones de elementos donde sabes que el valor nunca pasará de 127 (ej. un valor de un sensor, un byte de una imagen, un flag), usar `byte[]` en vez de `int[]` puede ahorrar 3 de cada 4 bytes de memoria. También es la unidad base de E/S (leer un archivo, una conexión de red se lee en bytes).

- **short**: nació en una era donde la memoria era muchísimo más cara y limitada que hoy. En la práctica moderna casi no se usa, `int` es el default y el JIT/CPU moderna trabaja igual o mejor con `int` que con `short` en la mayoría de los casos.

- **int**: es el entero "de uso general". Resuelve el 90% de los casos cotidianos: contar, indexar arrays, IDs pequeños/medianos.

- **long**: resuelve el problema de OVERFLOW cuando el número puede exceder los ~2,147 millones que soporta un `int`. El caso más común en la vida real: timestamps en milisegundos desde 1970 (epoch time) ya superaron el límite de `int` hace años, por eso `System.currentTimeMillis()` devuelve `long`, no `int`.


##### EL OVERFLOW: qué pasa si te excedes del rango

Java NO lanza una excepción si te pasas del rango de un tipo primitivo. Simplemente "da la vuelta" silenciosamente (wraps around):

```java
int maximo = Integer.MAX_VALUE;   // 2,147,483,647
int overflow = maximo + 1;        // resultado: -2,147,483,648 (!!)
```

Esto pasa porque, en binario, sumar 1 al patrón `01111111...1` (el más
grande positivo) produce `10000000...0` — y ese patrón, según el bit
de signo, es el número NEGATIVO más grande. Por eso "se da la vuelta" al otro extremo del rango.

> ⚠️ Peligro real en banca: sumar montos o IDs sin validar el rango puede producir un overflow silencioso, sin error, sin log, sin excepción. El sistema simplemente sigue corriendo con un número incorrecto. Por eso en cálculos financieros casi nunca se usa `int`/`long` puro, se usa `BigDecimal`.

### PROMOCIÓN AUTOMÁTICA DE TIPOS (Widening)

Cuando mezclas tipos primitivos de distinto tamaño en una operación o
asignación, Java **agranda automáticamente** el tipo más chico al tamaño
del más grande, ANTES de operar. Esto pasa solo, sin pedirlo, y sin
ningún cast explícito.

> Ejemplo real que lo demuestra:
```java
long maximoLong = Integer.MAX_VALUE;  // 2,147,483,647 (int, 32 bits)
System.out.println(maximoLong + 1);   // resultado: 2147483648
```
> Aunque `Integer.MAX_VALUE` es el número más grande que un `int` puede soportar, al guardarlo en una variable `long` Java lo expande a 64 bits (rellenando con ceros a la izquierda, porque es positivo). Por eso sumarle 1 YA NO desborda — el `long` tiene muchísimas columnas de bits libres a la izquierda que el `int` no tenía.

#### Jerarquía de promoción (de menor a mayor)

`byte → short → int → long → float → double`

- Un tipo siempre se puede promover automáticamente hacia la derecha
  (ej. byte → int) sin perder información.
- Ir de un tipo mayor a uno menor (ej. long → int) NO es automático:
  requiere un cast explícito `(int) miLong`, y ahí SÍ puedes perder
  información (si el valor no cabe en el tipo destino).

> ⚠️ Regla práctica: la promoción automática solo va "hacia arriba" en tamaño (widening). Ir "hacia abajo" (narrowing) siempre exige un cast manual — es la forma en que Java te obliga a reconocer, explícitamente, que estás asumiendo el riesgo de perder datos.


#### ¿Qué resuelve este mecanismo?

Resuelve la necesidad de operar cómodamente entre tipos distintos sin
tener que castear manualmente en cada operación matemática básica
(ej. sumar un `int` con un `long` sin tener que escribir casts en
todas partes). El "costo" de esta comodidad es que hay que tener
presente en qué tipo vive realmente cada variable, como acabamos de
comprobar con el propio `Integer.MAX_VALUE` guardado en un `long`.


### 2. TIPO DE DATOS DE REFERENCIA

Compilación -> Ejecución