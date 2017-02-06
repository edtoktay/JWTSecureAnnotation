# SpringTools
## @JWTSecure Annotation
### What is JWT?
According to the Wikipedia; "JSON Web Token (JWT) is a JSON-based open standard (RFC 7519) for creating access tokens that assert some number of claims. For example, a server could generate a token that has the claim "logged in as admin" and provide that to a client. The client could then use that token to prove that he/she is logged in as admin. The tokens are signed by the server's key, so the client is able to verify that the token is legitimate. The tokens are designed to be compact, URL-safe and usable especially in web browser single sign-on (SSO) context. JWT claims can be typically used to pass identity of authenticated users between an identity provider and a service provider, or any other type of claims as required by business processes. The tokens can also be authenticated and encrypted.

JWT relies on other JSON-based standards: JWS (JSON Web Signature) RFC 7515 and JWE (JSON Web Encryption) RFC 7516" (Wikipedia, 2017).

### Java and JWT
#### JJWT Library (Java JWT: JSON Web Token for Java and Android)
"JJWT aims to be the easiest to use and understand library for creating and verifying JSON Web Tokens (JWTs) on the JVM. JJWT is a Java implementation based on the JWT, JWS, JWE, JWK and JWA RFC specifications."(Hazlewood, 2016)

### Dependencies
#### org.springframework.boot [1.4.4.RELEASE]
  spring-boot-starter-parent
  
  spring-boot-starter
  
  spring-boot-starter-web
  
  spring-boot-configuration-processor
  
#### io.jsonwebtoken
  jjwt[0.7.0]
  
#### joda-time
  joda-time
  
#### org.apache.commons
  commons-lang3[3.5]
  
## Installation
  
## Quick Start
  
# References:
Les Hazlewood. 2016. Java JWT: JSON Web Token for Java and Android. [ONLINE] Available at: https://github.com/jwtk/jjwt. [Accessed 6 February 2017].

Wikipedia. 2017. JSON Web Token. [ONLINE] Available at: https://en.wikipedia.org/wiki/JSON_Web_Token. [Accessed 6 February 2017].
