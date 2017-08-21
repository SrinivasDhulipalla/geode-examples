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

# Geode Lucene Index Example

This example demonstrates the use of a simple Lucene index. Lucene provides
a powerful text search and analysis. 

In this example, two servers host a single partitioned region with entries
that represent employee information. The example indexes the first and last
names of employees.

This example assumes that Java and Geode are installed.

## Set up the Lucene index and region
1. Set directory ```geode-examples/lucene``` to be the
current working directory.
Each step in this example specifies paths relative to that directory.

2. Build the example (with `EmployeeData` class)

        $ ../gradlew build

3. Run a script that starts a locator and two servers, creates 2 Lucene indexes,
one called ```simpleIndex``` and another called ```analyzerIndex```. The script 
then creates the ```example-region``` region.
A Lucene index must be created before creating the region.

        $ gfsh run --file=scripts/start.gfsh

4. Run the example to populate the `example-region` and both `simpleIndex` and `analyzerIndex`.
The data will also be retrieved from the region and printed to the console.

        $ ../gradlew run

## Try `gfsh` commands that interact with the region and do Lucene searches

1. Start `gfsh` command and connect to the cluster:

       $ gfsh
        ...
       gfsh>connect --locators=127.0.0.1[10334]
       gfsh>query --query="select * from /example-region"
        ...

2. Try different Lucene searches for data in example-region

       gfsh> list lucene indexes --with-stats

    Note that each server that holds partitioned data for this region has the 
```simpleIndex``` and the ```analyzerIndex```.  Each Lucene index is stored as a 
co-located region with the partitioned data region.

    // Search for an exact name match with a `lastName` field of `Jive`:
       gfsh>search lucene --name=simpleIndex --region=example-region --queryStrings="Jive" --defaultField=lastName

    // Search for a `lastName` using fuzzy logic: sounds like 'ive':
       gfsh>search lucene --name=simpleIndex --region=example-region --queryStrings="ive~" --defaultField=lastName

    // Do a compound search that considers both first and last name using fuzzy 'sounds like' logic
       gfsh>search lucene --name=simpleIndex --region=example-region --queryStrings="firstName:at~ OR lastName:ive~" --defaultField=lastName

    // Do a compound search for last name and email using analyzerIndex
       gfsh>search lucene --name=analyzerIndex --region=example-region --queryStrings="lastName:hall~ AND email:Kris.Call@example.com" --defaultField=lastName

3. Examine the Lucene index statistics
  
       gfsh>describe lucene index --name=simpleIndex --region=example-region

   Note the statistics show the fields that are indexed and the Lucene analyzer used 
for each field. Additional statistics listed are the number of documents (region entries) 
indexed, the number of entries committed, as well as the number of queries executed for 
each Lucene index.

4. Shut down the cluster and exit `gfsh`, answering `Y` when prompted:

        gfsh>run --file=scripts/stop.gfsh
        gfsh>exit

5. Clean up the generated directories and files so that this example
can be rerun from the step that invokes the `start.gfsh` script.
    
        $ ../gradlew cleanServer

