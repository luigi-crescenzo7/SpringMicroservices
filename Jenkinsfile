pipeline {
	agent any

    tools {
		maven 'maven-3.9.10' // Name configured in Jenkins Global Tool Configuration
        jdk 'jdk-21'        // Java version
    }

    stages {
		stage('Checkout') {
			steps {
				checkout scm
            }
        }

        stage('Build App') {
			steps {
				sh 'ls -al'
				sh 'export JAVA_HOME=/usr/bin/java'
				echo 'Preparing build...'
				sh 'mvn clean compile .'
            }
        }

    }

    post {
		always {
			cleanWs()
        }
        success {
			echo 'Pipeline succeeded!'
        }
        failure {
			echo 'Pipeline failed!'
        }
    }
}