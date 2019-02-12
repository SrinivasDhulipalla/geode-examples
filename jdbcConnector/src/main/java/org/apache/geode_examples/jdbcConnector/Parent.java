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
package org.apache.geode_examples.jdbcConnector;

import java.io.Serializable;
// import java.math.BigInteger;
// import java.math.BigDecimal;

public class Parent {
  private static final long serialVersionUID = 2095541179L;
  private final Long id;
  private String name;
  private Float income;

  public Parent() {
    this.id = 999999L;
    this.name = "test";
    this.income = 0.0f;
  }

  public Parent(Long id, String name, Float income) {
    this.id = id;
    this.name = name;
    this.income = income;
  }

  public Long getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public Float getIncome() {
    return this.income;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setIncome(Float income) {
    this.income = income;
  }

  @Override
  public String toString() {
    return "Parent [Id=" + id + ", name=" + name + ", income=" + income + "]";
  }
}
