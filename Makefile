.ONESHELL: # Applies to every targets in the file!

ENGINE = $(HOME)/repos/github/engine-alpha

JAR = $(ENGINE)/engine-alpha/target/engine-alpha-4-jar-with-dependencies.jar
SOURCES = $(ENGINE)/engine-alpha/target/engine-alpha-4-sources.jar

dependency:
	cd $(ENGINE)
	git checkout 4.x
	git pull
	mvn package
	mvn install:install-file \
		-Dfile=$(JAR) \
		-DgroupId=org.engine-alpha \
		-DartifactId=engine-alpha-parent \
		-Dsources=$(SOURCES) \
		-Dversion=4.0.0-SNAPSHOT \
		-Dpackaging=jar

package:
	mvn package

json:
	./.scripts/convert-task-json.mjs

test:
	mvn test

.PHONY: dependency
