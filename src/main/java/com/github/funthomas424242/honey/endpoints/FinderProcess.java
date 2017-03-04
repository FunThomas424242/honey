package com.github.funthomas424242.honey.endpoints;

import com.github.funthomas424242.unmodifiable.UnmodifiableQueue;
import de.b0n.dir.DupFinderCallback;
import de.b0n.dir.Launcher;
import de.b0n.dir.processor.Cluster;

import java.io.File;
import java.util.Queue;

/**
 * Created by huluvu424242 on 04.03.17.
 */
public class FinderProcess implements Runnable{

    final protected  String[] folderPaths;
    protected Queue<UnmodifiableQueue<File>> duplicates=null;

    public FinderProcess(final String[] folderPaths){
        this.folderPaths =folderPaths;
    }

    @Override
    public void run() {
        final Cluster<Long, File> model = new Cluster<>();
        final Launcher launcher = new Launcher(model);
        final File folder = new File(folderPaths[0]);
        this.duplicates = launcher.searchDuplicatesIn(folder, new DupFinderCallback(){});
    }

    public Queue<UnmodifiableQueue<File>> getDuplicates(){
        return duplicates;
    }

    public boolean isFinished(){
        return duplicates!=null;
    }
}
