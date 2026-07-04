## VARIABLES Y CONSTANTES
### 1. VARIABLES
Espacio de memoria donde se almacena un valor que puede cambiar.
```java
int number = 42;
```

| [Tipo de datos] | [Name] | [Fin sentencía] |
|:---------------:|:------:|:---------------:|
|       int       |  age   |        ;        |
> [!NOTE]:
> variable sin inicializar: valor por defecto = 0

#### REGLAS PARA DEFINIR NOMBRES DE VARIABLES
1. Usar camelCase
2. Usar nombres significativos
3. Java es sensible a mayúscula
4. Evita nombres muy largo

### 2. CONSTANTES
Espacio en memoria donde se almacena un valor que **no** puede cambiar durante la ejecución.

```java
final int MAX_USERS = 42;
```

| [Modificador] | [Tipo de datos] | [Name] | [fin sentencía] |
|:-------------:|:---------------:|:------:|:---------------:|
|     final     |       int       |  MAX   |        ;        |
> [!NOTE]:
> **NO** tiene valor por defecto

#### REGLAS PARA DEFINIR NOMBRES DE VARIABLES
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

#### TIPO DE DATO: **byte**, **short**, **int**, **long**
- Números enteros `z = {..., -2, -1, 0, 1, 2, ...}`
- Difieren en las precisiones y pueden ser positivos o negativos

    ```java
    // Declaración de variables en sus rangos máximos positivos
    byte enteroByte = 127;
    short enteroShort = 32767;
    int enteroInt = 2147483647;
    long enteroLong =9223372036854775807L;
    ```
    

### 2. TIPO DE DATOS DE REFERENCIA





Compilación -> Ejecución