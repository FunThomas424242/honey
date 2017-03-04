package com.github.funthomas424242.honey.endpoints;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.funthomas424242.unmodifiable.UnmodifiableQueue;
import org.springframework.hateoas.ResourceSupport;

import java.io.File;
import java.util.Queue;

/**
 * Created by huluvu424242 on 04.03.17.
 */
public class SearcherResource extends ResourceSupport{

    protected  final Queue<UnmodifiableQueue<File>> duplicates;

    @JsonCreator
    public SearcherResource(@JsonProperty("duplicates") Queue<UnmodifiableQueue<File>> duplicates){
        this.duplicates =duplicates;
    }

    public Queue<UnmodifiableQueue<File>> getDuplicates(){
        return duplicates;
    }

}
