# Entorno de Ejecución
## ¿Qué Java?
Es un lenguaje de **alto nivel** y una **plataforma de ejecución**. Es el estándar en la industria bancaria y Fintech debido a su **robustez** y **seguridad**.

Pilares fundamentales:
- **Fuertemente Tipado**: Java prioriza la claridad sobre la brevedad. Cada dato tiene un **tipo definido**, esto evita errores de desbordamiento o tipos incompatibles en cálculos críticos.
- **Verbozo**: Significa que el código se explica solo, es mejor `int balance` que un `int b`.
- **Orientado a objetos (POO)**: Organiza el código imitando conceptos del mundo real(Clientes, cuentas, transacciones), permitiendo crear sistemas complejos mediante modelos manejables.
- **Abstracción**: Java nos permite enfocarnos en el **que hace** un objeto en lugar del **como lo hace**. Es la capacidad de simplificar problemas complejos del mundo real en modelos.
- **Multihilos (Multithreading)**: Capacidad nativa para realizar tareas en paralelo. Fundamental para que un servidor procese miles de pagos simultáneos sin colapsar.
- **Independiente del Hardware**: Gracias a su lema: *"Write once, run anywhere"* (Escríbelo una vez, ejecútalo en cualquier lugar). El código que haces en tu laptop funcionará exactamente igual en el servidor gigante del banco.

### El Ecosistema
Para trabajar en Java, debes conocer la diferencia entre estas herramientas:

| Siglas | Nombre | ¿Para qué sirve? |
|--------|--------|------------------|
| **JDK** | Java Development Kit | **Caja de herramientas**. Contiene el compilador (`javac`), herramientas de documentación y el JRE. Es lo que instala el programador. Es un superconjunto del JRE: contiene todo lo que está en el JRE, más los compiladores y depuradores necesarios para desarrollar. |
| **JRE** | Java Runtime Environment | **Entorno de Ejecución**. Es el paquete mínimo necesario para que un usuario final pueda correr una aplicación Java (librerías, JVM, y otros componentes). |
| **JVM** | Java Virtual Machine | **El motor de la ejecución**. Es una máquina computacional abstracta con su propio conjunto de instrucciones, que traduce el código universal (Bytecode) a instrucciones que el procesador de la computadora (Intel, Apple, AMD) entiende. |

### Flujo de Ejecución y Seguridad - Sandbox
Java no corre directamente sobre el hardware. Se ejecuta dentro de una **JVM**, lo que crea un entorno seguro o **Sandbox**, protegiendo al sistema operativo de código malicioso o errores graves.

El componente específico que hace cumplir esta seguridad es el **Bytecode Verifier**: antes de ejecutar una sola instrucción, la JVM verifica que el bytecode sea válido y seguro (que no acceda a memoria fuera de rango, que no viole las reglas de tipos, etc.).

| Fase | Componente | Resultado | Descripción |
|------|------------|-----------|-------------|
| **Escritura** | Código Fuente | `.java` | El archivo de texto con lógica humana. |
| **Compilación** | `javac` | `.class` | El "Traductor Estricto" genera Bytecode (instrucciones optimizadas). |
| **Ejecución** | **JVM** | Ejecución | El motor traduce el Bytecode a lenguaje máquina, combinando Interpretación con el compilador JIT (Just-In-Time) para acelerar el código que se usa con más frecuencia. |

> 📄 Esta tabla es un resumen simple. Ver el documento
> [**"Mecanismo de Ejecución de Java"**](java-execution-mechanism.md) para el flujo detallado y
> completo de las 6 fases (compilación, class loading, verificación,
> ejecución con Interpretación + JIT, asignación de memoria
> Stack/Heap, y liberación con el Garbage Collector).