/**
 * Copyright (C) 2016 mikroskeem (mikroskeem@mikroskeem.eu)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zachsthings.netevents;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Scheduler task to handle autoreconnect
 */
class ReconnectTask implements Runnable {
    private static class ReconnectItem {
        private final Forwarder reconnect;
        private int runCount = 0, countPassed = 0;

        private ReconnectItem(Forwarder reconnect) {
            this.reconnect = reconnect;
        }
    }

    private final AtomicBoolean runAllNext = new AtomicBoolean();
    private final LinkedList<ReconnectItem> taskQueue = new LinkedList<>();
    private final Random rng = new Random();

    @Override
    public void run() {
        final boolean runAll = runAllNext.compareAndSet(true, false);
        new LinkedList<>(taskQueue).forEach(item -> {
            if (item.countPassed++ >= item.runCount || runAll) {
                try {
                    if (!item.reconnect.reconnect()) {
                        item.reconnect.getPlugin().removeForwarder(item.reconnect);
                    }
                    taskQueue.remove(item);
                } catch (IOException e) { // Failed to connect, go again.
                    item.runCount += rng.nextInt(5);
                    item.countPassed = 0;
                }
            }
        });
    }

    public void schedule(Forwarder toReconnect) {
        taskQueue.add(new ReconnectItem(toReconnect));
    }

    public void attemptAllNext() {
        runAllNext.set(true);
    }
}