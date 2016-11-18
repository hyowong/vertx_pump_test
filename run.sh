#!/bin/bash
mvn package

java -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.Log4j2LogDelegateFactory -server -Xms2g -Xmx4g -Djava.net.preferIPv4Stack=true -Djava.net.preferIPv6Addresses=false -d64 -jar target/runnable.jar -Dvertx.metrics.options.enabled=true -Dvertx.metrics.options.registryName=my-registry
