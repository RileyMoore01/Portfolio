<h1>How to use</h1>
1.) Make sure everything is plugged in and powered <br />
2.) Once you power the raspberry pi, my software will run on OS load<br />
3.) Navigate through the ui to use the applied features <br />

<h1>Notes</h1>
There is only 2 3.3 VDC GPIO pins, which will have to be used by the laser and laser receiver. Then we will need an external fan as it is the easiest replacement.

<br />
<p1>Put a delay on the sensonr so that it is not constantly pining on the Trigger pin </p1>
<p1> WHen a sensor is set to low it is not reading in the data </p1>

<br />

<h1>Camera Setup</h1>
1.) Open terminanl and use command "sudo raspi-config" <br />
2.) Select Advanced options and tunr on legacy camera <br />
3.) Reboot <br /> <br />
-- Commands -- <br />
<pre> raspistill -o ~/Pictures/name
  -vf to flip the camera </pre>

<h1>Laser setup</h1>
Laser: <br />
     Pin 17 (9 down from the first row) <br />
     Ground - Pin 20 (One up and one over) <br />
     
<h1>Other Hardware</h1>
Fan: <br />
  Pin 1 (Red) <br />
  Ground - Pin 14 (white) <br />
  Enable I2C in raspi-config <br />
 
 <h1>Autostart </h1><br />
  <p> Make a desktop command: </p>
  <t /><p><pre>mkdir /home/pearpi/.config/autostart/</pre></p>
  <t /><p><pre>nano pearpi.desktop in that directory</pre></p>
  
  <p><pre>
    [Desktop Entry]<br />
    Name = PearPi<br />
    Type = Application<br />
    Comment = some comment here<br />
    Exec = /usr/bin/python {codePathHere}.py<br />
  </p></pre>
<h1>Touch Drivers</h1> <br />
<pre>sudo apt-get install build-essential git cmake pkg-config libjpeg8-dev libtiff4-dev libjasper-dev libpng12-dev libavcodec-dev libavformat-dev libswscale-dev libv4l-dev libgtk2.0-dev libatlas-base-dev gfortran</pre>

<pre>git clone https://github.com/Itseez/opencv.git && cd opencv &&git checkout 3.0.0</pre>

<pre>sudo apt-get install build-essential cmake pkg-config libjpeg-dev libtiff5-dev libjasper-dev libpng-dev libavcodec-dev libavformat-dev libswscale-dev libv4l-dev libxvidcore-dev libx264-dev libfontconfig1-dev libcairo2-dev libgdk-pixbuf2.0-dev libpango1.0-dev libgtk2.0-dev libgtk-3-dev libatlas-base-dev gfortran libhdf5-dev libhdf5-serial-dev libhdf5-103 python3-pyqt5 python3-dev -y</pre>


<pre>sudo apt install -y build-essential cmake pkg-config libjpeg-dev libtiff5-dev libpng-dev libavcodec-dev libavformat-dev libswscale-dev libv4l-dev libxvidcore-dev libx264-dev libfontconfig1-dev libcairo2-dev libgdk-pixbuf2.0-dev libpango1.0-dev libgtk2.0-dev libgtk-3-dev libatlas-base-dev gfortran libhdf5-dev libhdf5-serial-dev libhdf5-103 libqt5gui5 libqt5webkit5 libqt5test5 python3-pyqt5 python3-dev</pre>


<h1>Required pip install's</h1><br />
<pre>sudo apt install python3-opencv</pre> <br />


<h1>Random Links</h1>
https://stackoverflow.com/questions/44496249/mark-the-difference-between-2-pictures-in-python
  
sensor: https://www.youtube.com/watch?v=LLzRWzszbzQ

https://www.quora.com/How-do-you-connect-a-photoelectric-sensor-to-a-Raspberry-pi

https://community.element14.com/learn/learning-center/stem-academy/b/blog/posts/reading-a-photo-sensor-with-the-raspberry-pi-b

https://stackoverflow.com/questions/52240652/tkinter-ability-to-click-on-other-buttons-while-script-is-running
