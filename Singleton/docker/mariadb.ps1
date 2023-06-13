docker run -p 3306:3306 `
	-e "MARIADB_ROOT_PASSWORD=password" `
	-e "MARIADB_USER=user" `
	-e "MARIADB_PASSWORD=password" `
	-e "MARIADB_DATABASE=dbcity" `
	--name "dbcity_mariadb" `
	-d "mariadb:10"