mvn dependency:copy-dependencies
mkdir data 2> /dev/null
cd data
java -cp ../target/dependency/hsqldb-2.5.1.jar org.hsqldb.Server
