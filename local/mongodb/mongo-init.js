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
        "firstName": "AAA",
        "lastName": "AAAAA",
        "additionalInfo": {
            "phone": "111111111111"
        }
    });
    db.customers.insert({
        "firstName": "BBB",
        "lastName": "BBBBB",
        "additionalInfo": {
            "phone": "222222222222"
        }
    });
    db.customers.insert({
        "firstName": "CCC",
        "lastName": "CCCCC",
        "additionalInfo": {
            "phone": "333333333333"
        }
    });
    db.customers.insert({
        "firstName": "DDD",
        "lastName": "DDDDD",
        "additionalInfo": {
            "address": "adddddddresssss1",
            "phone": "444444444444"
        }
    });
    db.customers.insert({
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