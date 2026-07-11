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