pipeline {
	agent any

    tools {
		maven 'maven-3.9.10' // Name configured in Jenkins Global Tool Configuration
        jdk 'jdk-21'        // Java version
    }

    environment {
		JAVA_HOME = '/usr/bin/java'
	}

    stages {
		stage('Checkout') {
			steps {
				checkout scm
            }
        }

        stage('Build App') {
			steps {
				echo 'Listing current workspace files...'
				sh 'ls -al'
				sh '''echo 'Using Java: $JAVA_HOME' '''
				echo 'Preparing build...'
				sh 'mvn clean compile -Dmaven.compiler.compilerArgs="-proc:none"'
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