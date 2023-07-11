import subprocess
import sys
import easygui
import wiringpi

from gpiozero import LightSensor, Buzzer
from time import sleep
from matplotlib.pyplot import imread
from scipy.linalg import norm
from numpy import sum, average

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
    #file1, file2 = sys.argv[1:1+2]
    file1 = "Images/testCompare1.jpg"
    file2 = ("Images/pic"+str(index)+".jpg")
    
    # read images as 2D arrays (convert to grayscale for simplicity)
    img1 = to_grayscale(imread(file1).astype(float))
    img2 = to_grayscale(imread(file2).astype(float))
    
    # compare
    n_m, n_0 = compare_images(img1, img2)
    print ("Manhattan norm:", n_m, "/ per pixel:", n_m/img1.size)
    print ("Zero norm:", n_0, "/ per pixel:", n_0*1.0/img1.size)   
    if(n_m/img1.size < 25):
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

while True:
    sleep(0.3)
    print(ldr.value)
    if ldr.value > 0.5:
        captureImage(index)
        index = index + 1
