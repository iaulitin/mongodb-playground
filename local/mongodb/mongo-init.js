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
    db.customers.insert({
        "uuid": "a3ffc197-ce86-483b-b635-a1a25058defd",
        "firstName": "AAA",
        "lastName": "AAAAA",
        "additionalInfo": {
            "phone": "111111111111"
        }
    });
    db.customers.insert({
        "uuid": "7838bf96-4b9b-443d-a4c2-e75a1f363c82",
        "firstName": "BBB",
        "lastName": "BBBBB",
        "additionalInfo": {
            "phone": "222222222222"
        }
    });
    db.customers.insert({
        "uuid": "aca332a5-8030-4014-a6ba-678d9555073a",
        "firstName": "CCC",
        "lastName": "CCCCC",
        "additionalInfo": {
            "phone": "333333333333"
        }
    });
    db.customers.insert({
        "uuid": "cbca35bd-3684-41dc-b6b5-27cd930f1fb2",
        "firstName": "DDD",
        "lastName": "DDDDD",
        "additionalInfo": {
            "address": "adddddddresssss1",
            "phone": "444444444444"
        }
    });
    db.customers.insert({
        "uuid": "88a1a984-d8bf-44f3-8d2c-b286a93611c0",
        "firstName": "EEE",
        "lastName": "EEEEE",
        "additionalInfo": {
            "email": "aa@aa.aa",
            "address": "adddddddresssss1",
            "phone": "555555555555"
        }
    });
}


insertData();