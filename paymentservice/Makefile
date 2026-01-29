COMPOSE_FILE=compose.yaml

.PHONY: build up down logs

build:
	sudo docker compose -f $(COMPOSE_FILE) build

up:
	sudo docker compose -f $(COMPOSE_FILE) up -d --build

down:
	sudo docker compose -f $(COMPOSE_FILE) down

logs:
	sudo docker compose -f $(COMPOSE_FILE) logs -f
