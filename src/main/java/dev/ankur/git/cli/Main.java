package dev.ankur.git.cli;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import dev.ankur.git.core.Repository;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java-git <command> [options]");
            return;
        }

        String command = args[0];
        Path currentDir = Paths.get(System.getProperty("user.dir"));

        switch (command) {
            case "init":
                try {
                    Repository repo = Repository.init(currentDir);
                    System.out.println("Initialized empty Java Git repository in " + currentDir);
                } catch (IOException e) {
                    System.err.println("Failed to initialize repository: " + e.getMessage());
                    break;
                }
                break;
            case "clone":
                System.out.println("Initialize a repo from remote server");
                break;
            case "add":
                if (args.length < 2) {
                    System.err.println("Usage: java-git add <regex>");
                    break;
                }
                String regex = args[1];
                try {
                    Repository repo = Repository.load(currentDir);
                    repo.add(regex);
                } catch (Exception e) {
                    System.err.println("Failed to add files: " + e.getMessage());
                    break;
                }
                System.out.println("Add changes locally for commit");
                break;
            case "commit":
                System.out.println("Add local changes to a commit");
                break;
            case "pull":
                System.out.println("Sync changes in repo from remote repo");
                break;
            case "push":
                System.out.println("Sync changes in repo onto remote repo");
                break;
            case "reset":
                System.out.println("Reset changes in repo");
                break;
            default:
                System.out.println("Unknown command '" + command + "': see 'java-git help'");
        }
    }
}