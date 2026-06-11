def call(String SonarQubeAPI, String Projectname, String ProjectKey){

    withSonarQubeEnv("${SonarQubeAPI}") {

        withCredentials([
            string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')
        ]) {

            sh """
            \$SONAR_HOME/bin/sonar-scanner \
            -Dsonar.projectName=${Projectname} \
            -Dsonar.projectKey=${ProjectKey} \
            -Dsonar.login=\$SONAR_TOKEN
            """
        }
    }
}
