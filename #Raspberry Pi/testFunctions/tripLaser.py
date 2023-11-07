import RPi.GPIO as GPIO
import time
import wiringpi
#--------------------------------
#    Setting up GPIO Pins      --
#--------------------------------
GPIO.setmode(GPIO.BOARD)

#pin for the LED
ledpin = 11
GPIO.setup(ledpin, GPIO.OUT)
#pin for the sensor
pin = 7

#--------------------------------
#       Turn on Laser          --
#--------------------------------
#For gpio pin numbering
wiringpi.wiringPiSetupGpio()
pinMode(LASER_PIN, OUTPUT)
digitalRead(LASER_PIN, HIGH)
print("Laser On")
#--------------------------------
#       Laser Detection        --
#--------------------------------
def laser_detector(pin):
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
            GPIO.output(ledpin, GPIO.HIGH)
            light = 1

try:
    while True:
        print(laser_detector(pin))
except KeyboardInterrupt:
    pass
finally:
    GPIO.cleanup()
