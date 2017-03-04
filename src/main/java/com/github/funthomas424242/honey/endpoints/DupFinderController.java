package com.github.funthomas424242.honey.endpoints;

import com.github.funthomas424242.unmodifiable.UnmodifiableQueue;
import de.b0n.dir.DupFinderCallback;
import de.b0n.dir.Launcher;
import de.b0n.dir.processor.Cluster;
import de.b0n.dir.processor.DupFinder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by huluvu424242 on 04.03.17.
 */
@RestController
@RequestMapping(path="/dupfinder")
public class DupFinderController {

    protected static long processID=0;
    protected static Map<Long,FinderProcess> searcher= new HashMap<>();


    @RequestMapping(path = "getDuplicates/{id}",method = RequestMethod.GET)
    public HttpEntity<SearcherResource> getDuplicates(@PathVariable("id") final Long searchID){
        System.out.println("Get duplicates for process "+searchID);
        final FinderProcess process = searcher.get(searchID);
        final SearcherResource resource;
        if(process.isFinished()){
            resource=new SearcherResource(process.getDuplicates());

        }else{
            resource= new SearcherResource(null);
        }
        return new ResponseEntity<SearcherResource>(resource, HttpStatus.OK);
    }

    @RequestMapping(path = "searchDuplicates",method = RequestMethod.GET)
    public HttpEntity<ProcessResource> searchDuplicates(@RequestParam String... folderPath){
        final String[] folderPaths=folderPath;
        System.out.println("Anzahl Pfade:"+folderPaths.length);
        for(String path:folderPaths) {
            System.out.println("FolderPath:" + path);
        }
        final Long processHandle= addNewSearchProcess(folderPaths);
        System.out.println("Process: "+processHandle+" started.");
        final ProcessResource resource= new ProcessResource(processHandle);
        resource.add(linkTo(methodOn(DupFinderController.class).searchDuplicates(folderPaths)).withSelfRel());
        resource.add(linkTo(methodOn(DupFinderController.class).getDuplicates(processHandle)).withRel("next"));

        return new ResponseEntity<ProcessResource>(resource,HttpStatus.OK);
    }

    protected Long addNewSearchProcess(final String[] folderPaths){
        final Long id= getNewProcessID();
        final FinderProcess process = new FinderProcess(folderPaths);
        this.searcher.put(id,process);
        (new Thread(process)).start();
        return id;
    }

    protected synchronized Long getNewProcessID() {
        processID++;
        return processID;
    }
}
