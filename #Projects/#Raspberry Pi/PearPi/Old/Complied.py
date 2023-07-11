import RPi.GPIO as GPIO
import time
import wiringpi
import subprocess
import sys
import easygui
import wiringpi

# from scipy.misc import imread
from matplotlib.pyplot import imread
from scipy.linalg import norm
from numpy import sum, average
#--------------------------------
#    Setting up GPIO Pins      --
#--------------------------------
GPIO.setmode(GPIO.BOARD)

#pin for the LED
ledpin = 11
GPIO.setup(ledpin, GPIO.OUT)
#pin for the sensor
pin = 7
#index for referencing the right image
index = 0

#--------------------------------
#       Take Picture           --
#--------------------------------
def captureImage(index):
    file2 = subprocess.call((['raspistill -o pic'+str(index)+'.jpg']),shell=True)
    index = index + 1
    #execfile("captureImage("+index+")")
    Compare(file2)

#--------------------------------
#       Compare Images         --
#--------------------------------
#Read two images, convert to grayscale, compare and print results
def Compare(file2):
    #file1, file2 = sys.argv[1:1+2]
    file1 = "compare1.jpg"
    #file2 = "compare2.jpg"
    # read images as 2D arrays (convert to grayscale for simplicity)
    img1 = to_grayscale(imread(file1).astype(float))
    img2 = to_grayscale(imread(file2).astype(float))
    # compare
    n_m, n_0 = compare_images(img1, img2)
    print ("Manhattan norm:", n_m, "/ per pixel:", n_m/img1.size)
    print ("Zero norm:", n_0, "/ per pixel:", n_0*1.0/img1.size)
    
    if(n_m > 500000000):
        easygui.msgbox("This shirt needs to be checked", title="ERROR")    

def compare_images(img1, img2):
    # normalize to compensate for exposure difference, this may be unnecessary
    # consider disabling it
    img1 = normalize(img1)
    img2 = normalize(img2)
    # calculate the difference and its norms
    diff = img1 - img2  # elementwise for scipy arrays
    m_norm = sum(abs(diff))  # Manhattan norm
    z_norm = norm(diff.ravel(), 0)  # Zero norm
    return (m_norm, z_norm)

def to_grayscale(arr):
    #Convert arr to 2-D
    if len(arr.shape) == 3:
        return average(arr, -1)  # average over the last axis (color channels)
    else:
        return arr

def normalize(arr):
    rng = arr.max()-arr.min()
    amin = arr.min()
    return (arr-amin)*255/rng

#--------------------------------
#       Laser Detection        --
#--------------------------------
def main(pin):
    light = 0
    GPIO.setup(pin, GPIO.OUT)
    GPIO.output(pin, GPIO.LOW)
    time.sleep(0.1)

    GPIO.setup(pin, GPIO.IN)

    while True:
        if(GPIO.input(pin) == GPIO.LOW):
            if(light == 0):
                continue
            print("no laser detected")
            GPIO.output(ledpin, GPIO.LOW)
            light = 0
        else:
            if(light == 1):
                continue
            print("Laser detected")
            captureImage(index)
            GPIO.output(ledpin, GPIO.HIGH)
            light = 1

try:
    while True:
        print(main(pin))
except KeyboardInterrupt:
    pass
finally:
    GPIO.cleanup()
    
if __name__ == "__main__":
    main()
