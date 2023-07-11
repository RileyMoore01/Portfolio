#define LASER_PIN 27
#include<stdio.h>
#include<wiringPi.h>
int main(void){
wiringPiSetupGpio();
pinMode(LASER_PIN,OUTPUT);
while(1){
digitalRead(LASER_PIN,HIGH);
if(digitalRead(Laser_PIN)==HIGH)
{
printf("Laser On");
printf("\n");
delay(4000);
}
digitalWrite(LASER_PIN,LOW);
if(digitalRead(LASER_PIN)==LOW)
{
printf("Laser Off");
printf("\n");
dealy(1000);
}
}
}
