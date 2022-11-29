#Scripts necesarios para desplegar en Docker 

# 1.- Obtener imagen de mysql desde docker hub
docker pull mysql:5.7

# 2.- Crear red de docker para comunicar el container de spring y el de mysql 
docker network create springboot-mysql-net

# 3.- Ejecutar el contenedor de mysql en la red recien creada 
docker run --name mysqldb --network springboot-mysql-net -e MYSQL_ROOT_PASSWORD=contrasenia -e MYSQL_DATABASE=bd_prueba_cuentas -e MYSQL_USER=usr_prueba_cuentas -e MYSQL_ROOT_PASSWORD=contrasenia -d mysql:5.7
# Para acceder a la consola en el container de mysql:
docker ps 
#Identificar el contenedor por su CONTAINERID:
docker exec -it CONTAINERID bash
#Se accede al bash del container, ahora acceder a mysql 
mysql -uusr_prueba_cuentas -pcontrasenia

# 4.- Modificar el archivo application.properties del aplicativo springboot:
 spring.datasource.url=jdbc:mysql://mysqldb:3306/bd_prueba_cuentas?serverTimezone=America/Mexico_City
 spring.datasource.username=root
 spring.datasource.password=contrasenia

 #Ejecutar
 mvn install 
 #Si marca error, podria ser por que ejecuta primero test, 
 # desactivarlo con: Clic derecho sobre el proyecto > Run As > Run Configurations > Maven Build > Input "Goals": install, Casilla "Skip Tests" activar > Run

# 5.- Construir la imagen docker del aplicativo springboot
docker build -t pruebacuenta .

# 6.- Iniciar el contenedor de spring boot en la red recien creada
docker run --network springboot-mysql-net --name pruebacuenta -p 8081:8081 -d pruebacuentas