SHELL := /bin/bash

compile:
	# -B enables mvnd verbose logging
	mvnd -B clean compile -Dsmartbuilder.profiling=true

build:
	# -B enables mvnd verbose logging
	mvnd -B clean package -Dsmartbuilder.profiling=true

generate-code:
	rm -rf src/main/java/com/petromirdzhunev/*/infra/http/server/api/*
	rm -rf src/main/java/com/petromirdzhunev/*/infra/db/jooq/*
	mvnd -B clean generate-sources -P generate-code -am -pl application
	# Putting back the files to git
	git add src/main/java/com/petromirdzhunev/*/infra/http/server/api/*
	git add src/main/java/com/petromirdzhunev/*/infra/db/jooq/*

clean-database:
	docker-compose --file ./docker/docker-compose.yaml down
	docker-compose --file ./docker/docker-compose.yaml up -d --remove-orphans