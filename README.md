# mqtt-hassio-ubuntu-suspend

-------------------------------------------------------------------------------------
sudo vim /etc/systemd/system/mqtt-hassio-ubuntu-suspend.service

[Unit]
Description=MQTT HASSIO Ubuntu Suspend Service
[Service]
User=trodrigues
# The configuration file application.properties should be here:

#change this to your workspace
WorkingDirectory=/home/trodrigues/dev/repo/mqtt-hassio-ubuntu-suspend/target

#path to executable. 
#executable is a bash script which calls jar file
ExecStart=/home/trodrigues/dev/repo/mqtt-hassio-ubuntu-suspend

SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5

[Install]
WantedBy=multi-user.target

-------------------------------------------------------------------------------------

bash:
#!/bin/sh
sudo /usr/bin/java -jar mqtt-hassio-ubuntu-suspend-0.0.1-SNAPSHOT.jar

sudo chmod u+x mqtt-hassio-ubuntu-suspend.bash

-------------------------------------------------------------------------------------

sudo systemctl daemon-reload
sudo systemctl enable mqtt-hassio-ubuntu-suspend.service
sudo systemctl start mqtt-hassio-ubuntu-suspend
sudo systemctl status mqtt-hassio-ubuntu-suspend
