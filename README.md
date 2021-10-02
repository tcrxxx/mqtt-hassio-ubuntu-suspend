# mqtt-hassio-ubuntu-suspend

-------------------------------------------------------------------------------------
sudo vim /etc/systemd/system/mqtt-hassio-ubuntu-suspend.service

[Unit]
Description=MQTT HASSIO Ubuntu Suspend Service
[Service]
User=trodrigues
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

bash:
#!/bin/sh
/usr/bin/java -jar mqtt-hassio-ubuntu-suspend-jar-with-dependencies.jar


sudo chmod u+x mqtt-hassio-ubuntu-suspend.bash

-------------------------------------------------------------------------------------

sudo systemctl daemon-reload
sudo systemctl enable mqtt-hassio-ubuntu-suspend.service
sudo systemctl start mqtt-hassio-ubuntu-suspend
sudo systemctl status mqtt-hassio-ubuntu-suspend
