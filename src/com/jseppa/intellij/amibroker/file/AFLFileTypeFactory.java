package com.jseppa.intellij.amibroker.file;

import com.intellij.openapi.fileTypes.*;
import org.jetbrains.annotations.NotNull;

public class AFLFileTypeFactory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
        fileTypeConsumer.consume(AFLFileType.INSTANCE);
    }
}
