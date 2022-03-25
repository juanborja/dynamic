## Analisis de Algoritmos

### Programación dinámica

##### Juan J. Borja

### El dominio del problema

Ha modo ludico con el objetivo de dotar de significado a los algoritmos se plantea una variacion moderna del problema del viajero.

Las personas que viajan por argentina con espacio disponible en sus vehiculos pueden registrar en la aplicación: La ciudad de donde parten
(origen), la ciudad a donde llegan (destino), el monto que desean cobrar por ese tramo y su nombre.

Quienes desean viajar y no disponen de un vehiculo, pueden ingresar la ciudad desde la que desean partir y recibiran un listado
de todos los destinos posibles con su costo asociado (Si hubiera mas de un camino hacia el mismo destino se listara el de menor precio)

### La estructura de datos

El problema se modelo utilizando grafos dirigidos utilizando una lista de listas de nodos.
```
List<List<Node>> Graph
```
![img_1.png](img_1.png)

### La entrada de datos

Los datos se ingresan en un archivo separado por comas en el orden: origen, destino, precio, persona

```
Neuquen, Gral Roca, 500, Juan
Gral Roca, Rio Colorado, 300, Carlos
Rio Colorado, Bahia Blanca, 670, Pedro
Bahia Blanca, Necochea, 560, Laura
...
```

### Abstracción y modelado

Para pasar de la imagen anterior a la estructura de grafo que finalmente se almacena en la memoria
se relizan una serie de procesos. 

- Se lee el archivo de entrada linea por linea y a cada ciudad que se lee por primer vez se le asigna un numero (loadCityNumbers en data/dataLoader.java)
- El proceso anterior ocurre dentro del proceso que transforma el archivo csv en la estructura del grafo (loadGraphFromFile en data/dataLoader.java)

Luego de este proceso se tendra en memoria el Grafo cargado con las ciudades representadas con su numero unico asignado

### Datos utilizados

El archivo graph.txt cuenta con una entrada inicial de 104 conexiones. Ademas pueden generarse tantas conexiones como se desee
aleatoriamente. El algoritmo tomara una ciudad alaeatoria del archivo Cities.java y generara un valor aleatoriamente para el costo de la ruta
Esto ocurre en la funcion generateRandomConnections en dataLoader.java. La funcion espera como entrada la cantidad de conexiones y el archivo en que deben guardarse.

Con el fin de medir empiricamente los tiempos de ejecución se creo otra funcion que genera sub-graphos a partir del grafo generado anteriormente, esta
funcion se llama generateFiles y recibe como parametros: la dirección del archivo con el grafo y un arreglo indicando las dimensiones de los subgrafos

### Los algoritmos utilizados

Teniendo en cuenta que la pregunta que queremos responder es: Dado que parto de una ciudad X a que destinos puedo llegar gastando lo menos posible? 
Elejimos para responder la pregunta dos algoritmos: Dijktra y Floyd-Warshall.
En el caso de Dijktra cada vez que queremos saber la ruta más barata de una ciudad el algoritmo la calcula al momento de solicitarla,
Floyd-Warshall en cmabio pre-calcula todas las rutas del grafo y al momento de consultarla solo debe realizar la lectura.
Seria esperable entonces que el tiempo de carga del grafo sea inferior en Dijktra que en Floyd-Warshall y que el timepo de consulta
sea menos en Floyd-Warshall que en Dijktra.

### La medición empirica

Para comprobar la hipotesis tomamos los subgrafos generados y ejecutamos ambos algoritmos sobre estos algoritmos
midiendo los tiempos de carga y de consulta en ms. Almacenamos los resultados y los graficamos utilizando JfreeCharts

### El resultado

Obtenemos como resultado dos graficos para comparar los algoritmos, uno del tiempo de carga y el otro del tiempo de consulta.
Podemos observar que contrario a lo esperado en cuanto a velocidad de carga se comportan de manera similar. En cuanto al tiempo de consulta, como esperabamos
Floyd-Warshall se mantiene practicamente contante (No importa el tamaño del grafo sigue siendo un acceso a memoria) mientras que en el
caso de Dijktra crece con la entrada.
![img.png](img.png)

### Como probarlo

Basta con clonar este proyecto y ejecutar la clase principal (Main.java). Esta configurado para generar sub-grafos de entre 10 y 1000000 de aristas, por lo tanto se deberan esperar
unos minutos para ver las graficas en pantalla. 
