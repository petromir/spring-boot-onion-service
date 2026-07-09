SHELL := /bin/bash

compile:
	# -B enables mvnd verbose logging
	mvnd -B clean compile -Dsmartbuilder.profiling=true

build:
	# -B enables mvnd verbose logging
	mvnd -B clean package -Dsmartbuilder.profiling=true

generate-code:
	rm -f modules/*/src/main/java/com/petromirdzhunev/*/infra/http/server/controller/*Api.java
	rm -rf modules/*/src/main/java/com/petromirdzhunev/*/infra/http/server/controller/model
	rm -rf modules/*/src/main/java/com/petromirdzhunev/*/infra/db/jooq/*
	# -T1 disables parallel build: testcontainers-jooq-codegen-maven-plugin fails under mvnd parallel (Cannot end scope)
	mvnd -B clean generate-sources -P generate-code -am -pl application -T1
	# Putting back the files to git
	git add modules/*/src/main/java/com/petromirdzhunev/*/infra/http/server/controller/*Api.java
	git add modules/*/src/main/java/com/petromirdzhunev/*/infra/http/server/controller/model
	git add modules/*/src/main/java/com/petromirdzhunev/*/infra/db/jooq/*

clean-database:
	docker-compose --file ./docker/docker-compose.yaml down
	docker-compose --file ./docker/docker-compose.yaml up -d --remove-orphans