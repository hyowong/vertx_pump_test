package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Main extends AbstractVerticle {
    public static final int WORKER_POOL_SIZE = 1024;
    private Logger logger = LoggerFactory.getLogger(Main.class);
    @Override
    public void start(Future<Void> startFuture) throws Exception {


        DeploymentOptions httpVerticleOptions = new DeploymentOptions();

        httpVerticleOptions.setWorkerPoolSize(WORKER_POOL_SIZE);

        httpVerticleOptions.setInstances(1);

        httpVerticleOptions.setConfig(config());

        vertx.deployVerticle(HttpVerticle.class.getName(),httpVerticleOptions);

        startFuture.complete();
    }
}
