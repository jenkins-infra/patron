#!/usr/bin/env groovy

node {
    stage "Checkout"
    checkout scm
  
    stage 'Build HTML'
    withEnv(["JAVA_HOME=${tool 'jdk8'}",
                "PATH+GROOVY=${tool 'groovy'}/bin"]) {
        sh 'groovy ./build.groovy'
    }
}
