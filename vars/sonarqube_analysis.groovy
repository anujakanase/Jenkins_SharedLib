def call(String SonarQubeAPI, String Projectname, String ProjectKey){

    echo "Starting Sonar Analysis"

    withSonarQubeEnv("${SonarQubeAPI}") {

        echo "Loaded Sonar Environment"

        withCredentials([
            string(credentialsId: 'sonar-token', variable: 'SONAR_TOKEN')
        ]) {

            echo "Loaded Sonar Token"

            def scannerHome = tool 'sonar-scanner'

            echo "Scanner Home = ${scannerHome}"

            sh """
            ${scannerHome}/bin/sonar-scanner \
            -Dsonar.projectName=${Projectname} \
            -Dsonar.projectKey=${ProjectKey} \
            -Dsonar.token=${SONAR_TOKEN}
            """
        }
    }
}
