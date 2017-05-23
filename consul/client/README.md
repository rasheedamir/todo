
A local consul agent must be running in order for everything to work! App talks to local consul agent which in turn talks to consul server

1. fix the config.json; provide address where consul is running in "start_join"

2. provide bind_addr in case you have multiple private ips; you need to specify one of them; so, you need to following

run `ifconfig` and then e.g. use the ip of `en0`

```
en0: flags=8863<UP,BROADCAST,SMART,RUNNING,SIMPLEX,MULTICAST> mtu 1500
        ether 98:01:a7:90:48:9f 
        inet6 fe80::a4:dd52:52c1:cc80%en0 prefixlen 64 secured scopeid 0x4 
        inet 10.0.114.19 netmask 0xffffff00 broadcast 10.0.114.255
        nd6 options=201<PERFORMNUD,DAD>
        media: autoselect
        status: active
```

3. then call ./start.sh
