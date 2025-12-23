# DOME Trust Framework API

This repository contains the **new Trust Framework API**, which is part of the DOME Marketplace ecosystem. This project is designed as the next generation of the trust framework, providing a modern, more extensible, and robust solution for managing trust registries.
## Table of Contents
 - [Overview](#overview)
 - [Improvements over Existing Trust Framework](#improvements-over-existing-trust-framework)
 - [Status](#status)
 - [Getting Started](#getting-started)
 - [Server Configuration](#server-configuration)
 - [Blockchain Contract](#blockchain-contract)
 - [Testing](#testing)
 - [Trusted Registries in DOME](#trusted-registries-in-dome) 

## Overview

The API in this repository supports all the functionalities needed by the new DOME Trust Registries User Interface (UI) and provides significant improvements and capabilities over our existing solution. 

The UI is developed in a separate repository for modularity and ease of maintenance.
  👉 [Trust Framework UI Repository](https://github.com/DOME-Marketplace/dome-trust-registries-ui)

## Improvements over Existing Trust Framework

The current Trust Framework does not have a user interface and is managed through a GitHub repository, where DOME Operators must edit YAML files directly to update or add entries to the trusted registries. The new Trust Framework introduces a dedicated UI, significantly improving the workflow for DOME Operators by making it much easier and more intuitive to add new trusted entities to the various trust registries required for DOME. This streamlines the process, reduces the potential for manual errors, and increases overall efficiency.


## Status

> **Note:**  
> This new Trust Framework and UI/API are **not yet integrated** with the verifier of DOME.  

## Getting Started

The underlying library integrating OpenAPI to Spring Boot is [springdoc](https://springdoc.org). Springdoc will generate an OpenAPI v3 specification based on the generated Controller and Model classes.

### Running the API

Start your server as a simple Java application:

```bash
mvn spring-boot:run
```

You can view the API documentation in Swagger UI by pointing to:

```
http://localhost:YOUR_PORT/swagger-ui.html
```

The OpenAPI specification is available to download using the following URL:

```
http://localhost:YOUR_PORT/v3/api-docs
```

You can also navigate to the `/docs` folder where the documentation is already available in raw format.

Change the default port value in `application.properties`.

## Server Configuration

Depending on how the server will be running (locally or in a Docker container), there are a few changes that have to be done in the following files:

- `src/main/resources/application.properties`
- `src/main/java/com/digitelts/dome/trust/registry/api/RegistryApiController.java` (default value for `API_URL` attribute)

## Blockchain Contract

> 💡 *If you use Windows, WSL will make it much easier*

The server can dump the Trust Participant list data into a Blockchain. For this to be achieved, make sure that the `.env` file and the `src/main/resources/application.yml` file contain the correct Web3 configuration values (`ETH_*` and `eth:`). In addition, the following files are needed:

- `src/main/resources/contract/<contract name>.abi`: It contains the contract's ABI.
- `src/main/java/com/digitelts/dome/trust/registry/contracts/<contract name>.java`: Here is the auto-generated contract code. It should include a file with the same name as the smart-contract itself.

### Compiling the Contract

If the `<contract name>.java` file does not exist, it can be generated with Maven by running the following command:

```bash
mvn web3j:generate-sources
```

## Testing

The directory `src/test/java/com/digitelts/dome/trust/registry/` contains test classes. The following command runs the tests:

```bash
mvn test
```

The command for running individual tests is:

```bash
mvn test -Dtest=<classname>
```

Three test files have been created for testing the API with the *Mockito* framework:

- `api/AccessNodeRegistryApiControllerTest.java` (without DELETE method)
- `api/SchemaRegistryApiControllerTest.java` (with DELETE method)
- `api/ParticipantsRegistryApiControllerTest.java` (with DELETE method and Blockchain interaction)

> ⚠️ *Running all the tests will also run the `invoker/OpenApiGeneratorApplicationTests.java` file. This test will fail if the database server is not available.*

### 💡 Useful Links

- [Compiling a Solidity smart-contract](https://docs.web3j.io/latest/getting_started/deploy_interact_smart_contracts/)
- [Maven plugin](https://github.com/LFDT-web3j/web3j-maven-plugin)
- [Mockito](https://site.mockito.org/)

## Trusted Registries in DOME

This repository is self-contained and will fully replace previous registries. The DOME Trust Framework consists of several trusted registries, each serving a distinct purpose within the network:

1. **Trusted Services List**  
   The trusted services list contains all the verified and authorized services within the dome ecosystem. These services have met the required standards for secure and trusted interactions.

2. **Trusted Access Node Operators List**  
   The access node directory contains the public data for organizations within the DOME network.

3. **Trusted Schemas List**  
   Contains references to the credential schemas approved for use within the ecosystem.

4. **Trusted participant list**

5. **Trusted issuers list**

---

### Data Needed for a New Entry

#### **Trusted Services List**

To add a new entry to the trusted services list, specific information must be provided through the UI:

**Field   Description**

| Field                                      | Description                                                                                                                                                                                                                                                                                          |
|---------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| clientId                                   | Should be a did:key or a unique identifier for your client. Using a did:key allows the verifier to obtain your public keys for signature verification without needing a separate JWKS endpoint.                                                               |
| url                                        | The base URL of your service or application.                                                                                                                                                                                                                                                        |
| redirectUris                               | Must include all the URLs where you expect to receive authentication responses. These should be HTTPS URLs to ensure secure communication.                                                                                                                     |
| scopes                                     | Currently, only openid_learcredential is accepted. This scope allows your service to request the necessary credentials.                                                                                                                                      |
| clientAuthenticationMethods                | Must be set to ["client_secret_jwt"], as this is the only supported authentication method.                                                                                                                                                                   |
| authorizationGrantTypes                    | Must be set to ["authorization_code"], as this is the only supported grant type.                                                                                                                                                                             |
| postLogoutRedirectUris                     | Include URLs where users should be redirected after they log out from your service.                                                                                                                                                                           |
| requireAuthorizationConsent                | Set to false because explicit user consent is not required in this flow.                                                                                                                                                                                     |
| requireProofKey                            | Set to false as PKCE is not utilized.                                                                                                                                                                                                                        |
| jwkSetUrl                                  | If you're using a did:key for your clientId, you do not need to provide a jwkSetUrl because the verifier can derive your JWKS directly from the did:key. However, if you're not using a unique identifier, you must provide the jwkSetUrl where your public keys can be fetched. |
| tokenEndpointAuthenticationSigningAlgorithm| Must be set to ES256, as this is the only supported algorithm.                                                                                                                                                                                               |

**example yaml entry**

```yaml
clients:

  - clientId: "did:key:zDnaeypyWjzn54GuUP7PmDXiiggCyiG7ksMF7Unm7kjtEKBez"

    url: "https://dome-marketplace-sbx.org"

    redirectUris: ["https://dome-marketplace-sbx.org/auth/vc/callback"]

    scopes: ["openid_learcredential"]

    clientAuthenticationMethods: ["client_secret_jwt"]

    authorizationGrantTypes: ["authorization_code"]

    postLogoutRedirectUris: ["https://dome-marketplace-sbx.org/"]

    requireAuthorizationConsent: false

    requireProofKey: false

    jwkSetUrl: "https://verifier.dome-marketplace-sbx.org/oidc/did/did:key:zDnaeypyWjzn54GuUP7PmDXiiggCyiG7ksMF7Unm7kjtEKBez"

    tokenEndpointAuthenticationSigningAlgorithm: "ES256"
```

---

#### **Trusted Access Node Operators List**

The Access Node directory contains the public data for organizations within the DOME network. For detailed instructions on how to collect or create your own data, please refer to the [How to Deploy - Previous steps](https://github.com/DOME-Marketplace/access-node#previous-steps) section of the Access Node Guide.

To generate your data, follow the step-by-step instructions at the [DOME Access Node Key Generator](https://dome-marketplace.github.io/dome-crypto-generator/). The generated YAML file will include the following information:

```yaml
organizations:

  - name: "Organization 1"

    dlt_address: "0x43b27fef24cfe8a0b797ed8a36de2884f9963c0c2a0da640e3ec7ad6cd0c493d"

  - name: "Organization 2"

    dlt_address: "0xb794f5ea0ba39494ce83...9613fffba74279579268"
```

NOTE: The dlt_address must be the Ethereum Address of the organization's Access Node.

---

See the OpenAPI documentation (`/swagger-ui.html` endpoint) in this repository for schema details and available API operations.

## License

This project is licensed under the [Apache 2.0 License](./LICENSE).

