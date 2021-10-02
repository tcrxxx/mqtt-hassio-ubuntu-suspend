# mqtt-hassio-ubuntu-suspend

## Descrição

Este serviço tem como o objetivo executar o comando de suspensão do Ubuntu, apartir do listener de um tópico MQTT.
Ele visa facilitar as automações e integrações principalmente com o homeassistant.

## Dependências 
- Java 8
- Maven
- Servidor de MQTT

## Instalação

### 1) Criar o arquivo /etc/systemd/system/mqtt-hassio-ubuntu-suspend.service:

Comando: sudo vim /etc/systemd/system/mqtt-hassio-ubuntu-suspend.service

Conteúdo:
```shell
[Unit]
Description=MQTT HASSIO Ubuntu Suspend Service

[Service]
User=root
# The configuration file application.properties should be here:

#change this to your workspace
WorkingDirectory=/usr/local/bin/mqtt-hassio-ubuntu-suspend

#path to executable. 
#executable is a bash script which calls jar file
ExecStart=/usr/local/bin/mqtt-hassio-ubuntu-suspend/mqtt-hassio-ubuntu-suspend.bash

SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target
```


### 2) Criar pasta: /usr/local/bin/mqtt-hassio-ubuntu-suspend

Dentro desta pasta: 
- inclua o arquivo: target/mqtt-hassio-ubuntu-suspend-jar-with-dependencies.jar (para gerar este jar, use maven install no projeto)

- inclua o arquivo mqtt-hassio-ubuntu-suspend.bash

Conteúdo:

```shell
#!/bin/sh
/usr/bin/sudo java -jar mqtt-hassio-ubuntu-suspend-jar-with-dependencies.jar
```


- Não esqueça da permissão de execução para o arquivo bash:

```shell
sudo chmod u+x /usr/local/bin/mqtt-hassio-ubuntu-suspend/mqtt-hassio-ubuntu-suspend.bash
```


### 3) Para instalar o serviço

- reload do daemon:
```shell
sudo systemctl daemon-reload
```

- enable do service:
```shell
sudo systemctl enable mqtt-hassio-ubuntu-suspend.service
```

- start do service
```shell
sudo systemctl start mqtt-hassio-ubuntu-suspend
```

- Verifique o status do serviço:

```shell
sudo systemctl status mqtt-hassio-ubuntu-suspend
```

- Acompanhe os logs do serviço:

```shell
sudo journalctl -f -n 1000 -u mqtt-hassio-ubuntu-suspend.service 
```

## Autores

* **DEV e amante por IOT** - *Desenvolvimento por* - [Thalles Rodrigues](https://github.com/tcrxxx)

## Licença

Este projeto está sob a licença: 
- use a vontade para uso pessoal. 
- Ah, ao usar, divulgue para seus amigos.


