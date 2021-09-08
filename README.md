# PulsarClient
## Start a Pulsar stanalone cluster

docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  --name pulsar \
  --mount source=pulsardata,target=/pulsar/data \
  --mount source=pulsarconf,target=/pulsar/conf \
  apachepulsar/pulsar:2.7.1 \
  bin/pulsar standalone

## Exec inside the container

docker exec -it :idContainer 

# Consumer

https://github.com/ngocduc120499/PulsarClient/tree/master/src/main/java/Consumer

# Producer

https://github.com/ngocduc120499/PulsarClient/tree/master/src/main/java/Producer
