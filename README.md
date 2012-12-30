# railroutes_cmd

## Description

This demo Java command-level web application refers to an embedded *Neo4j* graph database containing publicly available railroad
locations. A graph database consists of nodes connected by links. Nodes and links can have name / value properties. The DBMS provides an
API to navigate the resulting graph. A *Lucene* index enables an application to find a node-of-interest.

In our case, each node is a integer-numbered "station" from a subset of public 1996 census data of the USA railroad network. A link
(track) connects two stations. Each link has a node-to-node distance property. *Neo4j* has a built-in A* shortest path algorithm that
returns an iterator across the shortest path through the graph of "stations". 

Our mobile / desktop web application uses a "from" and "to" slider to select rail station route endpoint numbers (1..133752) to compute a
shortest path route between the two stations. A server-side controller calls *Neo4j* to obtain an iterator used to emit Google KML for
the shortest path between the two stations. The controller is independently addressable via a URL. A browser-side button click handler reads the
station slider values. Then it uses a Google API to insert a KML layer into a Google Map of the USA. The insertion API takes our
controller URL as a parameter, thus obtaining the KML for the route layer.

## Workspace

Clone this repository to your file system. We used Eclipse 3.7 Juno. The directory tree is suitable to import into Eclipse as a Maven
project. You may need environmental adjustments if your computer is not a Mac on Mountain Lion. We created a web application server
runtime for Apache Tomcat 7.29 in Eclipse. 

To publish a derivative of this application on your own site, you would need your own Google API key to programmatically supply a KML layer to Google Maps. It's located in the *index.html* file, the
only html file. The map API requires that your KML be publicly available on the web. Our development order was:
1. Test the shortest path logic with JUnit
2. Write a controller that used that logic to return a stream of KML. Test the controller by dropping its URL into Google Maps' search field in a browser.
3. Publish a web application containing the controller to a publicly visible site (we first opened an HTTP port on our router for this)
4. Only then did we flesh out the view logic.

Note that if you test locally, your logic must always refer to a publicly available KML source. We eventually deployed our WAR to a public
Heroku application, as we refined the browser-side "logic." Our single HTML page always referred to our Heroku version of the application
developed in steps 1 - 3.

## To Deploy Your Own Site
You'll need to change two points in the index.html. 
1. The Google API key
2. The KML controller URL
Changing these two dependency values to integrate via a Mustache or Handlebars template would be a good TODO for us.

+ A route displays as a blue line -- Google Earth zooms to accommodate.

![Train route example](https://github.com/mauget/railroutes_web2/raw/master/RailRouteWeb.png "Fiqure 1. Google Maps rail route")

[Lou.Mauget@keyholesoftware.com](mailto:Lou.Mauget@keyholesoftware.com)