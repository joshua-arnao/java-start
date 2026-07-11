# MECANISMO DE EJECUCIÓN DE JAVA
Java no ejecuta tu código directamente sobre el hardware. Pasa por varias
fases intermedias que le dan su característica de "Write Once, Run Anywhere".

### FASE 1: ESCRITURA y COMPILACIÓN (javac)
Cuando escribes código es solo **texto plano**, son letras que no entiende la computadora. El compilador (`javac`) hace un trabajo puntual:

```
    Main.java  (texto plano, lógica humana)
       |
       |  javac (el compilador) lo traduce
       |
       ▼
    Main.class (Bytecode: instrucciones estandarizadas para la JVM,
              NO para tu procesador físico todavía)
```

- El bytecode es un lenguaje intermedio. No es el binario nativo de tu
  CPU (x86, ARM), es un conjunto de instrucciones que solo la JVM entiende
  (ej. la instrucción `iadd` para sumar dos enteros).
- Esta fase detecta errores de sintaxis y de tipos ANTES de ejecutar nada.
- NO se crea ningún objeto real, NO se reserva Heap, NO se ejecuta nada
  todavía. El `.class` es solo un "plano", no algo vivo.

### FASE 2: ARRANQUE DE LA JVM y `Class Loading`

Cuando ejecutas `java Main`, la JVM arranca y crea sus áreas de memoria (esto pasa UNA sola vez por cada arranque):

- **Method Area / Metaspace**: se crea al arrancar la JVM. Aquí se guarda
  la metadata de cada clase: su estructura, campos, métodos, constant pool,
  y variables `static`. Compartida por toda la app.
- **Heap**: se crea al arrancar la JVM. Aquí vivirán todos los objetos y arrays que se creen con `new`. Compartido por todos los hilos.
- **Stack**: se crea UNO por cada hilo que arranque (el hilo `main` ya tiene el suyo desde el inicio).

El **ClassLoader** es el componente que localiza el archivo `.class`
según se va necesitando (carga perezosa / lazy), lo lee, y guarda su
estructura en el Metaspace. Sin esto, la JVM no sabría qué campos o
métodos tiene tu clase.

### FASE 3: VERIFICACIÓN (Bytecode Verifier)

Antes de ejecutar una sola instrucción, la JVM verifica que el bytecode sea válido y seguro (que no acceda a memoria fuera de rango, que no viole las reglas de tipos, etc.). Esto es parte del "Sandbox" o entorno seguro: protege al sistema operativo de bytecode corrupto o malicioso.

### FASE 4: EJECUCIÓN (Execution Engine)

Aquí es donde tu programa realmente "corre". La JVM tiene DOS modos
de ejecutar bytecode, y los usa combinados:

1. **Interpretación**: lee el bytecode instrucción por instrucción y la ejecuta al vuelo. Arranque rápido, pero más lento en el largo plazo porque vuelve a interpretar la misma instrucción cada vez. TODO método arranca aquí.

2. **JIT (Just-In-Time Compiler)**: compila métodos a código nativo de la CPU y los guarda en una zona de memoria llamada **Code Cache**, para no volver a interpretarlos.

#### ¿Cómo decide el JIT qué compilar?

Cada método tiene un **contador de invocaciones** interno. Cada vez
que se llama, el contador sube. Al llegar a un umbral, se compila:

```
Nivel 0: Interpretado         → todo método arranca aquí
   │  ~100 invocaciones
   ▼
Nivel 1-3: Compilado con C1   → compilación rápida, poco optimizada,
                                 sigue recolectando datos de uso
   │  ~10,000 invocaciones
   ▼
Nivel 4: Compilado con C2     → compilación más costosa, pero genera
                                 el código MÁS optimizado posible
```

> El Code Cache tiene tamaño fijo: si se llena, el JIT deja de
> compilar métodos nuevos. Por eso el JIT es selectivo — solo
> compila lo que demuestra, con uso real, que vale la pena.
> Esto explica por qué una app "tarda en calentar" al inicio, pero
> después de unos segundos/minutos corre mucho más rápido.

#### ¿Por qué hay DOS compiladores (C1 y C2)?

| | C1 (Cliente) | C2 (Servidor) |
|---|---|---|
| Actúa | Primero, ~100 invocaciones | Después, ~10,000 invocaciones |
| Velocidad de compilar | Rápida | Lenta (analiza mucho más a fondo) |
| Calidad del código generado | Optimización básica | Optimización agresiva |

Si solo existiera C2, el método seguiría interpretado (lento) mucho
tiempo, esperando llegar a 10,000 invocaciones. Si solo existiera C1,
nunca se llegaría al máximo rendimiento posible. Por eso trabajan en
**niveles (tiered compilation)**: C1 da una mejora rápida y temprana,
y mientras tanto, si el método sigue siendo muy frecuente, C2 compila
en paralelo (en otro hilo) una versión más optimizada que reemplaza
a la de C1 cuando está lista — sin detener el programa a esperar.

> ⚠️ Esa compilación de C2 en paralelo consume CPU real del sistema.
> Si mides tiempos de ejecución (benchmarks caseros con
> `System.nanoTime()`), es normal ver un pico raro de lentitud justo
> cuando el JIT está haciendo esa transición de C1 a C2 — no es un
> error del código, es el propio compilador trabajando de fondo.

### FASE 5: ASIGNACIÓN DE MEMORIA EN TIEMPO REAL (Stack vs. Heap)
Mientras el Execution Engine corre tu código, cada instrucción decide
en tiempo real dónde se guardan los datos:

