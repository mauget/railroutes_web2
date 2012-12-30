# railroutes_cmd

## Description

This demo Java command-level application loads an embedded *Neo4j* graph database with publicly available railroad locations. A graph
database consists of nodes connected by links. Nodes and links can have name / value properties. The DBMS provides an API to navigate the
resulting graph. A *Lucene* index enables an application to find a node-of-interest. 

In our case, each node is a integer-numbered "station" from a subset of public 1996 census data of the USA railroad network. A link
(track) connects two stations. Each link has a node-to-node distance property. *Neo4j* has a built-in A* shortest path algorithm that
returns an iterator across the shortest path through the graph of "stations". Our Command executable class accepts a "from" and "to" integer
pair (1..133752) from the command line, to emit Google KML for the shortest path between the two stations. It pipes the KML to a local Google Earth
executable if found.

## Workspace

Clone this repository to your file system. We used Eclipse 3.7 Juno. The directory tree is suitable to import into Eclipse as a Maven
project. You may need environmental adjustments if your computer is not a Mac on Mountain Lion. We created a web appilcation server
runtime for Apache Tomcat 7.29 in Eclipse. 

You'll need a Google API key to programatically supply a KML layer to Google Maps. The map API requires that your KML be publically available on the web. 

Test the controller by dropping its URL into Google Maps' search field in a browser. 


+ A route displays as a blue line -- Google Earth zooms to accommodate.

![Train route example](https://github.com/mauget/railroutes_web2/raw/master/RailRouteWeb.png "Fiqure 1. Google Maps rail route")

[Lou.Mauget@keyholesoftware.com](mailto:Lou.Mauget@keyholesoftware.com)