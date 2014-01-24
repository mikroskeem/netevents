/**
 * Copyright (C) 2014 zml (netevents@zachsthings.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zachsthings.netevents.test;

import com.zachsthings.netevents.NetEventsPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Ping listener.
 */
public class PingListener implements Listener {
	private final NetEventsPlugin plugin;

	public PingListener(NetEventsPlugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
    public void onPingEvent(PingEvent event) {
        plugin.getLogger().info("Received test event from " + event.getHostname());
    }
}