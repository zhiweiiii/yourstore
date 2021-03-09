package com.wade.yourstore.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wade
 * @since 2021-02-19
 */
@Component
public class FileWatchedService {

    private WatchService watchService;

    @Autowired
    private FileWatchedAdapter listener;


    public FileWatchedService() throws IOException, InterruptedException {
        watchService = FileSystems.getDefault().newWatchService();
        Paths.get("out").register(watchService,
                /// 监听文件创建事件
                StandardWatchEventKinds.ENTRY_CREATE,
                /// 监听文件删除事件
                StandardWatchEventKinds.ENTRY_DELETE,
                /// 监听文件修改事件
                StandardWatchEventKinds.ENTRY_MODIFY);
    }

    @Async
    public void watch() throws InterruptedException, IOException {
        while (true) {
            WatchKey watchKey = watchService.take();
            List<WatchEvent<?>> watchEventList = watchKey.pollEvents();
            for (WatchEvent<?> watchEvent : watchEventList) {
                WatchEvent.Kind kind = watchEvent.kind();

                WatchEvent<Path> curEvent = (WatchEvent<Path>) watchEvent;
                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    listener.onOverflowed(curEvent);
                    continue;
                } else if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    listener.onCreated(curEvent);
                    continue;
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    listener.onModified(curEvent);
                    continue;
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    listener.onDeleted(curEvent);
                    continue;
                }
            }

            /**
             * WatchKey 有两个状态：
             * {@link sun.nio.fs.AbstractWatchKey.State.READY ready} 就绪状态：表示可以监听事件
             * {@link sun.nio.fs.AbstractWatchKey.State.SIGNALLED signalled} 有信息状态：表示已经监听到事件，不可以接续监听事件
             * 每次处理完事件后，必须调用 reset 方法重置 watchKey 的状态为 ready，否则 watchKey 无法继续监听事件
             */
            if (!watchKey.reset()) {
                break;
            }

        }
    }


}
