# CONSTANTES EN JAVA (modificador `final`)

## ¿Java tiene un tipo "constante"?
No. En Java Existe el modificador `final`, aplicable a CUALQUIER tipo de variable (primitivos, String, objetos, arrays), no es parte del sistema de tipos, es una restricción sobre la variable.

### ¿Qué resuelve?
Evita reasignaciones accidentales de un valor que, por diseño, nunca debería cambiar durante la ejecución (una tasa de interés fija, un límite máximo de reintentos). Es una herramienta de seguridad en TIEMPO DE COMPILACIÓN: el compilador rechaza el código si se intenta reasignar, en vez de fallar después en producción.



`final` en un objeto solo bloquea que la referencia (la dirección
guardada en el Stack) apunte a otro objeto distinto. El objeto en el
Heap sigue siendo completamente mutable si su clase lo permite.

### Convención de nombres

Por CONVENCIÓN (no una regla que el compilador obligue), las
constantes se escriben en MAYÚSCULAS_CON_GUION_BAJO:

```java
final double TAX_RATE = 0.18;      // ✅ convención correcta
final int MAX_REINTENTOS = 3;      // ✅ convención correcta
final String tasaInteres = "5%";   // ⚠️ compila, pero rompe la convención
```

> Si además la constante es `static` (pertenece a la clase, no se
> repite por instancia), la combinación típica en proyectos reales
> es `public static final` — ese trío completo se ve seguido en
> configuraciones y constantes globales. El "por qué" de `static`
> se documenta cuando le toque su propio tema.