# Keyfile

In order to be able to use the keyfile

Generate the key
```bash
openssl rand -base64 756 > <path-to-keyfile>
```

Provide the required permission to use the file
```bash
chmod 400 <path-to-keyfile>
```

# Configuration

This config allows connecting from within the container and from the docker network
```yaml
net:
   bindIp: localhost,mongodb-1,mongodb-2,mongodb-3
```