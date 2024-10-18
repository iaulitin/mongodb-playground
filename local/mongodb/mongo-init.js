/*
 For replica sets, MongoDB expects that the user accounts are created after the replica set has been initiated.
 This is because user authentication in MongoDB is tied to the replica set,
 and users need to be created within the context of the replica set itself.
*/

if (process.env.IS_REPLICA_SET_SETUP) {
    try {
        console.log("Trying to initialize replica set")
        rs.initiate(
            {
                _id: 'rs0',
                members: [
                    {_id: 0, host: 'host.docker.internal:27017', priority: 1},
                    {_id: 1, host: 'host.docker.internal:27027', priority: 0.5},
                    {_id: 2, host: 'host.docker.internal:27037', priority: 0.5}
                ]
            }
        )
        console.log("Successfully initialized replica set")
    } catch (err) {
        console.error("Failed to initialize replica set" + err)
    }
} else {
    console.log("Not a replica set setup, skipping RS initialization")
}

// by default init script is run unauthenticated. So we need to use user "root" which is
// created for the "admin" DB
db = db.getSiblingDB('admin')
db.auth('root', 'root')

// MongoDB creates a database when you first store data in that database
db = db.getSiblingDB('local_db')
db.createCollection("mock");

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

// Data initialization

insertData = () => {
    insertCustomers();
}

insertCustomers = () => {
    // example
    // db.customers.insert({
    //     "firstName": "AAA",
    //     "lastName": "AAAAA",
    //     "additionalInfo": {
    //         "phone": "111111111111"
    //     }
    // });
}


insertData();