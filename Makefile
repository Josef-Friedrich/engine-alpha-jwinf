.ONESHELL: # Applies to every targets in the file!

ENGINE = $(HOME)/repos/github/engine-alpha

JAR = $(ENGINE)/engine-alpha/target/engine-alpha-4-jar-with-dependencies.jar

dependency:
	cd $(ENGINE)
	git checkout 4.x
	git pull
	mvn package
	mvn install:install-file \
		-Dfile=$(JAR) \
		-DgroupId=org.engine-alpha \
		-DartifactId=engine-alpha-parent \
		-Dversion=4.0.0-SNAPSHOT \
		-Dpackaging=jar

package:
	mvn package

.PHONY: dependency
