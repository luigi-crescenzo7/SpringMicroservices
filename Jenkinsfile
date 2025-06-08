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

        stage('Install Dependencies') {
			steps {
				sh 'ls -al'
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