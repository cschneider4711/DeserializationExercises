# DeserializationExercises
This small repo contains some exercises as part of my [IT security trainings](https://Christian-Schneider.net/training.html) covering the sub-topic of Java deserialization vulnerabilities:

All exercises use a deserialization endpoint (could be a remote web-based endpoint or whatever), which for simplicity of this demo is just reading Base64-encoded serialized Java objects from stdin, so you can use it directly from within the IDE during the training.

## Level 1: Direct Exploitation of an Abuse Gadget 
The first level contains a simple directly usable gadget (serializable class with dangerous "magic method") for achieving remote code execution (RCE).

## Level 2: Exploiting InvocationHandlers with Trigger Gadget & Proxy
The second level contains a simpe two-step "gadget chain" where a harmless trigger gadget is used together with a dangerous InvocationHandler to gain remote code execution (RCE).

## Level 3: Gadget Chains in Libraries
TODO: The third level will showcase more real-world and complex gadget chains utilizing common libraries on the classpath of the target. 

## Level 4: Bypassing Blacklists
TODO: The fourth level will showcase a bypass technique of *nested deserialization* to bypass a gadget-blacklisting protection layer.

## Level 5: Bypassing ad-hoc Security Managers
TODO: The fifth level will showcase a bypass technique of *deferred execution* to bypass an ad-hoc SecurityManager protection layer.
