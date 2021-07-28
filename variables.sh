#!/usr/bin/env bash

case "$1" in
	"dev")
        export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/poc_db'
        export SPRING_DATASOURCE_USERNAME='product_ms_role'
        export SPRING_DATASOURCE_PASSWORD='product_ms_role'

        export DATABASE_ACTION='update'
    ;;

  "docker")
        export SPRING_DATASOURCE_URL='jdbc:mysql://localhost:3306/poc_db'
        export SPRING_DATASOURCE_USERNAME='product_ms_role'
        export SPRING_DATASOURCE_PASSWORD='product_ms_role'

        export DATABASE_ACTION='update'
    ;;

	*)
	  echo "Invalid spring profile"
	  ;;
esac
