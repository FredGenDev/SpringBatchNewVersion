package com.example.SpringBatchNewVersion.batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;



public class FileDeletingTasklet implements Tasklet {
    private Resource directory;

    public FileDeletingTasklet(Resource directory) {
        this.directory = directory;
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
        try (Stream<Path> walk = Files.walk(Paths.get(directory.getFile().getPath()))) {
            walk.filter(Files::isRegularFile).map(Path::toFile).forEach(File-> System.out.println(File.delete()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return RepeatStatus.FINISHED;
    }
}
