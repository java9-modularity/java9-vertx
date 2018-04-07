package javamodularity.easytext.web;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import io.vertx.reactivex.core.Vertx;
import javamodularity.easytext.algorithm.api.Analyzer;
import javamodularity.easytext.algorithm.api.Preprocessing;
import javamodularity.easytext.pagefetch.WikipediaFetcher;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {

        var wikipediaFetcher = ServiceLoader.load(WikipediaFetcher.class).findFirst().get();

        var analyzers = ServiceLoader.load(Analyzer.class);

        var vertx = ServiceLoader.load(Vertx.class).findFirst().get();
        var server = vertx.createHttpServer();

        server.requestHandler(request -> {
            var response = request.response();

            var topic = request.getParam("topic");

            if (topic == null) {
                request.response().setStatusCode(400).end("No topic set");
            } else {
                response.setChunked(true);

                response.putHeader("Content-Type", "text/event-stream");
                response.putHeader("Connection", "keep-alive");
                response.putHeader("Cache-Control", "no-cache");

                wikipediaFetcher.getText(topic).subscribeOn(Schedulers.computation()).toObservable()
                        .map(Preprocessing::toSentences)
                        .flatMap(text -> Observable.fromIterable(analyzers).flatMap(a -> Observable.create(observer -> {
                            var result = a.getName() + ": " + a.analyze(text);
                            observer.onNext(result);
                        }).subscribeOn(Schedulers.computation())))
                        .subscribe(text -> {
                            response.write(text + "\n");
                            System.out.println(text);
                        }, err -> {
                            err.printStackTrace();
                            response.setStatusCode(500);
                            response.end();
                        }, response::end);
            }

        });

        server.listen(8080, result -> System.out.println("Server listening: " + result.succeeded()));
    }
}
