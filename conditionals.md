# ESTRUCTURAS CONDICIONALES

Son parte de los Flujos de Control (junto con Secuencial y Repetitivo).
Permiten que el programa tome un camino distinto según se cumpla o no una condición.

## IF / ELSE
- Evalúa una expresión booleana y ejecuta un bloque u otro.

```java
void main(String[] args) {
    if (monto > 0) {
        procesar();
    } else {
        rechazar();
    } 
}
```

### ¿QUÉ RESUELVE?
Permite que el programa reaccione distinto según el estado de los
datos en tiempo de ejecución, en vez de ejecutar siempre la misma
secuencia de instrucciones.

## EL PROBLEMA: IF ANIDADOS (Arrow Code)

```java
void main(String[] args) {
    if (usuario != null) {
        if (usuario.tieneCuenta()) {
            if (cuenta.estaActiva()) {
                if (monto > 0) {
                    procesarTransaccion();
                }
            }
        }
    }
}
```

## ALTERNATIVA 1: EARLY RETURN (Guard Clauses)

Descartar los casos inválidos de inmediato con `return`, dejando el "camino feliz" al final, sin anidar nada.

```java
public void procesarTransaccion(Usuario usuario, Cuenta cuenta, double monto) {
    if (usuario == null) return;
    if (!usuario.tieneCuenta()) return;
    if (!cuenta.estaActiva()) return;
    if (monto <= 0) return;

    ejecutarProcesamiento();
}
```

> Mismo resultado y mismo rendimiento que la versión anidada — la diferencia es 100% legibilidad.

## ALTERNATIVA 2: OPERADOR TERNARIO

Para una decisión binaria simple, de una sola expresión.

```java
String estado = activo ? "ACTIVO" : "INACTIVO";
```

> ⚠️ NUNCA anidar ternarios (`a ? b : c ? d : e`) — genera el mismo problema de legibilidad que el if anidado, pero más comprimido y más difícil de leer todavía.

## ALTERNATIVA 3: SWITCH (clásico vs moderno)

Útil cuando se compara UNA sola variable contra MUCHOS valores posibles, reemplaza cadenas largas de `if / else if / else if`.

```java
void main(String[] args) {
    // Forma antigua (fall-through, riesgo de olvidar el 'break')
    switch (dia) {
        case 1:
            nombre = "Lunes";
            break;
        case 2:
            nombre = "Martes";
            break;
    }

    // Forma moderna (switch expression, Java 14+, sin fall-through)
    String nombre = switch (dia) {
        case 1 -> "Lunes";
        case 2 -> "Martes";
        default -> "Desconocido";
    };
}
```

### ¿QUÉ RESUELVE LA FORMA MODERNA?
Elimina el bug clásico de olvidar el `break` (que hacía que el código "cayera" al siguiente caso sin querer).

### ¿SWITCH ES MÁS RÁPIDO QUE IF/ELSE?
Con MUCHOS casos, el compilador puede generar una tabla de saltos (jump table) para `switch`, yendo directo al caso correcto en vez de evaluar comparaciones una por una como en una cadena de `if/else`. No es una regla absoluta — depende de qué tan denso/continuo sea el rango de valores.

## ALTERNATIVA 4 (avanzada — POO): POLIMORFISMO

Cuando el `if`/`switch` decide "qué comportamiento ejecutar" según el TIPO de objeto, en POO se reemplaza dejando que cada clase implemente su propio método. Se documenta cuando le toque su tema.

## RESUMEN: CUÁNDO USAR CADA UNA

| Situación                                          | Alternativa          |
|:-----------------------------------------------------|:-----------------------|
| Varias validaciones que descartan casos inválidos   | Early Return          |
| Una sola decisión binaria simple                    | Ternario               |
| Una variable comparada contra muchos valores fijos  | Switch (moderno)      |
| Comportamiento distinto según el TIPO de objeto     | Polimorfismo (POO)    |