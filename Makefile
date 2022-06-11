# commands
MVN = mvn
DOCKER = docker
COMPOSE = docker-compose -f $(CURDIR)/docker-compose.yml

-include $(CURDIR)/.env.local
export

help:
	@echo "Please use 'make <target>' where <target> is one of the following:"
	@echo "  ci                   to run the tests on ci/docker."
	@echo "  ci-cleanup           to kill & remove all ci containers."
	@echo "  test                 to run the tests locally with maven."
	@echo "  test                 to cleanup generated files and folders."

.PHONY: test
test:
	$(MVN) -B package --file pom.xml allure:report

.PHONY: clean
clean:
	$(MVN) clean
	rm -f test-output.log*

.PHONY: ci
ci: ci-cleanup
	$(COMPOSE) up --build --no-deps --force-recreate -d selenium-run
	$(COMPOSE) run selenium-run make test
	$(MAKE) ci-cleanup

.PHONY: ci-cleanup
ci-cleanup:
	$(COMPOSE) down