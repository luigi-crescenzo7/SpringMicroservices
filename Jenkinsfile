pipeline {
	agent any

    environment {
		// Define environment variables
        NODE_VERSION = '18'
    }

    tools {
		maven 'Maven-3.9.0' // Name configured in Jenkins Global Tool Configuration
        jdk 'JDK-21'        // Java version
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
				echo 'Preparing build...'
				mvn 'clean compile .'
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