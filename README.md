# mqtt-hassio-ubuntu-suspend

Este serviço java tem como o objetivo de executar o comando de suspensão do Ubuntu, apartir do listener de um tópico MQTT.
Visa, facilitar as automações e integrações com o homeassistant.

Importante:
É necessário que haja um servidor MQTT configurado e funcionando.
Para este projeto foi usado 

-------------------------------------------------------------------------------------
Criar arquivo /etc/systemd/system/mqtt-hassio-ubuntu-suspend.service:

Comando: sudo vim /etc/systemd/system/mqtt-hassio-ubuntu-suspend.service

Conteúdo:
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


-------------------------------------------------------------------------------------

Criar pasta: /usr/local/bin/mqtt-hassio-ubuntu-suspend

Dentro desta pasta: 
- inclua o arquivo: target/mqtt-hassio-ubuntu-suspend-jar-with-dependencies.jar (para gerar este jar, use maven install)

- inclua o arquivo mqtt-hassio-ubuntu-suspend.bash
  conteúdo:
#!/bin/sh
/usr/bin/sudo java -jar mqtt-hassio-ubuntu-suspend-jar-with-dependencies.jar

-------------------------------------------------------------------------------------

sudo chmod u+x /usr/local/bin/mqtt-hassio-ubuntu-suspend/mqtt-hassio-ubuntu-suspend.bash

-------------------------------------------------------------------------------------

Para instalar o serviço:

sudo systemctl daemon-reload
sudo systemctl enable mqtt-hassio-ubuntu-suspend.service
sudo systemctl start mqtt-hassio-ubuntu-suspend

Verifique o status do serviço:

sudo systemctl status mqtt-hassio-ubuntu-suspend

Acompanhe os logs do serviço:

sudo journalctl -f -n 1000 -u mqtt-hassio-ubuntu-suspend.service 

