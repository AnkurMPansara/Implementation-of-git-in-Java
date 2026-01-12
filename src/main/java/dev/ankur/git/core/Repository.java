package dev.ankur.git.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Repository {
    private final Path gitDir;

    public Repository(Path gitDir) {
        this.gitDir = gitDir;
    }

    public static Repository init(Path path) throws IOException {
        Path gitPath = path.resolve(".git");
        if (Files.exists(gitPath)) {
            throw new IOException("Git repository already exists");
        }

        Files.createDirectories(gitPath.resolve("objects"));
        Files.createDirectories(gitPath.resolve("refs/heads"));
        Files.createDirectories(gitPath.resolve("refs/tags"));

        Files.writeString(gitPath.resolve("HEAD"), "ref: refs/heads/master\n");

        return new Repository(gitPath);
    }

    public static Repository load(Path path) throws IOException {
        Path gitDir = path.resolve(".git");
        if (!Files.exists(gitDir) || !Files.isDirectory(gitDir)) {
            throw new IOException("No git repository found in " + path);
        }

        Path headFile = gitDir.resolve("HEAD");
        if (!Files.exists(headFile)) {
            throw new IOException("Repository HEAD is missing!");
        }

        return new Repository(gitDir);
    }

    public void add(String pathRegex) throws IOException {
        System.out.println("lol");
        return;
    }
}
