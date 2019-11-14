#María Rabanales González - examen 1º DAW

def añadir_películas():
    print("Para añadir película introduce los siguientes datos:")
    titulo = input("Título: ")
    director = input("Director: ")
    duracion = int(input("Duración en minutos: "))
    genero = input("Género: ")
    año = int(input("Año: "))
    disponible = True
    numcopia = int(input("Cantidad de copias totales: "))
    resercopia = 0
    nuevapelicula = [titulo, director, duracion, genero, año, disponible, numcopia, resercopia]
    return nuevapelicula

def reservar_película():
    listar_películas()
    idreserva = int(input("Introduce el id de la película que quieres reservar: "))
    if idreserva > len(listapeliculas):
        print("Error: no existe ninguna película con este id.")
    else:
        if listapeliculas[idreserva-1][6] == False:
            print("Error: no quedan copias disponibles de esta película.")
        else:
            print("Esta película ha sido reservada.")
            listapeliculas[idreserva-1][8] = listapeliculas[idreserva-1][8] + 1
            if listapeliculas[idreserva-1][8] == listapeliculas[idreserva-1][7]:
                listapeliculas[idreserva-1][6] = False

def listar_películas():
    print("===================\nLISTA PELÍCULAS\n===================")
    for i in range(len(listapeliculas)):
        print("ID:", listapeliculas[i][0], end = " - ")
        print("Título:", listapeliculas[i][1], end = " - ")
        print("Director:", listapeliculas[i][2], end = " - ")
        print("Duracion:", listapeliculas[i][3], end = " - ")
        print("Genero:", listapeliculas[i][4], end = " - ")
        print("Año:", listapeliculas[i][5], end = " - ")
        if listapeliculas[i][6] == True:
            print("Estado: Disponible")
        else:
            print("Estado: No disponible")

#si sobra tiempo comprobar 100% que mi opcion de búsqueda coincide con el índice.
def buscar_películas():
    print("===================\nBUSCA PELÍCULAS\n===================\n" +
          "¿Qué tipo de búsqueda deseas realizar?\n 1) por título\n" +
          " 2) por director\n 3) por duración\n 4) por género\n 5) por año")
    opcionbusqueda = int(input("Elige: "))
    textobusqueda = input("Introduce el texto que quieres que aparezca en la búsqueda: ")
    buscar_parametros(opcionbusqueda, textobusqueda)

def buscar_parametros(indice, texto):
    print("Resultado de la búsqueda:")
    busquedafallida = True
    for i in range(len(listapeliculas)):
        if str(listapeliculas[i][indice]).rfind(str(texto)) != -1:
            busquedafallida = False
            print("ID:", listapeliculas[i][0], end = " - ")
            print("Título:", listapeliculas[i][1], end = " - ")
            print("Director:", listapeliculas[i][2], end = " - ")
            print("Duracion:", listapeliculas[i][3], end = " - ")
            print("Genero:", listapeliculas[i][4], end = " - ")
            print("Año:", listapeliculas[i][5])
    if busquedafallida == True:
        print("No existe ningún título que cumpla con este criterio.")

salir = False
listapeliculas = []
campoid = 0
totalcopias = 0
while salir == False:
    print("===================\n   =  M E N U  =\n===================\n" +
          " 1) añadir película\n 2) reservar película\n" +
          " 3) buscar película\n 4) salir\n" +
          "===================")
    opcion = int(input("¿Qué opción deseas? "))
    if opcion == 1:
        if totalcopias >= 3000:
            print("Error: el videoclub no puede almacenar más copias.")
        else:
            campoid += 1
            nuevapelicula = añadir_películas()
            nuevapelicula.insert(0, campoid)
            totalcopias = totalcopias + nuevapelicula[7]
            if totalcopias > 3000:
                print("Error: el videoclub no puede almacenar tantas copias.")
            else:
                listapeliculas.append(nuevapelicula)
    elif opcion == 2:
        reservar_película()
    elif opcion == 3:
        buscar_películas()
    elif opcion == 4:
        print("¡Adiós!\n===================")
        salir = True
    else:
        print("Opción incorrecta. Prueba de nuevo.\n===================")

