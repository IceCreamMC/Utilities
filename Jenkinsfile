pipeline {
  agent any
  environment {
    DISCORD_WEBHOOK = credentials('jenkins-discord-webhook')
  }
  stages {
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }

    stage('Reposilite') {
      steps {
        sh 'mvn deploy'
      }
    }
  }
  tools {
    jdk 'OpenJDK-18'
  }
  post {
    success {
      archiveArtifacts(artifacts: '**/target/*.jar', fingerprint: true)
    }
    always {
      discordSend(description: "**Build:** ${env.BUILD_ID}\n**Status:** ${currentBuild.currentResult}", link: env.BUILD_URL, result: currentBuild.currentResult, title: "Utilities #${env.BUILD_ID}", webhookURL: "$DISCORD_WEBHOOK")
    }
  }
}
