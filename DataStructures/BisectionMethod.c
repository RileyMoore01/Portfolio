
/*
  Riley Moore
  cs-1412-502-Lab2
  File Name: Bisection Method
  09-02-2020
  Write a program to find the root of an interval
*/
#include <stdio.h>
#include <math.h>

#define f(x) x*x-3
#define epsilon 0.01

//Finding the root of a funciton
int main(void) {

  double x_l, x_r, x_m;
  double f_l, f_r, f_m;
  int root_founded;
  int count_iteration = 0;

  //Input for interval end points
  printf("Please enter the interval end points\n");
  scanf("%lf%lf", &x_l, &x_r);

  f_l = f(x_l);
  f_r = f(x_r);

  //Step one of finding root
  if (f_l *f_r > 0)
  printf("There is no root in this interval\n");

  //Step two of finding root
  else
  {
    while((x_r - x_l > epsilon) && (!root_founded))
    {
    //Finding the middle point
      x_m = (x_l + x_r)/2;
      f_l=f(x_l);
      f_m = f(x_m);

      //Check if midpoint is root   
      if(f_m == 0)
      {
        printf("%lf is the root\n", x_m);
        root_founded = 1;
      }
      else
      {
        if 
        (f_l *f_m < 0)
        x_r = x_m;

      else
        x_l = x_m; 
      }
count_iteration+=1;    
    }

    if (x_r - x_l < epsilon)
    printf("The final interval has the widde less than epsilon adn the f(%lf) is < %lf, so %lf can be considered as root\n",x_m, epsilon, x_m );
    printf("The number of iteration is %d\nThe interval is [%lf,%lf]\n", count_iteration, x_l, x_r);
  }
  return 0;
}
