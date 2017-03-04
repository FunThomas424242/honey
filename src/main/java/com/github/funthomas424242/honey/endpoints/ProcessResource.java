package com.github.funthomas424242.honey.endpoints;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by huluvu424242 on 04.03.17.
 */
public class ProcessResource extends ResourceSupport{

    protected Long processId;

    @JsonCreator
    public ProcessResource(final Long processId){
        this.processId=processId;
    }

    public Long getProcessId(){
        return processId;
    }
}
