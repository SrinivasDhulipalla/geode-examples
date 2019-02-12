<!--
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->

# Geode JDBC Connector example

This example demonstrates using the JDBC Connector for in-line caching between
Apache Geode and an external relational database, in this case Pivotal Greenplum,
a postgres RDBMS.

Prior to running this example we assume you have Greenplum running and have
preloaded a a table called 'parent' containing data. To accomplish this, please
follow instructions for the GemFire-Greenplum Connector demo found here: 
https://github.com/gemfire/gemfire-greenplum-examples/blob/master/basic

In this example, a locator and two servers will host a partitioned region called
'Parent' that will hold the cached data from the Greenplum 'parent' table.  
The gfsh commands to configure the JDBC Connector will establish a connection
to the Greenplum table, determine the mapping of fields in the Geode domain class
Parent to the 'parent' table columns, and configure the Cache Loader and Writer
to keep the Geode cache and Greenplum table in sync.

Once the connection and in-line caching properties are set up on the Geode cluster,
the example client app will add a new entry "George" into the Parent region, which
will get propogated to the Greenplum table. The client app will then get an old
entry, key=1 "Alice", from the Parent region which will trigger the cache loader to
fetch it from the Greenplum table.
Finally, the client app requests the current list of entries in the Parent region,
which now contains 2: George and Alice.

This example assumes that Java, Geode, and Pivotal Greenplum are installed.

## Demonstration of in-line caching with JDBC Connector

Follow instructions found here:
https://docs.google.com/document/d/1r5dbgn85jwNK8PkupgG7PTGS76Z_eTnWbAcn91sZZIk/edit#


