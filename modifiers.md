# MODIFICADORES
Java tiene dos categorías de modificadores que aplican a clases, métodos y variables.

## MODIFICADORES DE ACCESO

| MODIFICADOR       | ALCANCE                                 |
|:------------------|:----------------------------------------|
| `public`          | Visible desde cualquier lugar           |
| `protected`       | Visible en el mismo paquete + subclases |
| (sin modificador) | Visible solo en el mismo paquete |
| `private`         | Visible solo dentro de la misma clase |

## MODIFICADORES DE NO ACCESO

| MODIFICADOR    | QUÉ HACE                                                                                                     |
|:---------------|:-------------------------------------------------------------------------------------------------------------|
| `final`        | Bloquea reasignación (variable), sobrescritura (método), o herencia (clase)                                  |
| `static`       | Pertenece a la clase, no a la instancia, se comparte entre todos los objetos                                 |
| `abstract`     | Declara sin implementar; obliga a las subclases a implementar                                                |
| `synchronized` | Controla acceso concurrente entre hilos                                                                      |
| `transient`    | Excluye un campo de la serialización                                                                         |
| `volatile`     | Garantiza visibilidad de una variable entre hilos (sin ella, un hilo puede no "ver" el cambio que hizo otro) |


### LOS 3 USOS DE `final`

| Se aplica a... | Qué bloquea                                 | Ejemplo                           |
|:---------------|:--------------------------------------------|:----------------------------------|
| **Variable**   | Reasignar el valor/referencia               | `final double TAX_RATE = 0.18;`   |
| **Método**     | Que una subclase lo sobrescriba (@Override) | `final void procesar() {...}`     |
| **Clase**      | Que se pueda heredar de ella                | `final class Configuracion {...}` |

> Ejemplo real de `final class`: la propia clase `String` de Java es `final` — nadie puede heredar de `String` y modificar su comportamiento interno. Es una decisión de diseño que refuerza la inmutabilidad.

### El matiz importante: `final` en objetos NO es lo mismo que inmutable

```java
final Usuario usuario = new Usuario("Joshua");
usuario.setNombre("Otro");    // SÍ SE PUEDE — el objeto puede mutar
usuario = new Usuario("X");   // NO COMPILA — no se puede cambiar la REFERENCIA
```