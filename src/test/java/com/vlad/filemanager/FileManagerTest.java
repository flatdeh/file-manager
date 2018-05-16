package com.vlad.filemanager;

import org.junit.Assert;
import org.junit.Test;

public class FileManagerTest {

    @Test
    public void calculateFiles() {
        Assert.assertEquals(-1, FileManager.calculateFiles(""));
        Assert.assertEquals(0, FileManager.calculateFiles("empty-folder"));
        Assert.assertEquals(8, FileManager.calculateFiles("folder"));

    }

    @Test
    public void calculateDirs() {
        Assert.assertEquals(-1, FileManager.calculateDirs(""));
        Assert.assertEquals(0, FileManager.calculateDirs("empty-folder"));
        Assert.assertEquals(8, FileManager.calculateDirs("folder"));
    }

}