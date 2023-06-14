echo "compiling..."
./gradlew bootJar

echo "deploying..."
eb deploy Classicmodels-backend-env

echo "done"