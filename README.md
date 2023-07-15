

[![Build Referenceapp (CI Nightly)](https://github.com/apache-causeway-committers/causeway-nightly-deploys/actions/workflows/build-referenceapp.yml/badge.svg)](https://github.com/apache-causeway-committers/causeway-nightly-deploys/actions/workflows/build-referenceapp.yml)

You can explore the Apache Causeway™ programming model through the reference app, which illustrates many of the framework's annotations and programming conventions.

<img src="_images/screenshot.png" width="800px"/>

The app is available as a Docker image.

* to run the JPA variant:

  ```bash
  docker pull apache/causeway-app-demo-jpa:latest
  docker run -p 8080:8080 apache/causeway-app-demo-jpa:latest
  ```


* to run the JDO variant:

  ```bash
  docker pull apache/causeway-app-demo-jdo:latest
  docker run -p 8080:8080 apache/causeway-app-demo-jdo:latest
  ```

... then navigate to http://localhost:8080
