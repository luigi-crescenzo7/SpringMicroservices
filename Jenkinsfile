pipeline {
	agent any

    environment {
		// Define environment variables
        NODE_VERSION = '18'
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