package com.example;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.LoggerHandler;
import io.vertx.rxjava.core.Vertx;
import io.vertx.core.streams.Pump;
import rx.Observable;

public class HttpVerticle extends AbstractVerticle {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void start(Future<Void> startFuture) throws Exception {


        Router router = Router.router(vertx);

        router.route().handler(LoggerHandler.create());
        router.route().handler(ctx->{

            Observable<String> obs = Vertx.currentContext().executeBlockingObservable(f -> {


                AsyncFile aFile = vertx.fileSystem().openBlocking("aFile.txt", new OpenOptions());


                ctx.response().putHeader("Content-Length","19");

                Pump p= Pump.pump(aFile,ctx.response(),19);
                p.start();



//                aFile.endHandler(e->{
//                    logger.info("end file");
//                    ctx.response().end();
//                });




            });

            obs.subscribe(a->{
                logger.info(a);
            },error->{
                logger.error(error.getMessage(),error);
            });

        });

        HttpServer server=vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        logger.info("Server listening on port 8080");


    }
}
