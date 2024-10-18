# Keyfile

In order to be able to use the keyfile

Generate the key
```bash
openssl rand -base64 756 > keyfile
```

Provide the required permission to use the file
```bash
chmod 400 keyfile
```

# Configuration

This config allows connecting from within the container and from the docker network
```yaml
net:
   bindIp: localhost,mongodb-1,mongodb-2,mongodb-3
```