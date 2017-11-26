package javamodularity.easytext.pagefetch;

import io.reactivex.Single;

public interface WikipediaFetcher {
    Single<String> getText(String topic);
}
