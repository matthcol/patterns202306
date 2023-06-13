docker run -p 3307:3306 `
	-e "MYSQL_ROOT_PASSWORD=password" `
	-e "MYSQL_USER=user" `
	-e "MYSQL_PASSWORD=password" `
	-e "MYSQL_DATABASE=dbcity" `
	--name "dbcity_mysql" `
	-d "mysql:8"