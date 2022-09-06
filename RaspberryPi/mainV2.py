import subprocess
import sys
import easygui
import wiringpi

from gpiozero import LightSensor, Buzzer
from time import sleep
from matplotlib.pyplot import imread
from scipy.linalg import norm
from numpy import sum, average
from PIL import Image, ImageChops, ImageOps

#index for referencing the right image
index = 0
ldr = LightSensor(4)
buzzer = Buzzer(17)

#--------------------------------
#       Take Picture           --
#--------------------------------
def captureImage(index):
    subprocess.call((['raspistill -o /home/pearpi/Desktop/Images/pic'+str(index)+'.jpg']),shell=True)
    Compare()

#--------------------------------
#       Compare Images         --
#--------------------------------
#Read two images, convert to grayscale, compare and print results
def Compare():
    img1 = Image.open("newCable.jpg")
    img2 = Image.open("newCable2.jpg")
    img2 = img2.resize(img1.size)

    gray1 = ImageOps.grayscale(img1)
    gray2 = ImageOps.grayscale(img2)

    diff = ImageChops.difference(gray1, gray2)

    if diff.getbbox():
        buzzer.on()
        diff.show()
        easygui.msgbox("This shirt needs to be checked", title="ERROR")

#--------------------------------
#       Laser Detection        --
#--------------------------------
while True:
    sleep(0.3)
    print(ldr.value)
    if ldr.value > 0.5:
        captureImage(index)
        index = index + 1
