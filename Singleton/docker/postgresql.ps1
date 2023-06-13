docker run -p 5432:5432 `
    -e "POSTGRES_USER=user" `
    -e "POSTGRES_PASSWORD=password" `
    -e "POSTGRES_DB=dbcity" `
    --name "dbcity_pg" `
	-d "postgres:15"