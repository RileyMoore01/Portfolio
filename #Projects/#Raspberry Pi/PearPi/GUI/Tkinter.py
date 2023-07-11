import subprocess
import sys
import easygui
import wiringpi

from tkinter import *
from time import sleep
from time import sleep
from matplotlib.pyplot import imread
from PIL import Image, ImageChops, ImageOps
from gpiozero import LightSensor, Buzzer
from scipy.linalg import norm
from numpy import sum, average
from picamera import PiCamera

############################################################
##                      Global Variables                  ##
############################################################
THRESHOLD = 0
TIMEDELAY = 0
NEWTIMEDELAY = 0
BUTTON_HEIGHT = 150
BUTTON_WIDTH = 150
INDEX = 0


############################################################
##                      Main                              ##
############################################################

def main():
    global INDEX, THRESHOLD
    INDEX = 0
    thres = THRESHOLD % 10
    print(thres)
    buzzer = Buzzer(17)

    #--------------------------------
    #       Compare Images         --
    #--------------------------------
    img1 = Image.open("Images/ref.jpg")
    img2 = Image.open("Images/pic"+str(INDEX)+".jpg")
    img2 = img2.resize(img1.size)

    #Grayscale
    img1 = img1.convert('L')
    img2 = img2.convert('L')

    #Threshold
    img1 = img1.point(lambda p: 255 if p > thres else 0)
    img2 = img2.point(lambda p: 255 if p > thres else 0)

    #Monochrome
    img1 = img1.convert('1')
    img2 = img2.convert('1')

    diff = ImageChops.difference(img1, img2)

    if diff.getbbox():
        buzzer.on()
        sleep(1)
        buzzer.off()
        diff.show()
        #16x20


############################################################
##                      Window                            ##
############################################################
win=Tk()
win.geometry("800x440")
win.config(bg='white')
win.wm_title("Pear Pi")


############################################################
##                      Functions                         ##
############################################################
def RunProgram():
    print("*** Running program ***")
    main()

def PositionCamera():
    print("*** Turning on the camera ***")
    camera = PiCamera()
    camera.start_preview()
    sleep(10)
    camera.stop_preview()

def TakeReferenceImage():
    print("*** Taking reference Image ***")
    subprocess.call((['raspistill -o /home/pearpi/Desktop/Images/ref.jpg']),shell=True)

def Slider(val):
    global THRESHOLD
    THRESHOLD = val

def TimeDelayAdd():
    global TIMEDELAY
    TIMEDELAY = TIMEDELAY + 1
    timeDelayDisplay.config(text=f'{TIMEDELAY}')

def TimeDelaySub():
    global TIMEDELAY
    TIMEDELAY = TIMEDELAY - 1
    timeDelayDisplay.config(text=f'{TIMEDELAY}')

def clickExitButton(self):
    exit()




############################################################
##                      Image Buttons                     ##
############################################################

# Position button  ---------------------------------------------------------------------
positionButton = PhotoImage(file='/home/pearpi/Desktop/position.gif')
positionButton = positionButton.subsample(3, 3)

cameraBtn_label = Label(image=positionButton)
cameraBtn_label.config(font=('Helvatical bold', 10), bg='white')

button= Button(win, image=positionButton,command= PositionCamera,borderwidth=0, highlightthickness=0
    , height=BUTTON_HEIGHT, width=BUTTON_WIDTH, bg='white')
button.pack(pady=0, side=LEFT, anchor=N)


# Reference Image Button  ---------------------------------------------------------------
refButton = PhotoImage(file='/home/pearpi/Desktop/camera.gif')
refButton = refButton.subsample(2, 2)

cameraBtn_label2 = Label(image=refButton)
cameraBtn_label2.config(font=('Helvatical bold', 5), bg='white')

button2 = Button(win, image=refButton,command= TakeReferenceImage,borderwidth=0, highlightthickness=0
    , height=BUTTON_HEIGHT, width=BUTTON_WIDTH, bg='white')
button2.pack(pady=0, side=LEFT, anchor=N)


# Run program button  -------------------------------------------------------------------
RunButton = PhotoImage(file='/home/pearpi/Desktop/runProgram.gif')
RunButton = RunButton.subsample(2, 2)

cameraBtn_label3 = Label(image=RunButton)
cameraBtn_label2.config(font=('Helvatical bold', 5), bg='white')

button3 = Button(win, image=RunButton,command= RunProgram,borderwidth=0, highlightthickness=0
    , height=BUTTON_HEIGHT, width=BUTTON_WIDTH, bg='white')
button3.pack(pady=0, side=LEFT, anchor=N)


# Time Delay Button(s)  --------------------------------------------------------------------

TimeDelayAddImage = PhotoImage(file='/home/pearpi/Desktop/add.gif')
TimeDelayAddImage = TimeDelayAddImage.subsample(2, 2)

timeDelayLabel = Label(image=TimeDelayAddImage)
timeDelayLabel.config(font=('Helvatical bold', 5), bg='white')

button4 = Button(win, image=TimeDelayAddImage,command= TimeDelayAdd, borderwidth=0, highlightthickness=0,
    height=BUTTON_HEIGHT, width=BUTTON_WIDTH, bg='white')
button4.place(x=530, y=200)


TimeDelaySubImage = PhotoImage(file='/home/pearpi/Desktop/subtract.gif')
TimeDelaySubImage = TimeDelaySubImage.subsample(2, 2)

timeDelayLabel = Label(image=TimeDelaySubImage)
timeDelayLabel.config(font=('Helvatical bold', 5), bg='white')

button5 = Button(win, image=TimeDelaySubImage,command= TimeDelaySub, borderwidth=0, highlightthickness=0
    , height=BUTTON_HEIGHT, width=BUTTON_WIDTH, bg='white')
button5.place(x=420, y=200)



############################################################
##                      Slider                            ##
############################################################
slider = Scale(win, from_=0, to=100, orient=HORIZONTAL, command=Slider
    , sliderlength=50, length=400, bg='white')
slider.place(x=20, y=275)



############################################################
##                      Labels                            ##
############################################################

display = Label(win, text="Detail", font=('Helvetica bold', 20), bg='white')
display.place(x=30, y=200)

thresRange = Label(win, text="(0-100)", font=('Helvetica bold', 10), bg='white')
thresRange.place(x=30, y=250)

timeDelayDisplay = Label(win, text=f"{TIMEDELAY}", font=('Helvetica bold', 40), bg='white')
timeDelayDisplay.place(x=525, y=150)

timeLabel = Label(win, text="Time Delay", font=('Helvetica bold', 15), bg='white')
timeLabel.place(x=490, y=120)

win.mainloop()

