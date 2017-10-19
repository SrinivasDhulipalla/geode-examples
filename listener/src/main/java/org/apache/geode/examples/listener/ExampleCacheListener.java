/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.geode.examples.listener;

import java.util.Queue;

import org.apache.geode.cache.CacheListener;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.RegionEvent;
import org.apache.geode.cache.util.CacheListenerAdapter;

public class ExampleCacheListener extends CacheListenerAdapter<Integer, String> {
  private final Queue<EntryEvent<Integer, String>> events;

  public ExampleCacheListener(Queue<EntryEvent<Integer, String>> events) {
    this.events = events;
  }

  public Queue<EntryEvent<Integer, String>> getEvents() {
    return events;
  }

  @Override
  public void afterCreate(EntryEvent<Integer, String> event) {
    events.add(event);
  }
}