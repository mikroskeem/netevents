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
package com.zachsthings.netevents.packet;

import com.zachsthings.netevents.Forwarder;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * Represents a packet object. Provides methods to encode and handle. Decoding is handled separately because deserialization is annoying
 */
public interface Packet {
    public byte getOpcode();

    public void handle(Forwarder session) throws IOException;

    public ByteBuffer write() throws IOException;
}