- **Variable local primitiva** (`int contador = 10`) → se reserva espacio dentro del **frame** (marco) del método actual, en el **Stack** del hilo. Push al entrar al método, pop automático al salir. Costo casi cero.

- **Objeto** (`new Usuario()`) → la JVM reserva un bloque de bytes en el **Heap** (tamaño según la metadata que ya leyó en el Metaspace). Te devuelve una referencia (una dirección de memoria), y ESA referencia sí se guarda en el Stack o en otro objeto.

```
STACK (frame de metodo())          HEAP
┌─────────────────────--┐            ┌───────────────--───────┐
│ contador = 10         │            │ dirección: 5000        │
│ user = →dirección 5000| ──────────►│ [Usuario: nombre, edad]│
└────────────────────--─┘            └──────────────────--────┘
```

- **Stack (Pila)**: Tareas rápidas
  - Función: Memoria de acceso ultra rápido y corto plazo.
  - Qué guarda: Variables locales (primitivos como int, double) y las referencias (direcciones) a los objetos.
  - Orden: LIFO (Last In, First Out). Cuando un método termina, su Stack se limpia instantáneamente.
- **Heap (Montón)**: El Gran almacén
  - Función: Memoria de largo plazo y gran capacidad.
  - Qué guarda: Los Objetos Reales (instancias de clases).
  - Gestión: Aquí operan los objetos que el Garbage Collector (GC) analiza; el GC limpia automáticamente los que ya no tienen ninguna referencia viva apuntándoles desde el Stack.

### FASE 6: LIBERACIÓN DE MEMORIA (Garbage Collector)

- Cuando el método termina, su **frame se descarta del Stack** al instante (no necesita "decisión", solo retrocede un puntero).
- El objeto en el **Heap sigue existiendo físicamente** hasta que el **Garbage Collector** (un proceso de la JVM que opera sobre el heap, no "vive dentro" de él)
  recorra las referencias vivas y determine que ya nadie lo alcanza.
  Solo entonces ese espacio se recicla.

> Diferencia clave: el Stack se limpia con una regla simple y predecible (LIFO). El Heap necesita un proceso activo (GC) porque un objeto puede vivir minutos, horas, o toda la vida de la aplicación — su final NO es predecible como el de una variable local.

> ⚠️ Nota para más adelante: esta fase explica el mecanismo general del GC (qué hace y por qué existe), pero todavía no cubre CUÁNDO se ejecuta ni los distintos tipos de recolección (Minor GC en la generación joven, Full GC en la generación vieja). Eso corresponde al tema de "Optimización del Garbage Collector" que se documentará aparte.

### ¿QUÉ PASA EN COMPILACIÓN VS. EN EJECUCIÓN?

#### En COMPILACIÓN (javac) — pasa UNA sola vez, al escribir el código
- Se traduce el .java a bytecode (.class)
- Se valida sintaxis y tipos
- NO se crea ningún objeto real, NO se reserva Heap, NO se ejecuta nada

#### En EJECUCIÓN (cada vez que arrancas `java Main`) — se repite SIEMPRE
- Class Loading: cargar cada .class según se necesita (lazy)
- Bytecode Verifier: revisa cada clase cargada
- Interpretación: el código arranca lento (el JIT aún no acumuló invocaciones)
- Frameworks como Spring: usan REFLECTION para escanear clases, leer
  anotaciones, y construir el contenedor de Inyección de Dependencias
  — TODO esto ocurre recién en este momento, no antes

> Esta es la raíz real del "arranque pesado" en apps Java grandes: no
> es el código ejecutándose lento, es todo el trabajo de preparación
> (class loading + reflection + calentamiento del JIT) que se repite
> completo en cada arranque, porque la compilación dejó muy poco
> trabajo hecho de antemano.

### COLD START vs. HOT (código "caliente")

- **Cold Start (arranque en frío)**: el momento justo después de
  arrancar. Todo el código se interpreta línea por línea (lento),
  porque el JIT todavía no acumuló invocaciones suficientes de nada.
  Aquí también ocurre TODO el trabajo de preparación: Class Loading,
  Verificación, y si usas Spring, el escaneo por reflection y
  construcción del contenedor de Inyección de Dependencias.

- **Hot / Warm (código "caliente")**: después de un rato corriendo,
  los métodos más usados ya llegaron a los umbrales de C1/C2 y están
  compilados a nativo. La ejecución es mucho más rápida — es el
  estado "normal" de una app en producción que lleva tiempo corriendo.

> El costo de Cold Start se paga UNA VEZ por cada arranque de la JVM,
> no se puede evitar, solo reducir. Por eso en la nube (Azure,
> Kubernetes), donde se crean instancias/pods nuevos constantemente
> por auto-scaling o deploys, este costo se paga una y otra vez.

### RESUMEN VISUAL DEL FLUJO COMPLETO

```
Main.java
   │ (FASE 1: javac)
   ▼
Main.class (bytecode)
   │ (FASE 2: JVM arranca → crea Metaspace, Heap, Stack por hilo)
   ▼
ClassLoader carga la clase → guarda su estructura en Metaspace
   │ (FASE 3: Bytecode Verifier valida seguridad)
   ▼
Execution Engine ejecuta:
   ├── Interpreta instrucción por instrucción
   └── JIT compila a nativo el código "caliente" (por contador de invocaciones)
        │ (FASE 5: mientras ejecuta, asigna memoria)
        ├── primitivos locales   → Stack
        └── objetos (new)        → Heap
             │ (FASE 6: cuando ya nadie referencia el objeto)
             ▼
        Garbage Collector recicla ese espacio
```
