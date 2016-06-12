cp -v ./target/uberjar/busa-server-0.1.0-SNAPSHOT-standalone.jar ./docker/
docker build -t busa_server:latest ./docker
