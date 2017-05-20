
A local consul agent must be running in order for everything to work! App talks to local consul agent which in turn talks to consul server

1. fix the config.json; provide address where consul is running in "start_join"

2. provide bind_addr in case you have multiple 

3. then call ./start.sh
