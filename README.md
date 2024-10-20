# MongoDB-Playground
The project can be used as a playground for MongoDB

### Used technologies
- JDK 17
- Spring Boot 3
- Gradle
- Spring Web MVC
- MongoDB
- Docker, Docker-Compose
- Spring Doc
- MapStruct

### Build & Run

To build a project you can use:

```bash
gradle clean build
```

To build with [PiTest](https://github.com/pitest/pitest-junit5-plugin):

```bash
gradle clean build pitest
```

### How-to's

In order to do API calls you can utilize swagger UI, which is accessible through URL: `/swagger-ui/index.html`


#### Starting Mongo sharded cluster
Generate a keyfile:
```shell
openssl rand -base64 756 > local/mongodb/config_three_instances/common/keyfile
chmod 400 keyfile
```

Run the script from the project root directory
```bash
./run_mongo_cluster_3.sh
```
Wait for all the containers to start

SSH into config server 1 and connect to mongo
```shell
mongosh --port 27040
```
Create a configuration replica set
```shell
rs.initiate({
  _id: "configReplSet",
  configsvr: true,
  members: [
    { _id: 0, host: "host.docker.internal:27040" },
    { _id: 1, host: "host.docker.internal:27041" },
    { _id: 2, host: "host.docker.internal:27042" }
  ]
})
```

SSH into shard 1 replica 1 and connect to mongo
```shell
mongosh --port 27017
```
Create a shard1 replica set
```shell
rs.initiate({
  _id: "shard1",
  members: [
    { _id: 0, host: "host.docker.internal:27017" },
    { _id: 1, host: "host.docker.internal:27027" },
    { _id: 2, host: "host.docker.internal:27037" }
  ]
})
```

SSH into shard 2 replica 1 and connect to mongo
```shell
mongosh --port 27018
```
Create a shard2 replica set
```shell
rs.initiate({
  _id: "shard2",
  members: [
    { _id: 0, host: "host.docker.internal:27018" },
    { _id: 1, host: "host.docker.internal:27028" },
    { _id: 2, host: "host.docker.internal:27038" }
  ]
})
```

SSH into mongos router
```shell
mongosh --port 27050
```
Register shards in router:
```shell
sh.addShard("shard1/host.docker.internal:27017,host.docker.internal:27027,host.docker.internal:27037")
sh.addShard("shard2/host.docker.internal:27018,host.docker.internal:27028,host.docker.internal:27038")
```
Create a user which app will use to connect to Mongo cluster
```shell
use admin

db.createUser(
    {
        user: "root",
        pwd: "root",
        roles: [
            {
                role: "root",
                db: "admin"
            }
        ]
    }
);

db.auth('root','root')

db.createUser(
    {
        user: "app_name",
        pwd: "password",
        roles: [
            {
                role: "readWrite",
                db: "local_db"
            }
        ]
    }
);
```

Shard the required collections by connecting to `mongos` instance
```shell
mongosh --port 27050
```
```shell
sh.shardCollection("local_db.categories", { _id: 1 })
```

If you want to additionally connect to instances behind the router (e.g. for debugging purposes), you will need to create a user for it:
```shell
mongosh --port 27017
```
```shell
use admin
db.createUser({"user":"root","pwd":"root","roles":[{"role":"root","db":"admin"}]})
```
```shell
mongosh --port 27018
```
```shell
use admin
db.createUser({"user":"root","pwd":"root","roles":[{"role":"root","db":"admin"}]})
```